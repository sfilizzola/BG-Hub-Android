package dev.sfilizzola.bghub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dev.sfilizzola.bghub.Entidades.SearchResult;

/**
 * Created by samuel.filizzola on 18/06/2015.
 */
public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchResultHolder> {

    private List<SearchResult> Results;
    private Context mContext;
    private final String LOG_TAG = SearchRecyclerViewAdapter.class.getSimpleName();

    public SearchRecyclerViewAdapter(Context mContext, List<SearchResult> results) {
        Results = results;
        this.mContext = mContext;
    }

    @Override
    public SearchResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search_result, null);;
        SearchResultHolder searchResultHolder = new SearchResultHolder(view);
        return  searchResultHolder;
    }

    @Override
    public void onBindViewHolder(SearchResultHolder searchHolder, int position) {

        SearchResult item = Results.get(position);

        searchHolder.title_search.setText(item.getName());
        searchHolder.subtitle_search.setText(item.getType());
        searchHolder.IDBoardgame = item.getID();

    }

    @Override
    public int getItemCount() {
        return (Results != null ? Results.size() : 0);
    }

    public void loadNewData (List<SearchResult> NovaBusca){
        Results = NovaBusca;
        notifyDataSetChanged();
    }

    public SearchResult getSearchItem (int position){
        return (Results != null ? Results.get(position) : null);
    }
}
