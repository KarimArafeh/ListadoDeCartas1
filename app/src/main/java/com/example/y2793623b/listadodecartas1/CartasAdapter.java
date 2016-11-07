package com.example.y2793623b.listadodecartas1;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by y2793623b on 07/11/16.
 */


public class CartasAdapter extends ArrayAdapter<Card> {


    public CartasAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
    }
}
