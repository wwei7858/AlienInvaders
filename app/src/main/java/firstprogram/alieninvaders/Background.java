package firstprogram.alieninvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by williamwei on 7/7/17.
 */

public class Background {

    private Bitmap image;
    private int x=0, y=0;

    public Background(Bitmap res)
    {
        image = res;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }





}
