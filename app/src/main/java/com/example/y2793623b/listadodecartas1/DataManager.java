package com.example.y2793623b.listadodecartas1;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by y2793623b on 29/11/16.
 */

public class DataManager {

    private static UriHelper URI_HELPER = UriHelper.with(CartasContentProvider.AUTHORITY);
    private static Uri CARD_URI = URI_HELPER.getUri(Card.class);

    static void saveCartas(ArrayList<Card> cartas, Context context) {
        cupboard().withContext(context).put(CARD_URI, Card.class, cartas);
    }

    static void deleteCartas(Context context)
    {
        cupboard().withContext(context).delete(CARD_URI, "_id > ?", "1");
    }

}
