package bitspilani.goa.letsPlay.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import bitspilani.goa.letsPlay.R;
import bitspilani.goa.letsPlay.dtos.BillResponseDto;
import bitspilani.goa.letsPlay.dtos.GroupResponseDto;
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
    private final String FILENAME = "splitUp_me.txt";

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
                .baseUrl("http://3.1.202.158:8098/billshare/");
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
            tableLayout.removeAllViews();
            BillshareApiService billshareService = retrofit.create(BillshareApiService.class);
            try {
//                Call<List<UserResponseDto>> call = billshareService.getUsers();
//                call.enqueue(new Callback<List<UserResponseDto>>() {
//                    @Override
//                    public void onResponse(Call<List<UserResponseDto>> call, Response<List<UserResponseDto>> response) {
//                        if (response.isSuccessful() && response.body() != null) {
//                            fillUsersDataInTable(tableLayout, response.body());
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<List<UserResponseDto>> call, Throwable t) {
//                        System.out.println("Call failed");
//                    }
//                });

                FileInputStream fis = null;
                String data = "";
                try {
                    fis = openFileInput(FILENAME);
                    byte[] arr = new byte[fis.available()];//getting number of bytes in fis
                    while (fis.read(arr) != -1) ;//reading the bytes in fis
                    {
                        data = new String(arr);
                    }
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    Log.e(TAG, "No file found for user");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    Log.e(TAG, "Some I/O exception occured");
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            Log.e(TAG, "Unable to close file output tream");
                        }
                    }
                }

                Call<UserResponseDto> call = billshareService.getUserById(data);
                call.enqueue(new Callback<UserResponseDto>() {
                    @Override
                    public void onResponse(Call<UserResponseDto> call, Response<UserResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            fillUserDataInTable(tableLayout, response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<UserResponseDto> call, Throwable t) {
                        System.out.println("Call failed");
                    }
                });
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        } else if (v.getId() == R.id.bt2get) {
            tableLayout.removeAllViews();
            BillshareApiService billshareService = retrofit.create(BillshareApiService.class);
            try {
                Call<List<GroupResponseDto>> call = billshareService.getGroups();
                call.enqueue(new Callback<List<GroupResponseDto>>() {
                    @Override
                    public void onResponse(Call<List<GroupResponseDto>> call, Response<List<GroupResponseDto>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            fillGroupDataInTable(tableLayout, response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<GroupResponseDto>> call, Throwable t) {
                        System.out.println("Call failed");
                    }
                });
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        } else if (v.getId() == R.id.bt3get) {
            tableLayout.removeAllViews();
            BillshareApiService billshareService = retrofit.create(BillshareApiService.class);
            try {
                Call<BillResponseDto> call = billshareService.getBills(1);
                call.enqueue(new Callback<BillResponseDto>() {
                    @Override
                    public void onResponse(Call<BillResponseDto> call, Response<BillResponseDto> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            fillBillDataInTable(tableLayout, response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<BillResponseDto> call, Throwable t) {
                        System.out.println("Call failed");
                    }
                });
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private void fillUsersDataInTable(TableLayout tableLayout, List<UserResponseDto> userResponseDto) {

        for (UserResponseDto dto : userResponseDto) {
            fillUserDataInTable(tableLayout, dto);
        }
    }

    private void fillUserDataInTable(TableLayout tableLayout, UserResponseDto userResponseDto) {

        TableRow tableRow = new TableRow(this);
        TextView tvName = new TextView(this);
        TextView tvEmail = new TextView(this);
        TextView tvContact = new TextView(this);
        tvName.setText(userResponseDto.getName());
        tvEmail.setText(userResponseDto.getEmail());
        tvContact.setText(userResponseDto.getContact());
        tableRow.addView(tvName);
        tableRow.addView(tvEmail);
        tableRow.addView(tvContact);
        tableLayout.addView(tableRow);
    }

    private void fillGroupDataInTable(TableLayout tableLayout, List<GroupResponseDto> groupResponseDtos) {

        for (GroupResponseDto dto : groupResponseDtos) {
            TableRow tableRow = new TableRow(this);
            TextView tvName = new TextView(this);
            TextView tvEmail = new TextView(this);
            TextView tvContact = new TextView(this);
            tvName.setText(dto.getName());
            tvEmail.setText(dto.getAdmin().getName());
            StringBuilder sb = new StringBuilder();
            for (UserResponseDto userResponseDto : dto.getUsers()) {
                sb.append(userResponseDto.getName());
                sb.append(",");
            }
            tvContact.setText(sb.substring(0, sb.length() - 1));
            tableRow.addView(tvName);
            tableRow.addView(tvEmail);
            tableRow.addView(tvContact);
            tableLayout.addView(tableRow);
        }
    }

    private void fillBillDataInTable(TableLayout tableLayout, BillResponseDto billResponseDto) {

            TableRow tableRow = new TableRow(this);
            TextView tvName = new TextView(this);
            TextView tvEmail = new TextView(this);
            TextView tvContact = new TextView(this);
            tvName.setText(billResponseDto.getBillName());
            tvEmail.setText(billResponseDto.getGrpId() != null ? billResponseDto.getGrpId().toString() : null);
            tvContact.setText(billResponseDto.getAmount() != null ? billResponseDto.getAmount().toString() : null);
            tableRow.addView(tvName);
            tableRow.addView(tvEmail);
            tableRow.addView(tvContact);
            tableLayout.addView(tableRow);
    }

}
