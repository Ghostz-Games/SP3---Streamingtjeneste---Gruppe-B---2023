package streaming.ui;


import streaming.io.FileIO;

import java.io.File;
import java.io.FileNotFoundException;
public class ExceptionHandler {

    public ExceptionHandler(){

    }


    public String catchException(Exception e){

        String errorHandling = null;
        if(e instanceof FileNotFoundException){
            errorHandling = "Obi-Wan Kenobi could not find the file";
//        } else if (e) {
//            errorHandling = " Obi-Wan Kenobi says the passsword or Username is wrong try again";
        }
        return errorHandling;
    }


}
