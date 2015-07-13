package notdisliked.github.com.capra;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import notdisliked.github.com.capra.Models.Post;

/**
 * Created by notdi on 7/12/2015.
 */
public class PostAdapter extends ArrayAdapter<Post> {
    private final Context context;
    private List<Post> values = new ArrayList<Post>();

    public PostAdapter(Context context, List<Post> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    public void updatePosts(List<Post> posts) {
        System.out.println(posts);
        values.clear();
        values.addAll(posts);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View postView = inflater.inflate(R.layout.picture_post, parent, false);
        TextView postTitle = (TextView) postView.findViewById(R.id.post_title);
        postTitle.setText(values.get(position).title);
        return postView;
    }
}
