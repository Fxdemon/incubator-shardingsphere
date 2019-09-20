package org.apache.shardingsphere.transaction.base.pack;

import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.apache.servicecomb.pack.omega.context.TransactionContext;
import org.apache.servicecomb.pack.omega.context.annotations.SagaStart;
import org.apache.servicecomb.pack.omega.transaction.annotations.Compensable;


/***
 * @Description
 * @Author Fx_demon
 * @Date 2019-9-20 11:16
 * @Version 1.0
 **/
public class SagaImpl<T> {


    @SagaStart(autoClose = true)
    public T begin(TransactionContext localTxContext, OmegaContext omegaContext){
        return  null;
    }

    @Compensable(compensationMethod = "rollback",timeout = 60000,retries = 2)
    public T commit(TransactionContext localTxContext,OmegaContext omegaContext){
        return  null;
    }

    public T rollback(TransactionContext localTxContext,OmegaContext omegaContext){
        return  null;
    }
}
