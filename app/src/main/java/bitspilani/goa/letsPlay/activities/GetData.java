package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import bitspilani.goa.letsPlay.R;
import bitspilani.goa.letsPlay.dtos.UserResponseDto;
import bitspilani.goa.letsPlay.retrofit.BillshareService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static android.content.ContentValues.TAG;

public class GetData extends Activity implements View.OnClickListener {

    private EditText et;
    private TextView tv;
    private Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        initial();
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    private void initial() {
        // TODO Auto-generated method stub
        et = (EditText) findViewById(R.id.etget);
        tv = (TextView) findViewById(R.id.tv1get);
        b1 = (Button) findViewById(R.id.bt1get);
        b2 = (Button) findViewById(R.id.bt2get);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.bt1get) {
//            String str = et.getText().toString();
//            Bundle dataflow = new Bundle();
//            dataflow.putString("key", str);
//            Intent sa = new Intent(GetData.this, SendData.class);
//            sa.putExtras(dataflow);
//            startActivity(sa);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor())
                    .build();

            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl("http://13.250.111.164:8098/billshare/");
            Retrofit retrofit = retrofitBuilder.build();
            BillshareService billshareService = retrofit.create(BillshareService.class);

            Thread thread = new Thread(() -> {
                try {
                    Response<List<UserResponseDto>> userResponseDtoResponse = billshareService.getUsers().execute();
                    System.out.println(userResponseDtoResponse.body());
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            });
            thread.start();
        } else if (v.getId() == R.id.bt2get) {
            Intent safr = new Intent(GetData.this, SendData.class);
            startActivityForResult(safr, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle res = data.getExtras();
            String s = res.getString("result");
            tv.setText(s);
        }
    }

}
