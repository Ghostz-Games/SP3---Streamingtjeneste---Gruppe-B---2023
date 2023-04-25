package streaming.users;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import streaming.exceptions.InValidUsernameOrPasswordException;
import streaming.io.IO;
import streaming.exceptions.InValidPasswordException;
import streaming.exceptions.InValidUsername;

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

	public boolean login (String name, String password)throws Exception{
		for (User u: users) {
			if (name.equals(u.getUsername())) {
				if (password.equals(u.getPassword())) {
					setCurrentUser(u);
					return true;
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
                io.writeDataUser(user);
            }
        }
    }
    protected boolean isValidUsername(String name) throws InValidUsername{
        if(name.length() <= 3){
           throw new InValidUsername("invalid Username; must be longer than 3");
        }
        for(int i = 0; i < users.size();i++){
            if(name.equalsIgnoreCase((users.get(i).getUsername()))){
                throw new InValidUsername("invalid Username; its already in use");
                }
            }
        return true;
    }
    protected boolean isValidPassword(String password) throws InValidPasswordException{
        // tjeks if password contrain atleast 1 special char and 1 upper and lower letter
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
            throw new InValidPasswordException("Password is not valid, must contain atleast 1 special, 1 upper and lower case letter");
    }
    public void changeUsername(String name) throws InValidUsername {
        try {
            if (isValidUsername(name)) {
                this.currentUser.setUsername(name);
            }
        }catch (InValidUsername e){
            throw new InValidUsername(e.getMessage());
        }
    }
    public boolean currentUserIsAdult(){
        return this.currentUser.isAdult();
    }
    public boolean currentUserIsAdmin(){
        return this.currentUser.isAdmin();
    }
}
