package dev.sfilizzola.bghub;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Samuel on 04/06/2015.
 */
public class HotsItemHolder extends RecyclerView.ViewHolder {

    protected ImageView img_hot;
    protected TextView title_hot, rank_hot, year_hot;

    public HotsItemHolder(View view) {
        super(view);
        this.img_hot = (ImageView) view.findViewById(R.id.img_hot);
        this.title_hot = (TextView) view.findViewById(R.id.title_hot);
        this.rank_hot = (TextView) view.findViewById(R.id.rank_hot);
        this.year_hot = (TextView) view.findViewById(R.id.year_hot);

    }
}
