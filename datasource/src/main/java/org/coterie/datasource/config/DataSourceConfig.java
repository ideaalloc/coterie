/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Bill Lv (Lv, Chao).
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.coterie.datasource.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Data source configuration.
 * <p/>
 * Create c3p0 database connection pool.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2014-12-06
 */
@Configuration
@ComponentScan(basePackages = {"com.coterie.datasource"})
@PropertySources({
        @PropertySource("classpath:/jdbc.properties"),
        @PropertySource("classpath:/c3p0.properties")
})
public class DataSourceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(env.getRequiredProperty("jdbc.driverClassName"));
        } catch (PropertyVetoException e) {
            LOGGER.error("Class load exception", e);
            throw new RuntimeException(e);
        }
        dataSource.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUser(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        dataSource.setAcquireIncrement(env.getRequiredProperty("c3p0.acquireIncrement", Integer.class));
        dataSource.setMinPoolSize(env.getRequiredProperty("c3p0.minPoolSize", Integer.class));
        dataSource.setMaxPoolSize(env.getRequiredProperty("c3p0.maxPoolSize", Integer.class));
        dataSource.setMaxIdleTime(env.getRequiredProperty("c3p0.maxIdleTime", Integer.class));
        dataSource.setTestConnectionOnCheckout(env.getRequiredProperty("c3p0.testConnectionOnCheckout", Boolean.class));
        return dataSource;
    }
}
