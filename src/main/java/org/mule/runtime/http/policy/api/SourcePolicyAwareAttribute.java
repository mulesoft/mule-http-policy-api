/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.http.policy.api;

import org.mule.runtime.policy.api.PolicyAwareAttribute;
import org.mule.runtime.policy.api.SourcePolicyPointcutParametersFactory;

/**
 * Specialization of {@link PolicyAwareAttribute} that represent the attributes of implementations of
 * {@link SourcePolicyPointcutParametersFactory}.
 *
 * @since 1.3
 */
public enum SourcePolicyAwareAttribute implements PolicyAwareAttribute {

  REQUEST_PATH, HEADERS

}
