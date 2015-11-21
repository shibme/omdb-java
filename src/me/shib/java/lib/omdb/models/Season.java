package me.shib.java.lib.omdb.models;

import java.util.Arrays;

public class Season {
	
	private String title;
	private String season;
	private MiniEpisode[] episodes;
	
	public Season(String title, String season, MiniEpisode[] episodes) {
		this.title = title;
		this.season = season;
		this.episodes = episodes;
	}
	
	public String getTitle() {
		return title;
	}
	public String getSeason() {
		return season;
	}
	public MiniEpisode[] getEpisodes() {
		return episodes;
	}
	
	@Override
	public String toString() {
		return "Season [title=" + title + ", season=" + season + ", episodes=" + Arrays.toString(episodes) + "]";
	}
	
}
