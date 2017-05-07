package sekhar.mitra.dora.blink.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import sekhar.mitra.dora.blink.Blink;
import sekhar.mitra.dora.blink.R;
import sekhar.mitra.dora.blink.adapters.FavouriteAdapter;
import sekhar.mitra.dora.blink.utils.RecyclerItemClickListener;
import sekhar.mitra.dora.blink.utils.SimpleDividerItemDecoration;

public class Favourite extends Fragment {
  private Gson gson;
  private RecyclerView mRecyclerView;
  private FavouriteAdapter favouriteAdapter;

  public Favourite() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.favourites, container, false);
  }

  @Override public void onViewCreated(final View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    gson = new Gson();
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_favourite);
    mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(linearLayoutManager);
    favouriteAdapter = new FavouriteAdapter(getActivity(), Blink.getBlinkFavouriteList());
    mRecyclerView.setAdapter(favouriteAdapter);
    mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
        new RecyclerItemClickListener.OnItemClickListener() {
          @Override public void onItemClick(View view, int position) {
            RecyclerView.ViewHolder holder =
                mRecyclerView.findViewHolderForAdapterPosition(position);
          }
        }));
  }

  @Override public void onResume() {
    super.onResume();
    if (mRecyclerView != null) {
      favouriteAdapter.notifyDataSetChanged();
    }
  }

  @Override public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      if (mRecyclerView != null) {
        favouriteAdapter.update(Blink.getBlinkFavouriteList());
      }
    }
  }
}
