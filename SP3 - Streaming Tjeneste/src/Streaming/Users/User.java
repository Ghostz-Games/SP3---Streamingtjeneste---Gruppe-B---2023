package streaming.users;

import java.util.ArrayList;
import streaming.mediaHandler.*;

public class User {
    private boolean isAdult = false;
    private boolean isAdmin = false;
    private String username;
    private String password;
    private final int id;
    private ArrayList<Media> savedMedia;
    private ArrayList<Media> watchedMedia;
    User(String username,String password,boolean isAdult,boolean isAdmin){
        int countID = 1;
        this.username = username;
        this.password = password;
        this.isAdult = isAdult;
        this.isAdmin = isAdmin;
        this.id = countID;
        countID++;
    }
    public boolean isAdult() {
        return isAdult;
    }

    public boolean isAdmin() {
        return isAdmin;
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
    public String saveUserData(){
        String output = "";
        output += username+";";
        output += password+";";
        output += id+";";
        output += isAdult+";";
        output += isAdmin+";";
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
        /*StringBuilder sb1 = new StringBuilder();
        sb1.append(username+";");*/

        return output;
    }

    @Override
    public String toString(){
        return getId() + ";" + getUsername() + ";" + getPassword() + ";" + MediaHandler.InlineListString(getSavedMedia()) + ";" + MediaHandler.InlineListString(getWatchedMedia());
    }
}

