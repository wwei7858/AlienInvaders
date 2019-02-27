package firstprogram.alieninvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by williamwei on 7/14/17.
 */

public class MissileManager {

    private static int maxNumber = 100; // length of the array.
    private static int size;  // number of live missiles.

    private static Missile[] missiles;


    static {
        missiles = new Missile[maxNumber];
    }

    public MissileManager(Bitmap image){
        Missile.image = image;
    }


    public static void draw(Canvas canvas){
        for(int i=0; i<size; i++){
            missiles[i].draw(canvas);
        }
    }


    public static boolean collision(Torpedo torpedo){
        for(int i=0; i<size; i++){
            if(missiles[i].collision(torpedo)){
                return true;
            }
        }
        return false;
    }



    public static void update(Ship ship){

        // walk backward.
        for(int i=size-1; i>=0; i--){
            missiles[i].update(ship);
            if(missiles[i].getY()>GamePanel.HEIGHT){
                remove(i);
            }
        }

		/*
		// walk forward.
		for(int i=0; i<size; i++){
			missiles[i].move(ship);
			if(missiles[i].getY()>Coordinator.SCREEN_HEIGHT){
				remove(i);
				i--; // rewind the index
			}
		}
		*/

    }


    public static void add(Missile missile){
        if(size==maxNumber) return;
        missiles[size] = missile;
        size++;
    }


    public static void remove(int index){
        for(int i=index+1; i<size; i++){
            missiles[i-1] = missiles[i];
        }
        missiles[size-1] = null;
        size--;
    }


    public static void remove(Missile missile){
        for(int i=0; i<size; i++){
            if(missiles[i]==missile){
                remove(i);
                return;
            }
        }
    }

}
