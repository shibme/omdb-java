package me.shib.java.lib.omdb.service;

import me.shib.java.lib.common.utils.JsonLib;
import me.shib.java.lib.omdb.models.*;
import me.shib.java.lib.rest.client.Parameter;
import me.shib.java.lib.rest.client.ServiceAdapter;

import java.util.ArrayList;
import java.util.logging.Logger;

public final class RemoteOMDbServices implements OMDbServiceModel {

    private static final String omdbEndpoint = "http://www.omdbapi.com/";

    private static Logger logger = Logger.getLogger(RemoteOMDbServices.class.getName());

    private JsonLib jsonLib;
    private ServiceAdapter serviceAdapter;

    protected RemoteOMDbServices(JsonLib jsonLib) {
        this.jsonLib = jsonLib;
        this.serviceAdapter = new ServiceAdapter(omdbEndpoint);
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
        ArrayList<Parameter> params = new ArrayList<>();
        params.add(new Parameter("i", imdbID));
        try {
            return getOMDbContentForJson(serviceAdapter.get(null, params).getResponse());
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getContentByID", e);
        }
        return null;
    }

    public OMDbContent getContentByTitle(String title) {
        ArrayList<Parameter> params = new ArrayList<>();
        params.add(new Parameter("t", title));
        try {
            return getOMDbContentForJson(serviceAdapter.get(null, params).getResponse());
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getContentByTitle", e);
        }
        return null;
    }

    @Override
    public SearchResult[] searchContent(String title, Type type, int year, int pageNo) {
        ArrayList<Parameter> params = new ArrayList<>();
        params.add(new Parameter("s", title));
        if (type != null) {
            params.add(new Parameter("type", type.toString()));
        }
        if (year > 0) {
            params.add(new Parameter("y", year + ""));
        }
        if (pageNo > 0) {
            params.add(new Parameter("page", pageNo + ""));
        }
        try {
            IntermediateSearchResponseObject isr = jsonLib.fromUpperCamelCaseJson(serviceAdapter.get(null, params).getResponse(), IntermediateSearchResponseObject.class);
            if (isr != null) {
                return isr.getSearchResponse().getSearchResults();
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "searchContent", e);
        }
        return null;
    }

    public Season getSeasonByID(String imdbID, String seasonNumber) {
        ArrayList<Parameter> params = new ArrayList<>();
        params.add(new Parameter("i", imdbID));
        params.add(new Parameter("season", seasonNumber));
        try {
            IntermediateSeasonObject iso = jsonLib.fromUpperCamelCaseJson(serviceAdapter.get(null, params).getResponse(), IntermediateSeasonObject.class);
            if (iso != null) {
                return iso.getSeasonObject();
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getSeasonByID", e);
        }
        return null;
    }

    public Season getSeasonByTitle(String title, String seasonNumber) {
        ArrayList<Parameter> params = new ArrayList<>();
        params.add(new Parameter("t", title));
        params.add(new Parameter("season", seasonNumber));
        try {
            IntermediateSeasonObject iso = jsonLib.fromUpperCamelCaseJson(serviceAdapter.get(null, params).getResponse(), IntermediateSeasonObject.class);
            if (iso != null) {
                return iso.getSeasonObject();
            }
        } catch (Exception e) {
            logger.throwing(this.getClass().getName(), "getSeasonByTitle", e);
        }
        return null;
    }

}
