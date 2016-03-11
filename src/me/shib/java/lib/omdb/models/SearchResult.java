package me.shib.java.lib.omdb.models;

public class SearchResult {

    protected String imdbID;
    protected String title;
    protected String year;
    protected Type type;
    protected String poster;

    public SearchResult(SearchResult result1, SearchResult result2) {
        if (result1.imdbID != null) {
            this.imdbID = result1.imdbID;
        } else {
            this.imdbID = result2.imdbID;
        }

        if (result1.title != null) {
            this.title = result1.title;
        } else {
            this.title = result2.title;
        }

        if (result1.year != null) {
            this.year = result1.year;
        } else {
            this.year = result2.year;
        }

        if (result1.poster != null) {
            this.poster = result1.poster;
        } else {
            this.poster = result2.poster;
        }
        if ((this.poster != null) && (this.poster.equalsIgnoreCase("N/A"))) {
            this.poster = null;
        }

        if (result1.type != null) {
            this.type = result1.type;
        } else {
            this.type = result2.type;
        }
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public Type getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }

    @Override
    public String toString() {
        return "SearchResult [imdbID=" + imdbID + ", title=" + title + ", year=" + year + ", type=" + type + ", poster="
                + poster + "]";
    }

}
