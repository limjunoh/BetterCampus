package kwangwoon.abcampus;

import kwangwoon.abcampus.models.ServerRequest;
import kwangwoon.abcampus.models.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("learn2crack-login-register/")
    Call<ServerResponse> operation(@Body ServerRequest request);

}
