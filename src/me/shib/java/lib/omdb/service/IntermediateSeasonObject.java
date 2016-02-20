package me.shib.java.lib.omdb.service;

import me.shib.java.lib.common.utils.JsonLib;
import me.shib.java.lib.omdb.models.MiniEpisode;
import me.shib.java.lib.omdb.models.Season;

public final class IntermediateSeasonObject {

    private String title;
    private String season;
    private Object[] episodes;
    private MiniEpisode[] miniEpisodeList;

    private MiniEpisode getCorrectedMiniEpisode(Object miniEpisodeObject) {
        JsonLib jsonLib = new JsonLib();
        String jsonData = jsonLib.toJson(miniEpisodeObject);
        MiniEpisode result1 = jsonLib.fromJson(jsonData, MiniEpisode.class);
        MiniEpisode result2 = jsonLib.fromUpperCamelCaseJson(jsonData, MiniEpisode.class);
        return new MiniEpisode(result1, result2);
    }

    private MiniEpisode[] getMiniEpisodeListFromObjects() {
        if ((episodes != null) && (episodes.length > 0)) {
            miniEpisodeList = new MiniEpisode[episodes.length];
            for (int i = 0; i < miniEpisodeList.length; i++) {
                miniEpisodeList[i] = getCorrectedMiniEpisode(episodes[i]);
            }
            return miniEpisodeList;
        }
        return null;
    }

    private MiniEpisode[] getMiniEpisodes() {
        if (miniEpisodeList == null) {
            return getMiniEpisodeListFromObjects();
        }
        return miniEpisodeList;
    }

    protected Season getSeasonObject() {
        if ((title != null) && (season != null)) {
            return new Season(title, season, getMiniEpisodes());
        }
        return null;
    }

}
