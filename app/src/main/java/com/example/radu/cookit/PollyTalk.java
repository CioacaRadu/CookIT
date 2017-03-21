package com.example.radu.cookit;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;


import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyPresigningClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechPresignRequest;
import com.amazonaws.services.polly.model.Voice;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;


public class PollyTalk {

    List<Voice> voices;


    private AmazonPollyPresigningClient client;
    Regions MY_REGION = Regions.EU_WEST_1;
    int VOICE_ID = 6; // ROMANIA

    static boolean isRunning = false;



    URL presignedSynthesizeSpeechUrl;
    String COGNITO_POOL_ID = "eu-west-1:53098383-cfd2-4954-8ef3-2d036504be47";
    AsyncTask<Void,Void,URL> task;



    public PollyTalk(Context context,String message) {

        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context,
                COGNITO_POOL_ID,
                MY_REGION
        ); // login

        client = new AmazonPollyPresigningClient(credentialsProvider);


        task = new GetPollyVoices(message);
        try {
            presignedSynthesizeSpeechUrl = task.execute().get(); // and create speech
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }




    }



    public void PlaySpeech() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        isRunning = true;

        try {
            // Set media player's data source to previously obtained URL.
            mediaPlayer.setDataSource(presignedSynthesizeSpeechUrl.toString());
        } catch (IOException e) {
            Log.e(TAG, "Unable to set data source for the media player! " + e.getMessage());
        }

// Prepare the MediaPlayer asynchronously (since the data source is a network stream).
        mediaPlayer.prepareAsync();

// Set the callback to start the MediaPlayer when it's prepared.
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

// Set the callback to release the MediaPlayer after playback is completed.
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                isRunning = false;
            }
        });


        task.cancel(true);

    }

    private class GetPollyVoices extends AsyncTask<Void, Void, URL> {

        String message;


        public GetPollyVoices(String message) {

            this.message = message;

        }
        @Override
        protected URL doInBackground(Void... params) {
            if (voices != null) {
                return null;
            }


            DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

            DescribeVoicesResult describeVoicesResult;
            try {

                describeVoicesResult = client.describeVoices(describeVoicesRequest);
            } catch (RuntimeException e) {
                Log.e("Warn", "Unable to get available voices. " + e.getMessage());
                return null;
            }


            voices = describeVoicesResult.getVoices();


            Log.i("Warn", "Available Polly voices: " + voices);
            SynthesizeSpeechPresignRequest synthesizeSpeechPresignRequest =
                    new SynthesizeSpeechPresignRequest()
                            // Set the text to synthesize.
                            .withText(message)
                            // Select voice for synthesis.
                            .withVoiceId(voices.get(VOICE_ID).getId()) // "Joanna"
                            // Set format to MP3.
                            .withOutputFormat(OutputFormat.Mp3);


            return client.getPresignedSynthesizeSpeechUrl(synthesizeSpeechPresignRequest);
        }

        @Override
        protected void onPostExecute(URL aVoid) {
            if (voices == null) {
                return;
            }





        }
    }

}
