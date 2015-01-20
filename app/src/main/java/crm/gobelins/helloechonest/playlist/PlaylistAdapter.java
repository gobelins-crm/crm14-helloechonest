package crm.gobelins.helloechonest.playlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.echonest.api.v4.Song;
import com.squareup.picasso.Picasso;

import crm.gobelins.helloechonest.R;

/**
 * Created by lbeltramo on 19/01/2015.
 */
public class PlaylistAdapter extends ArrayAdapter<Song> {

    private LayoutInflater mInflater;

    public PlaylistAdapter(Context context) {
        super(context, R.layout.fragment_song);

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.fragment_song, parent, false);
        } else {
            view = convertView;
        }

        Song song = getItem(position);

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView artist = (TextView) view.findViewById(R.id.artist);
        ImageView image = (ImageView) view.findViewById(R.id.image);

        String imageUrl;
        try {
            imageUrl = song.getString("tracks[0].release_image");
        } catch (IndexOutOfBoundsException e) {
            imageUrl = null;
        }

        if (imageUrl != null) {
            Picasso.with(getContext())
                    .load(imageUrl)
                    .into(image);
        }

        title.setText(song.getTitle());
        artist.setText(song.getArtistName());

        return view;
    }
}
