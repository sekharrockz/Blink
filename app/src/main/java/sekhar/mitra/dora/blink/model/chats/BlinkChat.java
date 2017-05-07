package sekhar.mitra.dora.blink.model.chats;

import java.util.Comparator;
import org.json.JSONException;
import org.json.JSONObject;
import sekhar.mitra.dora.blink.model.search.Search;

/**
 * Created by user on 07/05/17.
 */

public class BlinkChat {

  String userName;
  Search searchPhotos;
  String message;
  String type;
  long timeToken;
  int position;
  boolean lock;

  public BlinkChat(String userName, Search searchPhotos, String type, long timeToken,
      String message) {
    this.userName = userName;
    this.searchPhotos = searchPhotos;
    this.type = type;
    this.timeToken = timeToken;
    this.type = type;
    this.message = message;
    this.position = 0;
  }

  public BlinkChat(String userName, String message, String type, long timeToken) {
    this.userName = userName;
    this.message = message;
    this.type = type;
    this.timeToken = timeToken;
    this.position = 0;
  }

  /*
      GETTERS...
   */

  public boolean getLock() {
    return lock;
  }

  public int getPosition() {
    return position;
  }

  public String getUserName() {
    return userName;
  }

  public Search getSearchPhotos() {
    return searchPhotos;
  }

  public String getMessage() {
    return message;
  }

  public long getTimeToken() {
    return timeToken;
  }

  public String getType() {
    return type;
  }

  /*
      SETTERS...
  */
  public void setLock(boolean lock) {
    this.lock = lock;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setSearchPhotos(Search searchPhotos) {
    this.searchPhotos = searchPhotos;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setTimeToken(long timeToken) {
    this.timeToken = timeToken;
  }

  public void setType(String type) {
    this.type = type;
  }
}
