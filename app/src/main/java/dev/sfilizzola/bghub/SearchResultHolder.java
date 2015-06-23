package dev.sfilizzola.bghub;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by samuel.filizzola on 18/06/2015.
 */
public class SearchResultHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView title_search, subtitle_search;
    protected String IDBoardgame;

    public SearchResultHolder(View view) {
        super(view);
        this.title_search = (TextView) view.findViewById(R.id.title_search);
        this.subtitle_search = (TextView) view.findViewById(R.id.subtitle_search);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), GameActivity.class);
        intent.putExtra("BGGID", IDBoardgame);
        v.getContext().startActivity(intent);
    }
}

