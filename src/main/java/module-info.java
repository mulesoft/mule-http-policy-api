/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

/**
 * Mule module HTTP Policy API.
 * 
 * @moduleGraph
 * @since 1.6
 */
module org.mule.runtime.http.policy.api {
  
  requires org.mule.runtime.api;
  requires org.mule.runtime.policy.api;
  requires com.google.common;
  
  exports org.mule.runtime.http.policy.api;

}