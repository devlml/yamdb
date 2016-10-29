package de.justpegasus.resources;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.justpegasus.api.Album;
import de.justpegasus.api.Track;
import de.justpegasus.core.infograbber.Reader;
import de.justpegasus.views.AlbumView;

@Path("/yamdb")
@Produces(MediaType.TEXT_HTML)
public class YamdbResource {
  final String defaultArtist, defaultFolder = "~/Music";

  /**
   * @param artist the artist
   */
  public YamdbResource(String artist) {
    this.defaultArtist = artist;
  }
  
  @Path("/track")
  @GET
  public Track showTrack(@QueryParam("artist") Optional<String> artist) {
      return new Track(artist.orElse(defaultArtist));
  }
  
  @Path("/album")
  @GET
  public AlbumView showAlbum(@QueryParam("folder") Optional<String> folder){
    Album album = new Album(folder.orElse(defaultFolder));
    Reader reader = new Reader(folder.orElse(defaultFolder));
    album.setTracks(reader.listMusicFiles(album.getFolder()));
    album.setArtist(reader.getArtist());
    return new AlbumView(album);
  }
  
}
