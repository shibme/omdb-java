package me.shib.java.omdb.service;

import me.shib.java.omdb.models.OMDbContent;
import me.shib.java.omdb.models.OMDbServiceModel;
import me.shib.java.omdb.models.SearchResult;
import me.shib.java.omdb.models.Season;
import me.shib.java.rest.client.lib.JsonLib;

public class LocalCacheOMDbServices implements OMDbServiceModel {
	
	private JsonLib jsonLib;
	private LocalCacheManager localCache;
	
	protected LocalCacheOMDbServices(LocalCacheManager localCache, JsonLib jsonLib) {
		this.localCache = localCache;
		this.jsonLib = jsonLib;
	}
	
	public OMDbContent getContentByID(String imdbID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void setContentByID(OMDbContent content) {
		// TODO Auto-generated method stub
	}

	public OMDbContent getContentByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void setContentByTitle(OMDbContent content) {
		// TODO Auto-generated method stub
	}

	public SearchResult[] searchContentByTitle(String title) {
		return null;
	}

	public Season getSeasonByID(String imdbID, String seasonNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void setSeasonByID(Season season) {
		// TODO Auto-generated method stub
	}

	public Season getSeasonByTitle(String title, String seasonNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void setSeasonByTitle(Season season) {
		// TODO Auto-generated method stub
	}
	
}
