package piratecrew.game;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.graphics.Color;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator toTheLeft = ObjectAnimator.ofInt(drawView, "squareX1", 50, 0);
                toTheLeft.setDuration(1000);
                Log.v("Verbose", String.valueOf(drawView.squareX1));

            }
        });

        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        p.addRule(RelativeLayout.ABOVE, R.id.button);
        p.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        ViewGroup mainView = (ViewGroup) findViewById(R.id.screen);

        drawView = new DrawView(this);
        //drawView.setBackgroundColor(Color.WHITE);
        mainView.addView(drawView, 0, p);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    boolean pressed;

    public boolean onTouch(View v, MotionEvent event) {

        //int action = event.getAction();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                pressed = true;
                break;

            case MotionEvent.ACTION_MOVE:
                //User is moving around on the screen
                break;

            case MotionEvent.ACTION_UP:
                pressed = false;
                break;
        }
        return pressed;
    }
}
