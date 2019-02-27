package firstprogram.alieninvaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by williamwei on 7/11/17.
 */

public class Torpedo extends GameObject {

    static Bitmap image;
    private boolean collided1, collided2;

    public Torpedo( int x, int y){


        this.x = x;
        this.y = y;
        height = image.getHeight();
        width = image.getWidth();
        vy = 7;
    }

    public void update(){
        y -= vy;

        if(AlienManager.collision(this)){
            collided1 = true;
            return;
        }

        if(MissileManager.collision(this)){
            collided2 = true;
            return;
        }


    }

    public void draw(Canvas canvas){
        if(collided1){
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            //canvas.drawCircle();
           //canvas.setColor(explosionColor1);
            //canvas.fillOval(x-explosionRadius1, y-explosionRadius1, 2*explosionRadius1, 2*explosionRadius1);
            TorpedoManager.remove(this);
        }
        else if(collided2){
            //canvas.setColor(explosionColor2);
            //canvas.fillOval(x-explosionRadius2, y-explosionRadius2, 2*explosionRadius2, 2*explosionRadius2);
            TorpedoManager.remove(this);
        }
        else {
            canvas.drawBitmap(image, x-width/2, y, null);
        }
    }



}
