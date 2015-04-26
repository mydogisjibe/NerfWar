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
    private int pos1X = 500, pos1Y = 40,pos2X = 500,pos2Y = 500;

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(3);

        float heightWidthRatio = getHeight()/getWidth();

        canvas.drawRect(pos1X - squareSize, pos1Y - squareSize,
                        pos1X + (squareSize), pos1Y + squareSize, paint);

        canvas.drawRect(pos2X - squareSize, pos2Y - squareSize, pos2X + (squareSize),
                        pos2Y + squareSize, paint);




    }

    void setPos1X(int pos1X){
        this.pos1X = pos1X;
    }
    void setPos2X(int pos2X) {this.pos2X = pos2X;}
    void setPos1Y(int pos1Y){
        this.pos1Y = pos1Y;
    }
    void setPos2Y(int pos2Y) {this.pos2Y = pos2Y;}
}
