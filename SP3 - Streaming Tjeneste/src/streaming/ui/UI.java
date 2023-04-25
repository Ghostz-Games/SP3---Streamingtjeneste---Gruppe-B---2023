package streaming.ui;

import streaming.mediaHandler.Media;
import streaming.users.User;

public interface UI {

    void loginOrRegister();
    void login();
    void registerUser();
    void library();
    void viewMovie(Media media);

    public void settings(User user);

}
