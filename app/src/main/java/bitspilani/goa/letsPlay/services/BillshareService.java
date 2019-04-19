package bitspilani.goa.letsPlay.services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;

import bitspilani.goa.letsPlay.activities.GetData;
import bitspilani.goa.letsPlay.dtos.UserResponseDto;
import bitspilani.goa.letsPlay.retrofit.BillshareApiService;
import bitspilani.goa.letsPlay.retrofit.RestClient;
import bitspilani.goa.letsPlay.retrofit.RestPropagateCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class BillshareService extends Activity {

    private BillshareApiService billshareApiService;

    private RestClient restClient;

    public BillshareService() {
        restClient = new RestClient();
        billshareApiService = restClient.initialiseRestClient().create(BillshareApiService.class);
    }

    /**
     * Not working yet
     * Need to figure this cos this is configurable and reusable code rather than existing implementation
     * @param callback
     */
    public void getUsers(RestPropagateCallback callback) {

        try {
            Call<List<UserResponseDto>> call = billshareApiService.getUsers();
            call.enqueue(new Callback<List<UserResponseDto>>() {
                @Override
                public void onResponse(Call<List<UserResponseDto>> call, Response<List<UserResponseDto>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        callback.apiResponse(response.body());
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
    }

}
