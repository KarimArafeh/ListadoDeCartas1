package com.example.y2793623b.listadodecartas1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by y2793623b on 07/11/16.
 */


public class CartasAdapter extends ArrayAdapter<Card> {


    public CartasAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Obtenim l'objecte en la possició corresponent
        Card carta = getItem(position);
        Log.w("xx", carta.toString());

        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_cartas_row, parent, false);
        }

        //unim el codi en les views del Layout
        TextView cartaNom = (TextView) convertView.findViewById(R.id.CartaNom);

        TextView cartaColor = (TextView) convertView.findViewById(R.id.CartaColor);
        ImageView cartaImage = (ImageView) convertView.findViewById(R.id.CartaImage);

        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        cartaNom.setText(carta.getName());
        cartaColor.setText(carta.getColors());

        Glide.with(getContext()).load(carta.getImageUrl()).into(cartaImage);


        // Retornem la View replena per a mostrarla
        return convertView;

    }
}
