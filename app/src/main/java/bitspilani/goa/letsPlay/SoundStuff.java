package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;

public class SoundStuff extends Activity implements OnClickListener,OnLongClickListener{

	SoundPool sp;
	int exp1=0,exp2=0;
	int clickcount=0;
	MediaPlayer mp;
	
	//soundpool help you to play multiple streams simultaneously
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View v=new View(this);
		v.setOnClickListener(this);
		v.setOnLongClickListener(this);
		setContentView(v);
		sp=new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		exp1=sp.load(this, R.raw.laugh, 1);
		exp2=sp.load(this, R.raw.applause, 1);	
		mp=MediaPlayer.create(this, R.raw.backgroundscore);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
			if(exp1!=0)
				sp.play(exp1, 1, 1, 0, 0, 1);
	}

	@Override
	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub
		clickcount++;
		if(clickcount==1){
			mp.start();
		}		
		else
		{
			mp.release();
			finish();
		}
		return false;
	}
	
}
