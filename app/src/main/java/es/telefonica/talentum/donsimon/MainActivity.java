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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greenButton = (Button) findViewById(R.id.activity_main___green_button);
        redButton = (Button) findViewById(R.id.activity_main___red_button);
        blueButton = (Button) findViewById(R.id.activity_main___blue_button);
        yellowButton = (Button) findViewById(R.id.activity_main___yellow_button);

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

        Simon simon = new Simon(sounds, buttons);
        simon.reset();
        simon.nextMove();

        simon.nextMove();

        List<Integer> myMoves = new LinkedList<>();
        myMoves.add(0);
        myMoves.add(1);
        myMoves.add(2);

        boolean correct = simon.checkMoves(myMoves);



        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               PlaySound(R.raw.mario_sound);

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

    }

    public void PlaySound(int resource) {
        MediaPlayer mpSound;
        mpSound = MediaPlayer.create(getBaseContext(), resource);
        mpSound.start();


    }
}
