/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 */
/**
 * Mule module HTTP Policy API.
 * 
 * @moduleGraph
 * @since 1.5
 */
module org.mule.runtime.http.policy.api {
  
  requires org.mule.runtime.api;
  requires org.mule.runtime.policy.api;
  requires com.google.common;
  
  exports org.mule.runtime.http.policy.api;

}
