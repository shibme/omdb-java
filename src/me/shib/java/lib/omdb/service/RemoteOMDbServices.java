package me.shib.java.lib.omdb.service;

import me.shib.java.lib.common.utils.JsonLib;
import me.shib.java.lib.omdb.models.*;
import me.shib.java.lib.restiny.RESTinyClient;
import me.shib.java.lib.restiny.requests.GET;

import java.util.logging.Logger;

final class RemoteOMDbServices implements OMDbServiceModel {

    private static final String omdbEndpoint = "http://www.omdbapi.com/";

    private static Logger logger = Logger.getLogger(RemoteOMDbServices.class.getName());

    private JsonLib jsonLib;
    private RESTinyClient resTinyClient;

    RemoteOMDbServices(JsonLib jsonLib) {
        this.jsonLib = jsonLib;
        this.resTinyClient = new RESTinyClient(omdbEndpoint);
    }

    private OMDbContent getOMDbContentForJson(String jsonData) {
        try {
            OMDbContent normalParsedObj = jsonLib.fromJson(jsonData, OMDbContent.class);
            OMDbContent upperCaseParsedObj = jsonLib.fromUpperCamelCaseJson(jsonData, OMDbContent.class);
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

    @Override
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
            IntermediateSearchResponseObject isr = jsonLib.fromUpperCamelCaseJson(resTinyClient.call(getRequest).getResponse(), IntermediateSearchResponseObject.class);
            if (isr != null) {
                return isr.getSearchResponse().getSearchResults();
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "searchContent", e);
        }
        return null;
    }

    public Season getSeasonByID(String imdbID, String seasonNumber) {
        GET getRequest = new GET(null);
        getRequest.addParameter("i", imdbID);
        getRequest.addParameter("season", seasonNumber);
        try {
            IntermediateSeasonObject iso = jsonLib.fromUpperCamelCaseJson(resTinyClient.call(getRequest).getResponse(), IntermediateSeasonObject.class);
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
            IntermediateSeasonObject iso = jsonLib.fromUpperCamelCaseJson(resTinyClient.call(getRequest).getResponse(), IntermediateSeasonObject.class);
            if (iso != null) {
                return iso.getSeasonObject();
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getSeasonByTitle", e);
        }
        return null;
    }

}
