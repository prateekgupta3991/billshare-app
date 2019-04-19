package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

import bitspilani.goa.letsPlay.R;

public class TextPlay extends Activity implements View.OnClickListener {

    private Button tri;
    private EditText type;
    private ToggleButton chk;
    private TextView disp;

    private void initial() {
        // TODO Auto-generated method stub
        tri = (Button) findViewById(R.id.bt1);
        type = (EditText) findViewById(R.id.eT1);
        chk = (ToggleButton) findViewById(R.id.tB1);
        disp = (TextView) findViewById(R.id.tV1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        initial();
        //for setting a user mouse click on a button
        chk.setOnClickListener(this);
        tri.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tB1:
                if (!chk.isChecked()) {

                    type.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    type.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.bt1:
                String check = type.getText().toString();
                disp.setText(check);
                Random num = new Random();
                disp.setTextSize(num.nextInt(50));
                if (check.contentEquals("left")) {
                    disp.setGravity(Gravity.LEFT);
                } else if (check.contentEquals("right")) {
                    disp.setGravity(Gravity.RIGHT);
                } else if (check.contentEquals("center")) {
                    disp.setGravity(Gravity.CENTER);
                    disp.setTextColor(Color.rgb(num.nextInt(10), num.nextInt(20), num.nextInt(13)));
                }
                break;
        }
    }
}
