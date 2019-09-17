/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.transaction.base.pack;


import org.apache.servicecomb.pack.omega.context.TransactionContext;

/***
 * @Description
 * @Author Fx_demon
 * @Date 2019-9-11 10:28
 * @Version 1.0
 **/
final class PackTransactionHolder {
    
    private static final ThreadLocal<TransactionContext> CONTEXT = new ThreadLocal<>();
    
    /**
     * Set pack global transaction.
     *
     * @param transaction global transaction context
     */
    static void set(final TransactionContext transaction) {
        CONTEXT.set(transaction);
    }
    
    /**
     * Get pack global transaction.
     *
     * @return global transaction
     */
    static TransactionContext get() {
        return CONTEXT.get();
    }
    
    /**
     * Clear global transaction.
     */
    static void clear() {
        CONTEXT.remove();
    }
}
