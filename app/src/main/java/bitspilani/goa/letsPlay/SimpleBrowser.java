package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {

    WebView bro;
    EditText ur;
    Button bgo, bref, bbk, bfd, bhy;

    @Override
    protected void onCreate(Bundle bun) {
        // TODO Auto-generated method stub
        super.onCreate(bun);

        setContentView(R.layout.simplebrowser);

        bro = (WebView) findViewById(R.id.webView);

        /*
         * setting some settings of the obtained URL like javascript,
         * site zoom ratio ,site view as desktop site
         */
        bro.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        bro.getSettings().setLoadWithOverviewMode(true);
        bro.getSettings().setUseWideViewPort(true);

        /*
         * ViewClient helps to customise the acquired URL of the clicked
         * item in the currently opened site to be open in the WebView
         * rather than in android's default browser
         */
        bro.setWebViewClient(new ViewClient());
        try {
            bro.loadUrl("http://www.google.com");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ur = (EditText) findViewById(R.id.eturl);
        bgo = (Button) findViewById(R.id.bgo);
        bref = (Button) findViewById(R.id.brefresh);
        bbk = (Button) findViewById(R.id.bgback);
        bfd = (Button) findViewById(R.id.bgforw);
        bhy = (Button) findViewById(R.id.bclearhis);
        bgo.setOnClickListener(this);
        bref.setOnClickListener(this);
        bbk.setOnClickListener(this);
        bfd.setOnClickListener(this);
        bhy.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.bgo:
                String str = ur.getText().toString();
                bro.loadUrl(str);
                //hiding the keyboard from screen after using ET at site load
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ur.getWindowToken(), 0);
                break;
            case R.id.brefresh:
                bro.reload();
                break;
            case R.id.bgback:
                if (bro.canGoBack())
                    bro.goBack();
                break;
            case R.id.bgforw:
                if (bro.canGoForward())
                    bro.goForward();
                break;
            case R.id.bclearhis:
                bro.clearHistory();
                break;
        }
    }

}

