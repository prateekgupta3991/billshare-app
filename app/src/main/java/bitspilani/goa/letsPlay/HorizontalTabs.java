package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class HorizontalTabs extends Activity implements OnClickListener {

    Button newtab, start, stop;
    TabHost th;
    TextView timeresult, bad;
    long starttime, stoptime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontaltabs);

        /*
         * setting up tabs
         * screen has multiple tabhosts
         * each tabhost has tabspec
         * each tabspec has id of linear layout of the corresponding tab
         * in the xml file
         */
        initial(this);
        bad.setText("Click for new tab");
        newtab.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);

    }

    private void initial(HorizontalTabs horizontalTabs) {
        // TODO Auto-generated method stub
        newtab = (Button) findViewById(R.id.badd);
        start = (Button) findViewById(R.id.bstart);
        stop = (Button) findViewById(R.id.bstop);
        timeresult = (TextView) findViewById(R.id.tvtimeres);
        bad = (TextView) findViewById(R.id.tvadd);
        starttime = 0;

        th = (TabHost) findViewById(R.id.tabhost);
        th.setup();
        TabSpec ts = th.newTabSpec("tabspec 1");
        ts.setContent(R.id.tab1);
        ts.setIndicator("StopWatch");
        th.addTab(ts);
        ts = th.newTabSpec("tabspec 2");
        ts.setContent(R.id.tab2);
        ts.setIndicator("Something");
        th.addTab(ts);
        ts = th.newTabSpec("tabspec 3");
        ts.setContent(R.id.tab3);
        ts.setIndicator("Add a tab");
        th.addTab(ts);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.bstart:
                starttime = System.currentTimeMillis();
                break;

            case R.id.bstop:
                stoptime = System.currentTimeMillis();
                if (starttime != 0) {
                    long timediff = stoptime - starttime;
                    int millis = (int) timediff;
                    int second = (int) timediff / 1000;
                    int minutes = second / 60;
                    millis = millis % 100;
                    second = second % 60;
                    timeresult.setText(String.format("Min:Sec:Millisec - %2d : %2d : %2d ", minutes, second, millis));
                } else {
                    timeresult.setText("0");
                    starttime = 0;
                }
                break;

            case R.id.badd:
                //for adding a new tab we need to define a new tabspec on a same tabhost.
                //tabspec is basically setting content and indicator.
                TabSpec ts1 = th.newTabSpec("tab2");
                ts1.setContent(new TabHost.TabContentFactory() {

                    @Override
                    public View createTabContent(String tag) {
                        // TODO Auto-generated method stub
                        TextView tv = new TextView(HorizontalTabs.this);
                        tv.setText("New tab created");
                        return (tv);
                        //Textview is being created without adding anything to the xml file.
                    }
                });
                ts1.setIndicator("New");
                th.addTab(ts1);
                bad.setText("New tab created.Click to create another tab!");
                break;
        }
    }

}
