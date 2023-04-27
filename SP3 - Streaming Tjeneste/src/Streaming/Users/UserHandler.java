package streaming.users;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import streaming.exceptions.InValidUsernameOrPasswordException;
import streaming.io.IO;
import streaming.exceptions.InValidPasswordException;
import streaming.exceptions.InValidUsernameException;
import streaming.mediaHandler.Media;
import streaming.mediaHandler.MediaHandler;

import java.util.ArrayList;

public class UserHandler {
    protected ArrayList<User> users = new ArrayList<>();
    protected User currentUser;
    private IO io;

    public UserHandler(IO io, MediaHandler mediaHandler)  {
        this.io = io;
        try {
            ArrayList<String> userString = io.readDataUser();
            for (String s: userString) {
                if(!s.equals("")) {
                    String[] splitS = s.split(";", -1); //// limit = -1 means that it doesn't ignore empty lines
                    ArrayList<Media> savedMedia = mediaHandler.getListFromInline(splitS[5]);
                    ArrayList<Media> watchedMedia = mediaHandler.getListFromInline(splitS[6]);
                    users.add(new User(splitS[0], splitS[1], splitS[3].equals("true"), splitS[4].equals("true"), savedMedia, watchedMedia));
                }
            }
        } catch (Exception e) {

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

	public void login (String name, String password) throws Exception{
		for (User u: users) {
			if (name.equals(u.getUsername())) {
				if (password.equals(u.getPassword())) {
					setCurrentUser(u);
					return;
				}
			}
		}
		throw new InValidUsernameOrPasswordException("Invalid username or password");
    }

    public void registerUser(String name,String password,boolean isAdult)throws Exception{
        boolean isAdmin = false;
        if(isValidUsername(name)){
            if(isValidPassword(password)){
                User user = new User(name,password,isAdult,isAdmin);
                users.add(user);
                setCurrentUser(user);
                io.writeDataUser(user);
            }
        }
    }
    protected boolean isValidUsername(String name) throws InValidUsernameException {
        if(name.length() <= 3){
           throw new InValidUsernameException("invalid Username; must be longer than 3");
        }
        if(!users.isEmpty()) {
            for (int i = 0; i < users.size(); i++) {
                if (name.equalsIgnoreCase((users.get(i).getUsername()))) {
                    throw new InValidUsernameException("invalid Username; its already in use");
                }
            }
        }
        return true;
    }
    protected boolean isValidPassword(String password) throws InValidPasswordException{
        // Checks if password contains at least 1 special, 1 upper and lower letter and only valid characters.
        Pattern rgSpecial = Pattern.compile("[!#¤%&/?+*]+"); //// At least 1 Special
        Pattern rgUpper = Pattern.compile("[A-Z]"); //// At least 1 Uppercase
        Pattern rgLower = Pattern.compile("[a-z]"); //// At least 1 Lowercase
        Pattern rgNonValids = Pattern.compile("[^a-zA-Z!#¤%&/?+*0-9]"); //// Allowed signs
        Matcher mtSpecial = rgSpecial.matcher(password);
        Matcher mtUpper = rgUpper.matcher(password);
        Matcher mtLower = rgLower.matcher(password);
        Matcher mtNonValids = rgNonValids.matcher(password);
            if(mtSpecial.find() && mtUpper.find() && mtLower.find() && !mtNonValids.find() && password.length() > 8) {
                return true;
            }
            throw new InValidPasswordException("Password is not valid, must contain atleast 1 special, 1 upper and lower case letter, and be longer than 8 characters");
    }
    public void changeUsername(String name) throws InValidUsernameException {
        try {
            if (isValidUsername(name)) {
                this.currentUser.setUsername(name);
            }
        }catch (InValidUsernameException e){
            throw new InValidUsernameException(e.getMessage());
        }
    }
    public void save(){
        io.writeDataUser(currentUser);
    }

    public boolean currentUserIsAdult(){
        return this.currentUser.isAdult();
    }
    public boolean currentUserIsAdmin(){
        return this.currentUser.isAdmin();
    }
}
