package sekhar.mitra.dora.blink;

import android.app.Application;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sekhar.mitra.dora.blink.fragments.Chats;
import sekhar.mitra.dora.blink.model.chats.BlinkChat;
import sekhar.mitra.dora.blink.model.search.Photo;

/**
 * Created by sekharrockz on 06/05/17.
 */

public class Blink extends Application {

  private static Context context;
  public static List<BlinkChat> blinkChatList = new ArrayList<>();
  public static HashMap<String, Photo> blinkFavouriteList = new HashMap<>();
  public static final String REST_KEY = "4b29f4c496e7aa55e95bbbd23ba6867b";
  public static final String REST_SECRET_KEY = "5b7aeb367c328b29";

  @Override public void onCreate() {
    super.onCreate();
    Blink.context = this;
  }

  public static List<BlinkChat> getBlinkChatList() {
    return blinkChatList;
  }

  public static List<Photo> getBlinkFavouriteList() {
    List<Photo> photoList = new ArrayList<>();
    HashMap<String, Photo> stringPhotoHashMap = new HashMap<>(blinkFavouriteList);
    Iterator it = stringPhotoHashMap.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      photoList.add((Photo) pair.getValue());
      it.remove(); // avoids a ConcurrentModificationException
    }
    return photoList;
  }

  public static void addFavouritePhoto(Photo photo) {
    if (!blinkFavouriteList.containsKey(photo.getId())) {
      blinkFavouriteList.put(photo.getId(), photo);
    }
  }

  public static void removeFavouritePhoto(Photo photo) {
    if (blinkFavouriteList.containsKey(photo.getId())) {
      blinkFavouriteList.remove(photo.getId());
    }
  }

  public static void updateFavourite(Photo photo) {
    for (int i = 0; i < blinkChatList.size(); i++) {
      BlinkChat blinkChat = blinkChatList.get(i);
      if (blinkChat.getType().equals(Chats.BOT_MESSAGE)) {
        List<Photo> photoList = blinkChatList.get(i).getSearchPhotos().getPhotos().getPhoto();
        for (int j = 0; j < photoList.size(); j++) {
          Photo photo1 = photoList.get(j);
          if (photo1.getId().equals(photo.getId())) {
            if (photo1.getFavourite()) {
              photo1.setFavourite(photo.getFavourite());
            }
          }
        }
      }
    }
  }
}
