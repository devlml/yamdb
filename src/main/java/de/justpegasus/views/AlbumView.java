package de.justpegasus.views;

import de.justpegasus.api.Album;
import io.dropwizard.views.View;

public class AlbumView extends View{
  private final Album album;

  public AlbumView(Album album) {
    super("Album.ftl");
    this.album = album;
  }

  /**
   * @return the album
   */
  public Album getAlbum() {
    return album;
  }

}
