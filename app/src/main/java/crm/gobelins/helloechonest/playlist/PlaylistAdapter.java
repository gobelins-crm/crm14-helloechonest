package crm.gobelins.helloechonest.playlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.echonest.api.v4.Song;

/**
 * Created by lbeltramo on 19/01/2015.
 */
public class PlaylistAdapter extends ArrayAdapter<Song> {

    private LayoutInflater mInflater;

    public PlaylistAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_2);

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = mInflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        } else {
            view = convertView;
        }

        Song song = getItem(position);

        TextView title = (TextView) view.findViewById(android.R.id.text1);
        TextView artist = (TextView) view.findViewById(android.R.id.text2);

        title.setText(song.getTitle());
        artist.setText(song.getArtistName());

        return view;
    }
}
