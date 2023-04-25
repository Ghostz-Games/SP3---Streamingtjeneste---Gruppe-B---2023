package streaming.users;

import java.util.ArrayList;
import streaming.mediaHandler.*;

public class User {
    private boolean isAdult = false;
    private boolean isAdmin = false;
    private String username;
    private String password;
    private final int id;
    private ArrayList<String> savedMedia = new ArrayList<>();
    private ArrayList<String> watchedMedia = new ArrayList<>();
    private static int countID = 1;
    User(String username,String password,boolean isAdult,boolean isAdmin, String saved, String watched){
        this.username = username;
        this.password = password;
        this.isAdult = isAdult;
        this.isAdmin = isAdmin;
        this.id = countID;
        ++countID;
        String[] splitSaved = saved.split(",");
        for(String s: splitSaved){
            addSavedMedia(s);
        }
        String[] splitWatched = saved.split(",");
        for(String s: splitWatched){
            addWatchedMedia(s);
        }
    }
    User(String username,String password,boolean isAdult,boolean isAdmin){
        this(username, password, isAdult,isAdmin,"","");
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

    public ArrayList<String> getSavedMedia() {
        return savedMedia;
    }

    public ArrayList<String> getWatchedMedia() {
        return watchedMedia;
    }

    public void addSavedMedia(String media){
        savedMedia.add(media);
    }
    public void addWatchedMedia(String media){
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
            output += savedMedia.get(i);
            if(i < savedMedia.size()-1){
                output += ",";
            }
        }
        output += ";";
        for(int i = 0; i < watchedMedia.size();i++){
            output += watchedMedia.get(i);
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
        return getId() + ":" + getUsername();
    }
}

