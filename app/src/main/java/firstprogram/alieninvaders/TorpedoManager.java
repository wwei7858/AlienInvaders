package firstprogram.alieninvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by williamwei on 7/12/17.
 */

public class TorpedoManager {
    private static int maxNumber = 100; // length of the array.
    private static int size;  // number of live torpedoes.


    private static Torpedo[] torpedoes;


    public TorpedoManager(Bitmap image){
        Torpedo.image = image;
    }

    static {
        torpedoes = new Torpedo[maxNumber];
    }


    public static void draw(Canvas canvas){
        for(int i=0; i<size; i++){
            torpedoes[i].draw(canvas);
        }
    }


    public static void update(){

        // walk backward.
        for(int i=size-1; i>=0; i--){
            torpedoes[i].update();
            if(torpedoes[i].getY()<0){
                remove(i);
            }
        }

		/*
		// walk forward.
		for(int i=0; i<size; i++){
			torpedoes[i].move();
			if(torpedoes[i].getY()<0){
				remove(i);
				i--; // rewind the index
			}
		}
		*/

    }


    public static void add(Torpedo torpedo){
        if(size==maxNumber) return;
        torpedoes[size] = torpedo;
        size++;
    }


    public static void remove(int index){
        for(int i=index+1; i<size; i++){
            torpedoes[i-1] = torpedoes[i];
        }
        torpedoes[size-1] = null;
        size--;
    }


    public static void remove(Torpedo torpedo){
        for(int i=0; i<size; i++){
            if(torpedoes[i]==torpedo){
                remove(i);
                return;
            }
        }
    }

}
