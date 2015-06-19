package dev.sfilizzola.bghub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import dev.sfilizzola.bghub.Entidades.HotItem;

/**
 * Created by Samuel on 04/06/2015.
 */
public class HotsRecyclerViewAdapter extends RecyclerView.Adapter<HotsItemHolder> {

    private List<HotItem> Hots;
    private Context mContext;
    private final String LOG_TAG = HotsRecyclerViewAdapter.class.getSimpleName();

    public HotsRecyclerViewAdapter(Context mContext, List<HotItem> hots) {
        Hots = hots;
        this.mContext = mContext;
    }

    @Override
    public HotsItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_hot_layout, null);
        HotsItemHolder hotsItemHolder = new HotsItemHolder(view);
        return  hotsItemHolder;
    }

    @Override
    public void onBindViewHolder(HotsItemHolder hotsItemHolder, int i) {
        HotItem hotItem = Hots.get(i);

        Log.d(LOG_TAG, "Processing: " + hotItem.getThumbnail() + " --> " + Integer.toString(i));

        //picasso biblioteca de imagem
        Picasso.with(mContext).load(hotItem.getThumbnail())
                .error(R.drawable.placeh)
                .placeholder(R.drawable.placeh)
                .into(hotsItemHolder.img_hot);

        hotsItemHolder.title_hot.setText(hotItem.getName());
        hotsItemHolder.year_hot.setText(hotItem.getYearpublished());
        hotsItemHolder.rank_hot.setText(hotItem.getRank()+"");
    }



    @Override
    public int getItemCount() {
        return (Hots != null ? Hots.size() : 0);
    }

    public void loadNewData (List<HotItem> NovosHots){
        Hots = NovosHots;
        notifyDataSetChanged();
    }

    public HotItem getHotItem (int position){
        return (Hots != null ? Hots.get(position) : null);
    }
}
