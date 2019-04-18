package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bitspilani.goa.letsPlay.R;

public class SharedPrefData extends Activity implements OnClickListener {

    public static String filename = "MySharedData";
    private EditText getd;
    private TextView setd;
    private Button sve, lad;
    private SharedPreferences dat;

    /*
     * shared preferences is used for data to be stored in
     * the memory andcan be used for further references
     * data is stored in a file.
     * This class stores only single variable value
     * DataToInternal stores full file of data input by user
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdatafrmsharedpref);
        initial(this);

        dat = getSharedPreferences(filename, 0);
    }

    private void initial(SharedPrefData sharedPrefData) {
        // TODO Auto-generated method stub
        getd = (EditText) findViewById(R.id.etspref);
        setd = (TextView) findViewById(R.id.tvspref);
        sve = (Button) findViewById(R.id.bspref1);
        lad = (Button) findViewById(R.id.bspref2);

        sve.setOnClickListener(this);
        lad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            //save button
            case R.id.bspref1:
                String strd = getd.getText().toString();
                //SharedPreferences editor
                Editor editor = dat.edit();
                editor.putString("SharedString", strd);
                editor.commit();
                //commit at the end of editing
                break;
            //load button
            case R.id.bspref2:
                /*
                 * we need to set the SharedPref variable again and get the
                 * string into another variable
                 */
                dat = getSharedPreferences(filename, 0);
                String gotdata = dat.getString("SharedString", "String not loaded");
                setd.setText(gotdata);
                break;
        }

    }

}
