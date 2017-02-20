package org.thiagoelias.helpers;

import com.oracle.javafx.jmx.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.thiagoelias.models.Post;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Thiago Elias on 19/02/17.
 */
public class WebService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private String fetchUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
            connection.setRequestProperty("Accept","*/*");
            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            String response = "";

            while (scanner.hasNext()) {
                response += scanner.nextLine();
            }

            return response;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Post fetchPost(int id) {

        String json = fetchUrl(BASE_URL + "posts/" + String.valueOf(id));

        if (json == null)
            return null;

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            Post post = new Post();

            if (jsonObject.has("userId"))
                post.setUserId(jsonObject.getInt("userId"));

            if (jsonObject.has("id"))
                post.setId(jsonObject.getInt("id"));

            if (jsonObject.has("title"))
                post.setTitle(jsonObject.getString("title"));

            if (jsonObject.has("body"))
                post.setBody(jsonObject.getString("body"));

            return post;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Post> fetchAllPosts() {
        String json = fetchUrl(BASE_URL + "posts/");

        if (json == null)
            return null;

        JSONArray jarray = null;
        try {
            jarray = new JSONArray(json);
            ArrayList<Post> posts = new ArrayList<>();
            if (jarray != null) {

                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jsonObject = jarray.getJSONObject(i);
                    if (jsonObject != null) {
                        Post post = new Post();
                        if (jsonObject.has("userId"))
                            post.setUserId(jsonObject.getInt("userId"));

                        if (jsonObject.has("id"))
                            post.setId(jsonObject.getInt("id"));

                        if (jsonObject.has("title"))
                            post.setTitle(jsonObject.getString("title"));

                        if (jsonObject.has("body"))
                            post.setBody(jsonObject.getString("body"));

                        posts.add(post);
                    }
                }

                return posts;

            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
