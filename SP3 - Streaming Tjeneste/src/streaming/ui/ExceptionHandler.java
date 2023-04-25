package streaming.ui;


import streaming.io.FileIO;

import java.io.File;
import java.io.FileNotFoundException;

import streaming.exceptions.*;
public class ExceptionHandler {
    TextUI ui;

    public ExceptionHandler() {
    }


    public void catchException(Exception e) {

        String errorHandling = null;
        if (e instanceof FileNotFoundException) {
            System.out.println("File not Found");
        }/* else if (e instanceof Exception) {
            System.out.println(e.getMessage());
        }*/else if (e instanceof InValidPasswordException) {
            System.out.println(e.getMessage());
        } else if(e instanceof InValidUsername){
            System.out.println(e.getMessage());
        }
        //System.out.println(e.getMessage());
    }


    }
