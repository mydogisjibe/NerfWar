package piratecrew.game;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by mgaim_000 on 4/26/2015.
 */
public class Box {
    public Box(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    private int posX, posY;
    private int squareSize = 30;
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(3);

        canvas.drawRect(posX - (squareSize), posY - squareSize,
                posX + (squareSize), posY + squareSize, paint);

    }

    int getPosX(){
        return posX;
    }
    int getPosY(){
        return posY;
    }

    void setPosX(int posX){
        this.posX = posX;
    }
    void setPosY(int posY){
        this.posY = posY;
    }
}
