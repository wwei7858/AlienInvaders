package firstprogram.alieninvaders;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public class Ship extends GameObject{
    private Bitmap image;
    //private Bitmap torpedoImage;
    private int score;
    private boolean playing;
    private long startTime;
    private boolean left, right, shoot;

    public Ship(Bitmap res) {

        x = GamePanel.WIDTH/2;
        y = GamePanel.HEIGHT - 100;
        score = 0;
        height = res.getHeight();
        width = res.getWidth();
        image = res;
        //torpedoImage = torpedoRes;
        startTime = System.nanoTime();
        dx = 20;

    }

    public boolean collision(Alien alien){
        if(Rect.intersects(this.getRectangle(), alien.getRectangle())){
            return true;
        }
        return false;
    }

    public boolean collision(Missile missile){
        if(Rect.intersects(this.getRectangle(), missile.getRectangle())){
            return true;
        }
        return false;
    }

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
            score++;
            startTime = System.nanoTime();
        }

        if(left){
            x -= dx;
            if(x<=0){
                x = 0;
            }
        }
        if(right){
            x += dx;
            if(x>=GamePanel.WIDTH-width){
                x  = GamePanel.WIDTH-width;
            }
        }
        if(shoot){
            TorpedoManager.add( new Torpedo(x+31, y));
        }



    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
    }
    public int getScore(){return score;}
    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
    public void resetScore(){score = 0;}


    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
}
