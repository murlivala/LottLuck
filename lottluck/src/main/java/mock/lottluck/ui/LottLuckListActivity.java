package mock.lottluck.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mock.lottluck.LottLuckApp;
import mock.lottluck.adapter.CustomAdapter;
import mock.lottluck.callbacks.ItemClickCallback;
import mock.lottluck.callbacks.UICallback;
import mock.lottluck.model.BaseModel;
import mock.lottluck.model.LottLuckDrawItems;
import mock.lottluck.model.LottLuckDrawModel;
import mock.lottluck.utils.Constants;
import mock.lottluck.utils.InternetUtil;
import mock.lottluck.viewmodel.LottLuckListActivityVM;
import mock.lottluck.wrapper.LinearLayoutManagerWrapper;
import mock.lottluck.BuildConfig;
import mock.lottluck.R;
import retrofit2.Response;

public class LottLuckListActivity extends BaseActivity implements UICallback<BaseModel>,ItemClickCallback {

    private final String TAG = LottLuckListActivity.class.getSimpleName();
    @BindView(R.id.tv_header_name) TextView tvHeader;
    @BindView(R.id.header_icon) ImageView imageView;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private CustomAdapter mAdapter;
    private LinearLayoutManager layoutManager;
    private int scrollToPos = 0;
    private LottLuckListActivityVM lottLuckListActivityVM;
    private int lastViewedItem = RecyclerView.NO_POSITION;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lott_list);
        lottLuckListActivityVM = getViewModel();
        mAdapter = lottLuckListActivityVM.getAdapter();
        setView();
        setHandler();
        getLottList();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(lastViewedItem != RecyclerView.NO_POSITION){
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);//right to left slide
            mAdapter.notifyItemChanged(lastViewedItem);
            lastViewedItem = RecyclerView.NO_POSITION;
        }
    }

    private void setHandler(){
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                switch(inputMessage.what){
                    default:
                            /*
                             * Pass along other messages from the UI
                             */
                        super.handleMessage(inputMessage);
                }
            }
        };
    }

    private LottLuckListActivityVM getViewModel(){
       return ((LottLuckApp)getApplication()).getLottLuckListActivityVM(this,this);
    }

    private void setView(){
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManagerWrapper(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        recyclerView.setAdapter(mAdapter);
    }

    private void getLottList(){
        if(InternetUtil.isInternetOn(getApplicationContext())){
            showProgressDialog();
            removeAllMessages();
            tvHeader.setText(getText(R.string.text_loading));
            lottLuckListActivityVM.getLottList();
        }else{
            String err = getString(R.string.network_error);
            new ShowErrorDialogAndTryAgain(LottLuckListActivity.this,LottLuckListActivity.this)
                    .getAlert(err)
                    .show();
            Log.e(TAG,"getLottList() - "+err);
        }
    }

    public void showLottDraw(final LottLuckDrawModel lottLuckDrawModel){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LottLuckDrawItems lottLuckDrawItems = lottLuckDrawModel.getDraws().get(0);
                final Intent intent = new Intent();
                intent.setClassName(getPackageName(),getPackageName()+".ui.LottDrawActivity");
                intent.putExtra("id", lottLuckDrawItems.getProductId());
                intent.putExtra("DrawDisplayName", lottLuckDrawItems.getDrawDisplayName());
                intent.putExtra("timer", lottLuckDrawItems.getDrawCountDownTimerSeconds());
                intent.putExtra("url", lottLuckDrawItems.getDrawLogoUrl());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);//left to right slide
            }
        });
    }

    private Message getMessage(int what){
        return mHandler.obtainMessage(what);
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    @Override
    public void onItemClick(int position){

    }

    @Override
    public void updateView(int status ,Response<BaseModel> response){

        switch(status){
            case Constants.SUCCESS:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.scrollToPosition(scrollToPos);
                        removeAllMessages();

                    }
                });
                break;
            case Constants.UPDATE_VIEW_ON_ERROR:
                /**
                 * Placeholder for case when user presses OK on error dialog
                 */
                break;
            case Constants.TRY_AGAIN_ON_ERROR:
                getLottList();
                break;
            case Constants.LAUNCH_DRAW_SCREEN:
                LottLuckDrawModel lottLuckDrawModel = (LottLuckDrawModel) response.body();
                showLottDraw(lottLuckDrawModel);
                break;
            default:
        }
        dismiss();
    }

    @Override
    public void onError(int status, String errMessage){
        dismiss();
        if(status == Constants.NETWORK_FAILURE){
            removeAllMessages();
            String err = getString(R.string.network_error);
            new ShowErrorDialogAndTryAgain(LottLuckListActivity.this,LottLuckListActivity.this)
                    .getAlert(err)
                    .show();
            Log.e(TAG,"onError() - "+err);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        removeAllMessages();
    }

    private void removeAllMessages(){
        if(null != mHandler){

        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(isFinishing()){
            if(null != lottLuckListActivityVM){
                lottLuckListActivityVM.cancel();
            }
        }
    }

}
