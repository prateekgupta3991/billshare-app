package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class Flipp extends Activity implements OnClickListener{

	/*
	 * Useful for flipping between multiple items
	 * Can be used as a image viewer and flip between multiple images
	 */
	ViewFlipper flop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flipp);
		flop=(ViewFlipper) findViewById(R.id.viewflip);
		flop.setOnClickListener(this);
		flop.setFlipInterval(500);
		flop.startFlipping();
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		flop.showNext();
	}
	
}
