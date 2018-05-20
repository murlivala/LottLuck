package mock.lottluck.viewmodel;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mock.lottluck.callbacks.UICallback;
import mock.lottluck.BuildConfig;
import mock.lottluck.adapter.CustomAdapter;
import mock.lottluck.callbacks.ItemClickCallback;
import mock.lottluck.callbacks.ServiceCallback;
import mock.lottluck.model.BaseModel;
import mock.lottluck.model.LottLuckDrawModel;
import mock.lottluck.model.LottLuckItems;
import mock.lottluck.model.LottLuckMainModal;
import mock.lottluck.service.LottLuckListService;
import mock.lottluck.utils.Constants;
import retrofit2.Response;

public class LottLuckListActivityVM implements ServiceCallback<BaseModel>,ItemClickCallback {
    private final String TAG = LottLuckListActivityVM.class.getSimpleName();
    private CustomAdapter mAdapter;
    private LottLuckListService lottLuckListService;
    private UICallback uiCallback;
    private ItemClickCallback itemClickCallback;
    List<LottLuckItems> results;
    public LottLuckListActivityVM(Context context, UICallback uiCallback,ItemClickCallback itemClickCallback){
        mAdapter = new CustomAdapter(context, LottLuckListActivityVM.this);
        this.itemClickCallback = itemClickCallback;
        this.uiCallback = uiCallback;
    }

    public void processResponse(final int status,final Response<BaseModel> response){
        Thread t = new Thread(){
            public void run(){
                processResponseInThread(status,response);
            }
        };
        t.start();
    }

    private void processResponseInThread(final int status,Response<BaseModel> response){
        if(null != uiCallback){
            mAdapter.updateDataset(response);
            final LottLuckMainModal lottLuckMainModal = (LottLuckMainModal)response.body();
            results = lottLuckMainModal.getCompanies();
            uiCallback.updateView(status,response);
        }
    }
    public void onSuccess(int status, Response<BaseModel> response){
        if(response.body() instanceof LottLuckMainModal){
            mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(),status);
            processResponse(status,response);
        }else if(response.body() instanceof LottLuckDrawModel){
            LottLuckDrawModel lottLuckDrawModel = (LottLuckDrawModel) response.body();
            Log.d("####",""+lottLuckDrawModel.getDraws().get(0).getDrawDisplayName());
            uiCallback.updateView(Constants.LAUNCH_DRAW_SCREEN,response);
        }
    }

    public void onFailure(int status,String errorMessage){
        if(null != uiCallback){
            uiCallback.onError(status,errorMessage);
        }
    }

    public CustomAdapter getAdapter(){
        return mAdapter;
    }

    @Override
    public void onItemClick(int position){
        if(BuildConfig.DEBUG){
            Log.d(TAG,"onItemClick - pos:"+position);
        }
        if(null != itemClickCallback){
            String[] p = {"OzLotto"};
            ArrayList<String> pp = new ArrayList<String>();
            pp.add("OzLotto");
            Log.d("####",""+results.get(position).getCompanyId());
            Integer num = 1;
            lottLuckListService.request(results.get(position).getCompanyId(),num,pp);
        }
    }

    public void getLottList(){
        if(null == lottLuckListService){
            lottLuckListService = new LottLuckListService(this);
            lottLuckListService.start();
        }else{
         //TBD
        }
    }

    public void cancel(){
        if(null != lottLuckListService){
            uiCallback = null;
            lottLuckListService.cancel();
        }
    }

}
