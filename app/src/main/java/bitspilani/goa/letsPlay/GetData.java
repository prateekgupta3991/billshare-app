package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class GetData extends Activity implements View.OnClickListener {

    EditText et;
    TextView tv;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        initial();
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    private void initial() {
        // TODO Auto-generated method stub
        et = (EditText) findViewById(R.id.etget);
        tv = (TextView) findViewById(R.id.tv1get);
        b1 = (Button) findViewById(R.id.bt1get);
        b2 = (Button) findViewById(R.id.bt2get);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.bt1get) {
            String str = et.getText().toString();
            Bundle dataflow = new Bundle();
            dataflow.putString("key", str);
            Intent sa = new Intent(GetData.this, SendData.class);
            sa.putExtras(dataflow);
            startActivity(sa);
        } else if (v.getId() == R.id.bt2get) {
            Intent safr = new Intent(GetData.this, SendData.class);
            startActivityForResult(safr, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle res = data.getExtras();
            String s = res.getString("result");
            tv.setText(s);
        }
    }

}
