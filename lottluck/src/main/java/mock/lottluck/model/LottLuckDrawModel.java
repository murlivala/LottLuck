package mock.lottluck.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LottLuckDrawModel implements BaseModel{

    @SerializedName("Draws")
    @Expose
    private List<LottLuckDrawItems> draws = null;
    @SerializedName("ErrorInfo")
    @Expose
    private Object errorInfo;
    @SerializedName("Success")
    @Expose
    private Boolean success;

    public List<LottLuckDrawItems> getDraws() {
        return draws;
    }

    public void setDraws(List<LottLuckDrawItems> lottLuckDrawItems) {
        this.draws = lottLuckDrawItems;
    }

    public Object getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(Object errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}