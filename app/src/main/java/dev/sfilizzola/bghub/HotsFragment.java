package dev.sfilizzola.bghub;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dev.sfilizzola.bghub.BLL.BoardGames;
import dev.sfilizzola.bghub.Entidades.HotItem;


public class HotsFragment extends Fragment {

    private List<HotItem> Hots;
    private RecyclerView recyclerView;
    private HotsRecyclerViewAdapter hotsRecyclerViewAdapter;
    private View mProgressView;
    private Context fragContext;

    private OnFragmentInteractionListener mListener;


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
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager( new LinearLayoutManager(fragContext));

        hotsRecyclerViewAdapter = new HotsRecyclerViewAdapter(fragContext, new ArrayList<HotItem>());

        recyclerView.setAdapter(hotsRecyclerViewAdapter);

        mProgressView = view.findViewById(R.id.progress_main);


        TestTask mTask = new TestTask();
        mTask.execute();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
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
        }
    }

}
