package firstprogram.alieninvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

/**
 * Created by williamwei on 7/11/17.
 */

public abstract class Alien extends GameObject{

    protected Bitmap image1, image2;
    protected boolean collided;
    protected static Color explosionColor;
    protected static int explosionRadius;

    public Alien(int x, int y, int vx, int vy, Bitmap image1, Bitmap image2){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

        this.image1 = image1;
        this.image2 = image2;

        width = image1.getWidth();
        height = image1.getHeight();
    }

    public boolean collision(Torpedo torpedo){
        if(Rect.intersects(this.getRectangle(), torpedo.getRectangle())){
            AlienManager.remove(this);
            return true;
        }

        return false;
    }

    public abstract void update(Ship ship);

    public abstract void shootMissile();

    public abstract void draw(Canvas canvas);

}
