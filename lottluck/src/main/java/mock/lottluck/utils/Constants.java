package mock.lottluck.utils;

public class Constants {

    //network failure
    public static final int NETWORK_FAILURE = -99;

    //service response failure
    public static final int FAILURE = -1;
    public static final int SUCCESS = 0;

    //messages to direct UI in error cases
    public static final int UPDATE_VIEW_ON_ERROR = 2;
    public static final int TRY_AGAIN_ON_ERROR = 3;

    public static final int LAUNCH_DRAW_SCREEN = 100;

    public static final int MAX_CEILING_HOURS_IN_DAY = 23;
    public static final int MAX_CEILING_MINUTES_IN_HOUR = 59;
    public static final int MAX_CEILING_SECONDS_IN_MINUTE = 59;


    public static final String SERVICE_BASE_URL = "https://api.thelott.com/svc/sales/vmax/web/data/lotto/";
}
