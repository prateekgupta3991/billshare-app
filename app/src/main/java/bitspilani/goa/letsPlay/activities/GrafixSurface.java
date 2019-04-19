package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import bitspilani.goa.letsPlay.R;

public class GrafixSurface extends Activity implements OnTouchListener {

    private UsedByGrafixSurface obj;
    private float x, y, xmove, ymove;
    private float sx, sy, fx, fy;

    /* The problem with Grafix class is that it is handling both the UI and
     * the graphics which makes it a little bit slow.
     * So to avoid such a situation we are distributing it into two parts
     * as surfaceview and the graphics part.
     * This implementation explains the concept.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        obj = new UsedByGrafixSurface(this);
        obj.setOnTouchListener(this);
        //for touch sensitivity similar to click sensitivity
        x = 0;
        sx = 0;
        fx = 0;
        xmove = 0;
        y = 0;
        sy = 0;
        fy = 0;
        ymove = 0;

        setContentView(obj);
    }

    /*
     * pause and resume method in activity extended class
     * but this class has content view of UsedByGrafixSurface so we need to
     * define methods over there and try to stop the running thread over
     * there ;
     */

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        obj.pause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        obj.resume();
    }

    /*
     * implementing action when screen is touched
     */
    @Override
    public boolean onTouch(View v, MotionEvent me) {
        // TODO Auto-generated method stub

        //setting fps=frame per second
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        x = me.getX();
        y = me.getY();

        switch (me.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pathstart(x, y);
                break;
            case MotionEvent.ACTION_UP:
                pathend(x, y);
                break;
        }
        return true;

        /*
         * Important aspect of touch listener
         * the touch listener can be used for single touch as well as
         * continuous touch.
         * The boolean value returned by the Touch method if
         * False - is used for single touch event data fetching
         * True - is used for continuous screen touch event data fetching
         */
    }

    private void pathend(float x2, float y2) {
        // TODO Auto-generated method stub
        fx = x2;
        fy = y2;
        xmove = fx;
        ymove = fy;
    }

    private void pathstart(float x2, float y2) {
        // TODO Auto-generated method stub
        sx = x2;
        sy = y2;
    }

    private class UsedByGrafixSurface extends SurfaceView implements Runnable {

        /* Runnable has a method run() that is executed whenever a new
         * custom thread is created in a class.
         */

        SurfaceHolder hold;
        Thread trd = null;
        boolean running = false;
        Bitmap bmp, bmp2;

        public UsedByGrafixSurface(Context context) {
            // TODO Auto-generated constructor stub
            super(context);

            /*
             * SurfaceHolder contains interfaces which gives the option to control the surface attributes
             * like size,pixel,etc.
             * can work on multiple threads on the surface
             */
            hold = getHolder();
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.androidvlc);

            /*trd=new Thread(this);
             * trd.start();
             * Thread is started in the constructor but only once the
             * constructor is called.
             * So we put it in the resume method which is invoked whenever
             * activity is paused.
             */
        }

        //pauses the activity and destroys running thread
        public void pause() {
            running = false;
            while (true) {
                try {
                    trd.join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
            trd = null;
        }

        //resumes the activity and starts a new custom thread
        public void resume() {
            running = true;
            trd = new Thread(this);
            trd.start();
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (running) {
                if (hold.getSurface().isValid()) {
                    Canvas can = hold.lockCanvas();
                    can.drawRGB((int) x, (int) y, (int) (x + y));
                    if (x != 0 && y != 0) {
                        can.drawBitmap(bmp, x - (bmp.getWidth() / 2), y - (bmp.getHeight() / 2), null);
                        can.drawBitmap(bmp2, y - (bmp.getHeight() / 2), x - (bmp.getWidth() / 2), null);
                        Paint pt = new Paint();
                        pt.setColor(Color.BLACK);
                        pt.setStrokeWidth(4);
                        can.drawLine(fx, fy, sx, sy, pt);
                    }
                    hold.unlockCanvasAndPost(can);
                } else
                    continue;
            }
        }

    }
}
