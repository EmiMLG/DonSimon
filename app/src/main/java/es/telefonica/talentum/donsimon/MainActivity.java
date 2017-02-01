package es.telefonica.talentum.donsimon;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaySound();

            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaySound();
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaySound();
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaySound();
            }
        });

    }

    public void PlaySound() {
        MediaPlayer mpSound;
        mpSound = MediaPlayer.create(this, R.raw.sound);
        mpSound.start();
        mpSound.pause();
    }


}
