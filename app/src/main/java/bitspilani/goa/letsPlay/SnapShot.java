package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class SnapShot extends Activity implements View.OnClickListener {

    final static int camdata = 0;
    private ImageButton ib;
    private Button bton;
    private ImageView iv;
    private Intent intention;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snap);
        initialcam();
        //fixing for initialising an initial value for bmp via inputstream
        InputStream i = getResources().openRawResource(R.drawable.ic_launcher);
        bmp = BitmapFactory.decodeStream(i);
        ib.setOnClickListener(this);
        bton.setOnClickListener(this);

    }

    private void initialcam() {
        // TODO Auto-generated method stub
        ib = (ImageButton) findViewById(R.id.iB1);
        iv = (ImageView) findViewById(R.id.iV1);
        bton = (Button) findViewById(R.id.bsnap1);

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case (R.id.bsnap1):
                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case (R.id.iB1):
                intention = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intention, camdata);
                break;
        }
    }

    //We use startACtivityForResult when we need to get some result
    //when starting an activity for which confirmation is required
    //after the initiation of the activity
    //onActivityResult method is imported for seeing the result of the
    //called activity.
    //As "Intent" is used for passing the transfer control from an activity to
    //another , similarly "bundles" are used for the getting string information
    //from the end of ectivity or pass string to another activity.
    //like when a camera image is captured the resultant bitmap value of image
    //captured is stored which is retrieved via bundle for manipulation.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bun = data.getExtras();
            bmp = (Bitmap) bun.get("data");
            iv.setImageBitmap(bmp);
        }
    }

}
