
package sekhar.mitra.dora.blink.model.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Iconurls {

    @SerializedName("retina")
    @Expose
    private String retina;
    @SerializedName("large")
    @Expose
    private String large;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("small")
    @Expose
    private String small;
    @SerializedName("default")
    @Expose
    private String _default;

    public String getRetina() {
        return retina;
    }

    public void setRetina(String retina) {
        this.retina = retina;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

}
