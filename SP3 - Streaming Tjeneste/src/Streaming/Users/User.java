package streaming.users;

import java.util.ArrayList;
import streaming.mediaHandler.*;

public class User {
    private boolean isAdult = false;
    private boolean isAdming = false;
    private String username;
    private String password;
    private final int id;
    private ArrayList<Media> savedMedia;
    private ArrayList<Media> watchedMedia;
    User(String username,String password,boolean isAdult,boolean isAdming){
        int countID = 1;
        this.username = username;
        this.password = password;
        this.isAdult = isAdult;
        this.isAdming = isAdming;
        this.id = countID;
        countID++;
    }
    public boolean isAdult() {
        return isAdult;
    }

    public boolean isAdming() {
        return isAdming;
    }

    public String getUsername() {
        return username;
    }
    protected String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Media> getSavedMedia() {
        return savedMedia;
    }

    public ArrayList<Media> getWatchedMedia() {
        return watchedMedia;
    }

    public void addSavedMedia(Media media){
        savedMedia.add(media);
    }
    public void addWatchedMedia(Media media){
        watchedMedia.add(media);
    }
    protected void setUsername(String name){
            this.username = name;
        }
    public String saveUserDate(){
        String output = "";
        output += username+";";
        output += password+";";
        output += id+";";
        output += isAdult+";";
        output += isAdming+";";
        for(int i = 0; i < savedMedia.size();i++){
            output += savedMedia.get(i).getName();
            if(i < savedMedia.size()-1){
                output += ",";
            }
        }
        output += ";";
        for(int i = 0; i < watchedMedia.size();i++){
            output += watchedMedia.get(i).getName();
            if(i < savedMedia.size()-1){
                output += ",";
            }
        }
        output += ";";
        return output;
    }
}

