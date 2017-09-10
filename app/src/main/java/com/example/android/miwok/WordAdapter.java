package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Amanda on 11/7/2016.
 */

public class WordAdapter extends ArrayAdapter<Word>{

    private int mCategoryColor;
    /**
     * This is a custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param words          A List of Word objects to display in a list
     * @param categoryColor  The resource ID of the color for the category associated with the words list
     */
    public WordAdapter(Activity context, ArrayList<Word> words, int categoryColor) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mCategoryColor = categoryColor;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Check if the existing view is being reused, otherwise inflate the view
                View listItemView = convertView;
                if(listItemView == null) {
                    listItemView = LayoutInflater.from(getContext()).inflate(
                            R.layout.list_item, parent, false);
                }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        //Find the Linear Layout in the list_item.xml layout with the ID text_background
        LinearLayout textBackground = (LinearLayout) listItemView.findViewById(R.id.text_background);
        //Set the background of this Linear Layout to the category color
        textBackground.setBackgroundResource(mCategoryColor);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the Miwok translation for the current Word object and
        // set this text on the Miwok TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation for the current Word object and
        // set this text on the default TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());


        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);

        //Check to see if the word has an image asset associated with it
        if (currentWord.hasImage()) {
            //Set the ImageView to the image resource specified in the current Word
            iconView.setImageResource(currentWord.getImageResourceId());

            //Make sure the view is visible
            iconView.setVisibility(View.VISIBLE);
        }
        else {
            //If there is no image asset, hide the image view
            iconView.setVisibility(View.GONE);
        }


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
