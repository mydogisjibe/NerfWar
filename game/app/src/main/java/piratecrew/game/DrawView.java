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
    private int squareSize = 30;
    private int posX = 500, posY = 100;
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(3);

        float heightWidthRatio = getHeight()/getWidth();

        canvas.drawRect(posX - (squareSize), posY - squareSize,
                posX + (squareSize), posY + squareSize, paint);
        for(int i=0; i<lasers.size();i++){
            lasers.get(i).sketch(canvas);
            if(lasers.get(i).pos > getHeight()) {
                laserAnimators.remove(i);
                lasers.remove(i);
            }
        }


    }

    void setPosX(int posX){
        this.posX = posX;
    }
    void setPosY(int posY){
        this.posY = posY;
    }
    int getPosX(){
        return posX;
    }
    int getPosY(){
        return posY;
    }

    public void laserCreater(){
        Laser laser = new Laser();
        lasers.add(laser);
        ObjectAnimator laserMover = ObjectAnimator.ofInt(laser, "pos", laser.getPos(), 1_000);
        laserMover.setDuration(1_000);
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
        public Laser(){
            x = posX;
        }
        void sketch(Canvas canvas){
            paint.setColor(Color.RED);
            canvas.drawRect(x-5, pos,
                    x+5, pos+60, paint);

        }
        void setPos(int pos){
            this.pos = pos;
        }
        int getPos(){
            return pos;
        }
    }
}
