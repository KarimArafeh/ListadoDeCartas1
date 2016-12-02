package com.example.y2793623b.listadodecartas1;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.y2793623b.listadodecartas1.databinding.LvCartasRowBinding;

/**
 * Created by y2793623b on 02/12/16.
 */

public class CartasCursorAdapter extends CupboardCursorAdapter<Card> {


    public CartasCursorAdapter(Context context, Class<Card> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Card model, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        LvCartasRowBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.lv_cartas_row, parent, false);

        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Card model) {

        LvCartasRowBinding binding = DataBindingUtil.getBinding(view);
        binding.CartaNom.setText(model.getName());
        Glide.with(context).load(model.getImageUrl()).into(binding.CartaImage);

    }
}
