package com.transfar.mail.config.datebase;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/18 19:57
 * @version:1.0.0
 */
public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataBaseContextHolder.getDataBaseType();
    }
}
