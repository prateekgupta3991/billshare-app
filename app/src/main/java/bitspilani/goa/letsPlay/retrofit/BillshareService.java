package bitspilani.goa.letsPlay.retrofit;

import bitspilani.goa.letsPlay.dtos.UserResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BillshareService {

    @GET("/v1/user")
    Call<UserResponseDto> getUsers();

}
