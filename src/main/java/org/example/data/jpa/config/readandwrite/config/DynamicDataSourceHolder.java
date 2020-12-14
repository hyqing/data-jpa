package org.example.data.jpa.config.readandwrite.config;

/**
 * Created by hyq on 2020/12/13 14:36.
 */
public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

    public static void setDataSource(String dataSourceName) {
        dataSources.set(dataSourceName);
    }

    public static String getDataSource() {
        return dataSources.get();
    }

    public static void clearDataSource() {
        dataSources.remove();
    }
}
