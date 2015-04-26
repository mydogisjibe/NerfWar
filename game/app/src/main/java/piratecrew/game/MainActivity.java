package piratecrew.game;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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


public class MainActivity extends ActionBarActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private byte tiltDirection;
    DrawView drawView;
    ObjectAnimator boxSlider = new ObjectAnimator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor ,SensorManager.SENSOR_DELAY_GAME);

        Button button = (Button)findViewById(R.id.shoot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.laserCreater();
            }
        });
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        p.addRule(RelativeLayout.ABOVE, R.id.shoot);
        p.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        ViewGroup mainView = (ViewGroup) findViewById(R.id.screen);

        drawView = new DrawView(this);
        //drawView.setBackgroundColor(Color.WHITE);
        mainView.addView(drawView, 0, p);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(y < findViewById(R.id.screen).getHeight()*2/3 &&
                        x < findViewById(R.id.screen).getWidth()/2) {
                    //Box moves at constant pps no mater where it is. Takes 2 sec from one end to the other.
                    //time needed is the current position/totalWidth, though it is factored in that posX is the
                    //center of the box and can't move to right or let all the way. This fraction is multiplied
                    //by the amount of time needed to cross one side to the other.);
                    boxSlider.cancel();
                    animateBox(75, 2000 * (drawView.getPosX() - 30) / (drawView.getWidth() - 60));
                }
                else if(y < findViewById(R.id.screen).getHeight()*2/3 && x > findViewById(R.id.screen).getWidth()/2){
                    boxSlider.cancel();
                    animateBox(drawView.getWidth() - 75, 2000 * (1 - (drawView.getPosX() - 30) / (drawView.getWidth() - 60)));
                }
        }
        return true;
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
    @Override
    public void onSensorChanged(SensorEvent event){
        float[] gravityData = event.values;
        float xGravityData = gravityData[0];
        if(xGravityData > 1.0){
        }
        else if (xGravityData < -1.0){

        }
        else{

        }
    }
    private void animateBox(int endPos, int pps){ //Sets up animation for box with the given end position.
        boxSlider = ObjectAnimator.ofInt(drawView, "posX", drawView.getPosX(), endPos);
        boxSlider.setDuration(pps);
        boxSlider.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                drawView.invalidate();
            }
        });
        boxSlider.start();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
