package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private String nickname;
    private String pass;
    private int gender;
    private int age_group;
    private String[] fav_genres;
    private int group_id;
    private HashMap<String,Double> genres_rank = new HashMap<>();
    private List<Anime> watchlist = new ArrayList<>();
    private List<Anime> finished_list = new ArrayList<>();
    public User(String nickname, String pass, int gender, int age_group, int group_id){
        this.nickname = nickname;
        this.pass = pass;
        this.gender= gender;
        this.group_id = group_id;
        this.age_group = age_group;
    }
    public boolean add_to_watchlist(Anime anime){
        if (watchlist.size() > 8)
            return false;
        else{
            this.watchlist.add(anime);
            return true;
        }
    }
    public void add_to_finished_list(Anime anime){
        this.finished_list.add(anime);
    }

    public void setFinished_list(List<Anime> finished_list) {
        this.finished_list = finished_list;
    }

    public String getWatchlist_as_String() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Anime a:watchlist) {
            stringBuilder.append(a.getId()).append(",");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
        else
            return "";
    }
    public List<Anime> getWatchlist(){
        return watchlist;
    }

    public List<Anime> getFinished_list() {
        return finished_list;
    }
    public String getFinished_list_as_String(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Anime a:finished_list) {
            stringBuilder.append(a.getId()).append(",");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
        else
            return "";

    }
    private void setGenres_rank(){
        for (Anime a:finished_list) {
            for (String g:a.getGenres()) {
                if (this.genres_rank.containsKey(g)){
                    this.genres_rank.put(g,(this.genres_rank.get(g) + 1));
                } else{
                    this.genres_rank.put(g,1.0);
                }
            }
        }
        for (String i:this.genres_rank.keySet()) {
            this.genres_rank.put(i,this.genres_rank.get(i)/finished_list.size());
        }
    }
    private HashMap<String,Double> getGenres_rank(){
        return this.genres_rank;
    }
    public String getNickname() {
        return nickname;
    }

    public String getPass() {
        return pass;
    }

    public int getGender() {
        return gender;
    }

    public int getAge_group() {
        return age_group;
    }

    public int getGroup_id() {
        return group_id;
    }

    public String[] getFav_genres() {
        return fav_genres;
    }
}
