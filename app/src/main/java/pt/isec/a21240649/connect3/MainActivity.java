package pt.isec.a21240649.connect3;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.RoundingMode;

public class MainActivity extends Activity {

    //0= yellow, 1 = red
    int activePlayer=0;
    public AlertDialog aDHandler;
    boolean gameEnd = false;
    ImageView player;
    //2 means unplayed
    int[] gameState= {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={  {0,1,2},{3,4,5},{6,7,8}, // horizontal
                                {0,3,6},{1,4,7},{2,5,8}, // vertical
                                {0,4,8},{2,4,6}};        // diagnal
    public LinearLayout llEmp;
    public Button btnEmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = (ImageView) findViewById(R.id.player);
        llEmp = findViewById(R.id.LLEpate);
        llEmp.setVisibility(View.INVISIBLE);
        btnEmp = findViewById(R.id.btn_empate);
        btnEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

    }






    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && gameEnd==false) {
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

                    gameEnd=true;
                }

            }



            AnimationSet animation = new AnimationSet(false);
            Animation Rollout = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, -1.5f, Animation.RELATIVE_TO_SELF,
                   0.0f, Animation.RELATIVE_TO_SELF, 0.0f);

            Rollout.setDuration(300);

            Rollout.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {


                    if(activePlayer==0){
                        player.setImageResource(R.drawable.yellow);

                    }else{
                        player.setImageResource(R.drawable.red);

                    }

                    Animation RollIn = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.5f,
                            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                            0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    RollIn.setDuration(300);


                    player.startAnimation(RollIn);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });



            Rollout.setDuration(300);

            animation.addAnimation(Rollout);
            player.startAnimation(animation);


            if(gameEnd==true){
                WIN();
            }
        }//end if


        Boolean flag=false;

        for(int i = 0 ; i<9;i++){
            if(gameState[i]==2){
                flag=true;
                break;
            }
        }
        if(flag==false){
            llEmp.setVisibility(View.VISIBLE);
        }





    }//end func

    public void WIN(){




        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        View alert =getLayoutInflater().inflate(R.layout.layout,null);
        ImageView iV=(ImageView) alert.findViewById(R.id.win);


        if(activePlayer!=0){
            iV.setImageResource(R.drawable.yellow);
        }else{
            iV.setImageResource(R.drawable.red);
        }

        Button btn = alert.findViewById(R.id.BTNalertNewGame);



        alertDialogBuilder.setView(alert);
        aDHandler = alertDialogBuilder.show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aDHandler.dismiss();
                recreate();
            }
        });
    }



}
