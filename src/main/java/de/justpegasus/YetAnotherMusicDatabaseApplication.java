package de.justpegasus;

import de.justpegasus.health.YamdbHealthCheck;
import de.justpegasus.resources.YamdbResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class YetAnotherMusicDatabaseApplication extends
    Application<YetAnotherMusicDatabaseConfiguration> {

  public static void main(final String[] args) throws Exception {
    new YetAnotherMusicDatabaseApplication().run(args);
  }

  @Override
  public String getName() {
    return "Yet Another Music Database";
  }

  @Override
  public void initialize(
      final Bootstrap<YetAnotherMusicDatabaseConfiguration> bootstrap) {
    bootstrap.addBundle(new ViewBundle<YetAnotherMusicDatabaseConfiguration>());
    bootstrap.addBundle(new AssetsBundle());
  }

  @Override
  public void run(final YetAnotherMusicDatabaseConfiguration configuration,
      final Environment environment) {
    final YamdbResource resource = new YamdbResource(
        configuration.getDefaultArtist());
    final YamdbHealthCheck healthcheck = new YamdbHealthCheck();
    environment.healthChecks().register("track artist", healthcheck);
    environment.jersey().register(resource);
  }

}
