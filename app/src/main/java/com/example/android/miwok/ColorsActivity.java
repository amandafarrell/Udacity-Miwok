package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // ArrayList of Word objects
        final ArrayList<Word> primaryLanguage = new ArrayList<Word>();
        primaryLanguage.add(new Word("red","weṭeṭṭi", R.drawable.color_red,R.raw.color_red));
        primaryLanguage.add(new Word("green","chokokki", R.drawable.color_green,R.raw.color_green));
        primaryLanguage.add(new Word("brown","ṭakaakki", R.drawable.color_brown,R.raw.color_brown));
        primaryLanguage.add(new Word("gray","ṭopoppi", R.drawable.color_gray,R.raw.color_gray));
        primaryLanguage.add(new Word("black","kululli", R.drawable.color_black,R.raw.color_black));
        primaryLanguage.add(new Word("white","kelelli", R.drawable.color_white,R.raw.color_white));
        primaryLanguage.add(new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow,
                R.raw.color_dusty_yellow));
        primaryLanguage.add(new Word("mustard yellow","chiwiiṭә", R.drawable.color_mustard_yellow,
                R.raw.color_mustard_yellow));

        //Creat a {@link WordAdapter}, whose data source is a list of
        //{@link Word}s. The adaptor knows how to create list item views for each item in the list
        WordAdapter adapter = new WordAdapter(this, primaryLanguage, R.color.category_colors);

        //Get a reference to the ListView, and attach the adapter to the ListView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        //Set click listener to play the pronunciation when the list item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Get the {@link Word} object at the given position the user clicked on
                Word word = primaryLanguage.get(position);

                //Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                //create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getPronunciation());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    //Releases media player resources when app is stopped
    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}