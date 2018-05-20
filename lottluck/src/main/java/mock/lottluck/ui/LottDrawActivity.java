package mock.lottluck.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import mock.lottluck.R;
import mock.lottluck.utils.Constants;

public class LottDrawActivity extends AppCompatActivity {

    @BindView(R.id.tv_days_heading) TextView tv_days_heading;
    @BindView(R.id.tv_day)TextView tv_day;
    @BindView(R.id.tv_day_txt) TextView tv_day_txt;
    @BindView(R.id.tv_hr) TextView tv_hr;
    @BindView(R.id.tv_min) TextView tv_min;
    @BindView(R.id.tv_sec) TextView tv_sec;
    @BindView(R.id.tv_amount) TextView tv_amount;
    @BindView(R.id.icon)ImageView imageView;
    CountDownTimer countDownTimer;
    long days;
    long hours;
    long mins;
    long secs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottdraw);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Float fTimer = intent.getFloatExtra("timer",0);
        long timer = fTimer.longValue();
        Log.d("####","timer:"+timer);
        days = TimeUnit.MILLISECONDS.toDays(timer*1000);
        long restMilliSec = timer*1000 - getMillisecInDays(days);

        hours = TimeUnit.MILLISECONDS.toHours(restMilliSec);

        restMilliSec = timer*1000 - (getMillisecInDays(days)+getMillisecInHours(hours));
        mins = TimeUnit.MILLISECONDS.toMinutes(restMilliSec);

        restMilliSec = timer*1000 - (getMillisecInDays(days)+getMillisecInHours(hours)+getMillisecInMins(mins));
        secs = TimeUnit.MILLISECONDS.toSeconds(restMilliSec);

        tv_amount.setText(intent.getStringExtra("DrawDisplayName"));
        if(days > 0){
            tv_days_heading.setText("In "+days+" days");
            tv_day.setText(""+days);
        }else{
            tv_days_heading.setText("TONIGHT");
            tv_day.setVisibility(View.GONE);
            tv_day_txt.setVisibility(View.GONE);
        }
        if(null == countDownTimer){
            countDownTimer = new CountDownTimer(timer*1000, 1000) {

                public void onTick(long millisUntilFinished) {
                    update(millisUntilFinished);
                }

                public void onFinish() {
                    tv_sec.setText("done!");
                }
            }.start();
        }
        //Glide.with(getApplicationContext()).load(intent.getStringExtra("url")).error(R.mipmap.ic_launcher).into(imageView);
    }

    private long getMillisecInHours(long hours){
        return (hours>0)?hours*60*60*1000:0;
    }

    private long getMillisecInDays(long days){
        return (days>0)?days*24*60*60*1000:0;
    }

    private long getMillisecInMins(long mins){
        return (mins>0)?mins*60*1000:0;
    }

    private long getMillisecInSecs(long secs){
        return (secs>0)?secs*1000:0;
    }

    private void update(long millisUntilFinished){
        tv_hr.setText(""+hours);
        tv_min.setText(""+mins);
        tv_sec.setText("" + secs);
        secs--;
        if(secs == -1){
            mins--;
            secs = Constants.MAX_CEILING_SECONDS_IN_MINUTE;
        }
        if(mins == -1){
            hours--;
            mins = Constants.MAX_CEILING_MINUTES_IN_HOUR;
        }
        if(hours == -1){
            days--;
            hours = Constants.MAX_CEILING_HOURS_IN_DAY;
        }
        if(days == 0){
            tv_days_heading.setText("TONIGHT");
            tv_day.setVisibility(View.GONE);
            tv_day_txt.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(isFinishing()){
            countDownTimer.cancel();
        }
    }
}
