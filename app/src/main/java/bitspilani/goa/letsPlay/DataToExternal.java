package bitspilani.goa.letsPlay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataToExternal extends Activity implements OnItemSelectedListener, OnClickListener {

    /*
     * for interaction of data on/out external memory like sd cards etc
     * some important equired variables are state of sdcard
     * environment of sdcard
     */

    TextView canread, canwrite;
    String state;
    Boolean canr, canw;
    Spinner spin;
    String path[] = {"Music", "Pictures", "Downloads"};
    File pathto = null;
    File savetofile = null;
    Button confirm, save;
    EditText srcfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datatoexternal);
        canread = (TextView) findViewById(R.id.tvread);
        canwrite = (TextView) findViewById(R.id.tvwrite);
        confirm = (Button) findViewById(R.id.bconf);
        save = (Button) findViewById(R.id.bsave);
        srcfile = (EditText) findViewById(R.id.etsave);
        confirm.setOnClickListener(this);
        save.setOnClickListener(this);

        canwrite.setText("false");
        canread.setText("false");
        canw = canr = false;

        checkmemstate();
        ArrayAdapter<String> adap = new ArrayAdapter<String>(DataToExternal.this, android.R.layout.simple_spinner_item, path);
        spin = (Spinner) findViewById(R.id.spinner1);
        spin.setAdapter(adap);

        spin.setOnItemSelectedListener(this);
    }

    public void checkmemstate() {
        state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            //read and write
            canwrite.setText("true");
            canread.setText("true");
            canw = canr = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            //read only
            canwrite.setText("false");
            canread.setText("true");
            canr = true;
            canw = false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        int pos = spin.getSelectedItemPosition();
        if (pos == 0) {
            pathto = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        } else if (pos == 1) {
            pathto = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        } else {
            pathto = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.bsave) {
            String str = srcfile.getText().toString();
            savetofile = new File(pathto, str);
            checkmemstate();
            if (canr == true && canw == true) {

                //if dir does not exists then create a dir
                pathto.mkdirs();

                try {
                    @SuppressLint("ResourceType") InputStream is = getResources().openRawResource(R.drawable.wtd);
                    OutputStream os = new FileOutputStream(savetofile);
                    byte[] array = new byte[is.available()];
                    is.read(array);
                    os.write(array);
                    os.close();

                    //Toast are for showing msg to the user on an action completion
                    Toast ts = Toast.makeText(DataToExternal.this, "File saved", Toast.LENGTH_LONG);
                    ts.show();

                    //update file for the user to use
                    //just for efficiency
                    MediaScannerConnection.scanFile(DataToExternal.this,
                            new String[]{path.toString()}, null,
                            (arg0, arg1) -> {
                                // TODO Auto-generated method stub
                                Toast t = Toast.makeText(DataToExternal.this, "Scan complete", Toast.LENGTH_SHORT);
                                t.show();
                            });

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            save.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


}
