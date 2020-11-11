/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.http.policy.api;

import org.mule.runtime.api.util.MultiMap;

public class HttpRequestMessage {

  private final MultiMap<String, String> headers;
  private final String scheme;
  private final String domain;
  private final int port;
  private final String rawPath;
  private final String method;

  public HttpRequestMessage(MultiMap<String, String> headers, String scheme, String domain, int port, String rawPath,
                            String method) {
    this.headers = headers;
    this.scheme = scheme;
    this.domain = domain;
    this.port = port;
    this.rawPath = rawPath;
    this.method = method;
  }

  public MultiMap<String, String> getHeaders() {
    return headers;
  }

  public String getMethod() {
    return method;
  }

  public String getScheme() {
    return scheme;
  }

  public String getDomain() {
    return domain;
  }

  public int getPort() {
    return port;
  }

  public String getRawPath() {
    return rawPath;
  }
}
