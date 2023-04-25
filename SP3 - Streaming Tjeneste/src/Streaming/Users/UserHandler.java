package streaming.users;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import streaming.io.IO;

import java.util.ArrayList;

public class UserHandler {
    protected ArrayList<User> users = new ArrayList();
    protected User currentUser;

    private IO io;

    public UserHandler(IO io){
        this.io = io;
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

        public boolean login (String name, String password)throws Exception{
        /*try {*/
            for (int i = 0; 0 < users.size(); i++) {
                if (name.equalsIgnoreCase((users.get(i).getUsername()))) {
                    if (password.equals(users.get(i).getPassword())) {
                        setCurrentUser(getUser(i));
                        return true;
                    }
                }
            }
            return false;
     /*   }catch (Exception e){
            throw new IllegalArgumentException();
        }*/
    }

    public void registerUser(String name,String password,boolean isAdult)throws Exception{
        boolean isAdmin = false;
        if(isValidUsername(name)){
            if(isValidPassword(password)){
                User user = new User(name,password,isAdult,isAdmin);
                users.add(user);
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
