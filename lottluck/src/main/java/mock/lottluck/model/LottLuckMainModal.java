package mock.lottluck.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LottLuckMainModal implements BaseModel{

    @SerializedName("Companies")
    @Expose
    private List<LottLuckItems> companies = null;
    @SerializedName("ErrorInfo")
    @Expose
    private Object errorInfo;
    @SerializedName("Success")
    @Expose
    private Boolean success;

    public List<LottLuckItems> getCompanies() {
        return companies;
    }

    public void setCompanies(List<LottLuckItems> companies) {
        this.companies = companies;
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