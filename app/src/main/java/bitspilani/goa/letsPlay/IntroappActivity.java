package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class IntroappActivity extends Activity {

    MediaPlayer song;

    @Override
    protected void onCreate(Bundle bun) {
        super.onCreate(bun);
        setContentView(R.layout.introapp);
        /*adding music to an activity
         * two classes-soundpool and mediaplayer
         * soundpool for sudden small sounds.
         * mediaplayer for background scores.
         */

        song = MediaPlayer.create(IntroappActivity.this, R.raw.backgroundscore);

        //fetching the preference value set by the user for modification in the
        //media being played
        //datatype-SharedPreference
        //preference items are accessed via key allotted to each item
        SharedPreferences getprefval = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getprefval.getBoolean("check", true);
        if (music == true)
            song.start();
		
		/*for maintaining a time difference between two activities
		important for game development*/
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //pointing to different activity
                    /*intent declaration should have exactly same intent action
                     * name of the class pointing to as that in manifest.*/
                    Intent openman = new Intent("bitspilani.goa.letsPlay.MENU");
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
        song.release();//to stop the music on when in pause or exchange mode.
        finish();
    }

}
