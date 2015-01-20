package crm.gobelins.helloechonest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.echonest.api.v4.Song;

import crm.gobelins.helloechonest.playlist.PlaylistFragment;
import crm.gobelins.helloechonest.server.ApiWrapper;
import crm.gobelins.helloechonest.song.SongActivity;


public class MainActivity extends ActionBarActivity implements PlaylistFragment.OnSongClickListener {

    private static final String TAG = "MAIN_ACTIVITY";
    public static final String TITLE = "TITLE";
    public static final String ARTIST = "ARTIST";
    public static final String IMAGE = "IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, PlaylistFragment.newInstance(10, "Alec Empire"))
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    
    @Override
    public void onSongClick(Song song) {
        Log.d(TAG, song.getTitle());

        Intent intent = new Intent(this, SongActivity.class);

        intent.putExtra(TITLE, song.getTitle());
        intent.putExtra(ARTIST, song.getArtistName());
        intent.putExtra(IMAGE, ApiWrapper.getReleaseImage(song));

        startActivity(intent);
    }
}
