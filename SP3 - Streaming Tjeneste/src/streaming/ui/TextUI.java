package streaming.ui;
import streaming.mediaHandler.MediaHandler;
import streaming.users.UserHandler;

import java.util.Scanner;

public class TextUI {

    private Scanner scan;
    private ExceptionHandler exceptionHandler = new ExceptionHandler();
    private UserHandler userHandler;
    private MediaHandler mediaHandler;
    private boolean isAdult = false;
    private boolean isAdmin = false;

    public TextUI(UserHandler userHandler, MediaHandler mediaHandler){

        this.userHandler = userHandler;

        this.mediaHandler = mediaHandler;

        this.scan = new Scanner(System.in);

    }

    public void settings(boolean adult, boolean admin) {
        isAdult = adult;
        isAdmin = admin;
    }

    public String getUserInput(String msg){
        return msg;
    }

    public void displayMessage(String msg){
        System.out.println(msg);
    }

    public void printMenu(){
        if(isAdult && !isAdmin){
            System.out.println("What do you want to do with your life?");
            System.out.println("--------------------------------------");
            System.out.println("-1.play movie");
            System.out.println("-2.search for movie");
            System.out.println("-3.see list of watched movies");
            System.out.println("-0. exit");
            System.out.println("--------------------------------------");
            if(scan.hasNextInt()){
                switch (scan.nextInt()){
                    case 1:
                        watchMovieMenu();
                        break;

                    case 2:
                        searchMovieMenu();
                        break;

                    case 3:
                        seeListOfWatchedMovies();
                        break;

                    case 0:
                        System.out.println("goodbye obi wan kenobi");
                        System.exit(0);

                    default:
                        System.out.println("not an option try again");
                        printMenu();
                }
            }
        }
    }
    public void watchMovieMenu(){
        System.out.println("What do you want to do with your life?");
        System.out.println("--------------------------------------");
        System.out.println("-1. play movie");
        System.out.println("-2. rewind movie");
        System.out.println("-3. forward movie");
        System.out.println("-4. return to main menu");
        if (scan.hasNextInt()){
            switch(scan.nextInt()){
                case 1:
                    mediaHandler.getMedia();
                    break;
                case 2:
                    System.out.println("now rewinding");
                    watchMovieMenu();
                    break;
                case 3:
                    System.out.println("now forwarding");
                    watchMovieMenu();
                    break;
                case 4:
                    printMenu();
                    break;
                default:
                    System.out.println("not an option try again");
                    watchMovieMenu();
            }

        }
    }
    public void searchMovieMenu(){
        String name = null;
        String genre = null;
        String year = null;
        float minRating = 0;
        float maxRating = 0;
        boolean exit = false;
        while(!exit) {
            System.out.println("What do you want to do with your life?");
            System.out.println("--------------------------------------");
            System.out.println("-1. search for name" + (name==null?"":" - " + name));
            System.out.println("-2. search by genre" + (genre==null?"":" - " + genre));
            System.out.println("-3. year" + (year==null?"":" - " + year));
            System.out.println("-4. min,max rating" +
                    (minRating != 0 || maxRating != 0?" - ":"") +
                    (minRating==0?"": "min: " + minRating) +
                    (minRating != 0 || maxRating != 0?" - ":"") +
                    (maxRating==0?"": "max: " + maxRating));
            System.out.println("-5. start search");
            System.out.println("-0. return to main menu");
            if (scan.hasNextInt()) {
                switch (scan.nextInt()) {
                    case 1:
                        System.out.println("please type the name you want to search for:");
                        if (scan.hasNextLine()) {
                            name = scan.nextLine();
                        }
                        searchMovieMenu();
                        break;

                    case 2:

                        System.out.println("please type the genre you want to search for:");
                        if (scan.hasNextLine()) {
                            genre = scan.nextLine();
                        }
                        searchMovieMenu();
                        break;

                    case 3:

                        System.out.println("please type the year you want to search by");
                        if (scan.hasNextLine()) {
                            year = scan.nextLine();
                        }
                        searchMovieMenu();
                        break;

                    case 4:

                        System.out.println("please type the minimum rating and maximum rating");
                        System.out.println("minimum rating:");
                        if (scan.hasNextFloat()) {
                            minRating = scan.nextFloat();
                            scan.nextLine();
                            System.out.println("maximum rating:");
                            if (scan.hasNextFloat()) {
                                maxRating = scan.nextFloat();
                                scan.nextLine();
                                searchMovieMenu();
                            }

                        }
                        break;

                    case 5:
                        System.out.println(mediaHandler.searchMedia(name, genre, year, minRating, maxRating));

                        break;

                    case 0:
                        exit = true;
                        printMenu();
                        //printMenu(userHandler.getCurrentUser().isAdult(), userHandler.getCurrentUser().isAdmin());
                        break;
                    case 6:
                        System.out.println("name "+ name+ " genre :"+ genre+ " year "+ year+ " min rating: " +minRating + " max rating: "+maxRating);
                        break;
                    default:
                        System.out.println("not an option try again");
                        searchMovieMenu();
                }
            }
        }
    }
    public void seeListOfWatchedMovies(){
        System.out.println("here is your watched media:");
        System.out.println(userHandler.getCurrentUser().getWatchedMedia());
        System.out.println("do you want to return to main menu? y/n?");
        if(scan.hasNextLine()){
            switch (scan.nextLine()){
                case "y":
                    printMenu();
                    break;
                case "n":
                    seeListOfWatchedMovies();
                    break;
                default:
                    System.out.println("not an option try again");
                    seeListOfWatchedMovies();
            }
        }
    }
}
