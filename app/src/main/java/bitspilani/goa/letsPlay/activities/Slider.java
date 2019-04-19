package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

import bitspilani.goa.letsPlay.R;

public class Slider extends Activity implements OnClickListener, OnCheckedChangeListener, OnDrawerOpenListener, OnDrawerCloseListener {

    private Button b1, b2, b3, b4;
    private CheckBox chb;
    private SlidingDrawer sd;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider);
        initial(this);
    }


    private void initial(Slider slider) {
        // TODO Auto-generated method stub
        b1 = (Button) findViewById(R.id.b1slider);
        b2 = (Button) findViewById(R.id.b2slider);
        b3 = (Button) findViewById(R.id.b3slider);
        b4 = (Button) findViewById(R.id.b4slider);
        chb = (CheckBox) findViewById(R.id.cbslider);
        sd = (SlidingDrawer) findViewById(R.id.sdslider1);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        chb.setOnCheckedChangeListener(this);
        sd.setOnDrawerOpenListener(this);
        sd.setOnDrawerCloseListener(this);
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.b1slider)
            sd.open();
            //	else if( v.getId() == R.id.b2slider )

        else if (v.getId() == R.id.b3slider)
            sd.toggle();
        else if (v.getId() == R.id.b4slider)
            sd.close();
    }

    @Override
    public void onCheckedChanged(CompoundButton op, boolean yn) {
        // TODO Auto-generated method stub
        if (op.isChecked()) {
            sd.lock();
        } else {
            sd.unlock();
        }
    }


    @Override
    public void onDrawerOpened() {
        // TODO Auto-generated method stub
        mp = MediaPlayer.create(this, R.raw.backgroundscore);
        mp.start();
    }


    @Override
    public void onDrawerClosed() {
        // TODO Auto-generated method stub
        mp.release();
    }

}
