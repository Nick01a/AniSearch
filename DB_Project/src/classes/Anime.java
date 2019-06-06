package classes;

import jdk.nashorn.api.scripting.JSObject;

import java.util.ArrayList;

public class Anime {
    private final int id;
    private final String title;
    private final String picture;
    private final ArrayList<String> genres;
    private String english_title;
    private int episodes;
    private int scored_by;
    private double score;

    public Anime(int id, String title, String picture, int episodes, double score, int scored_by, ArrayList<String> genres){
        this.id = id;
        this.title = title;
        this.picture = picture;
        this.genres = genres;
        this.episodes = episodes;
        this.score = score;
        this.scored_by = scored_by;
    }

    public void setEnglish_title(String english_title) {
        this.english_title = english_title;
    }
    public String getEnglist_title(){
        return english_title;
    }
    public String getTitle() {
        return title;
    }

    public String getPicture() {
        return picture;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public String getEnglish_title() {
        return english_title;
    }

    public int getEpisodes() {
        return episodes;
    }

    public int getScored_by() {
        return scored_by;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    public int getId() {
        return this.id;
    }

    public JSObject JSONify() {
        JSONObject json = new JSONObject();
    }
}
