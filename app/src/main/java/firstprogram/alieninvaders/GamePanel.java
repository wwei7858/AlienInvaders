package firstprogram.alieninvaders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by williamwei on 7/7/17.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private Background bg;
    private MainThread thread;
    private Ship ship;
    private TorpedoManager torpedoManager;
    private AlienManager alienManager;
    private MissileManager missileManger;
    public static boolean gameOver;

    public GamePanel(Context context)
    {
        super(context);


        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {


        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bg));
        ship = new Ship(BitmapFactory.decodeResource(getResources(), R.drawable.ship));
        torpedoManager = new TorpedoManager(BitmapFactory.decodeResource(getResources(), R.drawable.torpedo));
        alienManager = new AlienManager(BitmapFactory.decodeResource(getResources(), R.drawable.blue_alien1), BitmapFactory.decodeResource(getResources(), R.drawable.blue_alien2),
                                        BitmapFactory.decodeResource(getResources(), R.drawable.green_alien1), BitmapFactory.decodeResource(getResources(), R.drawable.green_alien2),
                                        BitmapFactory.decodeResource(getResources(), R.drawable.orange_alien1), BitmapFactory.decodeResource(getResources(), R.drawable.orange_alien2));
        missileManger = new MissileManager(BitmapFactory.decodeResource(getResources(), R.drawable.missile));


        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        boolean retry = true;
        int counter = 0;
        while(retry && counter<1000)
        {
            counter++;
            try{thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null;

            }catch(InterruptedException e){e.printStackTrace();}

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){


        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(!ship.getPlaying()) {
                ship.setPlaying(true);
            }
            if(ship.getPlaying()) {
                int x = (int) (event.getX() * WIDTH / getWidth());
                int y = (int) (event.getY() * HEIGHT / getHeight());

                if(x<GamePanel.WIDTH/2 && y>GamePanel.HEIGHT-100){
                   ship.setLeft(true);
                    System.out.println("Left");
                }

                if(x>GamePanel.WIDTH/2 && y>GamePanel.HEIGHT-100){
                    ship.setRight(true);
                    System.out.println("Right");
                }

                if(y<GamePanel.HEIGHT-100){
                    ship.setShoot(true);
                    System.out.println("Shoot");
                }

                System.out.println("x = " + x);
                System.out.println("y = " + y);
            }
            return true;
        }
        if(event.getAction()==MotionEvent.ACTION_UP) {
            ship.setLeft(false);
            ship.setRight(false);
            ship.setShoot(false);
            return true;
        }

        return super.onTouchEvent(event);
    }


    public void update(){
        if(ship.getPlaying()){
            ship.update();
            torpedoManager.update();
            alienManager.update(ship);
            missileManger.update(ship);
        }

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {

        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);

        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            ship.draw(canvas);
            torpedoManager.draw(canvas);
            alienManager.draw(canvas);
            missileManger.draw(canvas);
            this.drawText(canvas);
            canvas.restoreToCount(savedState);
        }

        if(gameOver){
            ship.setPlaying(false);
            this.drawText(canvas);
            gameOver = false;

            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void drawText(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        //canvas.drawText("DISTANCE: " + (player.getScore()*3), 10, HEIGHT - 10, paint);
        //canvas.drawText("Current Score: " + player.getScore(), WIDTH/3, HEIGHT - 10, paint);
        //canvas.drawText("BEST: " + best, WIDTH - 215, HEIGHT - 10, paint);

        if(gameOver && !ship.getPlaying()){
            Paint paint1 = new Paint();
            paint1.setColor(Color.WHITE);
            paint1.setTextSize(40);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("GAME OVER", WIDTH/2-90, HEIGHT/2, paint1);

        }

        if(!gameOver && !ship.getPlaying())
        {
            Paint paint1 = new Paint();
            paint1.setColor(Color.WHITE);
            paint1.setTextSize(40);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("PRESS TO START", WIDTH/2-110, HEIGHT/2, paint1);

            paint1.setTextSize(20);
            canvas.drawText("PRESS HERE TO SHOOT", WIDTH/2-100, HEIGHT/2 + 70, paint1);
            canvas.drawText("PRESS HERE TO GO LEFT", 100, HEIGHT-60, paint1);
            canvas.drawText("PRESS HERE TO GO RIGHT", WIDTH/2+175, HEIGHT-60, paint1);
        }
    }
}
