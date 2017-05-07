package sekhar.mitra.dora.blink.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sekhar.mitra.dora.blink.Blink;
import sekhar.mitra.dora.blink.R;
import sekhar.mitra.dora.blink.adapters.ChatAdapter;
import sekhar.mitra.dora.blink.adapters.HorizontalAdapter;
import sekhar.mitra.dora.blink.java.BlinkHttpClient;
import sekhar.mitra.dora.blink.java.OnChipClickListener;
import sekhar.mitra.dora.blink.model.chats.BlinkChat;
import sekhar.mitra.dora.blink.model.search.Photo;
import sekhar.mitra.dora.blink.model.search.Search;
import sekhar.mitra.dora.blink.utils.RecyclerItemClickListener;

public class Chats extends Fragment implements OnChipClickListener {

  private Gson gson;
  private RecyclerView mRecyclerView;
  private EditText msg;
  private ImageView send;
  private ChatAdapter chatAdapter;
  public static final String BOT_MESSAGE = "bot_message";
  public static final String USER_MESSAGE = "user_message";
  public static final String USER_CUSTOM = "sekhar";
  public static final String USER_BOT = "bot";
  private RecyclerView horizontal_recycler_view;
  private List<String> horizontalList;
  private HorizontalAdapter horizontalAdapter;

  public Chats() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.chats, container, false);
  }

  @Override public void onViewCreated(final View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    gson = new Gson();
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    msg = (EditText) view.findViewById(R.id.msg);
    send = (ImageView) view.findViewById(R.id.send);
    // SETTING HORIZANTAL RECYCLER VIEW...
    horizontal_recycler_view = (RecyclerView) view.findViewById(R.id.horizontal_recycler_view);
    horizontalList = Arrays.asList(getResources().getStringArray(R.array.input));
    LinearLayoutManager horizontalLayoutManagaer =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
    horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
    horizontalAdapter = new HorizontalAdapter(horizontalList, new OnChipClickListener() {
      @Override public void onChipClicked(String text) {
        processMessage(text, USER_CUSTOM);
      }
    });
    horizontal_recycler_view.setAdapter(horizontalAdapter);
    // SETTING VERTICAL RECYCLER VIEW
    send.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String text = msg.getText().toString();
        processMessage(text, USER_CUSTOM);
      }
    });
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(linearLayoutManager);
    chatAdapter = new ChatAdapter(getActivity(), Blink.getBlinkChatList());
    mRecyclerView.setAdapter(chatAdapter);
    mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
        new RecyclerItemClickListener.OnItemClickListener() {
          @Override public void onItemClick(View view, int position) {
            RecyclerView.ViewHolder holder =
                mRecyclerView.findViewHolderForAdapterPosition(position);
            if (holder instanceof ChatAdapter.BotViewHolder) {
              int pagerPosition =
                  ((ChatAdapter.BotViewHolder) holder).horizontalInfiniteCycleViewPager.getRealItem();
              BlinkChat blinkChat = chatAdapter.getBlinkChat(position);
              Photo photo = blinkChat.getSearchPhotos().getPhotos().getPhoto().get(pagerPosition);
            } else {

            }
          }
        }));
  }

  /**
   * Send User Message and fetch Result from Bot.
   * @param text
   * @param user
   */
  public void processMessage(String text, String user) {
    int page = 1;
    if (text.toLowerCase().equals("more")) {
      BlinkChat blinkChat = chatAdapter.getLastBotMessage();
      if (blinkChat != null) {
        page = blinkChat.getSearchPhotos().getPhotos().getPage();
        int pages = Integer.parseInt(blinkChat.getSearchPhotos().getPhotos().getPages());
        int total = Integer.parseInt(blinkChat.getSearchPhotos().getPhotos().getTotal());
        page = page + 1;
        text = blinkChat.getMessage();
      }
    }
    List<String> arrayList = Arrays.asList(getResources().getStringArray(R.array.input));
    if (arrayList.contains(text)) {
      BlinkChat blinkChat =
          new BlinkChat(user, text, Chats.USER_MESSAGE, System.currentTimeMillis());
      chatAdapter.addBlinkChat(blinkChat);
      mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount());
      msg.setText("");
      final String finalText = text;
      BlinkHttpClient.getClient()
          .searchPhotos(text, Integer.toString(page), Blink.REST_KEY)
          .enqueue(new Callback<Search>() {
            @Override public void onResponse(Call<Search> call, Response<Search> response) {
              if (response.isSuccessful()) {
                BlinkChat blinkChat = new BlinkChat(USER_BOT, response.body(), Chats.BOT_MESSAGE,
                    System.currentTimeMillis(), finalText);
                chatAdapter.addBlinkChat(blinkChat);
                mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount());
              }
            }

            @Override public void onFailure(Call<Search> call, Throwable t) {

            }
          });
    } else {
      Toast.makeText(getActivity(), R.string.not_allowed, Toast.LENGTH_SHORT).show();
    }
  }

  @Override public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      if (mRecyclerView != null) {
        chatAdapter.update(Blink.getBlinkChatList());
      }
    }
  }

  @Override public void onChipClicked(String text) {
  }
}
