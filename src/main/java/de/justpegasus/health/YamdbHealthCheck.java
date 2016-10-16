package de.justpegasus.health;

import com.codahale.metrics.health.HealthCheck;

import de.justpegasus.api.Track;

public class YamdbHealthCheck extends HealthCheck{

  @Override
  protected Result check() throws Exception {
    String artist = "healthcheck_artist";
    Track track = new Track(artist);
    if (!track.getArtist().equals(artist)){
      return Result.unhealthy("Setting track artist not successful!");
    }
    return Result.healthy();
  }

}
