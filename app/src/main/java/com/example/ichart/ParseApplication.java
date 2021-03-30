package com.example.ichart;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("tEHsDiJcmsx8amXFDPfoIwu2HjPKFhF7RsK0lDSM")
                .clientKey("kG3UY6D0jiXiR6laMC3Se8SDdN0C3BT7GeFsn1aH")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
