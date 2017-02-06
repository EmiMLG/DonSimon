package es.telefonica.talentum.donsimon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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



    private static final int GREEN_BUTTON = 0;
    private static final int RED_BUTTON = 1;
    private static final int BLUE_BUTTON = 2;
    private static final int YELLOW_BUTTON = 3;
    private static final int GAME_OVER = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        greenButton = (Button) findViewById(R.id.activity_main___green_button);
        redButton = (Button) findViewById(R.id.activity_main___red_button);
        blueButton = (Button) findViewById(R.id.activity_main___blue_button);
        yellowButton = (Button) findViewById(R.id.activity_main___yellow_button);
        startButton = (Button) findViewById(R.id.activity_main___start_button);

        Button[]buttons = {
                greenButton,
                redButton,
                blueButton,
                yellowButton
        };

        simon = new Simon(this,sounds, buttons);


        boolean correct = simon.checkMoves(myMoves);
        simon = new Simon(this,sounds, buttons);
        int hs = new PreferenceManager().getHighScore(this);

        simon.setHighScore(hs);

        disableAllButton();

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveAction(GREEN_BUTTON);
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveAction(RED_BUTTON);
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveAction(BLUE_BUTTON);
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              moveAction(YELLOW_BUTTON);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simon.reset();
                simon.nextMove();

                myMoves = new LinkedList<>();

               // enableAllButton();



            }
        });

    }

    private void disableAllButton() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_main___easy_level) {

            simon.setTime(2000);

            return true;

        } else if (id == R.id.menu_main____normal_level){
            simon.setTime(1000);
            return true;

        } else if (id == R.id.menu_main___hard_level){
            simon.setTime(500);

            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    /*private void enableAllButton() {
        for (Button b: buttons){
            b.setEnabled(true);
        }
    }*/

    public void moveAction(int button){
        simon.playSounds(button);
        myMoves.add(button);
        boolean checkmoves = simon.checkMoves(myMoves);
        if (checkmoves == true) {
            if (myMoves.size()== simon.getLevel()){
                simon.nextMove();
                myMoves = new LinkedList<Integer>();
            }

            if (simon.getLevel() > simon.getHighScore()){
                simon.getHighScore(simon.getLevel());
                new PreferenceManager().putHighScore(this, simon.getHighScore());
                this.setTitle("Current Level " + simon.getLevel() + " HighScore" + simon.getHighScore());
            }
        } else {
            //game over
            simon.playSounds(GAME_OVER);
            simon.reset();
            myMoves = LinkedList<Integer>();
            disableAllButton();
        }
    }


}
