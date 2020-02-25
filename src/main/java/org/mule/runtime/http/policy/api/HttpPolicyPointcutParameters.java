/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.http.policy.api;

import static org.mule.runtime.api.util.MultiMap.emptyMultiMap;

import org.mule.runtime.api.component.Component;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.policy.api.PolicyPointcutParameters;

import java.util.Objects;

/**
 * Specific implementation of {@link PolicyPointcutParameters} for HTTP.
 *
 * @since 4.0
 */
public abstract class HttpPolicyPointcutParameters extends PolicyPointcutParameters {

  private final String path;
  private final String method;
  private String maskedRequestPath;
  private MultiMap<String, String> headers;

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
    this(component, sourceParameters, path, method, null, emptyMultiMap());
  }

  /**
   * Creates a new {@link PolicyPointcutParameters}
   *
   * @param component the component where the policy is being applied.
   * @param sourceParameters parameters used to match pointcuts of source policies
   * @param path the target path of the message
   * @param method the HTTP method of the message
   * @param maskedRequestPath the target path of the http message without the base path where the listener is deployed
   * @param headers the HTTP headers of the message
   * @since 1.3
   */
  public HttpPolicyPointcutParameters(Component component, PolicyPointcutParameters sourceParameters, String path,
                                      String method, String maskedRequestPath, MultiMap<String, String> headers) {
    super(component, sourceParameters);
    this.path = path;
    this.method = method;
    this.maskedRequestPath = maskedRequestPath;
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
   * @return the target path of the http message without the base path where the listener is deployed. Note that this is only
   *         calculated when the {@code listenerPath} is open (ends with a wildcard) and will be {@code null} otherwise.
   */
  public String getMaskedRequestPath() {
    return maskedRequestPath;
  }

  /**
   * @return the HTTP headers of the http message.
   */
  public MultiMap<String, String> getHeaders() {
    return headers;
  }

  /**
   * Setter added to overcome that this is an abstract class and the constructor that sets this field was added in a later runtime
   * version, thus not possible to be invoked using reflection from older versions.
   */
  public void setHeaders(MultiMap<String, String> headers) {
    this.headers = headers;
  }

  /**
   * Setter added to overcome that this is an abstract class and the constructor that sets this field was added in a later API
   * version, thus not possible to be invoked using reflection from older versions.
   */
  public void setMaskedRequestPath(String maskedRequestPath) {
    this.maskedRequestPath = maskedRequestPath;
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
        && Objects.equals(method, other.method)
        && Objects.equals(path, other.path)
        && Objects.equals(getHeaders(), other.getHeaders());
  }
}
