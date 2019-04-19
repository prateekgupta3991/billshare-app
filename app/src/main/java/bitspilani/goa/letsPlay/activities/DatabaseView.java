package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import bitspilani.goa.letsPlay.R;

public class DatabaseView extends Activity {

    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbmsview);
        info = (TextView) findViewById(R.id.tvinfodbms);
        AddType obj = new AddType(this);
        obj.open();
        String data = obj.getD();
        obj.close();
        System.out.println(data);
        info.setText(data);

    }

}
