package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {

    int count = 0;
    TextView tv;
    Button badd, bsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        badd = (Button) findViewById(R.id.badd1);
        tv = (TextView) findViewById(R.id.tv1);
        bsub = (Button) findViewById(R.id.bsub1);
        badd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg) {
                // TODO Auto-generated method stub
                count++;
                tv.setText("The count is " + count);
            }
        });
        bsub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                count--;
                tv.setText("The count is " + count);
            }
        });
    }
}
