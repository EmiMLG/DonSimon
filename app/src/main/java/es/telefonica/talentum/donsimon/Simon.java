package es.telefonica.talentum.donsimon;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Simon {
    private int time = 1000;
    private static final int ONE_SECOND = 1000;
    private int level = 0; //current level
    private final int[] sounds; //sounds to play
    private final Button[] buttons; // game buttons
    private List<Integer> moves; //game moves (sounds & buttons to highlight)
    private MediaPlayer mediaPlayer;
    private Context context;
    private int highScore;

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer i: moves){
                    Log.d("Simon", "move " + i);
                    //TODO: make noise
                    //TODO: Click button
                    pressButton(buttons[i], true);
                    playSounds(i);
                    pressButton(buttons[i], false);
                }
            }
        }).start();
    }

    private void pressButton(final Button b, final boolean state){
        MainThread.run(new Runnable() {
            @Override
            public void run() {
                b.setPressed(state);
            }
        });
    }

    public void playSounds(Integer i) {
        if (mediaPlayer !=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }

        mediaPlayer = MediaPlayer.create(context, sounds[i]);
        mediaPlayer.start();

        SystemClock.sleep(time);
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
        if (myMoves == null){
            return false;
        }
        for (int i = 0; i < myMoves.size(); i++) {
            Integer simonMove = moves.get(i);
            Integer myMove = myMoves.get(i);
            if (!simonMove.equals(myMove)){
                check = false;
                break;
            }
        }

        return check;
    }

    public int getLevel() {
        return level;
    }

    public int getTime() {
        return time;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

class MainThread {
    public static void run(final Runnable runnable) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        });
    }

}
