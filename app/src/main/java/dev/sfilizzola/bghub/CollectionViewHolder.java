package dev.sfilizzola.bghub;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by samuel.filizzola on 03/07/2015.
 */
public class CollectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    protected ImageView img_collection;
    protected TextView title_collection, subtitle_collection;
    protected String IDBoardGame;

    public CollectionViewHolder(View view) {
        super(view);
        this.img_collection = (ImageView) view.findViewById(R.id.img_hot);
        this.title_collection = (TextView) view.findViewById(R.id.title_hot);
        this.subtitle_collection = (TextView) view.findViewById(R.id.rank_hot);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), GameActivity.class);
        intent.putExtra("BGGID", IDBoardGame);
        v.getContext().startActivity(intent);
    }
}
