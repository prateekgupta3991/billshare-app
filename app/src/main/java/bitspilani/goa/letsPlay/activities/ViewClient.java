package bitspilani.goa.letsPlay.activities;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView wv, String url) {
        // TODO Auto-generated method stub
        wv.loadUrl(url);
        return true;
    }
}
