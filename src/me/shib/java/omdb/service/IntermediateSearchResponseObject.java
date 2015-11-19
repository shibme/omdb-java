package me.shib.java.omdb.service;

import me.shib.java.omdb.models.SearchResponse;
import me.shib.java.omdb.models.SearchResult;
import me.shib.java.rest.client.lib.JsonLib;

public class IntermediateSearchResponseObject {
	
	private Object[] search;
	private SearchResult[] results;
	
	private SearchResult getCorrectedResult(Object searchObject) {
		JsonLib jsonLib = new JsonLib();
		String jsonData = jsonLib.toJson(searchObject);
		SearchResult result1 = jsonLib.fromJson(jsonData, SearchResult.class);
		SearchResult result2 = jsonLib.fromUpperCamelCaseJson(jsonData, SearchResult.class);
		return new SearchResult(result1, result2);
	}
	
	private SearchResult[] getSearchResultsFromObjects() {
		if((search != null) && (search.length > 0)) {
			results = new SearchResult[search.length];
			for(int i = 0; i < results.length; i++) {
				results[i] = getCorrectedResult(search[i]);
			}
		}
		return results;
	}
	
	private SearchResult[] getResults() {
		if(results == null) {
			return getSearchResultsFromObjects();
		}
		return results;
	}
	
	protected SearchResponse getSearchResponse() {
		return new SearchResponse(getResults());
	}
	
}
