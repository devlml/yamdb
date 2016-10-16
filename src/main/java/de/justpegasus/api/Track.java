package de.justpegasus.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {
  private String artist, title, album;

  /**
   * @param artist Artist of this track
   */
  public Track(String artist) {
    this.artist = artist;
    this.title = "not set";
    this.album = "not set";
  }

  public Track() {
    new Track("not set");
  }

  @JsonProperty
  public String getArtist() {
    return artist;
  }

  /**
   * @return the title
   */
  @JsonProperty
  public String getTitle() {
    return title;
  }

  /**
   * @return the album
   */
  @JsonProperty
  public String getAlbum() {
    return album;
  }

  /**
   * @param artist the artist to set
   */
  public void setArtist(String artist) {
    this.artist = artist;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @param album the album to set
   */
  public void setAlbum(String album) {
    this.album = album;
  }

  @Override
  public String toString(){
    return this.artist + ": " + this.album+" - "+this.title;
    
  }
}
