package com.example.y2793623b.listadodecartas1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
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
//import android.net.Uri;
//import nl.littlerobots.cupboard.tools.provider.UriHelper;
//import static nl.qbusict.cupboard.CupboardFactory.cupboard;

import android.databinding.DataBindingUtil;

import com.example.y2793623b.listadodecartas1.databinding.FragmentActivityDetailBinding;
import com.example.y2793623b.listadodecartas1.databinding.FragmentMainBinding;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private ArrayList<Card> Listcartas;
    //private CartasAdapter adapter;
    private CartasCursorAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCartas = (ListView) view.findViewById(R.id.LvCartas);
        */
        //String[] cartas = {"carta1" , "carta2" , "carta3" , "carta4" , "carta5"};

        //Listcartas = new ArrayList<>(Arrays.asList(cartas));
        FragmentMainBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);
        View view = binding.getRoot();

        Listcartas = new ArrayList<>();
        /*
        adapter = new CartasAdapter(
                getContext(),
                R.layout.lv_cartas_row,
                Listcartas
        );
        */
        adapter = new CartasCursorAdapter(getContext(), Card.class);
        //lvCartas.setAdapter(adapter);

        binding.LvCartas.setAdapter(adapter);
        binding.LvCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Card carta = (Card) adapterView.getItemAtPosition(position);

                //intent para llamar Activity_detail
                Intent intent = new Intent(getContext(), Activity_detail.class);
                //poner las cartas dentro del intent
                intent.putExtra("card",carta);
                //llannem l'intent
                startActivity(intent);
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

    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

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
            DataManager.deleteCartas(getContext());
            DataManager.saveCartas(resultat, getContext());

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
    }


}