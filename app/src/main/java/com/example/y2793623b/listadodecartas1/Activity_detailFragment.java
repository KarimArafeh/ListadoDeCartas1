package com.example.y2793623b.listadodecartas1;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class Activity_detailFragment extends Fragment {

    public Activity_detailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activity_detail, container, false);

        Intent i = getActivity().getIntent();

        if(i != null)
        {
            Card carta = (Card) i.getSerializableExtra("carta");

            if(carta != null)
            {
                updateUi(carta);
            }

        }


        return view;
    }

    private void updateUi(Card carta)
    {
        Log.d("CARD", carta.toString());
    }
}
