package org.example.data.jpa.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by hyq on 2020/12/13 14:20.
 */
@Configuration
@EnableJpaRepositories(value = "org.example.data.jpa.repository",
        entityManagerFactoryRef = "readEntityManagerFactoryBean",
        transactionManagerRef = "readTransactionManager")
public class ReadDataSourceConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("readDruidDataSource")
    private DataSource readDruidDataSource;

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     *
     * @return
     */
    @Bean(name = "readEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(readDruidDataSource)
                .properties(jpaProperties.getProperties())
                .packages("org.example.data.jpa.entity") //设置实体类所在位置
                .persistenceUnit("readPersistenceUnit")//持久化单元名称
                .build();
        //.getObject();
    }

//    /**
//     * @param builder
//     * @return
//     */
//    @Bean(name = "readEntityManagerFactory")
//    public EntityManagerFactory readEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return this.readEntityManagerFactoryBean(builder).getObject();
//    }

    /**
     * 配置事物管理器
     *
     * @return
     */
    @Bean(name = "readTransactionManager")
    public PlatformTransactionManager readTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(readEntityManagerFactoryBean(builder).getObject());
    }
}
