
import streaming.io.db.DBIO;
import streaming.io.IO;
import streaming.mediaHandler.MediaHandler;
import streaming.ui.ExceptionHandler;
import streaming.ui.UI;
import streaming.users.UserHandler;
import streaming.users.User;
import streaming.ui.TextUI;


public class Streaming {

    public static IO io = new DBIO();
    public static UserHandler userHandler;
    public static MediaHandler mediaHandler;
    public static UI textUI;
    public static ExceptionHandler exceptionHandler = new ExceptionHandler();

    public static void main(String[] args) {
        setup();

        //debug();

        textUI.loginOrRegister();
        /*try{
            userHandler.login("admin", "Root!1000");
        } catch(Exception ignored){
            textUI.loginOrRegister();
        }*/
        textUI.settings(getCurrentUser());
        textUI.mainMenu();
    }
    private static void setup(){
        mediaHandler = new MediaHandler(io);
        try {
            mediaHandler.loadMovies();
            mediaHandler.loadSeries();
        }catch(Exception e){
            exceptionHandler.catchException(e);
        }
        userHandler = new UserHandler(io, mediaHandler);
        textUI = new TextUI(userHandler, mediaHandler);
    }

    /*private static void debug(){
        try {
            userHandler.registerUser("Lars2", "Coolseeeeeeeeej!", true);
        }catch (Exception e){
            exceptionHandler.catchException(e);
        }

        try {
            userHandler.login("Lars2", "Coolseeeeeeeeej!");
        }catch (Exception e){
            exceptionHandler.catchException(e);
        }
        ////Debugging
        System.out.println(userHandler.getUsers());
        System.out.println(userHandler.getCurrentUser());
    }*/

    public static User getCurrentUser(){
        return userHandler.getCurrentUser();
    }

}