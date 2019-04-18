package bitspilani.goa.letsPlay;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity {


    //Preferences require separate folder in resource folder.
    //This folder consists of xml file for preferences and consists of
    //multiple preferences type.
    //Each preference consists of title,key,summary and other attr.
    //Some preferences access value for entries from  values folder.
    //In values folder , xml files are maintained for each type of
    //value called in preference.cml and each of this file contains
    //multiple items inside the type of value.

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }

}
