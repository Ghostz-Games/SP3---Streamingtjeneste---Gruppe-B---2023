
import streaming.mediaHandler.Media;
import streaming.mediaHandler.MediaHandler;
import streaming.users.UserHandler;
import streaming.io.FileIO;
import streaming.users.User;

public class Streaming {

    public static void main(String[] args) {

        FileIO io = new FileIO();
        UserHandler userHandler = new UserHandler(io);
        userHandler.registerUser("Lars2", "Coolseeeeeeeeej!", true);
        userHandler.login("Lars2","Coolseeeeeeeeej!");
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
        io.writeDataUser(userHandler.getCurrentUser());



    }
}