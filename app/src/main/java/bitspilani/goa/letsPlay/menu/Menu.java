package bitspilani.goa.letsPlay.menu;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import bitspilani.goa.letsPlay.R;

public class Menu extends ListActivity {

    private String arr[];

    /**
     * listener for item clicked on the menu
     * create intent for the clicked menu item and start activity for the respective intent
     *
     * @param l
     * @param v
     * @param position
     * @param id
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);

        Class mainact;
        try {
            String nxtxt = arr[position];
            mainact = Class.forName("bitspilani.goa.letsPlay.activities." + nxtxt);
            Intent mainactt = new Intent(Menu.this, mainact);
            startActivity(mainactt);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Setting a list adapter of Activity class to display menu
     *
     * @param bun
     */
    @Override
    protected void onCreate(Bundle bun) {
        // TODO Auto-generated method stub
        super.onCreate(bun);

        arr = new String[]{"GetData", "UserMetaStorage"};
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setListAdapter(new ArrayAdapter<>(Menu.this, android.R.layout.simple_list_item_1, arr));
    }


    /**
     * setting up inflatable menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // TODO Auto-generated method stub

        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.stdmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == R.id.aus) {
            Intent i = new Intent("bitspilani.goa.letsPlay.ABOUTUS");
            startActivity(i);
        } else if (item.getItemId() == R.id.pref) {
            Intent i = new Intent("bitspilani.goa.letsPlay.PREFERENCE");
            startActivity(i);
        } else {
            finish();
        }
        return true;
    }

    public String[] getArr() {
        return arr;
    }

    public void setArr(String[] arr) {
        this.arr = arr;
    }
}
