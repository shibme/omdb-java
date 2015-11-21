package me.shib.java.lib.omdb.service;

import java.util.ArrayList;

import me.shib.java.lib.omdb.models.OMDbContent;
import me.shib.java.lib.omdb.models.OMDbServiceModel;
import me.shib.java.lib.omdb.models.SearchResult;
import me.shib.java.lib.omdb.models.Season;
import me.shib.java.lib.rest.client.JsonLib;
import me.shib.java.lib.rest.client.Parameter;
import me.shib.java.lib.rest.client.ServiceAdapter;

public class RemoteOMDbServices implements OMDbServiceModel {
	
	private JsonLib jsonLib;
	private ServiceAdapter serviceAdapter;
	
	protected RemoteOMDbServices(JsonLib jsonLib) {
		this.jsonLib = jsonLib;
		this.serviceAdapter = new ServiceAdapter(Config.omdbEndpoint);
	}
	
	private OMDbContent getOMDbContentForJson(String jsonData) {
		try {
			OMDbContent normalParsedObj = jsonLib.fromJson(jsonData, OMDbContent.class);
			OMDbContent upperCaseParsedObj = jsonLib.fromUpperCamelCaseJson(jsonData, OMDbContent.class);
			OMDbContent oc = new OMDbContent(normalParsedObj, upperCaseParsedObj);
			if(oc.isResponse()) {
				return oc;
			}
		} catch (Exception e) {}
		return null;
	}
	
	public OMDbContent getContentByID(String imdbID) {
		ArrayList<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("i", imdbID));
		try {
			return getOMDbContentForJson(serviceAdapter.get(null, params).getResponse());
		} catch (Exception e) {}
		return null;
	}
	
	public OMDbContent getContentByTitle(String title) {
		ArrayList<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("t", title));
		try {
			return getOMDbContentForJson(serviceAdapter.get(null, params).getResponse());
		} catch (Exception e) {}
		return null;
	}
	
	public SearchResult[] searchContentByTitle(String title) {
		ArrayList<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("s", title));
		try {
			IntermediateSearchResponseObject isr = jsonLib.fromUpperCamelCaseJson(serviceAdapter.get(null, params).getResponse(), IntermediateSearchResponseObject.class);
			if(isr != null) {
				return isr.getSearchResponse().getSearchResults();
			}
		} catch (Exception e) {}
		return null;
	}
	
	public Season getSeasonByID(String imdbID, String seasonNumber) {
		ArrayList<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("i", imdbID));
		params.add(new Parameter("season", seasonNumber));
		try {
			IntermediateSeasonObject iso = jsonLib.fromUpperCamelCaseJson(serviceAdapter.get(null, params).getResponse(), IntermediateSeasonObject.class);
			if(iso != null) {
				return iso.getSeasonObject();
			}
		} catch (Exception e) {}
		return null;
	}
	
	public Season getSeasonByTitle(String title, String seasonNumber) {
		ArrayList<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("t", title));
		params.add(new Parameter("season", seasonNumber));
		try {
			IntermediateSeasonObject iso = jsonLib.fromUpperCamelCaseJson(serviceAdapter.get(null, params).getResponse(), IntermediateSeasonObject.class);
			if(iso != null) {
				return iso.getSeasonObject();
			}
		} catch (Exception e) {}
		return null;
	}
	
}
