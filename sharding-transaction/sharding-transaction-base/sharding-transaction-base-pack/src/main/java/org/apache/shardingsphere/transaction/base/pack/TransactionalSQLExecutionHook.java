package org.apache.shardingsphere.transaction.base.pack;

import org.apache.shardingsphere.core.execute.hook.SQLExecutionHook;
import org.apache.shardingsphere.core.route.RouteUnit;
import org.apache.shardingsphere.spi.database.DataSourceMetaData;

import java.util.Map;

/***
 * @Description
 * @Author Fx_demon
 * @Date 2019-9-11 10:27
 * @Version 1.0
 **/
public class TransactionalSQLExecutionHook implements SQLExecutionHook {

    @Override
    public void start(RouteUnit routeUnit, DataSourceMetaData dataSourceMetaData, boolean isTrunkThread, Map<String, Object> shardingExecuteDataMap) {
        if (!isTrunkThread) {
            PackTransactionBroadcaster.broadcastIfNecessary(shardingExecuteDataMap);
        }
    }

    @Override
    public void finishSuccess() {

    }

    @Override
    public void finishFailure(Exception cause) {

    }
}
