package com.example.y2793623b.listadodecartas1;

/**
 * Created by y2793623b on 25/11/16.
 */

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class CartasContentProvider extends CupboardContentProvider {


    // The content provider authority is used for building Uri's for the provider
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Card.class);
    }

    public CartasContentProvider() {
        super(AUTHORITY, 1);
    }

}
