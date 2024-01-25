package com.ld.poetize.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 *
 * @Author zuosy
 * @Date 2024/1/24 19:39
 **/
@Configuration
public class LiquibaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.liquibase")
    public LiquibaseProperties getLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        LiquibaseProperties liquibaseProperties = getLiquibaseProperties();
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(liquibaseProperties.getChangeLog());
        liquibase.setDataSource(dataSource);
        liquibase.setShouldRun(true);
        return liquibase;
    }
}