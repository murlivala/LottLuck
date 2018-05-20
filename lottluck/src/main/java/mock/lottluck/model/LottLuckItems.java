package mock.lottluck.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LottLuckItems implements BaseModel{

    @SerializedName("CompanyId")
    @Expose
    private String companyId;
    @SerializedName("CompanyDisplayName")
    @Expose
    private String companyDisplayName;
    @SerializedName("CompanyDescription")
    @Expose
    private String companyDescription;
    @SerializedName("CompanyLogoUrl")
    @Expose
    private String companyLogoUrl;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyDisplayName() {
        return companyDisplayName;
    }

    public void setCompanyDisplayName(String companyDisplayName) {
        this.companyDisplayName = companyDisplayName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }

}
