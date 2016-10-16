package de.justpegasus;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class YetAnotherMusicDatabaseConfiguration extends Configuration {

  @NotEmpty
  private String path;
  
  @NotEmpty
  private String defaultArtist = "John Doe";

  /**
   * @return the path
   */
  @JsonProperty
  public String getPath() {
    return path;
  }

  /**
   * @param path the path to set
   */
  @JsonProperty
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * @return the defaultArtist
   */
  @JsonProperty
  public String getDefaultArtist() {
    return defaultArtist;
  }

  /**
   * @param defaultArtist the defaultArtist to set
   */
  @JsonProperty
  public void setDefaultArtist(String defaultArtist) {
    this.defaultArtist = defaultArtist;
  }
  
  
  
}
