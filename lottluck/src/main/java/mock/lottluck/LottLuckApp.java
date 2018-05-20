package mock.lottluck;

import android.app.Application;

import mock.lottluck.callbacks.ItemClickCallback;
import mock.lottluck.callbacks.UICallback;
import mock.lottluck.viewmodel.LottLuckListActivityVM;


public class LottLuckApp extends Application {
    LottLuckListActivityVM lottLuckListActivityVM;
    @Override
    public void onCreate(){
        super.onCreate();

    }

    public LottLuckListActivityVM getLottLuckListActivityVM(UICallback uiCallback, ItemClickCallback itemClickCallback) {
        if(null == lottLuckListActivityVM){
            lottLuckListActivityVM = new LottLuckListActivityVM(getApplicationContext(),uiCallback, itemClickCallback);
        }
        return lottLuckListActivityVM;
    }
}
