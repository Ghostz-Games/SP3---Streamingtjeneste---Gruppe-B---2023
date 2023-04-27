
import streaming.io.IO;
import streaming.mediaHandler.MediaHandler;
import streaming.ui.ExceptionHandler;
import streaming.ui.UI;
import streaming.users.UserHandler;
import streaming.io.FileIO;
import streaming.users.User;
import streaming.ui.TextUI;


public class Streaming {

    public static IO io = new FileIO();
    public static UserHandler userHandler = new UserHandler(io);
    public static MediaHandler mediaHandler;
    public static UI textUI;
    public static ExceptionHandler exceptionHandler = new ExceptionHandler();

    public static void main(String[] args) {

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

        mediaHandler = new MediaHandler(io, userHandler.getCurrentUser());

        try {
            mediaHandler.loadMovies();
            mediaHandler.loadSeries();
        }catch(Exception e){
            exceptionHandler.catchException(e);
        }
        ////Debugging
        System.out.println(userHandler.getUsers());
        System.out.println(userHandler.getCurrentUser());


        textUI = new TextUI(userHandler, mediaHandler);
        textUI.loginOrRegister();
        textUI.settings(getCurrentUser());
        textUI.mainMenu();
    }

    public static User getCurrentUser(){
        return userHandler.getCurrentUser();
    }

}