package streaming.ui;
import streaming.mediaHandler.Media;
import streaming.mediaHandler.MediaHandler;
import streaming.users.User;
import streaming.users.UserHandler;

import java.util.Scanner;

public class TextUI implements UI {

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

    public void settings(User user) {
        isAdult = user.isAdult();
        isAdmin = user.isAdmin();
    }

    public String getUserInput(String msg){
        System.out.println(msg);
        return scan.nextLine();
    }

    public int getUserInputInt(){
        int scanInt;
        if(scan.hasNextInt()){
            scanInt = scan.nextInt();
            scan.nextLine();
            return scanInt;
        }
        return -1;
    }

    public void displayMessage(String msg){
        System.out.println(msg);
    }


    @Override
    public void loginOrRegister(){
        displayMessage("Hello user, please choose to login or register a user");
        displayMessage("Press '1' to login");
        displayMessage("Press '2' to register a user");
        if(scan.hasNextInt()){
            switch (scan.nextInt()) {
                case 1 -> {
                    scan.nextLine();
                    login();
                }
                case 2 -> {
                    scan.nextLine();
                    registerUser();
                }
            }
        }

    }

    @Override
    public void registerUser() {
        //displayMessage("Please insert username and password to register a user");

        String usernameInput = getUserInput("Please write your username:");
        String passwordInput = getUserInput("Please write your password:");
        String isAdultInput = getUserInput("Is the created user over the age of 18? Y/N");
        boolean isAdult = false;

        if(isAdultInput.equalsIgnoreCase("y")){
            isAdult = true;
        }
        
        try {
            userHandler.registerUser(usernameInput, passwordInput, isAdult);
        }catch(Exception e){
            exceptionHandler.catchException(e);
            registerUser();
        }


    }

    @Override
    public void login() {
        displayMessage("Please insert your login credentials below:");

        String usernameInput = getUserInput("Please type your username:");
        String passwordInput = getUserInput("Please type your password");

        try{
            userHandler.login(usernameInput, passwordInput);
        }catch(Exception e){
            exceptionHandler.catchException(e);
            login();
        }
    }


    public void mainMenu(){
        if(isAdult && !isAdmin){
            this.displayMessage("Welcome to the streaming service (TITLE WORK IN PROGRESS). Please select one of the options below");
            this.displayMessage("--------------------------------------");
            this.displayMessage("-1.play movie");
            this.displayMessage("-2.search for movie");
            this.displayMessage("-3.see list of watched movies");
            this.displayMessage("-0. exit");
            this.displayMessage("--------------------------------------");

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
                        this.mainMenu();
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
                    this.mainMenu();
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
                int lineInt = scan.nextInt();
                scan.nextLine();
                switch (lineInt) {
                    case 1:
                        System.out.println("please type the name you want to search for:");
                        if (scan.hasNextLine()) {
                            name = scan.nextLine();
                        }
                        break;

                    case 2:

                        System.out.println("please type the genre you want to search for:");
                        if (scan.hasNextLine()) {
                            genre = scan.nextLine();
                        }
                        break;

                    case 3:

                        System.out.println("please type the year you want to search by");
                        if (scan.hasNextLine()) {
                            year = scan.nextLine();
                        }
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
                            }

                        }
                        break;

                    case 5:
                        System.out.println(mediaHandler.searchMedia(name, genre, year, minRating, maxRating));
                        exit = true;
                        break;

                    case 0:
                        exit = true;
                        this.mainMenu();
                        break;

                    default:
                        System.out.println("not an option try again");
                }
            }
        }
    }
    public void seeListOfWatchedMovies(){
        System.out.println("here is your watched media:");
        System.out.println(userHandler.getCurrentUser().getWatchedMedia());
        System.out.println("enter to return to main menu...");
        if(scan.hasNextLine()){
            scan.nextLine();
            this.mainMenu();
        }
    }

    @Override
    public void library(){
        for(Media m : mediaHandler.getMedia()){
            System.out.println(m.toString());
        }
    }

    @Override
    public void viewMovie(Media media){
        //// play movie function.
    }
}
