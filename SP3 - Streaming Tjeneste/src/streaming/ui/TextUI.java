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
        return scan.nextLine();
    }

    public int getUserInputInt(){
        return scan.nextInt();
    }

    public void displayMessage(String msg){
        System.out.println(msg);
    }



    public void welcomeWindow(){
        displayMessage("Hello user, please choose to login or register a user");
        displayMessage("Press '1' to login");
        displayMessage("Press '2' to register a user");
        if(scan.hasNextInt()){
            switch (scan.nextInt()) {
                case 1 -> loginMenu();
                case 2 -> registerMenu();
            }
        }

    }

    private void registerMenu() {
        displayMessage("Please insert username and password to register a user");

        String usernameinput = getUserInput("Please write your username:");
        String passwordinput = getUserInput("Please write your password");
        String isAdultInput = getUserInput("Is the created user over the age of 18? Y/N");
        boolean isAdult = false;

        if(isAdultInput.equalsIgnoreCase("y")){
            isAdult = true;
        }
        
        try {
            userHandler.registerUser(usernameinput, passwordinput, isAdult);
        }catch(Exception e){
            exceptionHandler.catchException(e);
            registerMenu();
        }


    }

    private void loginMenu() {
        displayMessage("Please insert your login credentials below:");

        String usernameInput = getUserInput("Please type your username:");
        String passwordInput = getUserInput("Please type your password");

        try{
            userHandler.login(usernameInput, passwordInput);
        }catch(Exception e){
            exceptionHandler.catchException(e);
            loginMenu();
        }
    }


    public void printMenu(){
        if(isAdult && !isAdmin){
            this.displayMessage("Welcome to the streaming service (TITLE WORK IN PROGRESS). Please select on of the options below");
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
            System.out.println("-1. search for name");
            System.out.println("-2. search by genre");
            System.out.println("-3. year");
            System.out.println("-4. min,max rating");
            System.out.println("-0. return to main menu");
            System.out.println("-5. start search");
            System.out.println("-6. See search criteria");
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
                            if (scan.hasNextFloat()) {
                                System.out.println("maximum rating:");
                                maxRating = scan.nextFloat();
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
