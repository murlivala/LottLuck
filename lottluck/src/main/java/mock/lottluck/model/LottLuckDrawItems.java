package mock.lottluck.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LottLuckDrawItems {

    @SerializedName("ProductId")
    @Expose
    private String productId;
    @SerializedName("DrawNumber")
    @Expose
    private Integer drawNumber;
    @SerializedName("DrawDisplayName")
    @Expose
    private String drawDisplayName;
    @SerializedName("DrawDate")
    @Expose
    private String drawDate;
    @SerializedName("DrawLogoUrl")
    @Expose
    private String drawLogoUrl;
    @SerializedName("DrawType")
    @Expose
    private String drawType;
    @SerializedName("Div1Amount")
    @Expose
    private Float div1Amount;
    @SerializedName("IsDiv1Estimated")
    @Expose
    private Boolean isDiv1Estimated;
    @SerializedName("IsDiv1Unknown")
    @Expose
    private Boolean isDiv1Unknown;
    @SerializedName("DrawCloseDateTimeUTC")
    @Expose
    private String drawCloseDateTimeUTC;
    @SerializedName("DrawEndSellDateTimeUTC")
    @Expose
    private String drawEndSellDateTimeUTC;
    @SerializedName("DrawCountDownTimerSeconds")
    @Expose
    private Float drawCountDownTimerSeconds;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getDrawNumber() {
        return drawNumber;
    }

    public void setDrawNumber(Integer drawNumber) {
        this.drawNumber = drawNumber;
    }

    public String getDrawDisplayName() {
        return drawDisplayName;
    }

    public void setDrawDisplayName(String drawDisplayName) {
        this.drawDisplayName = drawDisplayName;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public String getDrawLogoUrl() {
        return drawLogoUrl;
    }

    public void setDrawLogoUrl(String drawLogoUrl) {
        this.drawLogoUrl = drawLogoUrl;
    }

    public String getDrawType() {
        return drawType;
    }

    public void setDrawType(String drawType) {
        this.drawType = drawType;
    }

    public Float getDiv1Amount() {
        return div1Amount;
    }

    public void setDiv1Amount(Float div1Amount) {
        this.div1Amount = div1Amount;
    }

    public Boolean getIsDiv1Estimated() {
        return isDiv1Estimated;
    }

    public void setIsDiv1Estimated(Boolean isDiv1Estimated) {
        this.isDiv1Estimated = isDiv1Estimated;
    }

    public Boolean getIsDiv1Unknown() {
        return isDiv1Unknown;
    }

    public void setIsDiv1Unknown(Boolean isDiv1Unknown) {
        this.isDiv1Unknown = isDiv1Unknown;
    }

    public String getDrawCloseDateTimeUTC() {
        return drawCloseDateTimeUTC;
    }

    public void setDrawCloseDateTimeUTC(String drawCloseDateTimeUTC) {
        this.drawCloseDateTimeUTC = drawCloseDateTimeUTC;
    }

    public String getDrawEndSellDateTimeUTC() {
        return drawEndSellDateTimeUTC;
    }

    public void setDrawEndSellDateTimeUTC(String drawEndSellDateTimeUTC) {
        this.drawEndSellDateTimeUTC = drawEndSellDateTimeUTC;
    }

    public Float getDrawCountDownTimerSeconds() {
        return drawCountDownTimerSeconds;
    }

    public void setDrawCountDownTimerSeconds(Float drawCountDownTimerSeconds) {
        this.drawCountDownTimerSeconds = drawCountDownTimerSeconds;
    }

}
