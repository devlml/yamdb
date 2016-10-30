package de.justpegasus.core.infograbber;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jflac.sound.spi.FlacAudioFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import de.justpegasus.api.Track;

public class Reader {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().getClass());
	private String rootPath;

	public Reader(String fileName) {
		this.rootPath = fileName;
	}

	/**
	 * Returns music files in a given directory.
	 * 
	 * @param directory
	 * @return
	 */
	public ArrayList<Track> listMusicFilesForDirectory(String directory) {
		ArrayList<Track> tracks = new ArrayList<Track>();
		File rootPathDir = new File(directory);
		if (Files.isDirectory(rootPathDir.toPath())) {
			for (File trackFile : rootPathDir.listFiles()) {

				AudioFile audioFile;
				Track track = new Track();
				try {
					audioFile = AudioFileIO.read(trackFile);
					Tag tag = audioFile.getTag();
					track.setArtist(tag.getFirst(FieldKey.ARTIST));
					track.setAlbum(tag.getFirst(FieldKey.ALBUM));
					track.setTitle(tag.getFirst(FieldKey.TITLE));
					track.setComment(tag.getFirst(FieldKey.COMMENT));
					track.setYear(tag.getFirst(FieldKey.YEAR));
					track.setNumberOnAlbum(tag.getFirst(FieldKey.TRACK));
					track.setNumberOfDisc(tag.getFirst(FieldKey.DISC_NO));
					track.setComposer((tag.getFirst(FieldKey.COMPOSER)));
					track.setSource(trackFile);
					
					tracks.add(track);
				} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
						| InvalidAudioFrameException e1) {
					System.out.println(e1.getMessage());
				}
			}
		}
		return tracks;
	}

	public Track getMp3Track(File trackFile) {
		try {
			Mp3File musicFile = new Mp3File(trackFile);
			Track newTrack = new Track();
			if (musicFile.hasId3v1Tag()) {
				newTrack.setArtist(musicFile.getId3v1Tag().getArtist());
				newTrack.setAlbum(musicFile.getId3v1Tag().getAlbum());
				newTrack.setTitle(musicFile.getId3v1Tag().getTitle());
			}
			if (musicFile.hasId3v2Tag()) {
				// TODO
			}
			return newTrack;
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			logger.error("non mp3 file skipped: " + trackFile.getAbsolutePath());
		}
		return null;
	}

	public String getArtist() {
		String variousArtists = "Various Artists";
		String curArtist = null;
		boolean various = false;
		for (Track track : listMusicFilesForDirectory(rootPath)) {
			if (curArtist == null) {
				curArtist = track.getArtist();
			} else {
				if (!curArtist.equals(track.getArtist())) {
					various = true;
					logger.info("found various artists: " + curArtist + " != " + track.getArtist());
					break;
				}
			}
		}
		return various ? variousArtists : curArtist;
	}

	/**
	 * @param rootPath
	 *            the rootPath to set
	 */
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public static void main(String[] args) throws IOException {
		Path dir = new File("/home/lutz/Music/flac/Kamelot/The Black Halo").toPath();
		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				AudioFile f;
				try {
					f = AudioFileIO.read(file.toFile());
					Tag tag = f.getTag();
					AudioHeader a = f.getAudioHeader();
					System.out.println(tag.getFirst(FieldKey.ARTIST));
					System.out.println(tag.getFirst(FieldKey.ALBUM));
					System.out.println(tag.getFirst(FieldKey.TITLE));
					System.out.println(tag.getFirst(FieldKey.COMMENT));
					System.out.println(tag.getFirst(FieldKey.YEAR));
					System.out.println(tag.getFirst(FieldKey.TRACK));
					System.out.println(tag.getFirst(FieldKey.DISC_NO));
					System.out.println(tag.getFirst(FieldKey.COMPOSER));
					System.out.println(tag.getFirst(FieldKey.ARTIST_SORT));

					System.out.println("header: " + a.getFormat().toString());
				} catch (CannotReadException | TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return FileVisitResult.CONTINUE;
			}
		});
	}

}
