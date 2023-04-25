package streaming.ui;


import streaming.io.FileIO;

import java.io.File;
import java.io.FileNotFoundException;

import streaming.exceptions.*;
public class ExceptionHandler {
    TextUI ui;
    public ExceptionHandler(TextUI ui){
        this.ui = ui;
    }


    public String catchException(Exception e){

        String errorHandling = null;
        if(e instanceof FileNotFoundException){
            System.out.println(e);
        } else if (e instanceof InValidPasswordException) {
            errorHandling = " Obi-Wan Kenobi says the passsword or Username is wrong try again";
        } else if(e instanceof InValidUsername){
            errorHandli
        }
        return errorHandling;
    }


}
