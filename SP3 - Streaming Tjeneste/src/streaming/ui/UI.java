package streaming.ui;

import streaming.mediaHandler.Media;

public interface UI {

    void loginOrRegister();
    void login();
    void registerUser();
    void library();
    void viewMovie(Media media);


}
