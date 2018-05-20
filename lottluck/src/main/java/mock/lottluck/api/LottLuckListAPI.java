package mock.lottluck.api;

import mock.lottluck.model.LottLuckDrawModel;
import mock.lottluck.model.LottLuckMainModal;
import mock.lottluck.model.PostDataModal;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LottLuckListAPI {

    @GET("companies")
    Call<LottLuckMainModal> getChanges();

    @Headers("Cache-control: no-cache")
    @POST("opendraws")
    Call<LottLuckDrawModel> getDraw(@Body PostDataModal postDataModal);

}
