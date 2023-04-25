package streaming.users;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import streaming.io.IO;
import streaming.ui.ExceptionHandler;

import java.util.ArrayList;

public class UserHandler {
    protected ArrayList<User> users = new ArrayList();
    protected User currentUser;

    private IO io;

    public UserHandler(IO io)  {
        this.io = io;
        try {
            ArrayList<String> userString = io.readDataUser();
            for (String s: userString) {
                String[] splitS = s.split(";", -1); //// limit = -1 means that it doesn't ignore empty lines
                users.add(new User(splitS[0], splitS[1],splitS[3].equals("true"),splitS[4].equals("true"),splitS[5],splitS[6]));
            }
        } catch (IOException e) {
            //Exception call goes here.
        }
    }

    public ArrayList<User> getUsers(){
        return users;
    }
    private User getUser(int index){
        return users.get(index);
    }
    private void setCurrentUser(User user){
        this.currentUser = user;
    }
    public User getCurrentUser(){
        return currentUser;
    }

    public boolean hasCurrentUser(){
        return currentUser != null;
    }

    public boolean login(String name,String password){
        for(int i = 0; 0 < users.size();i++){
           if(name.equalsIgnoreCase((users.get(i).getUsername()))){
               if(password.equals(users.get(i).getPassword())){
                   setCurrentUser(getUser(i));
                   return true;
               }
            }
        }
        return false;
    }
    public void registerUser(String name,String password,boolean isAdult){
        boolean isAdmin = false;
        if(isValidUsername(name)){
            if(users.contains(name)){
                //exception handler message here.
                return;
            }
            if(isValidPassword(password)){
                User user = new User(name,password,isAdult,isAdmin);
                users.add(user);
                io.writeDataUser(user);
            }
        }
    }
    protected boolean isValidUsername(String name){
        if(name.length() < 3){
           return false;
        }
        for(int i = 0; 0 < users.size();i++){
            if(name.equalsIgnoreCase((users.get(i).getUsername()))){
                return false;
                }
            }
        return true;
    }
    protected boolean isValidPassword(String password){
        // tjeks if password contrain atleast 1 special char and 1 upper and lower letter
        Pattern rgSpecial = Pattern.compile("[!#¤%&/?+*]+");
        Pattern rgUpper = Pattern.compile("[A-Z]");
        Pattern rgLower = Pattern.compile("[a-z]");
        Pattern rgNonValids = Pattern.compile("[^a-zA-Z!#¤%&/?+*]");
        Matcher mtSpecial = rgSpecial.matcher(password);
        Matcher mtUpper = rgUpper.matcher(password);
        Matcher mtLower = rgLower.matcher(password);
        Matcher mtNonValids = rgNonValids.matcher(password);
            if(mtSpecial.find() && mtUpper.find() && mtLower.find() && !mtNonValids.find() && password.length() > 8) {
                return true;
            }
        return false;
    }
    public void changeUsername(String name) {
        if (isValidUsername(name)) {
            this.currentUser.setUsername(name);
        }
    }
    public boolean currentUserIsAdult(){
        return this.currentUser.isAdult();
    }
    public boolean currentUserIsAdmin(){
        return this.currentUser.isAdmin();
    }



}
