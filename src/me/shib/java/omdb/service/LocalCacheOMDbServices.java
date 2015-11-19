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
		try {
			String fileData = localCache.getDataforKey("id", imdbID.toLowerCase());
			if(fileData != null) {
				return jsonLib.fromJson(fileData, OMDbContent.class);
			}
		} catch (Exception e) {}
		return null;
	}
	
	protected void setContentByID(OMDbContent content) {
		if(content != null) {
			localCache.putDataForKey("id", content.getImdbID().toLowerCase(), jsonLib.toJson(content));
		}
	}

	public OMDbContent getContentByTitle(String title) {
		try {
			String fileData = localCache.getDataforKey("title", title.toLowerCase());
			if(fileData != null) {
				return jsonLib.fromJson(fileData, OMDbContent.class);
			}
		} catch (Exception e) {}
		return null;
	}
	
	protected void setContentByTitle(OMDbContent content) {
		if(content != null) {
			localCache.putDataForKey("title", content.getTitle().toLowerCase(), jsonLib.toJson(content));
		}
	}

	public SearchResult[] searchContentByTitle(String title) {
		return null;
	}

	public Season getSeasonByID(String imdbID, String seasonNumber) {
		try {
			String fileData = localCache.getDataforKey("id-SeasonNo", imdbID.toLowerCase() + "-" + seasonNumber.toLowerCase());
			if(fileData != null) {
				return jsonLib.fromJson(fileData, Season.class);
			}
		} catch (Exception e) {}
		return null;
	}
	
	protected void setSeasonByID(String imdbID, Season season) {
		if((imdbID != null) && (!imdbID.isEmpty()) && (season != null)) {
			localCache.putDataForKey("id-SeasonNo", 
					imdbID.toLowerCase() + "-" + season.getSeason().toLowerCase(),
					jsonLib.toJson(season));
		}
	}

	public Season getSeasonByTitle(String title, String seasonNumber) {
		try {
			String fileData = localCache.getDataforKey("title-SeasonNo", title.toLowerCase() + "-" + seasonNumber.toLowerCase());
			if(fileData != null) {
				return jsonLib.fromJson(fileData, Season.class);
			}
		} catch (Exception e) {}
		return null;
	}
	
	protected void setSeasonByTitle(String title, Season season) {
		if((title != null) && (!title.isEmpty()) && (season != null)) {
			localCache.putDataForKey("title-SeasonNo", 
					title.toLowerCase() + "-" + season.getSeason().toLowerCase(),
					jsonLib.toJson(season));
		}
	}
	
}
