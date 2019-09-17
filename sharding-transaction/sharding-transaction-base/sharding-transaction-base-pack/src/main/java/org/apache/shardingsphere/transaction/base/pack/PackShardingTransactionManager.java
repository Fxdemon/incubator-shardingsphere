package org.apache.shardingsphere.transaction.base.pack;

import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.apache.shardingsphere.spi.database.DatabaseType;
import org.apache.shardingsphere.transaction.core.ResourceDataSource;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.spi.ShardingTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/***
 * @Description
 * @Author Fx_demon
 * @Date 2019-9-11 10:28
 * @Version 1.0
 **/
public class PackShardingTransactionManager implements ShardingTransactionManager {

    private final Map<String, DataSource> dataSourceMap = new HashMap<>();

    private  OmegaContext omegaContext;

    @Override
    public void init(DatabaseType databaseType, Collection<ResourceDataSource> resourceDataSources) {
        initPackRPCClient();
        for (ResourceDataSource each : resourceDataSources) {
            dataSourceMap.put(each.getOriginalName(), null );
        }
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.BASE_PACK;
    }

    @Override
    public boolean isInTransaction() {
        return null != omegaContext.globalTxId();
    }

    @Override
    public Connection getConnection(String dataSourceName) throws SQLException {
        return null;
    }

    @Override
    public void begin() {

    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() throws Exception {

    }

    private void initPackRPCClient() {

    }
}
