package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class Grafix extends Activity {

    private UsedByGrafix obj;
    private WakeLock wl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //wake lock is the mechanism to stay on the device for the time the
        //the activity is running;
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Lock:");

        super.onCreate(savedInstanceState);
        wl.acquire();
        obj = new UsedByGrafix(this);
        setContentView(obj);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        wl.release();
    }

}
