package com.example.y2793623b.listadodecartas1;

import android.content.Intent;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class Activity_detailFragment extends Fragment {

    private View view;
    private ImageView cartaImage;
    private TextView cartaName;
    private TextView cartaColor;
    private TextView cartaSet;
    private TextView cartaText;
    private TextView cartaRarity;



    public Activity_detailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_activity_detail, container, false);

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

        cartaImage = (ImageView) view.findViewById(R.id.CartaImage);
        cartaName = (TextView) view.findViewById(R.id.CartaName);
        cartaColor = (TextView) view.findViewById(R.id.CartaColor);
        cartaSet = (TextView) view.findViewById(R.id.CartaSet);
        cartaRarity = (TextView) view.findViewById(R.id.CartaRarity);
        cartaText = (TextView) view.findViewById(R.id.CartaText);

    }
}
