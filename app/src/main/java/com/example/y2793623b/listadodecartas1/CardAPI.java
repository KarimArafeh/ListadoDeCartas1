package com.example.y2793623b.listadodecartas1;

import android.net.Uri;

import java.io.IOException;

/**
 * Created by y2793623b on 18/10/16.
 */

public class CardAPI {

    private final String BASE_URL = "https://docs.magicthegathering.io/";


    String getAllCards() throws IOException {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("name")
                .build();

        String url = builtUri.toString();

        try {

            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }



}
