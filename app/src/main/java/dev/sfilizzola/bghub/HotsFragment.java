package dev.sfilizzola.bghub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.sfilizzola.bghub.BLL.BoardGames;
import dev.sfilizzola.bghub.Entidades.HotItem;


public class HotsFragment extends Fragment {

    private final String LOG_TAG = getClass().getSimpleName();
    private List<HotItem> Hots;
    private RecyclerView recyclerView;
    private HotsRecyclerViewAdapter hotsRecyclerViewAdapter;
    private View mProgressView;
    private Context fragContext;

    public static HotsFragment newInstance() {
        HotsFragment fragment = new HotsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HotsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_hots, container, false);

        fragContext = getActivity().getApplicationContext();
        //recycler view
        recyclerView = (RecyclerView) view.findViewById(R.id.hot_recycler_view);
        recyclerView.setLayoutManager( new LinearLayoutManager(fragContext));

        hotsRecyclerViewAdapter = new HotsRecyclerViewAdapter(fragContext, new ArrayList<HotItem>());

        recyclerView.setAdapter(hotsRecyclerViewAdapter);
        recyclerView.addOnItemTouchListener(new HotItemClickListener(fragContext, recyclerView, new HotItemClickListener.OnItemCLickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(fragContext, "Normal Tap", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(fragContext, GameActivity.class );
                intent.putExtra("IDBGG", hotsRecyclerViewAdapter.getHotItem(position).getId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(fragContext, "Long Tap", Toast.LENGTH_SHORT).show();
            }
        }));


        mProgressView = getActivity().findViewById(R.id.progress_main);


        PopulaListaHots();

        return view;
    }



    @Override
    public void onResume() {
        Log.d(LOG_TAG, "Hots OnResume is called");
        if (Hots == null || Hots.size() == 0 ){
            PopulaListaHots();
        } else {
            hotsRecyclerViewAdapter.loadNewData(Hots);
        }
        super.onResume();
    }

    private void PopulaListaHots() {
        showProgress(true);
        TestTask mTask = new TestTask();
        mTask.execute();
    }

    public class TestTask  extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            BoardGames BLL = new BoardGames();
            Hots = BLL.Top50();
            BLL.Dispose();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            hotsRecyclerViewAdapter.loadNewData(Hots);
           showProgress(false);
        }
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

}
