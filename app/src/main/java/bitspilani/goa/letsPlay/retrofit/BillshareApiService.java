package bitspilani.goa.letsPlay.retrofit;

import java.util.List;

import bitspilani.goa.letsPlay.dtos.BillResponseDto;
import bitspilani.goa.letsPlay.dtos.GroupResponseDto;
import bitspilani.goa.letsPlay.dtos.UserResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BillshareApiService {

    @GET("v1/user")
    Call<List<UserResponseDto>> getUsers();

    @GET("v1/group")
    Call<List<GroupResponseDto>> getGroups();

    @GET("v1/bill/{billId}")
    Call<BillResponseDto> getBills(@Path("billId") Integer billId);

}
