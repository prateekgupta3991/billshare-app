package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

import bitspilani.goa.letsPlay.R;

public class IntroappActivity extends Activity {

    private MediaPlayer song;

    /**
     * initialising first activity with app opening sound
     * soundpool for sudden small sounds...mediaplayer for background scores...
     *
     * @param bun
     */
    @Override
    protected void onCreate(Bundle bun) {
        super.onCreate(bun);

        setContentView(R.layout.introapp);
        song = MediaPlayer.create(IntroappActivity.this, R.raw.applause);

        //fetching the preference value set by the user from SharedPreference in android
        SharedPreferences getprefval = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getprefval.getBoolean("check", true);
        if (music) {
            song.start();
        }

//		loading menu after initial timegap
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent openman = new Intent("bitspilani.goa.letsPlay.menu.MENU");
                    startActivity(openman);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        song.release();
        finish();
    }

}
