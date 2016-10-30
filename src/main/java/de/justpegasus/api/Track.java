package de.justpegasus.api;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {
	private String artist, title, album, comment, composer;
	private String year, numberOnAlbum, numberOfDisc;
	private File source;

	/**
	 * @param artist
	 *            Artist of this track
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
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param album
	 *            the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the composer
	 */
	public String getComposer() {
		return composer;
	}

	/**
	 * @param composer
	 *            the composer to set
	 */
	public void setComposer(String composer) {
		this.composer = composer;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the numberOnAlbum
	 */
	public String getNumberOnAlbum() {
		return numberOnAlbum;
	}

	/**
	 * @param numberOnAlbum
	 *            the numberOnAlbum to set
	 */
	public void setNumberOnAlbum(String numberOnAlbum) {
		this.numberOnAlbum = numberOnAlbum;
	}

	/**
	 * @return the numberOfDisc
	 */
	public String getNumberOfDisc() {
		return numberOfDisc;
	}

	/**
	 * @param numberOfDisc
	 *            the numberOfDisc to set
	 */
	public void setNumberOfDisc(String numberOfDisc) {
		this.numberOfDisc = numberOfDisc;
	}

	@Override
	public String toString() {
		return this.artist + ": " + this.album + " - " + this.title;

	}

	/**
	 * @return the source file
	 */
	@JsonProperty
	public File getSource() {
		return source;
	}

	/**
	 * @param source the source file to set
	 */
	public void setSource(File source) {
		this.source = source;
	}
}
