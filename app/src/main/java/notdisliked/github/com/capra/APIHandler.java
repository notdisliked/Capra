package notdisliked.github.com.capra;

import android.os.AsyncTask;

import com.google.common.io.ByteStreams;

import org.apache.commons.io.Charsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import notdisliked.github.com.capra.Models.Post;

/**
 * Created by tinlun123 on 7/12/2015.
 */
public class APIHandler extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... params){
        String urlString=params[0]; //Get URL
        InputStream in = null; //
        String jsonString = "";
        try{
            URL url = new URL(urlString); //
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Voat-ApiKey", "KGdCs0Paz/TNEQzsuucVyg==");

            System.out.println(urlConnection.getResponseCode());
            System.out.println(urlConnection.getResponseMessage());
            in = new BufferedInputStream(urlConnection.getInputStream());


            jsonString = new String(ByteStreams.toByteArray(in), Charsets.UTF_8);
            System.out.println(jsonString);



            System.out.println(in);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }

        return jsonString;
    }
    protected void onPostExecute(String result){
        JSONObject response = null;
        JSONArray jsonPosts = null;
        try {
            response = new JSONObject(result);
            jsonPosts = response.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<Post> posts = new ArrayList<Post>();
        for (int i = 0; i < jsonPosts.length(); i++) {
            Post post = null;
            try {
                JSONObject jsonPost = jsonPosts.getJSONObject(i);
                post = new Post(jsonPost);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            posts.add(post);
            System.out.println(post.title);

        }
        MainActivity.adapter.updatePosts(posts);

    }
}
