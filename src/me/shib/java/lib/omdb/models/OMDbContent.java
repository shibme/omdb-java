package me.shib.java.lib.omdb.models;

import me.shib.java.lib.common.utils.JsonLib;

public class OMDbContent {

    private String imdbID;
    private String title;
    private String year;
    private String rated;
    private String released;
    private String season;
    private String episode;
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
    private String seriesID;
    private Type type;
    private boolean response;

    public OMDbContent(OMDbContent oc1, OMDbContent oc2) {
        if (oc1.imdbID != null) {
            this.imdbID = oc1.imdbID;
        } else {
            this.imdbID = oc2.imdbID;
        }

        if (oc1.title != null) {
            this.title = oc1.title;
        } else {
            this.title = oc2.title;
        }

        if (oc1.year != null) {
            this.year = oc1.year;
        } else {
            this.year = oc2.year;
        }

        if (oc1.rated != null) {
            this.rated = oc1.rated;
        } else {
            this.rated = oc2.rated;
        }

        if (oc1.released != null) {
            this.released = oc1.released;
        } else {
            this.released = oc2.released;
        }

        if (oc1.season != null) {
            this.season = oc1.season;
        } else {
            this.season = oc2.season;
        }

        if (oc1.episode != null) {
            this.episode = oc1.episode;
        } else {
            this.episode = oc2.episode;
        }

        if (oc1.runtime != null) {
            this.runtime = oc1.runtime;
        } else {
            this.runtime = oc2.runtime;
        }

        if (oc1.genre != null) {
            this.genre = oc1.genre;
        } else {
            this.genre = oc2.genre;
        }

        if (oc1.director != null) {
            this.director = oc1.director;
        } else {
            this.director = oc2.director;
        }

        if (oc1.writer != null) {
            this.writer = oc1.writer;
        } else {
            this.writer = oc2.writer;
        }

        if (oc1.actors != null) {
            this.actors = oc1.actors;
        } else {
            this.actors = oc2.actors;
        }

        if (oc1.plot != null) {
            this.plot = oc1.plot;
        } else {
            this.plot = oc2.plot;
        }

        if (oc1.language != null) {
            this.language = oc1.language;
        } else {
            this.language = oc2.language;
        }

        if (oc1.country != null) {
            this.country = oc1.country;
        } else {
            this.country = oc2.country;
        }

        if (oc1.awards != null) {
            this.awards = oc1.awards;
        } else {
            this.awards = oc2.awards;
        }

        if (oc1.poster != null) {
            this.poster = oc1.poster;
        } else {
            this.poster = oc2.poster;
        }

        if (oc1.metascore != null) {
            this.metascore = oc1.metascore;
        } else {
            this.metascore = oc2.metascore;
        }

        if (oc1.imdbRating != null) {
            this.imdbRating = oc1.imdbRating;
        } else {
            this.imdbRating = oc2.imdbRating;
        }

        if (oc1.imdbVotes != null) {
            this.imdbVotes = oc1.imdbVotes;
        } else {
            this.imdbVotes = oc2.imdbVotes;
        }

        if (oc1.seriesID != null) {
            this.seriesID = oc1.seriesID;
        } else {
            this.seriesID = oc2.seriesID;
        }

        if (oc1.type != null) {
            this.type = oc1.type;
        } else {
            this.type = oc2.type;
        }

        this.response = (oc1.response || oc2.response);
    }

    public Movie getMovieObject() {
        if (type == Type.movie) {
            String jsonData = JsonLib.getDefaultInstance().toJson(this);
            return JsonLib.getDefaultInstance().fromJson(jsonData, Movie.class);
        }
        return null;
    }

    public Series getSeriesObject() {
        if (type == Type.series) {
            String jsonData = JsonLib.getDefaultInstance().toJson(this);
            return JsonLib.getDefaultInstance().fromJson(jsonData, Series.class);
        }
        return null;
    }

    public Episode getEpisodeObject() {
        if (type == Type.episode) {
            String jsonData = JsonLib.getDefaultInstance().toJson(this);
            return JsonLib.getDefaultInstance().fromJson(jsonData, Episode.class);
        }
        return null;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public boolean isResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "OMDbContent [imdbID=" + imdbID + ", title=" + title + ", year=" + year + ", rated=" + rated
                + ", released=" + released + ", season=" + season + ", episode=" + episode + ", runtime=" + runtime
                + ", genre=" + genre + ", director=" + director + ", writer=" + writer + ", actors=" + actors
                + ", plot=" + plot + ", language=" + language + ", country=" + country + ", awards=" + awards
                + ", poster=" + poster + ", metascore=" + metascore + ", imdbRating=" + imdbRating + ", imdbVotes="
                + imdbVotes + ", seriesID=" + seriesID + ", type=" + type + ", response=" + response + "]";
    }

}
