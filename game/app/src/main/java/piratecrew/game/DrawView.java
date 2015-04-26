package piratecrew.game;

/**
 * Created by mgaim_000 on 4/25/2015.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();

    public DrawView(Context context) {
        super(context);
    }
    private int squareSize = 150;
    private int posX = 500, posY = 40;
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(3);

        float heightWidthRatio = getHeight()/getWidth();

        canvas.drawRect(posX - squareSize, posY - squareSize,
                        posX + (squareSize), posY + squareSize, paint);



    }

    void setPosX(int posX){
        this.posX = posX;
    }
    void setPosY(int posY){
        this.posY = posY;
    }
}
