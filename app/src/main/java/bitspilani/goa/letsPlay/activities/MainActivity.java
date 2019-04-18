package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bitspilani.goa.letsPlay.R;

public class MainActivity extends Activity {

    private int count = 0;
    private TextView tv;
    private Button badd, bsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        badd = (Button) findViewById(R.id.badd1);
        tv = (TextView) findViewById(R.id.tv1);
        bsub = (Button) findViewById(R.id.bsub1);
        badd.setOnClickListener(arg -> {
            // TODO Auto-generated method stub
            count++;
            tv.setText("The count is " + count);
        });
        bsub.setOnClickListener(arg0 -> {
            // TODO Auto-generated method stub
            count--;
            tv.setText("The count is " + count);
        });
    }
}
