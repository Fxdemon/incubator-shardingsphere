package org.apache.shardingsphere.transaction.base.pack;

import org.apache.shardingsphere.spi.database.DatabaseType;
import org.apache.shardingsphere.transaction.core.ResourceDataSource;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.spi.ShardingTransactionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/***
 * @Description
 * @Author Fx_demon
 * @Date 2019-9-11 10:28
 * @Version 1.0
 **/
public class PackShardingTransactionManager implements ShardingTransactionManager {
    @Override
    public void init(DatabaseType databaseType, Collection<ResourceDataSource> resourceDataSources) {

    }

    @Override
    public TransactionType getTransactionType() {
        return null;
    }

    @Override
    public boolean isInTransaction() {
        return false;
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
}
