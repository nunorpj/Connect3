package pt.isec.a21240649.connect3;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    //0= yellow, 1 = red
    int activePlayer=0;

    //2 means unplayed
    int[] gameState= {2,2,2,2,2,2,2,2,2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }






    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        counter.setTranslationY(-1000f);

        if(activePlayer==0){
            counter.setImageResource(R.drawable.yellow);
            activePlayer=1;
        }else{
            counter.setImageResource(R.drawable.red);
            activePlayer=0;
        }
        counter.animate().translationYBy(1000f).rotationX(360).setDuration(500);
    }
}
