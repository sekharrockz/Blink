package sekhar.mitra.dora.blink.model.chats;

import org.json.JSONException;
import org.json.JSONObject;

public class BlinkPhoto {

  private String uid;
  private String name;
  private String url;

  public BlinkPhoto() {
    super();
  }

  public BlinkPhoto(JSONObject object) {
    super();
    try {
      this.uid = object.getString("id");
      this.name = object.getString("title");
      this.url = "http://farm"
          + object.getInt("farm")
          + ".staticflickr.com/"
          + object.getInt("server")
          + "/"
          + uid
          + "_"
          + object.getString("secret")
          + ".jpg";
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public String getUid() {
    return uid;
  }

  public String getUrl() {
    return url;
  }

  public String getName() {
    return name;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
