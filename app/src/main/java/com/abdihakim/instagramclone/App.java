package com.abdihakim.instagramclone;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("oCOXS7MMDsHGQsg3j1TGGIOcJbPGjeekDvC6ninh")
                // if defined
                .clientKey("Y8Df0dpp0oZVz8JSJtAZLFeJApCrtI1R2lL3xvE5")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }

}
