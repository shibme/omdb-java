package me.shib.java.omdb.models;

public class MiniEpisode {
	
	private String imdbID;
	private String title;
	private String released;
	private String episode;
	private String imdbRating;
	
	public MiniEpisode(MiniEpisode miniEp1, MiniEpisode miniEp2) {
		if(miniEp1.imdbID != null) {
			this.imdbID = miniEp1.imdbID;
		}
		else {
			this.imdbID = miniEp2.imdbID;
		}

		if(miniEp1.title != null) {
			this.title = miniEp1.title;
		}
		else {
			this.title = miniEp2.title;
		}

		if(miniEp1.released != null) {
			this.released = miniEp1.released;
		}
		else {
			this.released = miniEp2.released;
		}
		
		if(miniEp1.episode != null) {
			this.episode = miniEp1.episode;
		}
		else {
			this.episode = miniEp2.episode;
		}
		
		if(miniEp1.imdbRating != null) {
			this.imdbRating = miniEp1.imdbRating;
		}
		else {
			this.imdbRating = miniEp2.imdbRating;
		}
	}
	
	public String getImdbID() {
		return imdbID;
	}
	public String getTitle() {
		return title;
	}
	public String getReleased() {
		return released;
	}
	public String getEpisode() {
		return episode;
	}
	public String getImdbRating() {
		return imdbRating;
	}
	
	@Override
	public String toString() {
		return "MiniEpisode [imdbID=" + imdbID + ", title=" + title + ", released=" + released + ", episode=" + episode
				+ ", imdbRating=" + imdbRating + "]";
	}
	
}
