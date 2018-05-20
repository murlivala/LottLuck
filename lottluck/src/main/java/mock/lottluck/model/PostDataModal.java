package mock.lottluck.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostDataModal {
    private @SerializedName("CompanyId") String Id;
    private @SerializedName("MaxDrawCount") int maxCount;
    private @SerializedName("OptionalProductFilter") List<String> tagsList;

    public PostDataModal(String aId, Integer num, List<String> pp){
        Id = aId;
        maxCount = num;
        tagsList = pp;
    }
}
