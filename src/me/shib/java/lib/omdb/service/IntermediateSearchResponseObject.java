package me.shib.java.lib.omdb.service;

import me.shib.java.lib.omdb.models.SearchResponse;
import me.shib.java.lib.omdb.models.SearchResult;
import me.shib.java.lib.restiny.util.JsonUtil;

public final class IntermediateSearchResponseObject {

    private Object[] search;
    private SearchResult[] results;

    private SearchResult getCorrectedResult(Object searchObject) {
        JsonUtil jsonUtil = new JsonUtil();
        String jsonData = jsonUtil.toJson(searchObject);
        SearchResult result1 = jsonUtil.fromJson(jsonData, SearchResult.class);
        SearchResult result2 = jsonUtil.fromUpperCamelCaseJson(jsonData, SearchResult.class);
        return new SearchResult(result1, result2);
    }

    private SearchResult[] getSearchResultsFromObjects() {
        if ((search != null) && (search.length > 0)) {
            results = new SearchResult[search.length];
            for (int i = 0; i < results.length; i++) {
                results[i] = getCorrectedResult(search[i]);
            }
        }
        return results;
    }

    private SearchResult[] getResults() {
        if (results == null) {
            return getSearchResultsFromObjects();
        }
        return results;
    }

    protected SearchResponse getSearchResponse() {
        return new SearchResponse(getResults());
    }

}
