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
    private static int countID = 1;
    User(String username,String password,boolean isAdult,boolean isAdmin, ArrayList<Media> saved, ArrayList<Media> watched){
        this.username = username;
        this.password = password;
        this.isAdult = isAdult;
        this.isAdmin = isAdmin;
        this.id = countID;
        ++countID;
        this.savedMedia = saved;
        this.watchedMedia = watched;
//        String[] splitSaved = saved.split(",");
//        for(String s: splitSaved){
//            addSavedMedia(s);
//        }
//
//        String[] splitWatched = saved.split(",");
//        for(String s: splitWatched){
//            addWatchedMedia(s);
//        }
    }
    User(String username,String password,boolean isAdult,boolean isAdmin){
        this(username, password, isAdult,isAdmin,new ArrayList<>(),new ArrayList<>());
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
        StringBuilder output = new StringBuilder();
        output.append(username).append(";");
        output.append(password).append(";");
        output.append(id).append(";");
        output.append(isAdult).append(";");
        output.append(isAdmin).append(";");
        for(int i = 0; i < savedMedia.size();i++){
            output.append(savedMedia.get(i).getName());
            if(i < savedMedia.size()-1){
                output.append(",");
            }
        }
        output.append(";");
        for(int i = 0; i < watchedMedia.size();i++){
            output.append(watchedMedia.get(i).getName());
            if(i < savedMedia.size()-1){
                output.append(",");
            }
        }
        output.append(";");
        return output.toString();
    }
    @Override
    public String toString(){
        return getId() + ":" + getUsername();
    }
}

