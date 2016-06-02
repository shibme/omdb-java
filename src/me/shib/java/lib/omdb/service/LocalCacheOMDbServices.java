package me.shib.java.lib.omdb.service;

import me.shib.java.lib.common.utils.LocalFileCache;
import me.shib.java.lib.omdb.models.*;
import me.shib.java.lib.restiny.util.JsonUtil;

import java.util.logging.Logger;

final class LocalCacheOMDbServices implements OMDbServiceModel {

    private static final Logger logger = Logger.getLogger(LocalCacheOMDbServices.class.getName());

    private JsonUtil jsonUtil;
    private LocalFileCache localCache;

    LocalCacheOMDbServices(LocalFileCache localCache, JsonUtil jsonUtil) {
        this.localCache = localCache;
        this.jsonUtil = jsonUtil;
    }

    public OMDbContent getContentByID(String imdbID) {
        try {
            String fileData = localCache.getDataforKey("id", imdbID.toLowerCase());
            if (fileData != null) {
                return jsonUtil.fromJson(fileData, OMDbContent.class);
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getContentByID", e);
        }
        return null;
    }

    void setContentByID(OMDbContent content) {
        if (content != null) {
            localCache.putDataForKey("id", content.getImdbID().toLowerCase(), jsonUtil.toJson(content));
        }
    }

    public OMDbContent getContentByTitle(String title) {
        try {
            String fileData = localCache.getDataforKey("title", title.toLowerCase());
            if (fileData != null) {
                return jsonUtil.fromJson(fileData, OMDbContent.class);
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getContentByTitle", e);
        }
        return null;
    }

    @Override
    public SearchResult[] searchContent(String title, Type type, int year, int pageNo) {
        return null;
    }

    void setContentByTitle(OMDbContent content) {
        if (content != null) {
            localCache.putDataForKey("title", content.getTitle().toLowerCase(), jsonUtil.toJson(content));
        }
    }

    public Season getSeasonByID(String imdbID, String seasonNumber) {
        try {
            String fileData = localCache.getDataforKey("id-SeasonNo", imdbID.toLowerCase() + "-" + seasonNumber.toLowerCase());
            if (fileData != null) {
                return jsonUtil.fromJson(fileData, Season.class);
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getSeasonByID", e);
        }
        return null;
    }

    void setSeasonByID(String imdbID, Season season) {
        if ((imdbID != null) && (!imdbID.isEmpty()) && (season != null)) {
            localCache.putDataForKey("id-SeasonNo",
                    imdbID.toLowerCase() + "-" + season.getSeason().toLowerCase(),
                    jsonUtil.toJson(season));
        }
    }

    public Season getSeasonByTitle(String title, String seasonNumber) {
        try {
            String fileData = localCache.getDataforKey("title-SeasonNo", title.toLowerCase() + "-" + seasonNumber.toLowerCase());
            if (fileData != null) {
                return jsonUtil.fromJson(fileData, Season.class);
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getSeasonByTitle", e);
        }
        return null;
    }

    void setSeasonByTitle(String title, Season season) {
        if ((title != null) && (!title.isEmpty()) && (season != null)) {
            localCache.putDataForKey("title-SeasonNo",
                    title.toLowerCase() + "-" + season.getSeason().toLowerCase(),
                    jsonUtil.toJson(season));
        }
    }

}
