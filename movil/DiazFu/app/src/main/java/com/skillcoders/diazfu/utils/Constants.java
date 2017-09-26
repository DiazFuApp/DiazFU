package com.skillcoders.diazfu.utils;

import android.support.v4.app.Fragment;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.fragments.ListadoClientesFragment;
import com.skillcoders.diazfu.fragments.ListadoInicioFragment;
import com.skillcoders.diazfu.fragments.ListadoPromotoresFragment;

import java.util.HashMap;

/**
 * Created by jvier on 10/08/2017.
 */

public class Constants {

    /**
     * Fragmentos principales de lista
     **/
    public static final String FRAGMENT_LISTADO_INICIO = "fragment_listado_inicio";
    public static final String FRAGMENT_LISTADO_PROMOTORES = "fragment_listado_promotores";
    public static final String FRAGMENT_LISTADO_CLIENTES = "fragment_listado_clientes";
    public static final String FRAGMENT_LISTADO_GRUPOS = "fragment_listado_grupos";
    public static final String FRAGMENT_LISTADO_PRESTAMOS_GRUPALES = "fragment_listado_prestamos_grupales";
    public static final String FRAGMENT_LISTADO_PRESTAMOS_INDIVIDUALES = "fragment_listado_prestamos_individuales";
    public static final String FRAGMENT_LISTADO_PAGOS = "fragment_listado_pagos";

    /**
     * Fragmentos segundarios
     **/
    public static final String FRAGMENT_PROMOTORES = "fragment_promotores";
    public static final String FRAGMENT_CLIENTES = "fragment_clientes";

    public static final HashMap<Integer, String> ITEM_FRAGMENT;

    static {
        ITEM_FRAGMENT = new HashMap<>();
        ITEM_FRAGMENT.put(R.id.menu_item_inicio, FRAGMENT_LISTADO_INICIO);
        ITEM_FRAGMENT.put(R.id.menu_item_promotores, FRAGMENT_LISTADO_PROMOTORES);
        ITEM_FRAGMENT.put(R.id.menu_item_clientes, FRAGMENT_LISTADO_CLIENTES);
        ITEM_FRAGMENT.put(R.id.menu_item_grupos, FRAGMENT_LISTADO_GRUPOS);
        ITEM_FRAGMENT.put(R.id.menu_item_prestamos_grupales, FRAGMENT_LISTADO_PRESTAMOS_GRUPALES);
        ITEM_FRAGMENT.put(R.id.menu_item_prestamos_individuales, FRAGMENT_LISTADO_PRESTAMOS_INDIVIDUALES);
        ITEM_FRAGMENT.put(R.id.menu_item_registro_pagos, FRAGMENT_LISTADO_PAGOS);
    }

    public static final HashMap<String, Fragment> TAG_FRAGMENT;

    static {
        TAG_FRAGMENT = new HashMap<>();
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_PROMOTORES, new ListadoPromotoresFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_CLIENTES, new ListadoClientesFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_INICIO, new ListadoInicioFragment());

    }
}
