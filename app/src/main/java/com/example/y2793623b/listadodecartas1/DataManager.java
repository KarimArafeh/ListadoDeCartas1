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
    private static Uri MOVIE_URI = URI_HELPER.getUri(Card.class);

    static void saveMovies(ArrayList<Card> movies, Context context) {
        cupboard().withContext(context).put(MOVIE_URI, Card.class, movies);
    }

}
