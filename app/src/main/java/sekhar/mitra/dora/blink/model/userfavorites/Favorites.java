package sekhar.mitra.dora.blink.model.userfavorites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Favorites {

  @SerializedName("photos") @Expose private Photos photos;
  @SerializedName("stat") @Expose private String stat;

  public Photos getPhotos() {
    return photos;
  }

  public void setPhotos(Photos photos) {
    this.photos = photos;
  }

  public String getStat() {
    return stat;
  }

  public void setStat(String stat) {
    this.stat = stat;
  }
}
