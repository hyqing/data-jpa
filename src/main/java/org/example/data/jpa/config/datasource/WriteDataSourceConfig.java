package org.example.data.jpa.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by hyq on 2020/12/13 14:26.
 */
@Configuration
@EnableJpaRepositories(value = "org.example.data.jpa.repository",
        entityManagerFactoryRef = "writeEntityManagerFactoryBean",
        transactionManagerRef = "writeTransactionManager")
public class WriteDataSourceConfig {
    @Autowired
    JpaProperties jpaProperties;

    @Autowired
    @Qualifier("writeDruidDataSource")
    private DataSource writeDruidDataSource;

    /**
     * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
     *
     * @return
     */
    @Bean(name = "writeEntityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(writeDruidDataSource)
                .properties(jpaProperties.getProperties())
                .packages("org.example.data.jpa.entity") //设置实体类所在位置
                .persistenceUnit("writePersistenceUnit")
                .build();
        //.getObject();//不要在这里直接获取EntityManagerFactory
    }

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     *
     * @param builder
     * @return
     */
//    @Bean(name = "writeEntityManagerFactory")
//    @Primary
//    public EntityManagerFactory writeEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return this.writeEntityManagerFactoryBean(builder).getObject();
//    }

    /**
     * 配置事物管理器
     *
     * @return
     */
    @Bean(name = "writeTransactionManager")
    @Primary
    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(writeEntityManagerFactoryBean(builder).getObject());
    }
}
