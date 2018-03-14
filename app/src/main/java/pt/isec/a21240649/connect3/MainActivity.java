package pt.isec.a21240649.connect3;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    //0= yellow, 1 = red
    int activePlayer=0;

    //2 means unplayed
    int[] gameState= {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions={  {0,1,2},{3,4,5},{6,7,8}, // horizontal
                                {0,3,6},{1,2,7},{2,5,8}, // vertical
                                {0,4,8},{2,4,6}};        // diagnal
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }






    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if (gameState[tappedCounter]==2) {
            counter.setTranslationY(-1000f);
            gameState[tappedCounter]=activePlayer;

            if(activePlayer==0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer=1;
            }else{
                counter.setImageResource(R.drawable.red);
                activePlayer=0;
            }
            counter.animate().translationYBy(1000f).rotationX(360).setDuration(500);

            for(int[] winningPosition: winningPositions){

                if (gameState[winningPosition[0]] ==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                                gameState[winningPosition[2]] != 2){

                    Log.i("game","-> "+gameState[winningPosition[0]]);
                }

            }
        }

    }
}
