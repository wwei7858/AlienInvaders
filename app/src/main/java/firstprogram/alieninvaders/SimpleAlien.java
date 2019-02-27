package firstprogram.alieninvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

/**
 * Created by williamwei on 7/12/17.
 */

public class SimpleAlien extends Alien{

    protected int numOfDraw;

    static {

    }


    public SimpleAlien(int x, int y, int vx, int vy, Bitmap image1, Bitmap image2){
        super(x, y, vx, vy, image1, image2);
    }


    public int getX(){ return x; }
    public int getY(){ return y; }



    public boolean collision(Torpedo torpedo){

        // will access alien's x, y, w, h.
        // will access torpedo's x, y, w, h.
        // check the overlapping between two rectangles.
        // if overlapped (collided),
        // 1. it has to die.
        // 2. return true.

        if(Rect.intersects(this.getRectangle(), torpedo.getRectangle())){
            AlienManager.remove(this);
            return true;
        }

        return false;
    }


    public void update(Ship ship){
        x += vx;
        y += vy;

        shootMissile();

        if(ship.collision(this)){
            collided = true;
        }

    }


    public void shootMissile(){
       if(Math.random()<0.05) MissileManager.add( new Missile(x, y+10, 2*vy) );
    }


    public void draw(Canvas canvas){

        if(collided){
            //canvas.setColor(explosionColor);
            //canvas.fillOval(x-explosionRadius, y-explosionRadius, 2*explosionRadius, 2*explosionRadius);
            GamePanel.gameOver = true;
        }
        else {
            numOfDraw++;
            if(numOfDraw%6<3) canvas.drawBitmap(image1, x-width/2, y-height, null);
            else canvas.drawBitmap(image2, x-width/2, y-height, null);
        }
    }

}
