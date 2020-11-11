/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.http.policy.api;

import org.mule.runtime.api.util.MultiMap;

public class HttpResponseMessage {

  private final MultiMap<String, String> headers;
  private final int statusCode;
  //  private final

  public HttpResponseMessage(MultiMap<String, String> headers, int statusCode) {
    this.headers = headers;
    this.statusCode = statusCode;
  }

  public MultiMap<String, String> getHeaders() {
    return headers;
  }

  public int getStatusCode() {
    return statusCode;
  }

}
