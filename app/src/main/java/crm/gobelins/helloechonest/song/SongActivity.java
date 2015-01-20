package crm.gobelins.helloechonest.song;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import crm.gobelins.helloechonest.MainActivity;
import crm.gobelins.helloechonest.R;

public class SongActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Intent intent = getIntent();

        String title = intent.getStringExtra(MainActivity.TITLE);
        String artist = intent.getStringExtra(MainActivity.ARTIST);
        String image = intent.getStringExtra(MainActivity.IMAGE);

        SongDetailFragment fragment = new SongDetailFragment();
        fragment.setTitle(title);
        fragment.setArtist(artist);
        fragment.setImage(image);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_song, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SongDetailFragment extends Fragment {

        private TextView mTitleTv;
        private TextView mArtistTv;
        private ImageView mImageView;

        private String mTitle;
        private String mArtist;
        private String mImage;

        public SongDetailFragment() {
        }

        public void setTitle(String mTitle) {
            this.mTitle = mTitle;
        }

        public void setArtist(String mArtist) {
            this.mArtist = mArtist;
        }

        public void setImage(String mImage) {
            this.mImage = mImage;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_song_detail, container, false);

            mTitleTv = (TextView) rootView.findViewById(R.id.title);
            mArtistTv = (TextView) rootView.findViewById(R.id.artist);
            mImageView = (ImageView) rootView.findViewById(R.id.image);

            mTitleTv.setText(mTitle);
            mArtistTv.setText(mArtist);
            Picasso.with(getActivity()).load(mImage).into(mImageView);

            return rootView;
        }
    }
}
