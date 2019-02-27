package firstprogram.alieninvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by williamwei on 7/13/17.
 */

public class Missile extends GameObject {

    static Bitmap image;
    private boolean collided;

    public Missile( int x, int y, int vy){


        this.x = x;
        this.y = y;
        height = image.getHeight();
        width = image.getWidth();
        this.vy = vy;
    }


    public boolean collision(Torpedo torpedo){
        if(Rect.intersects(this.getRectangle(), torpedo.getRectangle())){

            MissileManager.remove(this);
            return true;
        }

        return false;
    }


    public void update(Ship ship){
        y += vy;

        if(ship.collision(this)){
            collided = true;

        }
    }


    public void draw(Canvas canvas){
        if(collided){
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            //canvas.drawCircle();
            //canvas.setColor(explosionColor1);
            //canvas.fillOval(x-explosionRadius1, y-explosionRadius1, 2*explosionRadius1, 2*explosionRadius1);
            MissileManager.remove(this);
            GamePanel.gameOver = true;

        }
        else {
            canvas.drawBitmap(image, x-width/2, y, null);
        }
    }



}


