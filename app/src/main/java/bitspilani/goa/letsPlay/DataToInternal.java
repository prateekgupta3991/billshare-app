package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataToInternal extends Activity implements OnClickListener {

    EditText getd;
    TextView setd;
    Button sve, lad;
    FileOutputStream fos;
    String FILENAME = "Internal Storage";

    /*
     * for using internal memory for data storage
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdatafrmsharedpref);

        try {
            initial(this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void initial(DataToInternal dataToInternal) throws IOException {
        // TODO Auto-generated method stub
        getd = (EditText) findViewById(R.id.etspref);
        setd = (TextView) findViewById(R.id.tvspref);
        sve = (Button) findViewById(R.id.bspref1);
        lad = (Button) findViewById(R.id.bspref2);

        sve.setOnClickListener(this);
        lad.setOnClickListener(this);
        fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        fos.close();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            //save button
            case R.id.bspref1:
                String str = getd.getText().toString();
		/*	File f=new File(FILENAME);
			try {
				fos=new FileOutputStream(f);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
                try {
                    fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(str.getBytes());
                    fos.close();
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            //load button
            case R.id.bspref2:
                new LoadStuff().execute(FILENAME);
                break;
        }
    }

    /*
     * just for dividing the workload
     * creating a separate class for loading the saved stuff
     */
    public class LoadStuff extends AsyncTask<String, Integer, String> {

        /*
         * Basically we set a progress dialog variable and the
         * set the style of progress bar in onpreexecute method
         * set the  progress value updation in doinbackground method
         */
        ProgressDialog dial;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            /*
             * used for activities before start of activity
             * adds dramatic effect
             */
            super.onPreExecute();
            dial = new ProgressDialog(DataToInternal.this);
            dial.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dial.setMax(100);
            dial.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            dial.incrementProgressBy(values[0]);
        }

        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            String data = null;
            FileInputStream fis = null;

            for (int i = 0; i < 25; i++) {
                //referencing progressupdate method
                publishProgress(4);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            dial.dismiss();
            try {
                fis = openFileInput(FILENAME);
                byte[] arr = new byte[fis.available()];//getting number of bytes in fis
                while (fis.read(arr) != -1) ;//reading the bytes in fis
                {
                    data = new String(arr);
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            setd.setText(result);
        }


    }
}
