package notdisliked.github.com.capra;

import android.os.AsyncTask;

import com.google.common.io.ByteStreams;

import org.apache.commons.io.Charsets;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    }
}
