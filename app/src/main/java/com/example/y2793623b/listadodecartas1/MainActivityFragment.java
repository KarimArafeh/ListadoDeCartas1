package com.example.y2793623b.listadodecartas1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
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

import com.alexvasilkov.events.Events;
import com.example.y2793623b.listadodecartas1.databinding.FragmentActivityDetailBinding;
import com.example.y2793623b.listadodecartas1.databinding.FragmentMainBinding;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
//public class MainActivityFragment extends Fragment {
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{


    //private ArrayList<Card> Listcartas;
    //private CartasAdapter adapter;
    private CartasCursorAdapter adapter;
    private ProgressDialog dialog;

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

        //Listcartas = new ArrayList<>();
        /*
        adapter = new CartasAdapter(
                getContext(),
                R.layout.lv_cartas_row,
                Listcartas
        );
        */
        adapter = new CartasCursorAdapter(getContext(), Card.class);
        //lvCartas.setAdapter(adapter);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("esta Loading cabrones .....");

        binding.LvCartas.setAdapter(adapter);
        binding.LvCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Card carta = (Card) adapterView.getItemAtPosition(position);

                /*
                //intent para llamar Activity_detail
                Intent intent = new Intent(getContext(), Activity_detail.class);
                //poner las cartas dentro del intent
                intent.putExtra("card",carta);
                */
                if(!esTablet())
                {
                    //intent para llamar Activity_detail
                    Intent intent = new Intent(getContext(), Activity_detail.class);
                    //poner las cartas dentro del intent
                    intent.putExtra("card",carta);
                    //llannem l'intent
                    startActivity(intent);
                } else
                {
                    Events.create("card-selected").param(carta).post();
                }
            }
        });

        getLoaderManager().initLoader(0, null, this);

        return view;
    }

    boolean esTablet()
    {
        return getResources().getBoolean(R.bool.tablet);
    }

    @Override
    public void onStart() {
        super.onStart();
        //refresh();
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
        RefreshDataTask task = new RefreshDataTask(getActivity().getApplicationContext());
        task.execute();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }





}