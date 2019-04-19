package bitspilani.goa.letsPlay.retrofit;

import java.util.List;

import bitspilani.goa.letsPlay.dtos.UserResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BillshareApiService {

    @GET("v1/user")
    Call<List<UserResponseDto>> getUsers();

}
