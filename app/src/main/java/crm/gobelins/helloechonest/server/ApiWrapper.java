package crm.gobelins.helloechonest.server;

import android.content.Context;

import com.echonest.api.v4.BasicPlaylistParams;
import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Playlist;

import crm.gobelins.helloechonest.R;

/**
 * Created by lbeltramo on 19/01/2015.
 */
public class ApiWrapper {

    private static ApiWrapper sInstance;
    private EchoNestAPI mApi;

    private ApiWrapper(Context context) {
        mApi = new EchoNestAPI(context.getString(R.string.api_key));
    }

    public static ApiWrapper with(Context context) {
        if (sInstance == null) {
            sInstance = new ApiWrapper(context);
        }
        return sInstance;
    }

    public Playlist getSimilarArtists(int results, String artist)
            throws EchoNestException {
        BasicPlaylistParams params = new BasicPlaylistParams();
        params.addArtist(artist);
        params.setType(BasicPlaylistParams.PlaylistType.ARTIST_RADIO);
        params.setResults(results);
        return mApi.createBasicPlaylist(params);
    }

}
