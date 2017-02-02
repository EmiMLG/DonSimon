package es.telefonica.talentum.donsimon;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Simon {
    private static final int ONE_SECOND = 1000;
    private int level = 0; //current level
    private final int[] sounds; //sounds to play
    private final Button[] buttons; // game buttons
    private List<Integer> moves; //game moves (sounds & buttons to highlight)
    private MediaPlayer mediaPlayer;
    private Context context;

    public Simon(Context context, int[] sounds, Button[] buttons) {
        this.context = context;
        this.sounds = sounds;
        this.buttons = buttons;
    }


    public void nextMove(){
        level = level + 1;
        int next = nextRandomMove();
        moves.add(next);

        playMoves();

    }

    private void playMoves() {
        for (Integer i: moves){
            Log.d("Simon", "move " + i);
            //TODO: make noise
            playSounds(i);
            //TODO: Click button
            //TODO: wait 1 second
            SystemClock.sleep(ONE_SECOND);
        }
    }

    private void playSounds(Integer i) {
        if (mediaPlayer !=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }

        mediaPlayer = MediaPlayer.create(context, sounds[i]);
        mediaPlayer.start();
    }


    public void reset(){
        level = 0;
        moves = new LinkedList<>();
    }

    private int nextRandomMove(){
        Random rnd = new Random();
        return rnd.nextInt(4);
    }

    public boolean checkMoves(List<Integer> myMoves) {

        boolean check = true;

        if (myMoves == null || myMoves.size() !=moves.size()){
            return false;
        }
        for (int i = 0; i < moves.size(); i++) {
            Integer simonMove = moves.get(i);
            Integer myMove = myMoves.get(i);
            if (!simonMove.equals(myMove)){
                check = false;
                break;
            }
        }

        return check;
    }
}
