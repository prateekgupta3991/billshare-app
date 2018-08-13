package bitspilani.goa.letsPlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class UsedByGrafix extends View{

	//how to do animation using bitmaps
	float mody,modx;
	Bitmap bmp;
	Typeface tf;
	
	public UsedByGrafix(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		bmp=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		mody=0;
		modx=0;
		//typeface is used for using custom font in an activity
		tf.createFromAsset(context.getAssets(), "RobotoCondensed-Italic.ttf");
	}
	
	//method for drawing on background,images etc.
	@Override
	protected void onDraw(Canvas can) {
		// TODO Auto-generated method stub
		super.onDraw(can);
		can.drawColor(Color.WHITE);
		
		Paint tp=new Paint();
		tp.setARGB(50,100, 13, 7);
		tp.setTextAlign(Align.CENTER);
		tp.setTypeface(tf);
		tp.setTextSize(50);
		can.drawText("Animation", can.getWidth()/2, 100, tp);
		
		can.drawBitmap(bmp, modx, mody, null );
		float ritlmt=can.getWidth()-bmp.getWidth();
		float lowlmt=can.getHeight()-bmp.getHeight();
		int flagx=0;
		int flagy=0;
		
		if(flagx==0)
		{
		if(modx < ritlmt )
		{
			if(flagy==0)
			{
			if(mody < lowlmt)
			{
				mody+=5;
				modx+=5;
				if(mody >= lowlmt-4)
					flagy=1;
			}
			}
			else if(flagy==1)
			{
			if(mody >= 0)
			{
				mody-=5;
				modx+=5;
				if(mody == 0)
					flagy=0;
			}
			}
			if(modx >= ritlmt-4)
				flagx=1;
		}
		}
		if(flagx==1)
		{
		if(modx >= 0 )
			{
			if(flagy==0)
			{
			if(mody < lowlmt)
			{
				mody+=5;
			    modx-=5;
				if(mody >= lowlmt)
					flagy=1;
			}
			}
			if(flagy==1)
			{
			if(mody >= 0)
			{
				mody-=5;
				modx-=5;
				if(mody == 0)
					flagy=0;
			}
			}
			if(modx==0)
				flagx=0;
			
			}		
		}
		//for painting something in the canvas thru which
		//android passes
		Rect mid=new Rect();
		mid.set(0, can.getHeight()/2,can.getWidth() ,200);
		Paint pnt=new Paint();
		pnt.setColor(Color.GRAY);
		can.drawRect(mid, pnt);
		invalidate();
	}	
}
