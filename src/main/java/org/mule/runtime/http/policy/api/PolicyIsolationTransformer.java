/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.http.policy.api;

import org.mule.runtime.api.message.Message;

public interface PolicyIsolationTransformer {

  /**
   * From Message with Attributes to Message with HTTPMessage
   *
   * @param message
   * @return
   */
  Message isolate(Message message);

  /**
   * From Message with HTTPMessage to Message with Attributes
   *
   * @param message
   * @return
   */
  Message desolate(Message message);

}
