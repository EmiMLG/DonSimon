package es.telefonica.talentum.donsimon;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {


    public int getHighScore(final Context context){

        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);//
        int highScore = preferences.getInt("HIGHSCORE", 0);

        return highScore;

    }

    public void putHighScore (Context context, final int highScore){
        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);//
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("HIGH SCORE", highScore);

        editor.apply();
    }


}
