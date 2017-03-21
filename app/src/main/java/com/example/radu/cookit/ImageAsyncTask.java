package com.example.radu.cookit;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by andreea on 19.03.2017.
 */

public class ImageAsyncTask extends AsyncTask<String, Void, Drawable>{



    @Override
    protected Drawable doInBackground(String... params) {

        try {
            InputStream is = (InputStream) new URL(params[0]).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
