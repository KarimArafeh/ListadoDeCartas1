package com.example.y2793623b.listadodecartas1;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private ArrayList<Card> Listcartas;
    private CartasAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCartas = (ListView) view.findViewById(R.id.LvCartas);

        //String[] cartas = {"carta1" , "carta2" , "carta3" , "carta4" , "carta5"};

        //Listcartas = new ArrayList<>(Arrays.asList(cartas));
        Listcartas = new ArrayList<>();
        adapter = new CartasAdapter(
                getContext(),
                R.layout.lv_cartas_row,
                Listcartas
        );

        lvCartas.setAdapter(adapter);

        lvCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    //Notifiquem a l'Activity que anem a agregar items de menu.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //Agreguem els items de menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    //Gestionem el clicks en "Refresh"

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_refresh)
        {
            refresh();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {

        /*
        CardAPI api = new CardAPI();
        String cards = api.getAllCards();

        Log.d("CARDS", cards);
        */
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Card>> {


        @Override
        protected ArrayList<Card> doInBackground(Void... params) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

            String colorIntroducido = preferences.getString("color" , "white");
            String tipoIntroducido = preferences.getString("rarity" , "todas");

            CardAPI api = new CardAPI();

            ArrayList<Card> cards;
            try {
                //cards = api.getAllCards();

                if(tipoIntroducido.equals("todas"))
                {
                    cards = api.getAllCards();
                }else
                {
                    cards = api.getCartasPorTipo(tipoIntroducido,colorIntroducido);
                }


                //Log.d("CARDS", cards.toString());
                Log.d("CARDS", cards != null ? cards.toString() : null);

                return cards;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Card> cards) {
            super.onPostExecute(cards);
            adapter.clear();
            for(int x = 0; x < cards.size(); x++)
            {
                adapter.add(cards.get(x));
            }
        }
    }


}