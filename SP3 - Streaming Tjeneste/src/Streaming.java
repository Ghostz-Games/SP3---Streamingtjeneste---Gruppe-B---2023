
import streaming.mediaHandler.Media;
import streaming.mediaHandler.MediaHandler;
import streaming.ui.ExceptionHandler;
import streaming.users.UserHandler;
import streaming.io.FileIO;
import streaming.users.User;
import streaming.ui.TextUI;


public class Streaming {

    public static FileIO io = new FileIO();
    public static UserHandler userHandler = new UserHandler(io);
    public static MediaHandler mediaHandler;
    public static TextUI textUI;
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
        textUI = new TextUI(userHandler, mediaHandler);

        try {

            mediaHandler.loadMovies();
            mediaHandler.loadSeries();

//            for(Media m : mediaHandler.getMedia()){
//                textUI.displayMessage(m.toString());
//            }

        }catch(Exception e){

            exceptionHandler.catchException(e);

        }
        System.out.println(userHandler.getUsers());
        System.out.println(userHandler.getCurrentUser());
    }

    public static User getCurrentUser(){
        return userHandler.getCurrentUser();
    }

}