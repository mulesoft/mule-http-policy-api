/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.http.policy.api;

import org.mule.runtime.policy.api.PolicyPointcut;

/**
 * Marker interface for classes that implement {@link PolicyPointcut}.
 * <p>
 * If the class that extends {@link HttpPolicyPointcutParameters} also implements this interface, the full request path will be
 * taken into account when evaluating the pointcut. Otherwise, it will be ignored.
 *
 * @since 4.3
 */
public interface PathAwarePointcut extends PolicyPointcut {

  /**
   * @return {@code true} if the full path must be considered for the pointcut, {@code false} otherwise.
   */
  boolean considersPath();
}
