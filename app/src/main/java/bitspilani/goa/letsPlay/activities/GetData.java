package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

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

public class GetData extends Activity implements View.OnClickListener {

    private Button b1, b2, b3;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        bindVarsWithViews();
        retrofit = initialiseRestClient();
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
    }

    private void bindVarsWithViews() {
        // TODO Auto-generated method stub
        b1 = (Button) findViewById(R.id.bt1get);
        b2 = (Button) findViewById(R.id.bt2get);
        b3 = (Button) findViewById(R.id.bt3get);
    }

    private Retrofit initialiseRestClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://10.0.2.2:8098/billshare/");
        return retrofitBuilder.build();
    }

    /**
     * listener for button clicks
     * prepares intent by another format, calls the respective activity of the intent
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        TableLayout tableLayout = (TableLayout) findViewById(R.id.datatable);
        if (v.getId() == R.id.bt1get) {
            BillshareApiService billshareService = retrofit.create(BillshareApiService.class);
            try {
                Call<List<UserResponseDto>> call = billshareService.getUsers();
                call.enqueue(new Callback<List<UserResponseDto>>() {
                    @Override
                    public void onResponse(Call<List<UserResponseDto>> call, Response<List<UserResponseDto>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            fillDataInTable(tableLayout, response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<UserResponseDto>> call, Throwable t) {
                        System.out.println("Call failed");
                    }
                });
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        } else if (v.getId() == R.id.bt2get) {
        } else if (v.getId() == R.id.bt3get) {
        }
    }

    private void fillDataInTable(TableLayout tableLayout, List<UserResponseDto> userResponseDto) {

        for (UserResponseDto dto : userResponseDto) {
            TableRow tableRow = new TableRow(this);
            TextView tvName = new TextView(this);
            TextView tvEmail = new TextView(this);
            TextView tvContact = new TextView(this);
            tvName.setText(dto.getName());
            tvEmail.setText(dto.getEmail());
            tvContact.setText(dto.getContact());
            tableRow.addView(tvName);
            tableRow.addView(tvEmail);
            tableRow.addView(tvContact);
            tableLayout.addView(tableRow);
        }
    }

}
