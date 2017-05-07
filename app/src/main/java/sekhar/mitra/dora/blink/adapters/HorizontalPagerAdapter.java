package sekhar.mitra.dora.blink.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sackcentury.shinebuttonlib.ShineButton;
import org.json.JSONException;
import org.json.JSONObject;
import sekhar.mitra.dora.blink.Blink;
import sekhar.mitra.dora.blink.R;
import sekhar.mitra.dora.blink.model.chats.BlinkChat;
import sekhar.mitra.dora.blink.model.chats.BlinkPhoto;
import sekhar.mitra.dora.blink.model.search.Photo;

/**
 * Created by sekharrockz on 07/05/17.
 */

public class HorizontalPagerAdapter extends PagerAdapter {

  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private BlinkChat blinkChat;
  private Gson gson;

  public HorizontalPagerAdapter(final Context context, BlinkChat blinkChat) {
    mContext = context;
    mLayoutInflater = LayoutInflater.from(context);
    this.blinkChat = blinkChat;
    gson = new Gson();
  }

  @Override public int getCount() {
    return blinkChat.getSearchPhotos().getPhotos().getPerpage().intValue();
  }

  @Override public Object instantiateItem(final ViewGroup container, final int position) {
    final View view;
    view = mLayoutInflater.inflate(R.layout.carousel_card, container, false);
    ImageView imageView = (ImageView) view.findViewById(R.id.thumbnail);
    TextView title = (TextView) view.findViewById(R.id.title);
    final ShineButton shineButton = (ShineButton) view.findViewById(R.id.favourite);
    try {
      if (blinkChat.getSearchPhotos().getPhotos().getPhoto().size() >= position) {
        Photo photo = blinkChat.getSearchPhotos().getPhotos().getPhoto().get(position);
        JSONObject photoJSON = new JSONObject(gson.toJson(photo));
        BlinkPhoto blinkPhoto = new BlinkPhoto(photoJSON);
        Glide.with(mContext).load(blinkPhoto.getUrl()).centerCrop().crossFade().into(imageView);
        title.setText(blinkPhoto.getName());
        if (photo.getFavourite()) {
          shineButton.setChecked(true);
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    shineButton.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(View view, boolean checked) {
        Photo photo = blinkChat.getSearchPhotos().getPhotos().getPhoto().get(position);
        if (shineButton.isChecked()) {
          photo.setFavourite(true);
          // save in favouriteList
          Blink.addFavouritePhoto(photo);
        } else {
          photo.setFavourite(false);
          // remove from favouriteList
          Blink.removeFavouritePhoto(photo);
        }
      }
    });
    container.addView(view);
    return view;
  }

  @Override public boolean isViewFromObject(final View view, final Object object) {
    return view.equals(object);
  }

  @Override public int getItemPosition(Object object) {
    return POSITION_NONE;
  }

  @Override
  public void destroyItem(final ViewGroup container, final int position, final Object object) {
    container.removeView((View) object);
  }
}