package firstprogram.alieninvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by williamwei on 7/12/17.
 */

public class AlienManager {

    private static int maxNumber = 100; // length of the array.
    private static int size;  // number of live aliens.

    private static Alien[] aliens;
    private static Bitmap[] images1, images2;


    static {
        aliens = new Alien[maxNumber];
        images1 = new Bitmap[3];
        images2 = new Bitmap[3];

    }

    public AlienManager(Bitmap image11, Bitmap image21,
                        Bitmap image12, Bitmap image22,
                        Bitmap image13, Bitmap image23){

        images1[0] = image11;
        images1[1] = image12;
        images1[2] = image13;
        images2[0] = image21;
        images2[1] = image22;
        images2[2] = image23;

    }


    public static void draw(Canvas canvas){
        for(int i=0; i<size; i++){
            aliens[i].draw(canvas);
        }
    }


    public static boolean isHit(Torpedo torpedo){
        for(int i=0; i<size; i++){
            if(aliens[i].collision(torpedo)){
                return true;
            }
        }
        return false;
    }

    public static void update(Ship ship){

        if(Math.random()>0.95) create();

        // walk backward.
        for(int i=size-1; i>=0; i--){
            aliens[i].update(ship);
            if(aliens[i].getY()>GamePanel.HEIGHT){
                remove(i);
            }
        }

		/*
		// walk forward.
		for(int i=0; i<size; i++){
			aliens[i].move(ship);
			if(aliens[i].getY()>Coordinator.SCREEN_HEIGHT){
				remove(i);
				i--; // rewind the index
			}
		}
		 */

    }

    public static boolean collision(Torpedo torpedo){
        for(int i=0; i<size; i++){
            if(aliens[i].collision(torpedo)){
                return true;
            }
        }
        return false;
    }

    public static void create(){
        int x = (int)(GamePanel.WIDTH*Math.random());
        int vx = (int)(Math.random()*10-5);
        int vy = (int)(Math.random()*5 + 5);

        int imageIndex = (int)(Math.random()*images1.length);
        Alien alien;

        double rand = Math.random();

        if(rand>-1) alien = new SimpleAlien(x, -50, vx, vy, images1[imageIndex], images2[imageIndex]);
        else alien = new SimpleAlien(x, -50, vx, vy, images1[imageIndex], images2[imageIndex]);
        //else if(rand>=0.6) alien = new NastyAlien(x, -50, vx, vy, images1[imageIndex], images2[imageIndex]);
        //else if(rand>=0.4) alien = new MultiShootAlien(x, -50, vx, vy, images1[imageIndex], images2[imageIndex]);
        //else if(rand>=0.4) alien = new CloakAlien(x, -50, vx, vy, images1[imageIndex], images2[imageIndex]);
        //else alien = new RealNastyAlien(x, -50, vx, vy, images1[imageIndex], images2[imageIndex]);


        add(alien);
    }


    public static void add(Alien alien){
        if(size==maxNumber) return;
        aliens[size] = alien;
        size++;
    }


    public static void remove(int index){
        for(int i=index+1; i<size; i++){
            aliens[i-1] = aliens[i];
        }
        aliens[size-1] = null;
        size--;
    }


    public static void remove(Alien alien){
        for(int i=0; i<size; i++){
            if(aliens[i]==alien){
                remove(i);
                return;
            }
        }
    }

}
