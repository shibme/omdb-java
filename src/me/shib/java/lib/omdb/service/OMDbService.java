package me.shib.java.lib.omdb.service;

import me.shib.java.lib.common.utils.LocalFileCache;
import me.shib.java.lib.omdb.models.*;
import me.shib.java.lib.restiny.util.JsonUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class OMDbService implements OMDbServiceModel {

    private RemoteOMDbServices remoteServices;
    private LocalCacheOMDbServices localServices;
    private LocalFileCache localFileCache;

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
        JsonUtil jsonUtil = new JsonUtil();
        remoteServices = new RemoteOMDbServices(jsonUtil);
        if (localCacheRenewalIntervalInMinutes > 0) {
            localFileCache = new LocalFileCache(localCacheRenewalIntervalInMinutes, localCacheDirectoryName, true);
            localServices = new LocalCacheOMDbServices(localFileCache, jsonUtil);
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

    public synchronized File getLocalCacheBackupFile() {
        File localCacheBackupFile = null;
        if (localFileCache != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd-hh-mm-ss");
            String date = sdf.format(new Date());
            localCacheBackupFile = localFileCache.getLocalCacheBackup(new File("OMDbLocalCache-" + date + ".zip"));
        }
        return localCacheBackupFile;
    }

}
