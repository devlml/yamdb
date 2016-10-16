package de.justpegasus.core.infograbber;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.spi.FileTypeDetector;
import java.util.ArrayList;

import javax.activation.MimeType;

import org.eclipse.jetty.http.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import de.justpegasus.api.Track;

public class Reader {
  final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());
  private String rootPath;

  public Reader(String fileName){
    this.rootPath = fileName;
//    File file = new File(fileName);
//    String fileType = "unknown";
//    try {
//      fileType = Files.probeContentType(file.toPath());
//    } catch (IOException e1) {
//      // TODO Auto-generated catch block
//      e1.printStackTrace();
//    }
//    System.out.println("Filetype: "+fileType);
//    try {
//      Mp3File mp3File = new Mp3File(fileName);
//      System.out.println("Length of this mp3 is: " + mp3File.getLengthInSeconds() + " seconds");
//      System.out.println("Bitrate: " + mp3File.getBitrate() + " kbps " + (mp3File.isVbr() ? "(VBR)" : "(CBR)"));
//      System.out.println("Sample rate: " + mp3File.getSampleRate() + " Hz");
//      System.out.println("Has ID3v1 tag?: " + (mp3File.hasId3v1Tag() ? "YES" : "NO"));
//      System.out.println("Has ID3v2 tag?: " + (mp3File.hasId3v2Tag() ? "YES" : "NO"));
//      System.out.println("Has custom tag?: " + (mp3File.hasCustomTag() ? "YES" : "NO"));
//      
//      if (mp3File.hasId3v1Tag()){
//        System.out.println(mp3File.getId3v1Tag().getAlbum());
//        System.out.println(mp3File.isOriginal());
//      }
//    } catch (UnsupportedTagException | InvalidDataException | IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
  }
  
  public ArrayList<Track> listMusicFiles(){
    ArrayList<Track> tracks = new ArrayList<Track>();
    File rootPathDir = new File(rootPath);
    if (Files.isDirectory(rootPathDir.toPath())){
        for (File trackFile : rootPathDir.listFiles()){
          try {
            Mp3File musicFile = new Mp3File(trackFile);
            Track newTrack = new Track();
            newTrack.setArtist(musicFile.getId3v1Tag().getArtist());
            newTrack.setAlbum(musicFile.getId3v1Tag().getAlbum());
            newTrack.setTitle(musicFile.getId3v1Tag().getTitle());
            tracks.add(newTrack);
          } catch (UnsupportedTagException | InvalidDataException | IOException e) {
            logger.error("non mp3 file skipped: "+trackFile.getAbsolutePath());
          }
        }
    }
    return tracks;
  }
  
  public String getArtist(){
    String variousArtists = "Various Artists";
    String curArtist = null;
    boolean various = false;
    for (Track track : listMusicFiles()){
      if (curArtist == null){
        curArtist = track.getArtist();
      } else {
        if (!curArtist.equals(track.getArtist())){
          various = true;
          logger.info("found various artists: "+curArtist + " != " + track.getArtist());
          break;
        }
      }
    }
    return various?variousArtists:curArtist;
  }
  
  /**
   * @param rootPath the rootPath to set
   */
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }
  
  public static void main(String[] args) throws IOException {
//    try {
//      Files.list(new File("c:\\Users\\lthrun\\Music\\A Shade Of My Former Self").toPath());
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//    for (File curFile : new File("c:\\Users\\lthrun\\Music\\A Shade Of My Former Self").listFiles()){
//      System.out.println(curFile.getName());
//      System.out.println(Files.probeContentType(curFile.toPath()));
//    }
    
//    new Reader("l:/music/Epica-The_Holographic_Principle-WEB-2016-ENTiTLED/00-epica-the_holographic_principle-web-2016.jpg");
    
    for (Track track : new Reader("c:\\Users\\lthrun\\Music\\A Shade Of My Former Self").listMusicFiles()){
      System.out.println(track.toString());
    }
  }

}
