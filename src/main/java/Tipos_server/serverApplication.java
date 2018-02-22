package Tipos_server;


import Tipos_server.resources.Bets;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;


public class serverApplication extends Application<serverConfiguration> {

    public static void main(final String[] args) throws Exception {
        new serverApplication().run(args);
    }

    @Override
    public String getName() {
        return "server";
    }

    @Override
    public void initialize(final Bootstrap<serverConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final serverConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register( new Tipos_server.resources.Login());
        environment.jersey().register( new Bets());

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

// Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

// Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
