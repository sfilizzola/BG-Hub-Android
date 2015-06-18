package dev.sfilizzola.bghub;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by samuel.filizzola on 18/06/2015.
 */
public class SearchResultHolder extends RecyclerView.ViewHolder {

    protected TextView title_search, subtitle_search;

    public SearchResultHolder(View view) {
        super(view);
        this.title_search = (TextView) view.findViewById(R.id.title_search);
        this.subtitle_search = (TextView) view.findViewById(R.id.subtitle_search);
    }


}

