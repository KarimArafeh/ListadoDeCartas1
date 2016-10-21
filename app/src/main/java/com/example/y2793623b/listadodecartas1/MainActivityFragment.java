package com.example.y2793623b.listadodecartas1;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private ArrayList<String> Listcartas;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCartas = (ListView) view.findViewById(R.id.LvCartas);

        String[] cartas = {"carta1" , "carta2" , "carta3" , "carta4" , "carta5"};

        Listcartas = new ArrayList<>(Arrays.asList(cartas));

        adapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.lv_cartas_row,
                R.id.txt_NameCard,
                Listcartas
        );

        lvCartas.setAdapter(adapter);
        return view;
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
            try {

                refresh();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() throws IOException {

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
        protected Void doInBackground(Void... params) {

            CardAPI api = new CardAPI();
            String cards = null;

            try {

                cards = api.getAllCards();

            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("CARDS", cards);

            return null;
        }
    }


}