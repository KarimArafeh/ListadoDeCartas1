package com.example.y2793623b.listadodecartas1;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by y2793623b on 18/10/16.
 */

public class CardAPI {

    private final String BASE_URL = "https://api.magicthegathering.io/v1/cards";


    ArrayList<Card> getAllCards() throws IOException {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .build();

        String url = builtUri.toString();
/*
        try {

            String JsonResponse = HttpUtils.get(url);
            return JsonResponse;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
*/

        return doCall(url);
    }


    ArrayList<Card> getCartasPorTipo(String tipo, String color) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("rarity", tipo)
                .appendQueryParameter("colors", color)
                .build();

        String url = builtUri.toString();
        Log.d("URL", url);

               return doCall(url);
           }



    private ArrayList<Card> doCall(String url) {

        String JsonResponse = null;

        try {

            JsonResponse = HttpUtils.get(url);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return processJson(JsonResponse);

    }

    private ArrayList<Card> processJson(String jsonResponse) {

        ArrayList<Card> cartas = new ArrayList<>();

        try {

            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCartas = data.getJSONArray("cards");
            for (int x = 0; x < jsonCartas.length(); x ++)
            {
                JSONObject jsonCard = jsonCartas.getJSONObject(x);

                Card carta = new Card();

                carta.setName(jsonCard.getString("name"));
                carta.setType(jsonCard.getString("type"));
                carta.setRarity(jsonCard.getString("rarity"));
                carta.setSet(jsonCard.getString("set"));
                if(jsonCard.has("text"))
                {
                    carta.setText(jsonCard.getString("text"));
                }else
                    carta.setText(null);

                if(jsonCard.has("colors"))
                {
                    String total_colors="";
                    JSONArray colors = jsonCard.getJSONArray("colors");
                    for (int i = 0; i < colors.length(); i++) {
                        total_colors += colors.get(i).toString() + ", ";
                    }

                    carta.setColors(total_colors.substring(0, total_colors.length()-2));

                }else
                    carta.setColors(null);

                if(jsonCard.has("imageUrl"))
                {
                    carta.setImageUrl(jsonCard.getString("imageUrl"));
                }else
                    carta.setImageUrl(null);




                cartas.add(carta);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;

    }


}
