package de.justpegasus.api;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Album {
  private ArrayList<Track> tracks = new ArrayList<Track>();
  private String artist, title = "unknown", folder;

  /**
   * @param folder
   */
  public Album(String folder) {

    this.folder = folder;
    this.title = new File(folder).getName();
    this.artist = "unknown";
  }

  /**
   * @return the tracks
   */
  @JsonProperty
  public ArrayList<Track> getTracks() {
    return tracks;
  }

  /**
   * @param tracks
   *          the tracks to set
   */
  public void setTracks(ArrayList<Track> tracks) {
    this.tracks = tracks;
  }

  /**
   * @return the artist
   */
  @JsonProperty
  public String getArtist() {
    return artist;
  }

  /**
   * @param artist
   *          the artist to set
   */
  public void setArtist(String artist) {
    this.artist = artist;
  }

  /**
   * @return the title
   */
  @JsonProperty
  public String getTitle() {
    return title;
  }

  /**
   * @param title
   *          the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the folder
   */
  @JsonProperty
  public String getFolder() {
    return folder;
  }

}
