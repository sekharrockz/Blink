package sekhar.mitra.dora.blink.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import sekhar.mitra.dora.blink.R;
import sekhar.mitra.dora.blink.fragments.Chats;
import sekhar.mitra.dora.blink.java.RowType;
import sekhar.mitra.dora.blink.model.chats.BlinkChat;

/**
 * Created by sekharrockz
 */

public class ChatAdapter extends RecyclerView.Adapter {

  public static List<BlinkChat> blinkChatList = new ArrayList<>();
  private Context context;
  private DateTimeFormatter fmt;
  private LocalDateTime msgDateTime;

  public ChatAdapter(Context context, List<BlinkChat> blinkChatList) {
    this.context = context;
    this.blinkChatList = blinkChatList;
  }

  public BlinkChat getLastBotMessage() {
    for (int i = blinkChatList.size() - 1; i >= 0; i--) {
      BlinkChat blinkChat = blinkChatList.get(i);
      if (blinkChat.getType().equals(Chats.BOT_MESSAGE)) {
        return blinkChatList.get(i);
      }
    }
    return null;
  }

  public void update(List<BlinkChat> newBlinkChatList) {
    blinkChatList = newBlinkChatList;
    notifyDataSetChanged();
  }

  public void addBlinkChat(BlinkChat blinkChat) {
    blinkChatList.add(blinkChat);
    notifyDataSetChanged();
  }

  public BlinkChat getBlinkChat(int position) {
    return blinkChatList.get(position);
  }

  @Override public int getItemViewType(int position) {
    BlinkChat blinkChat = blinkChatList.get(position);
    if (blinkChat.getType().equals(Chats.BOT_MESSAGE)) {
      return RowType.BOT_ROW_TYPE;
    } else if (blinkChat.getType().equals(Chats.USER_MESSAGE)) {
      return RowType.TEXT_ROW_TYPE;
    } else {
      return -1;
    }
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == RowType.BOT_ROW_TYPE) {
      View view =
          LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_chat_info, parent, false);
      return new BotViewHolder(view);
    } else if (viewType == RowType.TEXT_ROW_TYPE) {
      View view =
          LayoutInflater.from(parent.getContext()).inflate(R.layout.user_chat_info, parent, false);
      return new UserViewHolder(view);
    } else {
      return null;
    }
  }

  @Override public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
    final BlinkChat blinkChat = blinkChatList.get(position);
    if (holder instanceof BotViewHolder) {
      ((BotViewHolder) holder).horizontalInfiniteCycleViewPager.setAdapter(
          new HorizontalPagerAdapter(context, blinkChat));
      ((BotViewHolder) holder).friend_name.setText(blinkChat.getUserName().toString());
      ((BotViewHolder) holder).horizontalInfiniteCycleViewPager.setCurrentItem(
          blinkChat.getPosition());
      ((BotViewHolder) holder).shown = true;
    } else if (holder instanceof UserViewHolder) {
      ((UserViewHolder) holder).owner_text_message.setText(blinkChat.getMessage());
      msgDateTime = new LocalDateTime(blinkChat.getTimeToken());
      fmt = DateTimeFormat.forPattern("HH:mm");
      ((UserViewHolder) holder).owner_text_time.setText(msgDateTime.toString(fmt));
    }
  }

  @Override public int getItemCount() {
    return blinkChatList.size();
  }

  static class UserViewHolder extends RecyclerView.ViewHolder {
    protected TextView owner_text_time;
    protected TextView owner_text_message;

    public UserViewHolder(View itemView) {
      super(itemView);
      this.owner_text_time = (TextView) itemView.findViewById(R.id.owner_text_time);
      this.owner_text_message = (TextView) itemView.findViewById(R.id.owner_text_message);
    }
  }

  public static class BotViewHolder extends RecyclerView.ViewHolder {
    public HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager;
    public TextView friend_name;
    public boolean shown = false;

    public BotViewHolder(View itemView) {
      super(itemView);
      this.shown = false;
      this.horizontalInfiniteCycleViewPager =
          (HorizontalInfiniteCycleViewPager) itemView.findViewById(R.id.hicvp);
      this.friend_name = (TextView) itemView.findViewById(R.id.friend_name);
      this.horizontalInfiniteCycleViewPager.addOnPageChangeListener(
          new ViewPager.OnPageChangeListener() {
            @Override public void onPageScrolled(int position, float positionOffset,
                int positionOffsetPixels) {
            }

            @Override public void onPageSelected(int position) {
              if (position >= 5000000) {
                position = position - 5000000;
              }
              if (!shown) {
                // new item
                BlinkChat blinkChat = blinkChatList.get(getAdapterPosition());
              } else {
                if (position != 0) {
                  BlinkChat blinkChat = blinkChatList.get(getAdapterPosition());
                  blinkChat.setPosition(position);
                } else {

                }
                // old item
              }
            }

            @Override public void onPageScrollStateChanged(int state) {

            }
          });
    }
  }
}
