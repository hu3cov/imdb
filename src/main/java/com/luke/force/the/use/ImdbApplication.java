package com.luke.force.the.use;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ImdbApplication extends Application<ImdbConfiguration>
{
    public static void main(final String[] args) throws Exception
    {
        new ImdbApplication().run(args);
    }

    @Override
    public String getName()
    {
        return "imdb";
    }

    @Override
    public void initialize(final Bootstrap<ImdbConfiguration> bootstrap)
    {
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(bootstrap
            .getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
    }

    @Override
    public void run(final ImdbConfiguration configuration, final Environment environment)
    {
    }
}
