package notdisliked.github.com.capra;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import notdisliked.github.com.capra.Models.Post;
import com.google.common.io.ByteStreams;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.Charsets;
import org.json.JSONObject;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView postView = (ListView) findViewById(R.id.post_list);
        ListAdapter postAdapter = new PostAdapter(this, R.layout.picture_post);
        postView.setAdapter(postAdapter);
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
        if(id == R.id.action_search){
            System.out.println("searching!");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void openComments(View view){
//        new CallAPI().execute("https://voat.co/api/frontpage");
        new CallAPI().execute("https://fakevout.azurewebsites.net/api/v1/v/nsfw"); //Test, will remove
    }

    private class CallAPI extends AsyncTask<String, String, String> {
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
//                String ins = IOUtils.toString(in, "UTF-8");
                jsonString = new String(ByteStreams.toByteArray(in), Charsets.UTF_8);



                System.out.println(in);
            } catch (Exception e){
                System.out.println(e.getMessage());
                return e.getMessage();
            }

            return jsonString;
        }
        protected void onPostExecute(String result){

        }
    }

//    JSONObject jsonObj = new JSONObject(jsonString);
}
