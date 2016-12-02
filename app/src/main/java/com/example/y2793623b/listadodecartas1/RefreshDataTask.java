package com.example.y2793623b.listadodecartas1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.alexvasilkov.events.Events;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by y2793623b on 02/12/16.
 */
class RefreshDataTask extends AsyncTask<Void, Void, Void> {

    private Context context;

    RefreshDataTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Events.post("start-downloading-data");
    }

    @Override
    protected Void doInBackground(Void... voids) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String colorIntroducido = preferences.getString("color" , "white");
        String tipoIntroducido = preferences.getString("rarity" , "todas");

        //CardAPI api = new CardAPI();

        ArrayList<Card> resultat = null;
        try {
            //resultat = api.getAllCards();

            if(tipoIntroducido.equals("todas"))
            {
                //resultat = api.getAllCards();
                resultat = CardAPI.getAllCards();
            }else
            {
                //resultat = api.getCartasPorTipo(tipoIntroducido,colorIntroducido);
                resultat = CardAPI.getCartasPorTipo(tipoIntroducido,colorIntroducido);
            }


            //Log.d("CARDS", resultat.toString());
            Log.d("CARDS", resultat != null ? resultat.toString() : null);

            //return resultat;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return null;
        //}
            /*
            UriHelper helper = UriHelper.with(CartasContentProvider.AUTHORITY);
            Uri cardUri = helper.getUri(Card.class);
            cupboard().withContext(getContext()).put(cardUri, Card.class, resultat);
            */
        DataManager.deleteCartas(context);
        DataManager.saveCartas(resultat, context);

        /*
        @Override
        protected void onPostExecute(ArrayList<Card> cards) {
            super.onPostExecute(cards);
            adapter.clear();
            for(int x = 0; x < cards.size(); x++)
            {
                adapter.add(cards.get(x));
            }
            */
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Events.post("finish-downloading-data");
    }
}
