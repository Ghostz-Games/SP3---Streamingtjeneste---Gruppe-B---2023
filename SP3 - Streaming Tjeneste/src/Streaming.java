
import streaming.mediaHandler.Media;
import streaming.mediaHandler.MediaHandler;
import streaming.users.UserHandler;
import streaming.io.FileIO;
import streaming.users.User;

public class Streaming {

    public static FileIO io = new FileIO();
    public static UserHandler userHandler = new UserHandler(io);
    public static MediaHandler mediaHandler;

    public static void main(String[] args) {


        userHandler.registerUser("Lars3", "TCoolseeeeeeeeej!", true);
        userHandler.login("Lars3","TCoolseeeeeeeeej!");
        MediaHandler mediaHandler = new MediaHandler(io, userHandler.getCurrentUser());
        try {

            mediaHandler.loadMovies();
            mediaHandler.loadSeries();

            for(Media m : mediaHandler.getMedia()){
                System.out.println(m);
            }

        }catch(Exception e){

            System.out.println(e);

        }
        System.out.println(userHandler.getUsers());
        System.out.println(userHandler.getCurrentUser());
    }

    public static User getCurrentUser(){
        return userHandler.getCurrentUser();
    }

}