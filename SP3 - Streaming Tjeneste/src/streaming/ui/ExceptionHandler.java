package streaming.ui;


import java.io.FileNotFoundException;

import streaming.exceptions.*;
public class ExceptionHandler {
    public UI ui;

    public ExceptionHandler() {
    }
    public ExceptionHandler(UI ui) {
        this.ui = ui;
    }

    public void catchException(Exception e) {

        String errorHandling = null;
        if (e instanceof FileNotFoundException) {
            System.out.println("File not Found");
        }else if (e instanceof InValidPasswordException) {
            System.out.println(e.getMessage());
        } else if(e instanceof InValidUsernameException){
            System.out.println(e.getMessage());
        } else if(e instanceof InValidUsernameOrPasswordException){
            System.out.println(e.getMessage());
        }
        //System.out.println(e.getMessage());
    }
    }
