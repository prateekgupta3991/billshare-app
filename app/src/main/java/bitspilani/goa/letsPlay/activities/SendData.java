package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import bitspilani.goa.letsPlay.R;

public class SendData extends Activity implements View.OnClickListener, android.widget.RadioGroup.OnCheckedChangeListener {

    private TextView tv1, tv2;
    private Button bn1;
    private RadioGroup rg;
    private String str, senddata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initial();
		
		/*Bundle gotbun=new Bundle();
		gotbun=getIntent().getExtras();
		str=gotbun.getString("key");
		tv1.setText(str);
		We can't use two bundles in one activity so using the method of
		SharedPreferences*/

        SharedPreferences getprefval = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String st = getprefval.getString("name", "This app is ..");
        String values = getprefval.getString("list", "4");
        if (values.contentEquals("1")) {
            tv1.setText(st);
        }

        bn1.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);
    }

    //In case of radiogroup we do not need to set each radiobutton
    //only radiogroup neweds to be set
    //For detection of which radiobutton pressed,we can get the button id
    private void initial() {
        // TODO Auto-generated method stub
        tv1 = (TextView) findViewById(R.id.tv1send);
        tv2 = (TextView) findViewById(R.id.tv2send);
        bn1 = (Button) findViewById(R.id.bsend);
        rg = (RadioGroup) findViewById(R.id.rgsend);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        //passing the radiobutton result from this to another class via a bundle
        //important part is that this activity is made finished beacause
        //we are not going to use this activity further.
        //Thus to pass the intent containing bundle we use "setResult" with
        //params as (RESULT_OK,i).
        //RESULT_OK tell that the operation performed in this class is
        //successful because this class was called as startActivityForResult
        //and when returned to that class we need to provide param for operation
        //success. Intent is passed in this.
        //startActivity is not used bcoz we need to pass the result for which
        //the calling class called this activity.
        Bundle pass = new Bundle();
        pass.putString("result", senddata);
        Intent i = new Intent(SendData.this, GetData.class);
        i.putExtras(pass);
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup rg, int id) {
        // TODO Auto-generated method stub
        switch (id) {
            case (R.id.rb1send):
                senddata = "Good ans";
                break;
            case (R.id.rb2send):
                senddata = "ok ans";
                break;
            case (R.id.rb3send):
                senddata = "worst ans";
                break;
        }
        tv2.setText(senddata);
    }
}
