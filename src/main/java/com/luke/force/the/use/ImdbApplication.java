package com.luke.force.the.use;

import com.luke.force.the.use.api.Actor;
import com.luke.force.the.use.api.Genre;
import com.luke.force.the.use.api.Movie;
import com.luke.force.the.use.db.ActorRepository;
import com.luke.force.the.use.db.GenreRepository;
import com.luke.force.the.use.db.MovieRepository;
import com.luke.force.the.use.resources.ActorResource;
import com.luke.force.the.use.resources.GenreResource;
import com.luke.force.the.use.resources.MovieResource;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class ImdbApplication extends Application<ImdbConfiguration>
{
    public static void main(final String[] args) throws Exception
    {
        new ImdbApplication().run(args);
    }
    
    private final HibernateBundle<ImdbConfiguration> hibernateBundle = new HibernateBundle<ImdbConfiguration>(Movie.class, Genre.class, Actor.class)
    {
        @Override
        public DataSourceFactory getDataSourceFactory(ImdbConfiguration configuration)
        {
            return configuration.getDataSourceFactory();
        }
    };

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
        
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new SwaggerBundle<ImdbConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ImdbConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(final ImdbConfiguration configuration, final Environment environment)
    {
        final MovieRepository movieRepository = new MovieRepository(hibernateBundle.getSessionFactory());
        final GenreRepository genreRepository = new GenreRepository(hibernateBundle.getSessionFactory());
        final ActorRepository actorRepository = new ActorRepository(hibernateBundle.getSessionFactory());
        environment.jersey().register(new MovieResource(movieRepository));
        environment.jersey().register(new GenreResource(genreRepository));
        environment.jersey().register(new ActorResource(actorRepository));
    }
}
