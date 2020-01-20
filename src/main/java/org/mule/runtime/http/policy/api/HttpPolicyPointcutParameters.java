/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.http.policy.api;

import static org.mule.runtime.api.util.MultiMap.emptyMultiMap;

import org.mule.api.annotation.NoExtend;
import org.mule.runtime.api.component.Component;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.policy.api.PolicyPointcutParameters;

import java.util.Objects;

/**
 * Specific implementation of {@link PolicyPointcutParameters} for HTTP.
 *
 * @since 4.0
 */
@NoExtend
public abstract class HttpPolicyPointcutParameters extends PolicyPointcutParameters {

  private final String path;
  private final String method;
  private final MultiMap<String, String> headers;

  /**
   * Creates a new {@link PolicyPointcutParameters}
   *
   * @param component the component where the policy is being applied.
   * @param path the target path of the message
   * @param method the HTTP method of the message
   */
  public HttpPolicyPointcutParameters(Component component, String path, String method) {
    this(component, null, path, method);
  }

  /**
   * Creates a new {@link PolicyPointcutParameters}
   *
   * @param component the component where the policy is being applied.
   * @param sourceParameters parameters used to match pointcuts of source policies
   * @param path the target path of the message
   * @param method the HTTP method of the message
   */
  public HttpPolicyPointcutParameters(Component component, PolicyPointcutParameters sourceParameters, String path,
                                      String method) {
    this(component, sourceParameters, path, method, emptyMultiMap());
  }

  /**
   * Creates a new {@link PolicyPointcutParameters}
   *
   * @param component the component where the policy is being applied.
   * @param sourceParameters parameters used to match pointcuts of source policies
   * @param path the target path of the message
   * @param method the HTTP method of the message
   * @param headers the HTTP headers of the message
   */
  public HttpPolicyPointcutParameters(Component component, PolicyPointcutParameters sourceParameters, String path,
                                      String method, MultiMap<String, String> headers) {
    super(component, sourceParameters);
    this.path = path;
    this.method = method;
    this.headers = headers;
  }

  /**
   * @return the target path of the http message.
   */
  public String getPath() {
    return path;
  }

  /**
   * @return the HTTP method of the http message.
   */
  public String getMethod() {
    return method;
  }

  /**
   * @return the HTTP headers of the http message.
   */
  public MultiMap<String, String> getHeaders() {
    return headers;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getComponent(), getSourceParameters(), method, path, getHeaders());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    HttpPolicyPointcutParameters other = (HttpPolicyPointcutParameters) obj;
    return Objects.equals(getComponent(), other.getComponent())
        && Objects.equals(getSourceParameters(), other.getSourceParameters())
        && Objects.equals(method, other.method) && Objects.equals(path, other.path)
        && Objects.equals(getHeaders(), other.getHeaders());
  }

}
