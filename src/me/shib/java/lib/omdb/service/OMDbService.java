package me.shib.java.lib.omdb.service;

import me.shib.java.lib.common.utils.JsonLib;
import me.shib.java.lib.common.utils.LocalFileCache;
import me.shib.java.lib.omdb.models.*;

public class OMDbService implements OMDbServiceModel {

    private JsonLib jsonLib;
    private RemoteOMDbServices remoteServices;
    private LocalCacheOMDbServices localServices;

    public OMDbService(long localCacheRenewalIntervalInMinutes, String localCacheDirectoryName) {
        initOMDBService(localCacheRenewalIntervalInMinutes, localCacheDirectoryName);
    }

    public OMDbService(long localCacheRenewalIntervalInMinutes) {
        initOMDBService(localCacheRenewalIntervalInMinutes, null);
    }

    public OMDbService() {
        initOMDBService(0, null);
    }

    private void initOMDBService(long localCacheRenewalIntervalInMinutes, String localCacheDirectoryName) {
        jsonLib = new JsonLib();
        remoteServices = new RemoteOMDbServices(jsonLib);
        if (localCacheRenewalIntervalInMinutes > 0) {
            LocalFileCache localCache = new LocalFileCache(localCacheRenewalIntervalInMinutes, localCacheDirectoryName, true);
            localServices = new LocalCacheOMDbServices(localCache, jsonLib);
        } else {
            localServices = null;
        }
    }

    public OMDbContent getContentByID(String imdbID) {
        OMDbContent returnableContent;
        if (localServices != null) {
            returnableContent = localServices.getContentByID(imdbID);
            if (returnableContent != null) {
                return returnableContent;
            }
        }
        returnableContent = remoteServices.getContentByID(imdbID);
        if (localServices != null) {
            localServices.setContentByID(returnableContent);
        }
        return returnableContent;
    }

    public OMDbContent getContentByTitle(String title) {
        OMDbContent returnableContent;
        if (localServices != null) {
            returnableContent = localServices.getContentByTitle(title);
            if (returnableContent != null) {
                return returnableContent;
            }
        }
        returnableContent = remoteServices.getContentByTitle(title);
        if (localServices != null) {
            localServices.setContentByTitle(returnableContent);
        }
        return returnableContent;
    }

    @Override
    public SearchResult[] searchContent(String title, Type type, int year, int pageNo) {
        return remoteServices.searchContent(title, type, year, pageNo);
    }

    public SearchResult[] searchContent(String title, Type type, int year) {
        return searchContent(title, type, year, 0);
    }

    public SearchResult[] searchContent(String title, Type type) {
        return searchContent(title, type, 0);
    }

    public SearchResult[] searchContent(String title) {
        return searchContent(title, null);
    }

    public Season getSeasonByID(String imdbID, int seasonNumber) {
        return getSeasonByID(imdbID, seasonNumber + "");
    }

    public Season getSeasonByID(String imdbID, String seasonNumber) {
        Season returnableSeason;
        if (localServices != null) {
            returnableSeason = localServices.getSeasonByID(imdbID, seasonNumber);
            if (returnableSeason != null) {
                return returnableSeason;
            }
        }
        returnableSeason = remoteServices.getSeasonByID(imdbID, seasonNumber);
        if (localServices != null) {
            localServices.setSeasonByID(imdbID, returnableSeason);
        }
        return returnableSeason;
    }

    public Season getSeasonByTitle(String title, int seasonNumber) {
        return getSeasonByTitle(title, seasonNumber + "");
    }

    public Season getSeasonByTitle(String title, String seasonNumber) {
        Season returnableSeason;
        if (localServices != null) {
            returnableSeason = localServices.getSeasonByTitle(title, seasonNumber);
            if (returnableSeason != null) {
                return returnableSeason;
            }
        }
        returnableSeason = remoteServices.getSeasonByTitle(title, seasonNumber);
        if (localServices != null) {
            localServices.setSeasonByTitle(title, returnableSeason);
        }
        return returnableSeason;
    }

}
