package piratecrew.game;

/**
 * Created by mgaim_000 on 4/25/2015.
 */

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import java.util.ArrayList;

public class DrawView extends View {
    Paint paint = new Paint();

    public DrawView(Context context) {
        super(context);
    }
    Box user = new Box(500, 100);
    Box auto = new Box(500, 1000);
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(3);

        user.draw(canvas, paint);

        auto.draw(canvas, paint);
        for (int i = 0; i < lasers.size(); i++) {
            lasers.get(i).sketch(canvas);
            if (lasers.get(i).pos > getHeight()) {
                laserAnimators.remove(i);
                lasers.remove(i);
            }
        }

    }

    public void laserCreater(boolean goingDown){
        Laser laser = new Laser(goingDown);
        lasers.add(laser);
        ObjectAnimator laserMover;
        if(goingDown)
            laserMover = ObjectAnimator.ofInt(laser, "pos", laser.getPos(), 10_000);
        else
            laserMover = ObjectAnimator.ofInt(laser, "pos", laser.getPos(), -10_000);
        laserMover.setDuration(1000);
        laserMover.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        laserMover.start();
        laserAnimators.add(laserMover);
    }




    ArrayList<Laser> lasers = new ArrayList<Laser>();
    ArrayList<ObjectAnimator> laserAnimators = new ArrayList<ObjectAnimator>();



    public class Laser{
        int x;
        int pos = 130; //posY + squareSize
        public Laser( boolean from){
            if(from) //If froma real user
                x = user.getPosX();
            else {
                x = auto.getPosX();
                pos = auto.getPosY() - 30;
            }
        }
        void sketch(Canvas canvas){
            Paint redPaint = new Paint();
            redPaint.setColor(Color.RED);
            canvas.drawRect(x-5, pos,
                    x+5, pos+60, redPaint);

        }
        void setPos(int pos){
            this.pos = pos;
        }
        int getPos(){
            return pos;
        }
    }
}
