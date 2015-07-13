package notdisliked.github.com.capra.Models;

import android.view.View;

/**
 * Created by notdi on 7/10/2015.
 */
public class Post {
    String Title;
    int Likes;
    int Dislikes;
    String Name;
    String Date;
    String Subverse;
    String Id;
    String Content;


    public void openComments(View view){

        System.out.println("open comments!");
    }
}
