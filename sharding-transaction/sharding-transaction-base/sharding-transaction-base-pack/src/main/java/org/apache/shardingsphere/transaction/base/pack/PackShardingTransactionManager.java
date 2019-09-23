package org.apache.shardingsphere.transaction.base.pack;

import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.apache.servicecomb.pack.omega.context.TransactionContext;
import org.apache.servicecomb.pack.omega.spring.SpringContextUtils;
import org.apache.servicecomb.pack.omega.transaction.TransactionContextHelper;
import org.apache.shardingsphere.spi.database.DatabaseType;
import org.apache.shardingsphere.transaction.core.ResourceDataSource;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.spi.ShardingTransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private SagaImpl sagaImpl;

    @Override
    public void init(DatabaseType databaseType, Collection<ResourceDataSource> resourceDataSources) {
        initPackClient();
        for (ResourceDataSource each : resourceDataSources) {
            dataSourceMap.put(each.getOriginalName(), each.getDataSource() );
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
        return dataSourceMap.get(dataSourceName).getConnection();
    }

    @Override
    public void begin() {
        TransactionContext localTxContext = omegaContext.getTransactionContext();
        PackTransactionHolder.set(localTxContext);
        sagaImpl.begin( null,localTxContext, omegaContext);
        PackTransactionBroadcaster.collectGlobalTxId( omegaContext);
    }

    @Override
    public void commit() {
        try {
            sagaImpl.commit( null,PackTransactionHolder.get(), omegaContext);
        } finally {
            PackTransactionBroadcaster.clear();
            PackTransactionHolder.clear();
        }
    }

    @Override
    public void rollback() {
        try {
            //调运冲正
            sagaImpl.rollback( null,PackTransactionHolder.get(), omegaContext);
        } finally {
            PackTransactionBroadcaster.clear();
            PackTransactionHolder.clear();
        }
    }

    @Override
    public void close() throws Exception {
        dataSourceMap.clear();
        PackTransactionHolder.clear();
    }

    private void initPackClient() {
        omegaContext =  (OmegaContext) SpringContextUtils.getBean(OmegaContext.class);
        sagaImpl = new SagaImpl();
    }
}
