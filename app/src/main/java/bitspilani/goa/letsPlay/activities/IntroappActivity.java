package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import bitspilani.goa.letsPlay.R;

import static android.content.ContentValues.TAG;

public class IntroappActivity extends Activity implements View.OnClickListener {

    private MediaPlayer song;
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;

    private static final Integer RC_SIGN_IN = 007;

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
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        System.out.println(mGoogleSignInClient);

        //fetching the preference value set by the user from SharedPreference in android
//        SharedPreferences getprefval = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        boolean music = getprefval.getBoolean("check", true);
//        if (music) {
//            song.start();
//        }

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

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        System.out.println(account);
        if (account != null) {
            // add token validation later
            Intent openman = new Intent("bitspilani.goa.letsPlay.menu.MENU");
            startActivity(openman);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.sign_in_button) {
//            signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            if (account != null) {
                Intent openman = new Intent("bitspilani.goa.letsPlay.menu.MENU");
                startActivity(openman);
            }
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }
}
