package me.shib.java.lib.omdb.service;

import me.shib.java.lib.omdb.models.OMDbContent;
import me.shib.java.lib.omdb.models.SearchResult;
import me.shib.java.lib.omdb.models.Season;
import me.shib.java.lib.omdb.models.Type;
import me.shib.java.lib.restiny.RESTinyClient;
import me.shib.java.lib.restiny.requests.GET;
import me.shib.java.lib.restiny.util.JsonUtil;

import java.util.logging.Logger;

final class OMDbService {

    private static final String omdbEndpoint = "http://www.omdbapi.com/";

    private static Logger logger = Logger.getLogger(OMDbService.class.getName());

    private JsonUtil jsonUtil;
    private RESTinyClient resTinyClient;

    OMDbService() {
        this.jsonUtil = new JsonUtil();
        this.resTinyClient = new RESTinyClient(omdbEndpoint);
    }

    private OMDbContent getOMDbContentForJson(String jsonData) {
        try {
            OMDbContent normalParsedObj = jsonUtil.fromJson(jsonData, OMDbContent.class);
            OMDbContent upperCaseParsedObj = jsonUtil.fromUpperCamelCaseJson(jsonData, OMDbContent.class);
            OMDbContent oc = new OMDbContent(normalParsedObj, upperCaseParsedObj);
            if (oc.isResponse()) {
                return oc;
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getOMDbContentForJson", e);
        }
        return null;
    }

    public OMDbContent getContentByID(String imdbID) {
        GET getRequest = new GET(null);
        getRequest.addParameter("i", imdbID);
        try {
            return getOMDbContentForJson(resTinyClient.call(getRequest).getResponse());
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getContentByID", e);
        }
        return null;
    }

    public OMDbContent getContentByTitle(String title) {
        GET getRequest = new GET(null);
        getRequest.addParameter("t", title);
        try {
            return getOMDbContentForJson(resTinyClient.call(getRequest).getResponse());
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getContentByTitle", e);
        }
        return null;
    }

    public SearchResult[] searchContent(String title, Type type, int year, int pageNo) {
        GET getRequest = new GET(null);
        getRequest.addParameter("s", title);
        if (type != null) {
            getRequest.addParameter("type", type.toString());
        }
        if (year > 0) {
            getRequest.addParameter("y", year + "");
        }
        if (pageNo > 0) {
            getRequest.addParameter("page", pageNo + "");
        }
        try {
            IntermediateSearchResponseObject isr = jsonUtil.fromUpperCamelCaseJson(resTinyClient.call(getRequest).getResponse(), IntermediateSearchResponseObject.class);
            if (isr != null) {
                return isr.getSearchResponse().getSearchResults();
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "searchContent", e);
        }
        return null;
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
        GET getRequest = new GET(null);
        getRequest.addParameter("i", imdbID);
        getRequest.addParameter("season", seasonNumber);
        try {
            IntermediateSeasonObject iso = jsonUtil.fromUpperCamelCaseJson(resTinyClient.call(getRequest).getResponse(), IntermediateSeasonObject.class);
            if (iso != null) {
                return iso.getSeasonObject();
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getSeasonByID", e);
        }
        return null;
    }

    public Season getSeasonByTitle(String title, String seasonNumber) {
        GET getRequest = new GET(null);
        getRequest.addParameter("t", title);
        getRequest.addParameter("season", seasonNumber);
        try {
            IntermediateSeasonObject iso = jsonUtil.fromUpperCamelCaseJson(resTinyClient.call(getRequest).getResponse(), IntermediateSeasonObject.class);
            if (iso != null) {
                return iso.getSeasonObject();
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getSeasonByTitle", e);
        }
        return null;
    }

    public Season getSeasonByTitle(String title, int seasonNumber) {
        return getSeasonByTitle(title, seasonNumber + "");
    }

}
