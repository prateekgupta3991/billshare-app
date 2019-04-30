package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import bitspilani.goa.letsPlay.R;
import bitspilani.goa.letsPlay.dtos.UserResponseDto;
import bitspilani.goa.letsPlay.retrofit.BillshareApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static android.content.ContentValues.TAG;

public class UserMetaStorage extends Activity implements View.OnClickListener {

    private EditText editText;
    private Button button;
    private TextView textView;
    private Retrofit retrofit;
    private final String FILENAME = "splitUp_me.txt";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.landing_page);
        editText = (EditText) findViewById(R.id.etlp);
        button = (Button) findViewById(R.id.btlp);
        textView = (TextView) findViewById(R.id.tvlp);
        retrofit = initialiseRestClient();
        button.setOnClickListener(this);
    }

    private Retrofit initialiseRestClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://13.229.105.217:8098/billshare/");
        return retrofitBuilder.build();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btlp) {
            BillshareApiService billshareService = retrofit.create(BillshareApiService.class);
            try {
                Call<UserResponseDto> call = billshareService.getUserByEmail(editText.getText().toString().trim());
                call.enqueue(new Callback<UserResponseDto>() {
                    @Override
                    public void onResponse(Call<UserResponseDto> call, Response<UserResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            textView.setVisibility(View.VISIBLE);
                            saveUserIdLocally(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<UserResponseDto> call, Throwable t) {
                        System.out.println("Identify Me Call failed");
                    }
                });
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    public void saveUserIdLocally(UserResponseDto responseDto) {

        String str = responseDto.getId().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(str.getBytes());
            fos.close();
//            Menu menu = new Menu();
//            List<String> menuItems = Arrays.asList(menu.getArr());
//            menuItems.remove(1);
//            menu.setArr((String[]) menuItems.toArray());
            Intent intent = new Intent("bitspilani.goa.letsPlay.menu.MENU");
            startActivity(intent);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            Log.e(TAG, "No file found for user");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e(TAG, "Some I/O exception occured");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.e(TAG, "Unable to close file output tream");
                }
            }
        }
    }
}
