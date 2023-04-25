
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
    public static ExceptionHandler exceptionHandler;

    public static void main(String[] args) {

        try {
            userHandler.registerUser("Lars2", "Coolseeeeeeeeej!", true);
            userHandler.registerUser("Jens4", "Bananaaaaaaaaaa?", true);
        }catch (Exception e){

        }
        try {
            userHandler.login("Lars2", "Coolseeeeeeeeej!");
            userHandler.login("Jens4", "Bananaaaaaaaaaa?");
        }catch (Exception e){

        }

        mediaHandler = new MediaHandler(io, userHandler.getCurrentUser());
        textUI = new TextUI(userHandler, mediaHandler);

        try {

            mediaHandler.loadMovies();
            mediaHandler.loadSeries();

            for(Media m : mediaHandler.getMedia()){
                textUI.displayMessage(m.toString());
            }

        }catch(Exception e){

            textUI.displayMessage(e.toString());

        }
        textUI.displayMessage(userHandler.getUsers().toString());
        textUI.displayMessage(userHandler.getCurrentUser().getUsername());
        io.writeDataUser(userHandler.getCurrentUser());
    }

    public static User getCurrentUser(){
        return userHandler.getCurrentUser();
    }

}