package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Email extends Activity implements OnClickListener {

    private EditText senderadd, recadd, pernam, sub, pwd, msg;
    private String send, rec, nam, su, pw, gsm;
    private Button sendemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mail);
        initialvar();
        sendemail.setOnClickListener(this);
    }

    private void initialvar() {
        // TODO Auto-generated method stub
        senderadd = (EditText) findViewById(R.id.editText1);
        recadd = (EditText) findViewById(R.id.editText4);
        pernam = (EditText) findViewById(R.id.editText3);
        sub = (EditText) findViewById(R.id.editText2);
        pwd = (EditText) findViewById(R.id.editText5);
        msg = (EditText) findViewById(R.id.editText6);
        sendemail = (Button) findViewById(R.id.butclk);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        send = senderadd.getText().toString();
        rec = recadd.getText().toString();
        su = sub.getText().toString();
        nam = pernam.getText().toString();
        pw = pwd.getText().toString();
        gsm = msg.getText().toString();
        String mailadd[] = {send};
        Intent emailsend = new Intent(android.content.Intent.ACTION_SEND);
        emailsend.putExtra(android.content.Intent.EXTRA_EMAIL, mailadd);
        emailsend.putExtra(android.content.Intent.EXTRA_SUBJECT, su);
        emailsend.setType("plain/text");
        emailsend.putExtra(android.content.Intent.EXTRA_TEXT, gsm);
        startActivity(emailsend);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }


}
