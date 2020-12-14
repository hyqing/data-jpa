package org.example.data.jpa.config.readandwrite.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by hyq on 2020/12/13 14:39.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        //可以做一个简单的负载均衡策略
        String lookupKey = DynamicDataSourceHolder.getDataSource();
        System.out.println("------------lookupKey---------" + lookupKey);

        return lookupKey;
    }
}
