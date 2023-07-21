/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 */
package org.mule.runtime.http.policy.api;

import static com.google.common.collect.ImmutableSet.copyOf;
import static com.google.common.collect.Sets.union;
import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static java.util.Objects.requireNonNull;
import static org.mule.runtime.api.util.Preconditions.checkArgument;

import org.mule.runtime.policy.api.PolicyAwareAttributes;
import org.mule.runtime.policy.api.SourcePolicyPointcutParametersFactory;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Specialization of {@link PolicyAwareAttributes} that represent the attributes of implementations of
 * {@link SourcePolicyPointcutParametersFactory}.
 *
 * @since 1.3
 */
public class SourcePolicyAwareAttributes implements PolicyAwareAttributes {

  private final Set<String> headers;
  private final Set<Pattern> requestPathPatterns;

  private SourcePolicyAwareAttributes(Set<String> headers, Set<Pattern> requestPathPatterns) {
    requireNonNull(headers);
    requireNonNull(requestPathPatterns);
    this.headers = headers;
    this.requestPathPatterns = requestPathPatterns;
  }

  public static SourcePolicyAwareAttributes noAttributes() {
    return new SourcePolicyAwareAttributes(emptySet(), emptySet());
  }

  @Override
  public boolean requires(Attribute attribute) {
    switch ((SourceAttribute) attribute) {
      case HEADERS:
        return requireHeaders();
      case REQUEST_PATH:
        return requireRequestPath();
      default:
        throw new IllegalArgumentException("Illegal attribute " + attribute);
    }
  }

  @Override
  public PolicyAwareAttributes merge(PolicyAwareAttributes target) {
    SourcePolicyAwareAttributes that = (SourcePolicyAwareAttributes) target;
    Builder builder = new Builder();
    if (requireHeaders() || that.requireHeaders()) {
      builder.headers(union(getHeaders(), that.getHeaders()).toArray(new String[0]));
    }
    if (requireRequestPath() || that.requireRequestPath()) {
      builder.requestPathPatterns(union(getRequestPathPatterns(), that.getRequestPathPatterns()).toArray(new Pattern[0]));
    }
    return builder.build();
  }

  public boolean requireHeaders() {
    return headers.size() > 0;
  }

  public Set<String> getHeaders() {
    return headers;
  }

  public boolean requireRequestPath() {
    return requestPathPatterns.size() > 0;
  }

  public Set<Pattern> getRequestPathPatterns() {
    return requestPathPatterns;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    SourcePolicyAwareAttributes that = (SourcePolicyAwareAttributes) o;
    return Objects.equals(headers, that.headers) &&
        Objects.equals(requestPathPatterns, that.requestPathPatterns);
  }

  @Override
  public int hashCode() {
    return Objects.hash(headers, requestPathPatterns);
  }

  @Override
  public String toString() {
    return "SourcePolicyAwareAttributes{" +
        "headers=" + headers +
        ", requestPathPatterns=" + requestPathPatterns +
        '}';
  }

  public static class Builder {

    private Set<String> headers = new HashSet<>();
    private Set<Pattern> requestPathPatterns = new HashSet<>();

    public Builder headers(String... headers) {
      checkArgument(headers != null && headers.length > 0, "At least one header is required");
      this.headers = copyOf(asList(headers));
      return this;
    }

    public Builder requestPathPatterns(Pattern... requestPathPatterns) {
      checkArgument(requestPathPatterns != null && requestPathPatterns.length > 0, "At least one pattern is required");
      this.requestPathPatterns = copyOf(asList(requestPathPatterns));
      return this;
    }

    public SourcePolicyAwareAttributes build() {
      return new SourcePolicyAwareAttributes(headers, requestPathPatterns);
    }
  }

  /**
   * Specialization of {@link PolicyAwareAttributes.Attribute} that represent the attributes of implementations of
   * {@link SourcePolicyPointcutParametersFactory}.
   *
   */
  public enum SourceAttribute implements Attribute {

    REQUEST_PATH, HEADERS

  }
}
