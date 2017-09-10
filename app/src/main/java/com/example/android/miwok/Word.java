package com.example.android.miwok;

/**
 * Created by Amanda on 11/7/2016.
 */
/**
 * {@linkWord}represents a vocabulary word that the user wants to learn
 * It contains a default translation and a Miwok translation of a word
 */
public class Word {

    /**Default translation for the word*/
    private String mDefaultTranslation;

    /**Miwok translation for the word*/
    private String mMiwokTranslation;

    /**Image for the word*/
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    /**Pronunciation media file for the word*/
    private int mPronunciationResourceId;

    /**
     * Create a new Word object
     *
     * @param defaultTranslation is the word in a language that the user sets as default
     *
     * @param miwokTranslation is the word in the Miwok language
     *
     * @param imageResourceId is the drawable resource ID for the image asset for the word
     *
     *@param pronunciationResourceId  is the raw resource ID for the word's pronunciation audio file
     */
    public Word (String defaultTranslation, String miwokTranslation, int imageResourceId,
                 int pronunciationResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mPronunciationResourceId = pronunciationResourceId;
    }

    /**Constructor for phrases page
     * No image asset needed
     * */
    public Word (String defaultTranslation, String miwokTranslation, int pronunciationResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mPronunciationResourceId = pronunciationResourceId;
    }

    /**
     * Get the default translation of a word
     */
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of a word
     */
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    /**
     * Get the image for the word
     */
    public int getImageResourceId () { return  mImageResourceId; }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Get the [ronunciation for the word
     */
    public int getPronunciation() { return  mPronunciationResourceId; }


    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mPronunciationResourceId=" + mPronunciationResourceId +
                '}';
    }
}
