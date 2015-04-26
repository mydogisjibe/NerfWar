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
    public int squareX1 = 50,squareY1 = 50;
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(squareX1,squareY1, 500, 500, paint);
        paint.setStrokeWidth(2);
        paint.setColor(Color.CYAN);
        canvas.drawRect(squareX1,squareY1, 500, 500, paint );



    }


}
