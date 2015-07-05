package dev.sfilizzola.bghub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import dev.sfilizzola.bghub.Entidades.CollectionItem;

public class CollectionFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private final String LOG_TAG = getClass().getSimpleName();
    private List<CollectionItem> colecao;
    private RecyclerView recyclerView;
    private CollectionRecyclerViewAdapter collectionAdapter;
    private View mProgressView;
    private Context fragContext;
    private SwipeRefreshLayout swipeLayout;

    public static CollectionFragment newInstance(String param1, String param2) {
        CollectionFragment fragment = new CollectionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CollectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collection, container, false);

        fragContext = getActivity().getApplicationContext();
        //recycler view
        recyclerView = (RecyclerView) view.findViewById(R.id.collection_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(fragContext));

        collectionAdapter = new CollectionRecyclerViewAdapter(fragContext, new ArrayList<CollectionItem>());

        recyclerView.setAdapter(collectionAdapter);
        recyclerView.addOnItemTouchListener(new CollectionItemClickListener(fragContext, recyclerView, new CollectionItemClickListener.OnItemCLickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(fragContext, GameActivity.class);
                intent.putExtra("IDBGG", collectionAdapter.getCollectionItem(position).getID());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //Toast.makeText(fragContext, "Long Tap", Toast.LENGTH_SHORT).show();
            }
        }));

        mProgressView = view.findViewById(R.id.progress_collection);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);

        PopulaCollection();

        return view;
    }

    private void PopulaCollection() {
        showProgress(true);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Games_User");
        query.whereEqualTo("thisUser", ParseUser.getCurrentUser());
        query.include("thisGame");// inclui a classe referenciada por esse ponteiro.
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        //TODO Carrega celula de nenhum componente
                        Log.d(LOG_TAG, "Carregou nada.");
                        showProgress(false);
                        if (swipeLayout.isRefreshing())
                            swipeLayout.setRefreshing(false);
                    } else {
                        PreencheCollection(list);
                        Log.d(LOG_TAG, "Carregou lista de Games_User.");
                        showProgress(false);
                        if (swipeLayout.isRefreshing())
                            swipeLayout.setRefreshing(false);
                    }
                } else {
                    Log.d(LOG_TAG, "Deu ruim. ->" + e.getMessage());
                    showProgress(false);
                    if (swipeLayout.isRefreshing())
                        swipeLayout.setRefreshing(false);
                    //TODO carrega celula de erro
                }
            }
        });
    }

    private void PreencheCollection(List<ParseObject> list) {



        for (ParseObject item : list) {
            CollectionItem colItem = new CollectionItem();
            ParseObject game = item.getParseObject("thisGame");
            colItem.setID(game.getString("IdBGG"));
            colItem.setName(game.getString("GameName"));
            colItem.setImage(game.getString("PhotoUri"));
            colItem.setThumbnail(game.getString("ThumbUri"));
            colItem.setHaveIt(item.getBoolean("haveIt"));
            colItem.setWannaPlay(item.getBoolean("wannaPlay"));

            colecao.add(colItem);
        }
        collectionAdapter.loadNewData(colecao);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
            recyclerView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        PopulaCollection();
    }
}
