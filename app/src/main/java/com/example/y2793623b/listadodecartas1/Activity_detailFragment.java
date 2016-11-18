package com.example.y2793623b.listadodecartas1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import org.ecaib.rottentomatoesclient2016.databinding.FragmentDetailBinding;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawableLoadProvider;
import com.example.y2793623b.listadodecartas1.databinding.FragmentActivityDetailBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class Activity_detailFragment extends Fragment {

    /*
    private View view;
    private ImageView cartaImage;
    private TextView cartaName;
    private TextView cartaColor;
    private TextView cartaSet;
    private TextView cartaText;
    private TextView cartaRarity;
    */
    private FragmentActivityDetailBinding binding;



    public Activity_detailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //view = inflater.inflate(R.layout.fragment_activity_detail, container, false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_activity_detail, container, false);

        View view = binding.getRoot();

        Intent i = getActivity().getIntent();

        if(i != null)
        {
            Card carta = (Card) i.getSerializableExtra("card");

            //Log.d("--->>>>>>>>>>>","dentro del if (1)");
            if(carta != null)
            {
                //Log.d("--->>>>>>>>>>>","dentro del if (2)");
                updateUi(carta);
            }

        }


        return view;
    }

    private void updateUi(Card carta)
    {
        Log.d("CARD", carta.toString());
        /*
        cartaImage = (ImageView) view.findViewById(R.id.CartaImage);
        cartaName = (TextView) view.findViewById(R.id.CartaName);
        cartaColor = (TextView) view.findViewById(R.id.CartaColor);
        cartaSet = (TextView) view.findViewById(R.id.CartaSet);
        cartaRarity = (TextView) view.findViewById(R.id.CartaRarity);
        cartaText = (TextView) view.findViewById(R.id.CartaText);

        cartaName.setText(carta.getName());
        cartaColor.setText(carta.getColors());
        cartaSet.setText(carta.getSet());
        cartaRarity.setText(carta.getRarity());
        cartaText.setText(carta.getText());
        */
        binding.CartaName.setText(carta.getText());
        binding.CartaColor.setText(carta.getColors());
        binding.CartaRarity.setText(carta.getRarity());
        binding.CartaSet.setText(carta.getSet());
        binding.CartaText.setText(carta.getText());
        Glide.with(getContext()).load(carta.getImageUrl()).into(binding.CartaImage);


    }
}
