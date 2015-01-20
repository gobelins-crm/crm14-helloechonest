package crm.gobelins.helloechonest.playlist;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Playlist;
import com.echonest.api.v4.Song;

import crm.gobelins.helloechonest.server.ApiWrapper;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link crm.gobelins.helloechonest.playlist.PlaylistFragment.OnSongClickListener}
 * interface.
 */
public class PlaylistFragment extends ListFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_RESULTS = "results";
    private static final String ARG_ARTIST = "artist";

    private int mResults;
    private String mArtist;

    private OnSongClickListener mListener;

    public static PlaylistFragment newInstance(int results, String artist) {
        PlaylistFragment fragment = new PlaylistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RESULTS, results);
        args.putString(ARG_ARTIST, artist);
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlaylistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mResults = getArguments().getInt(ARG_RESULTS);
            mArtist = getArguments().getString(ARG_ARTIST);
        }

        final PlaylistAdapter adapter = new PlaylistAdapter(getActivity());


        class PlaylistAsyncTask extends AsyncTask<Void, Void, Void> {

            private Playlist playlist;

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    playlist = ApiWrapper.with(getActivity())
                            .getSimilarArtists(mResults, mArtist);
                } catch (EchoNestException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if(null != playlist) {
                    adapter.addAll(playlist.getSongs());
                } else {
                    Toast.makeText(getActivity(), "API error", Toast.LENGTH_LONG);
                }
            }
        }

        new PlaylistAsyncTask().execute();

        setListAdapter(adapter);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnSongClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSongClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Song song = (Song) getListAdapter().getItem(position);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onSongClick(song);
        }
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
    public interface OnSongClickListener {
        public void onSongClick(Song song);
    }

}
