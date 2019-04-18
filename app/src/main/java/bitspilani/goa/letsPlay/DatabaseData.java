package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class DatabaseData extends Activity implements OnClickListener {

    //This would be the main activity for the database class
    //Would use the AddType class for all database work.

    EditText etname, etmail, etadd, etid;
    Button bsaved, bviewd, bedit, bdel, binfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databasedata);
        initial(this);

    }

    private void initial(DatabaseData databaseData) {
        // TODO Auto-generated method stub
        bsaved = (Button) findViewById(R.id.bsdbms);
        bviewd = (Button) findViewById(R.id.bvdbms);
        bedit = (Button) findViewById(R.id.beditent);
        bdel = (Button) findViewById(R.id.bdelent);
        binfo = (Button) findViewById(R.id.bgetinfo);

        etname = (EditText) findViewById(R.id.etnamedbms);
        etmail = (EditText) findViewById(R.id.etmaildbms);
        etadd = (EditText) findViewById(R.id.etadddbms);
        etid = (EditText) findViewById(R.id.etupdate);

        bsaved.setOnClickListener(this);
        bviewd.setOnClickListener(this);
        bedit.setOnClickListener(this);
        bdel.setOnClickListener(this);
        binfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.bsdbms) {
            boolean work = true;
            try {
                String name = etname.getText().toString();
                String mail = etmail.getText().toString();
                String add = etadd.getText().toString();
                AddType entry = new AddType(this);
                entry.open();
                entry.createentry(name, mail, add);
                entry.close();

            } catch (Exception e) {
                work = false;
                String err = e.toString();
                Dialog d = new Dialog(this);
                d.setTitle("Success");
                TextView tv = new TextView(this);
                tv.setText(err);
                d.setContentView(tv);
                d.show();
            } finally {
                if (work) {
                    //Just printing a dialog to user..Li8!!!
                    Dialog d = new Dialog(this);
                    d.setTitle("Success");
                    TextView tv = new TextView(this);
                    tv.setText("Data Added");
                    d.setContentView(tv);
                    d.show();
                }
            }
        } else if (v.getId() == R.id.bvdbms) {
            try {
                Intent in = new Intent("bitspilani.goa.letsPlay.DBMSVIEW");
                startActivity(in);
            } catch (Exception e) {
                String err = e.toString();
                Dialog d = new Dialog(this);
                d.setTitle("Success");
                TextView tv = new TextView(this);
                tv.setText(err);
                d.setContentView(tv);
                d.show();
            }
        } else if (v.getId() == R.id.beditent) {
            try {
                String name = etname.getText().toString();
                String mail = etmail.getText().toString();
                String add = etadd.getText().toString();
                String rowid = etid.getText().toString();
                long l = Long.parseLong(rowid);

                AddType at = new AddType(this);
                at.open();
                at.updateEntry(l, name, mail, add);
                at.close();
            } catch (Exception e) {
                String err = e.toString();
                Dialog d = new Dialog(this);
                d.setTitle("Success");
                TextView tv = new TextView(this);
                tv.setText(err);
                d.setContentView(tv);
                d.show();
            }
        } else if (v.getId() == R.id.bdelent) {
            try {
                String rowid = etid.getText().toString();
                long l = Long.parseLong(rowid);
                AddType del = new AddType(this);
                del.open();
                del.deleteEnt(l);
                del.close();
            } catch (Exception e) {
                String err = e.toString();
                Dialog d = new Dialog(this);
                d.setTitle("Success");
                TextView tv = new TextView(this);
                tv.setText(err);
                d.setContentView(tv);
                d.show();
            }
        } else {
            try {
                String rowid = etid.getText().toString();
                long l = Long.parseLong(rowid);
                AddType obj = new AddType(this);
                obj.open();
                String nam = obj.getName(l);
                String em = obj.getemail(l);
                String add = obj.getaddr(l);
                obj.close();

                etname.setText(nam);
                etmail.setText(em);
                etadd.setText(add);
            } catch (Exception e) {
                String err = e.toString();
                Dialog d = new Dialog(this);
                d.setTitle("Success");
                TextView tv = new TextView(this);
                tv.setText(err);
                d.setContentView(tv);
                d.show();
            }
        }
    }


}
