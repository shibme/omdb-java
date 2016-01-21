package me.shib.java.lib.omdb.models;

public class Series {

    private String imdbID;
    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private Type type;
    private boolean response;

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getAwards() {
        return awards;
    }

    public String getPoster() {
        return poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public Type getType() {
        return type;
    }

    public boolean isResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "Series [imdbID=" + imdbID + ", title=" + title + ", year=" + year + ", rated=" + rated + ", released="
                + released + ", runtime=" + runtime + ", genre=" + genre + ", director=" + director + ", writer="
                + writer + ", actors=" + actors + ", plot=" + plot + ", language=" + language + ", country=" + country
                + ", awards=" + awards + ", poster=" + poster + ", metascore=" + metascore + ", imdbRating="
                + imdbRating + ", imdbVotes=" + imdbVotes + ", type=" + type + ", response=" + response + "]";
    }

}
