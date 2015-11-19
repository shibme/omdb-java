package me.shib.java.omdb.service;

import me.shib.java.omdb.models.OMDbContent;
import me.shib.java.omdb.models.OMDbServiceModel;
import me.shib.java.omdb.models.SearchResult;
import me.shib.java.omdb.models.Season;
import me.shib.java.rest.client.lib.JsonLib;

public class OMDbService implements OMDbServiceModel {
	
	private JsonLib jsonLib;
	private RemoteOMDbServices remoteServices;
	private LocalCacheOMDbServices localServices;
	
	public OMDbService(long localCacheRenewalIntervalInMinutes, String localCacheDirectoryName) {
		initDefaults();
		if(localCacheRenewalIntervalInMinutes > 0) {
			LocalCacheManager localCache = new LocalCacheManager(localCacheRenewalIntervalInMinutes, localCacheDirectoryName);
			localServices = new LocalCacheOMDbServices(localCache, jsonLib);
		}
		else {
			localServices = null;
		}
	}
	
	public OMDbService() {
		initDefaults();
		localServices = null;
	}
	
	private void initDefaults() {
		jsonLib = new JsonLib();
		remoteServices = new RemoteOMDbServices(jsonLib);
	}

	public OMDbContent getContentByID(String imdbID) {
		OMDbContent returnableContent = null;
		if(localServices != null) {
			returnableContent = localServices.getContentByID(imdbID);
			if(returnableContent != null) {
				return returnableContent;
			}
		}
		returnableContent = remoteServices.getContentByID(imdbID);
		if(localServices != null) {
			localServices.setContentByID(returnableContent);
		}
		return returnableContent;
	}

	public OMDbContent getContentByTitle(String title) {
		OMDbContent returnableContent = null;
		if(localServices != null) {
			returnableContent = localServices.getContentByTitle(title);
			if(returnableContent != null) {
				return returnableContent;
			}
		}
		returnableContent = remoteServices.getContentByTitle(title);
		if(localServices != null) {
			localServices.setContentByTitle(returnableContent);
		}
		return returnableContent;
	}

	public SearchResult[] searchContentByTitle(String title) {
		return remoteServices.searchContentByTitle(title);
	}

	public Season getSeasonByID(String imdbID, String seasonNumber) {
		Season returnableSeason = null;
		if(localServices != null) {
			returnableSeason = localServices.getSeasonByID(imdbID, seasonNumber);
			if(returnableSeason != null) {
				return returnableSeason;
			}
		}
		returnableSeason = remoteServices.getSeasonByID(imdbID, seasonNumber);
		if(localServices != null) {
			localServices.setSeasonByID(returnableSeason);
		}
		return returnableSeason;
	}

	public Season getSeasonByTitle(String title, String seasonNumber) {
		Season returnableSeason = null;
		if(localServices != null) {
			returnableSeason = localServices.getSeasonByTitle(title, seasonNumber);
			if(returnableSeason != null) {
				return returnableSeason;
			}
		}
		returnableSeason = remoteServices.getSeasonByTitle(title, seasonNumber);
		if(localServices != null) {
			localServices.setSeasonByTitle(returnableSeason);
		}
		return returnableSeason;
	}
	
}
