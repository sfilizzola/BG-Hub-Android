package dev.sfilizzola.bghub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import dev.sfilizzola.bghub.Entidades.CollectionItem;

/**
 * Created by samuel.filizzola on 03/07/2015.
 */
public class CollectionRecyclerViewAdapter extends RecyclerView.Adapter<CollectionViewHolder>
{
    private List<CollectionItem> Collection;
    private Context mContext;
    private final String LOG_TAG = getClass().getSimpleName();

    public CollectionRecyclerViewAdapter(Context mContext, List<CollectionItem> collection) {
        this.mContext = mContext;
        Collection = collection;
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_collection, null);
        CollectionViewHolder collectionViewHolder = new CollectionViewHolder(view);
        return  collectionViewHolder;
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int i) {

        CollectionItem colItem = Collection.get(i);

        Log.d(LOG_TAG, "Processing: " + colItem.getThumbnail() + " --> " + Integer.toString(i));

        //picasso biblioteca de imagem
        Picasso.with(mContext).load(colItem.getThumbnail())
                .error(R.drawable.placeh)
                .placeholder(R.drawable.placeh)
                .into(holder.img_collection);

        holder.title_collection.setText(colItem.getName());

        String subtitle;

        if (colItem.isHaveIt() && !colItem.isWannaPlay()){
            subtitle = "Have it!";
        } else if (!colItem.isHaveIt() && colItem.isWannaPlay()){
            subtitle = "Wanna Play it!";
        } else {
            subtitle = "I Want It Now!";
        }

        holder.subtitle_collection.setText(subtitle);
        holder.IDBoardGame = colItem.getID();
    }

    @Override
    public int getItemCount() {
        return Collection != null ? Collection.size() : 0;
    }

    public void loadNewData (List<CollectionItem> NovosItens){
        Collection = NovosItens;
        notifyDataSetChanged();
    }

    public CollectionItem getCollectionItem(int position){
        return Collection != null ? Collection.get(position) : null;
    }
}
