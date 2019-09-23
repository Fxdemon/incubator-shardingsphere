package org.apache.shardingsphere.transaction.base.pack;

import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.apache.servicecomb.pack.omega.context.TransactionContext;
import org.apache.servicecomb.pack.omega.context.annotations.SagaStart;
import org.apache.servicecomb.pack.omega.transaction.annotations.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/***
 * @Description
 * @Author Fx_demon
 * @Date 2019-9-20 11:16
 * @Version 1.0
 **/
public class SagaImpl<I,O> {

    private static final Logger logger = LoggerFactory.getLogger(SagaImpl.class);


    @SagaStart
    public O begin(I input,TransactionContext localTxContext, OmegaContext omegaContext){
        logger.info("ShardingSphere Pack Saga begin：" + omegaContext.toString());
        return  null;
    }

    @Compensable(compensationMethod = "rollback",timeout = 60000,retries = 2)
    public O commit(I input,TransactionContext localTxContext,OmegaContext omegaContext){
        logger.info("ShardingSphere Pack Saga commit：" + omegaContext.toString());
        return  null;
    }

    public O rollback(I input,TransactionContext localTxContext,OmegaContext omegaContext){
        logger.info("ShardingSphere Pack Saga rollback：" + omegaContext.toString());
        return  null;
    }

    public O end(I input,TransactionContext localTxContext, OmegaContext omegaContext){
        logger.info("ShardingSphere Pack Saga end：" + omegaContext.toString());
        return  null;
    }

}
