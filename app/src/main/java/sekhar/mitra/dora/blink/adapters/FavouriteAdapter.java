package sekhar.mitra.dora.blink.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sackcentury.shinebuttonlib.ShineButton;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import sekhar.mitra.dora.blink.Blink;
import sekhar.mitra.dora.blink.R;
import sekhar.mitra.dora.blink.model.chats.BlinkPhoto;
import sekhar.mitra.dora.blink.model.search.Photo;

/**
 * Created by sekharrockz on 07/05/17.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

  private Context context;
  public static List<Photo> blinkPhotoList = new ArrayList<>();
  private Gson gson;

  public FavouriteAdapter(Context context, List<Photo> blinkPhotoList) {
    this.context = context;
    this.blinkPhotoList = blinkPhotoList;
    gson = new Gson();
  }

  public void update(List<Photo> mBlinkPhotoList) {
    blinkPhotoList = mBlinkPhotoList;
    notifyDataSetChanged();
  }

  public void getPhoto(int position) {
    blinkPhotoList.get(position);
  }

  public void removePhoto(int position) {
    blinkPhotoList.get(position).setFavourite(false);
    Blink.updateFavourite(blinkPhotoList.get(position));
    Blink.removeFavouritePhoto(blinkPhotoList.get(position));
    blinkPhotoList.remove(position);
    notifyDataSetChanged();
  }

  public void addBlinkPhotoList(Photo blinkPhoto) {
    blinkPhotoList.add(blinkPhoto);
    notifyDataSetChanged();
  }

  static class FavouriteViewHolder extends RecyclerView.ViewHolder {
    protected ImageView thumbnail;
    protected TextView title;
    protected ShineButton favourite;

    public FavouriteViewHolder(View itemView) {
      super(itemView);
      this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
      this.title = (TextView) itemView.findViewById(R.id.title);
      this.favourite = (ShineButton) itemView.findViewById(R.id.favourite);
    }
  }

  @Override public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carousel_card, null);
    FavouriteViewHolder viewHolder = new FavouriteViewHolder(view);
    return viewHolder;
  }

  @Override public void onBindViewHolder(final FavouriteViewHolder holder, final int position) {
    try {
      if (blinkPhotoList.size() >= position) {
        Photo mBlinkPhoto = blinkPhotoList.get(position);
        JSONObject photoJSON = new JSONObject(gson.toJson(mBlinkPhoto));
        BlinkPhoto blinkPhoto = new BlinkPhoto(photoJSON);
        Glide.with(context)
            .load(blinkPhoto.getUrl())
            .centerCrop()
            .crossFade()
            .into(holder.thumbnail);
        holder.title.setText(blinkPhoto.getName());
        holder.favourite.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
          @Override public void onCheckedChanged(View view, boolean checked) {
            removePhoto(position);
          }
        });
        if (mBlinkPhoto.getFavourite()) {
          holder.favourite.setChecked(true);
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  @Override public int getItemCount() {
    return blinkPhotoList.size();
  }
}
