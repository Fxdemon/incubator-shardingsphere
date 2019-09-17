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

import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.apache.shardingsphere.core.execute.ShardingExecuteDataMap;

import java.util.Map;

/***
 * @Description
 * @Author Fx_demon
 * @Date 2019-9-11 10:28
 * @Version 1.0
 **/
class PackTransactionBroadcaster {


    private static OmegaContext omegaContext;

    static void collectGlobalTxId() {
        if ( omegaContext.inGlobalTransaction()) {
            ShardingExecuteDataMap.getDataMap().put(OmegaContext.GLOBAL_TX_ID_KEY, omegaContext.globalTxId());
        }
    }
    
    static void broadcastIfNecessary(final Map<String, Object> shardingExecuteDataMap) {
        if (shardingExecuteDataMap.containsKey(OmegaContext.GLOBAL_TX_ID_KEY)  ) {
              shardingExecuteDataMap.get(OmegaContext.GLOBAL_TX_ID_KEY);
        }
    }

    static void clear() {
        ShardingExecuteDataMap.getDataMap().remove(OmegaContext.GLOBAL_TX_ID_KEY);
    }
}
