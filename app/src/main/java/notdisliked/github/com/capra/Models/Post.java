package notdisliked.github.com.capra.Models;

import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by notdi on 7/10/2015.
 */
public class Post {
    public int id;
    public int commentCount;
    public String date;
    public int upVotes;
    public int downVotes;
    public String lastEditDate;
    public int views;
    public String userName;
    public String subverse;
    public String thumbnail;
    public String title;
    public int type;
    public String url;
    public String content;
    public String formattedContent;

    public Post(int id, int commentCount, String date, int upVotes, int downVotes, String lastEditDate, int views, String userName, String subverse, String thumbnail, String title, int type, String url, String content, String formattedContent) {
        this.id = id;
        this.commentCount = commentCount;
        this.date = date;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.lastEditDate = lastEditDate;
        this.views = views;
        this.userName = userName;
        this.subverse = subverse;
        this.thumbnail = thumbnail;
        this.title = title;
        this.type = type;
        this.url = url;
        this.content = content;
        this.formattedContent = formattedContent;
    }

    public Post(JSONObject post) {
        try {
            id = post.getInt("id");
            commentCount = post.getInt("commentCount");
            date = post.getString("date");
            upVotes = post.getInt("upVotes");
            downVotes = post.getInt("downVotes");
            lastEditDate = post.getString("lastEditDate");
            views = post.getInt("views");
            userName = post.getString("userName");
            subverse = post.getString("subverse");
            thumbnail = post.getString("thumbnail");
            title = post.getString("title");
            type = post.getInt("type");
            url = post.getString("url");
            content = post.getString("content");
            formattedContent = post.getString("formattedContent");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
