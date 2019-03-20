package com.luke.force.the.use;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class ImdbConfiguration extends Configuration
{
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();
    
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory()
    {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory)
    {
        this.database = dataSourceFactory;
    }
}
