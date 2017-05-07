package sekhar.mitra.dora.blink.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.pchmn.materialchips.ChipView;
import com.pchmn.materialchips.ChipsInput;
import java.util.List;
import sekhar.mitra.dora.blink.R;
import sekhar.mitra.dora.blink.java.OnChipClickListener;

/**
 * Created by user on 07/05/17.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

  private List<String> horizontalList;
  private OnChipClickListener onChipClickListener;

  public class MyViewHolder extends RecyclerView.ViewHolder {
    public ChipView chips_input;

    public MyViewHolder(View view) {
      super(view);
      chips_input = (ChipView) view.findViewById(R.id.chips_input);
    }
  }

  public HorizontalAdapter(List<String> horizontalList, OnChipClickListener onChipClickListener) {
    this.horizontalList = horizontalList;
    this.onChipClickListener = onChipClickListener;
  }

  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.horizontal_item_view, parent, false);
    return new MyViewHolder(itemView);
  }

  @Override public void onBindViewHolder(final MyViewHolder holder, int position) {
    holder.chips_input.setLabel(horizontalList.get(position));
    holder.chips_input.setOnChipClicked(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onChipClickListener.onChipClicked(holder.chips_input.getLabel().toString());
      }
    });
  }

  @Override public int getItemCount() {
    return horizontalList.size();
  }
}
