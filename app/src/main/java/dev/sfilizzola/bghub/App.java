package dev.sfilizzola.bghub;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "OOjZ2IorAoNKn0MF5sxlH7vVXQhvX4E9rTcWOx2I", "WrjsMoDWEX66tu6QY85BRcCitVW3SYqkkTTsSCbw");
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
}