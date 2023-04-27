package streaming.ui;
import streaming.mediaHandler.Media;
import streaming.mediaHandler.MediaHandler;
import streaming.mediaHandler.Series;
import streaming.users.User;
import streaming.users.UserHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUI implements UI {

    private final Scanner scan;
    private final ExceptionHandler exceptionHandler = new ExceptionHandler(this);
    private final UserHandler userHandler;
    private final MediaHandler mediaHandler;
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
        return Integer.MIN_VALUE;
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
        this.displayMessage("Welcome to the streaming service (TITLE WORK IN PROGRESS). Please select one of the options below");
        this.displayMessage("--------------------------------------");
        this.displayMessage("-1. play movie");
        this.displayMessage("-2. search for movie");
        this.displayMessage("-3. see the library");
        this.displayMessage("-4. see list of watched movies");
        this.displayMessage("-5. see list of Saved movies");
        this.displayMessage("-0. exit");
        this.displayMessage("--------------------------------------");
        if(isAdmin){
            this.displayMessage("hi admin, Here you can add genre to movies. but its not done yet");
        }

        switch (getUserInputInt()){
            case 1:

                watchMovieMenu();
                break;

            case 2:
                searchMovieMenu();
                break;

            case 3:
                library();
                break;

            case 4:
                seeListOfWatchedMovies();

                break;
            case 5:
                seeListOfSavedMovies();

                break;
            case 0:
                System.out.println("goodbye obi wan kenobi");
                System.exit(0);

            default:
                System.out.println("not an option try again");
                this.mainMenu();
        }
    }


    public void selectMedia(){
        String name;

        System.out.println("please type the name of the media you wanna use");
        
    }

    public void watchMovieMenu(){
        System.out.println("--------------------------------------");
        System.out.println("-1. play movie");
        //System.out.println("-2. rewind movie");
        //System.out.println("-3. forward movie");
        System.out.println("-0. return to main menu");
        if (scan.hasNextInt()){
            switch(scan.nextInt()){
                case 1:
                    viewMovie(mediaHandler.getCurrentMedia());
                    //watchMovieMenu();
                    break;
                case 2:
                    System.out.println("now rewinding");
                    //watchMovieMenu();
                    break;
                case 3:
                    System.out.println("now forwarding");
                    //watchMovieMenu();
                    break;
                case 0:
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
                    case 1 -> {
                        System.out.println("please type the name you want to search for:");
                        if (scan.hasNextLine()) {
                            name = scan.nextLine();
                        }
                    }
                    case 2 -> {
                        System.out.println("please type the genre you want to search for:");
                        if (scan.hasNextLine()) {
                            genre = scan.nextLine();
                        }
                    }
                    case 3 -> {
                        System.out.println("please type the year you want to search by");
                        if (scan.hasNextLine()) {
                            year = scan.nextLine();
                        }
                    }
                    case 4 -> {
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
                    }
                    case 5 -> {
                        ArrayList<Media> searched = mediaHandler.searchMedia(name, genre, year, minRating, maxRating);
                        for (Media m : searched) {
                            System.out.println(m);
                        }
                        boolean exitRedo = false;
                        while(!exitRedo){
                            System.out.println("Are one of these the movie you searched for? y/n");
                            if (scan.hasNextLine()) {
                                switch (scan.nextLine().toLowerCase()) {
                                    case "y" -> {
                                        pageSelectMenu(searched, 10);
                                        exit = true;
                                        exitRedo = true;
                                    }
                                    case "n" -> exitRedo = true;
                                    default -> this.displayMessage("Sorry I don't understand.");
                                }
                            }
                        }
                    }
                    case 0 -> {
                        exit = true;
                        this.mainMenu();
                    }
                    default -> System.out.println("not an option try again");
                }
            } else {
                scan.nextLine();
                System.out.println("not an option please use a number");
            }
        }
    }
    public void seeListOfWatchedMovies(){
        System.out.println("here is your watched media:");
        this.pageSelectMenu(userHandler.getCurrentUser().getWatchedMedia(), 10);
    }
    public void seeListOfSavedMovies(){
        System.out.println("here is your saved media:");
        this.pageSelectMenu(userHandler.getCurrentUser().getSavedMedia(), 10);
    }

    @Override
    public void library(){
        this.pageSelectMenu(mediaHandler.getMedia(), 10);
    }

    public void pageSelectMenu(ArrayList<Media> media, int pageLimit){
        if(!isAdult){
            media = mediaHandler.searchMedia(media,null,"family", null, 0, 0);
        }
        System.out.println("Type \"help\" for help");
        int page = 0;
        int maxPages = ((media.size()-1)/pageLimit);
        System.out.println(media.size());
        boolean exit = false;
        while(!exit){
            for(int i = page*pageLimit; i < media.size() && i < (page+1)*pageLimit; ++i){
                System.out.println(i + ": " + media.get(i));
            }
            System.out.println("page: " + page + " out of " + maxPages);
            if(scan.hasNextLine()){
                String input = scan.nextLine();
                String[] splitInput = input.split(" ", 2);
                switch (splitInput[0].toLowerCase()) { //// index 0 is the Action
                    case "help", "h", "?" -> {
                        System.out.println("page 0, p 0 - go to given page.");
                        System.out.println("watch 0, w 0 - watch media.");
                        System.out.println("save 0, s 0 - save media.");
                        System.out.println("search, sea - go to the search menu.");
                        System.out.println("library, lib - see the entire list of movies.");
                        System.out.println("back, b - return to main menu.");
                    }
                    case "page", "p" -> {
                        if(splitInput.length == 3){
                            System.out.println("number parameter required example: p 0");
                            break;
                        }
                        if (Integer.parseInt(splitInput[1]) >= 0 && Integer.parseInt(splitInput[1]) <= maxPages) {
                            page = Integer.parseInt(splitInput[1]);
                        }
                    }
                    case "watch", "w" -> {
                        if(splitInput.length == 3){
                            System.out.println("number parameter required example: watch 0");
                            break;
                        }
                        if (Integer.parseInt(splitInput[1]) >= 0 && Integer.parseInt(splitInput[1]) < media.size()) {
                            mediaHandler.selectMedia(media.get(Integer.parseInt(splitInput[1])));
                        }
                        this.viewMovie(mediaHandler.getCurrentMedia());
                        exit = true;
                    }
                    case "save", "s" -> {
                        if(splitInput.length == 3){
                            System.out.println("number parameter required example: save 0");
                            break;
                        }
                        if (Integer.parseInt(splitInput[1]) >= 0 && Integer.parseInt(splitInput[1]) <= media.size()) {
                            mediaHandler.selectMedia(media.get(Integer.parseInt(splitInput[1])));
                        }
                        userHandler.getCurrentUser().addSavedMedia(mediaHandler.getCurrentMedia());
                        userHandler.save();
                        System.out.println(mediaHandler.getCurrentMedia().getName() + " has been saved.");
                    }
                    case "search", "sea" -> {
                        this.searchMovieMenu();
                        exit = true;
                    }
                    case "library", "lib" -> {
                        this.library();
                        exit = true;
                    }
                    case "back", "b" -> {
                        this.mainMenu();
                        exit = true;
                    }
                }
            }
        }
    }

    @Override
    public void viewMovie(Media media){
        System.out.println("Now playing : "+ mediaHandler.getCurrentMedia().getName() + "...");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            exceptionHandler.catchException(e);
        }
        System.out.println("Thank you for watching!");
        userHandler.getCurrentUser().addWatchedMedia(media);
        userHandler.getCurrentUser().removeSavedMedia(media);
        userHandler.save();
        if(mediaHandler.getCurrentMedia() instanceof Series){
            System.out.println("watch next episode? Y/N");
            if(scan.hasNextLine()){
                switch (scan.nextLine().toLowerCase()) {
                    case "y" -> {
                        System.out.println("Sorry not implimented yet!");
                        mainMenu(); //// for now!
                    }
                    case "n" -> {
                        mainMenu();
                    }
                }
            }
        } else {
            mainMenu();
        }
    }
}
