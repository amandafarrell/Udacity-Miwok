package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;


import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    /**
     * Handles playback of all the sound files
     */
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
        primaryLanguage.add(new Word("Where are you going?", "minto wuksus",
                R.raw.phrase_where_are_you_going));
        primaryLanguage.add(new Word("What is your name?", "tinnә oyaase'nә",
                R.raw.phrase_what_is_your_name));
        primaryLanguage.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        primaryLanguage.add(new Word("How are you feeling?", "michәksәs?",
                R.raw.phrase_how_are_you_feeling));
        primaryLanguage.add(new Word("I’m feeling good.", "kuchi achit",
                R.raw.phrase_im_feeling_good));
        primaryLanguage.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        primaryLanguage.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        primaryLanguage.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        primaryLanguage.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        primaryLanguage.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        //Creat a {@link WordAdapter}, whose data source is a list of
        //{@link Word}s. The adaptor knows how to create list item views for each item in the list
        WordAdapter adapter = new WordAdapter(this, primaryLanguage, R.color.category_phrases);

        //Get a reference to the ListView, and attach the adapter to the ListView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        //Set click listener to play the pronunciation when the list item is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //Get the {@link Word} object at the given position the user clicked on
                Word word = primaryLanguage.get(position);

                //Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                //create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getPronunciation());
                mMediaPlayer.start();
                Log.v("PhrasesActivity", "MediaPlayer after start: " + mMediaPlayer);
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    //Releases media player resources when app is stopped
    @Override
    protected void onStop() {
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
            Log.v("PhrasesActivity", "MediaPlayer after release: " + mMediaPlayer);

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            Log.v("PhrasesActivity", "MediaPlayer after set to null: " + mMediaPlayer);
        }
    }
}