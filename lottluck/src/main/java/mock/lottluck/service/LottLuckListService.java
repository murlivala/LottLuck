package mock.lottluck.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import mock.lottluck.callbacks.ServiceCallback;
import mock.lottluck.model.LottLuckDrawModel;
import mock.lottluck.model.LottLuckMainModal;
import mock.lottluck.api.LottLuckListAPI;
import mock.lottluck.model.PostDataModal;
import mock.lottluck.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LottLuckListService {
    private Call<LottLuckMainModal> mCall;
    private Call<LottLuckDrawModel> mDrawCall;
    private LottLuckListAPI lottLuckListAPI;
    private ServiceCallback mServiceCallBack;
    public LottLuckListService(ServiceCallback serviceCallback){
        mServiceCallBack = serviceCallback;
    }
    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        lottLuckListAPI = retrofit.create(LottLuckListAPI.class);
        request();
    }

    public void request(){
        mCall = lottLuckListAPI.getChanges();
        mCall.enqueue(new Callback<LottLuckMainModal>() {
            @Override
            public void onResponse(Call<LottLuckMainModal> call, Response<LottLuckMainModal> response) {
                if(response.isSuccessful()) {
                    mServiceCallBack.onSuccess(Constants.SUCCESS,response);
                } else {
                    mServiceCallBack.onFailure(Constants.FAILURE,"Error");
                }
            }

            @Override
            public void onFailure(Call<LottLuckMainModal> call, Throwable t) {
                mServiceCallBack.onFailure(Constants.NETWORK_FAILURE,t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void request(String name,Integer num, ArrayList<String> productFilter){
        mDrawCall = lottLuckListAPI.getDraw(new PostDataModal(name,num,productFilter));
        mDrawCall.enqueue(new Callback<LottLuckDrawModel>() {
            @Override
            public void onResponse(Call<LottLuckDrawModel> call, Response<LottLuckDrawModel> response) {
                Log.d("####","onRes:"+response.body()+" :"+response.message()+" :"+response.errorBody());
                Log.d("####"," :"+response.toString()+" :"+response.code());
                if(response.isSuccessful()) {
                    mServiceCallBack.onSuccess(Constants.SUCCESS,response);
                } else {
                    mServiceCallBack.onFailure(Constants.FAILURE,"Error");
                }
            }

            @Override
            public void onFailure(Call<LottLuckDrawModel> call, Throwable t) {

            }
        });
    }

    public void cancel(){
        if(null != mCall){
            mCall.cancel();
            mDrawCall.cancel();
        }
    }

}