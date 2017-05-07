
package sekhar.mitra.dora.blink.model.userfavorites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlickrResult {

    @SerializedName("stat")
    @Expose
    private String stat;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
