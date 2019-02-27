package firstprogram.alieninvaders;

import android.app.Activity;
import android.graphics.Rect;

public abstract class GameObject{
    protected int x;
    protected int y;
    protected int vy;
    protected int vx;
    protected int dx;
    protected static int width;
    protected static int height;

    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public static int getHeight()
    {
        return height;
    }
    public static int getWidth()
    {
        return width;
    }
    public Rect getRectangle()
    {
        return new Rect(x, y, x+width, y+height);
    }

}