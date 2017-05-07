
package sekhar.mitra.dora.blink.model.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comments {

    @SerializedName("photo_id")
    @Expose
    private String photoId;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

}
