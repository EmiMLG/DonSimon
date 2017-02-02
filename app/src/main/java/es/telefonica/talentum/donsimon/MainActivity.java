package es.telefonica.talentum.donsimon;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button greenButton;
    Button redButton;
    Button blueButton;
    Button yellowButton;
    Button startButton;

    Simon simon;
    List<Integer> myMoves;

    int[]sounds = {
            R.raw.mario_sound,
            R.raw.mortal_sound,
            R.raw.sable_sound,
            R.raw.sonic_sound,
    };

    Button[]buttons = {
            greenButton,
            redButton,
            blueButton,
            yellowButton
    };

    private static final int GREEN_BUTTON = 0;
    private static final int RED_BUTTON = 1;
    private static final int BLUE_BUTTON = 2;
    private static final int YELLOW_BUTTON = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greenButton = (Button) findViewById(R.id.activity_main___green_button);
        redButton = (Button) findViewById(R.id.activity_main___red_button);
        blueButton = (Button) findViewById(R.id.activity_main___blue_button);
        yellowButton = (Button) findViewById(R.id.activity_main___yellow_button);
        startButton = (Button) findViewById(R.id.activity_main___start_button);

        simon = new Simon(this,sounds, buttons);
        simon.reset();
        simon.nextMove();

        myMoves = new LinkedList<>();
        myMoves.add(0);
        myMoves.add(1);
        myMoves.add(2);

        boolean correct = simon.checkMoves(myMoves);

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               simon.playSounds(GREEN_BUTTON);

                myMoves.add(GREEN_BUTTON);

                boolean checkmoves = simon.checkMoves(myMoves);
                if (checkmoves == true) {
                    if (myMoves.size()== simon.getLevel()){
                    simon.nextMove();
                    }
                } else {
                    //game over
                    simon.reset();
                }

            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaySound(R.raw.mortal_sound);
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaySound(R.raw.sable_sound);
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               PlaySound(R.raw.sonic_sound);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



    public void PlaySound(int resource) {
        MediaPlayer mpSound;
        mpSound = MediaPlayer.create(getBaseContext(), resource);
        mpSound.start();


    }
}
