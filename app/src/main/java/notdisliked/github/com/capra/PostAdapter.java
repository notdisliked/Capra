package notdisliked.github.com.capra;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

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

        Post postInfo = values.get(position);
        ((TextView) postView.findViewById(R.id.post_title)).setText(postInfo.title);
        ((TextView) postView.findViewById(R.id.post_info_1))
                .setText(new Formatter(new StringBuilder(), Locale.US).format("%d (%d|-%d) points and %d comments",
                        postInfo.upVotes - postInfo.downVotes,
                        postInfo.upVotes,
                        postInfo.downVotes,
                        postInfo.commentCount).toString());

        Instant date = new Instant().parse(postInfo.date);
        Instant now = new Instant();
        Interval diff = new Interval(date, now);
        Period period = diff.toPeriod();
        System.out.println(period.getSeconds());
        String timeString;
        if (period.getYears() == 0) {
            if (period.getMonths() == 0) {
                if (period.getDays() == 0) {
                    if (period.getHours() == 0) {
                        if (period.getMinutes() == 0) {
                            timeString = period.getSeconds() + " second" + (period.getSeconds() == 1 ? "" : "s");
                        } else {
                            timeString = period.getMinutes() + " minute" + (period.getMinutes() == 1 ? "" : "s");
                        }
                    } else {
                        timeString = period.getHours() + " hour" + (period.getHours() == 1 ? "" : "s");
                    }
                } else {
                    timeString = period.getDays() + " day" + (period.getDays() == 1 ? "" : "s");
                }
            } else {
                timeString = period.getMonths() + " month" + (period.getMonths() == 1 ? "" : "s");
            }
        } else {
            timeString = period.getYears() + " year" + (period.getYears() == 1 ? "" : "s");
        }
        ((TextView) postView.findViewById(R.id.post_info_2))
                .setText(new Formatter(new StringBuilder(), Locale.US).format("%s ago by %s to %s",
                        timeString,
                        postInfo.userName,
                        postInfo.subverse).toString());
        return postView;

        //1799 (+2823|-2224) points and 2489 comments
        //53 minutes ago by submitter to subverse
    }
}
