package bitspilani.goa.letsPlay;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class DatabaseView extends Activity{

	TextView info; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbmsview);
		info=(TextView) findViewById(R.id.tvinfodbms);
		AddType obj=new AddType(this);
		obj.open();
		String data=obj.getD();
		obj.close();
		System.out.println(data);
		info.setText(data);
		
	}
	
}