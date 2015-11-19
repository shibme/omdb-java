package me.shib.java.omdb.models;

public interface OMDbServiceModel {
	
	public OMDbContent getContentByID(String imdbID);
	public OMDbContent getContentByTitle(String title);
	public SearchResult[] searchContentByTitle(String title);
	public Season getSeasonByID(String imdbID, String seasonNumber);
	public Season getSeasonByTitle(String title, String seasonNumber);
	
}
