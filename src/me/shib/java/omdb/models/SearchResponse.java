package me.shib.java.omdb.models;

public class SearchResponse {
	
	private SearchResult[] search;
	
	public SearchResponse(SearchResult[] search) {
		this.search = search;
	}
	
	public SearchResult[] getSearchResults() {
		return search;
	}
	
}
