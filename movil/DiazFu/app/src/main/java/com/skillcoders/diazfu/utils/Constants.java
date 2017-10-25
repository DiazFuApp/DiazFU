package com.skillcoders.diazfu.utils;

import android.support.v4.app.Fragment;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.fragments.ListadoClientesFragment;
import com.skillcoders.diazfu.fragments.ListadoGruposFragment;
import com.skillcoders.diazfu.fragments.ListadoInicioFragment;
import com.skillcoders.diazfu.fragments.ListadoPrestamosGrupalesFragment;
import com.skillcoders.diazfu.fragments.ListadoPromotoresFragment;
import com.skillcoders.diazfu.fragments.RegistroClientesFragment;
import com.skillcoders.diazfu.fragments.RegistroGruposFragment;
import com.skillcoders.diazfu.fragments.RegistroPrestamosGrupalesFragment;
import com.skillcoders.diazfu.fragments.RegistroPromotoresFragment;

import java.util.HashMap;

/**
 * Created by jvier on 10/08/2017.
 */

public class Constants {

    /**
     * Constantes Principales
     **/
    public static final int TIPO_REFERENCIA_CONOCIDO = 1;
    public static final int TIPO_REFERENCIA_AVAL = 2;
    public static final int ESTATUS_AUTORIZADO = 4;
    public static final int ESTATUS_NO_AUTORIZADO = 3;
    public static final int ESTATUS_NO_RESPONSABLE = 0;
    public static final int ESTATUS_RESPONSABLE = 1;

    /**
     * Constantes traducidas
     **/
    public static final String ESTATUS_RESPONSABLE_STR = "Responsable";
    public static final String ESTATUS_AUTORIZADO_STR = "Autorizado";
    public static final String ESTATUS_NO_AUTORIZADO_STR = "No Autorizado";


    /**
     * Formatos del sistema
     **/
    public static final String MASK_DATE_ANDROID_DMY = "dd/MM/yyyy";
    public static final String MASK_DATE_FROM_SQL_YMD = "yyyy-MM-dd hh:mm:ss";
    public static final String MASK_DATE_TO_SQL_YMD = "yyyy-MM-dd";

    /**
     * Acciones generales
     **/
    public static final int ACCION_SIN_DEFINIR = 0;
    public static final int ACCION_REGISTRAR = 1;
    public static final int ACCION_EDITAR = 2;
    public static final int ACCION_VER = 3;
    public static final int ACCION_ELIMINAR = 4;

    /**
     * Key Preferences
     */
    public static final String KEY_PREF_CREDENCIALS = "key_pref_credencials";
    public static final String KEY_PREF_CREDENCIALS_USERNAME = "key_pref_credencials_username";
    public static final String KEY_PREF_CREDENCIALS_PASSWORD = "key_pref_credencials_password";
    public static final String KEY_PREF_CREDENCIALS_SESSION = "key_pref_credencials_session";

    /**
     * Key Extras
     **/
    public static final String KEY_MAIN_DECODE = "key_main_decode";
    public static final String KEY_SESSION_USER = "key_session_users";

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
     * Fragmentos de registros
     **/
    public static final String FRAGMENT_PROMOTORES_REGISTER = "fragment_promotores_register";
    public static final String FRAGMENT_CLIENTES_REGISTER = "fragment_clientes_register";
    public static final String FRAGMENT_GRUPOS_REGISTER = "fragment_grupos_register";
    public static final String FRAGMENT_PRESTAMOS_GRUPALES_REGISTER = "fragment_prestamos_grupales_register";
    public static final String FRAGMENT_PRESTAMOS_INDIVIDUALES_REGISTER = "fragment_prestamos_individuales_register";

    /**
     * Formularios
     **/
    public static final String FORMULARIO_PROMOTORES = "fragment_formulario_promotores";
    public static final String FORMULARIO_PROMOTORES_REFERENCIA = "fragment_formulario_promotores_referencia";
    public static final String FORMULARIO_PROMOTORES_SEGUNDA_REFERENCIA = "fragment_formulario_promotores_segunda_referencia";
    public static final String FORMULARIO_PROMOTORES_ACCIONES = "fragment_formulario_promotores_acciones";
    public static final String FORMULARIO_CLIENTES = "fragment_formulario_clientes";
    public static final String FORMULARIO_CLIENTES_ACCIONES = "fragment_formulario_clientes_acciones";
    public static final String FORMULARIO_GRUPOS = "fragment_formulario_grupos";
    public static final String FORMULARIO_GRUPOS_ASIGNACIONES = "fragment_formulario_grupos_asignaciones";
    public static final String FORMULARIO_GRUPOS_ASIGNACIONES_LISTADO = "fragment_formulario_grupos_asignaciones_listado";
    public static final String FORMULARIO_GRUPOS_ACCIONES = "fragment_formulario_grupos_acciones";
    public static final String FORMULARIO_PRESTAMOS_GRUPALES = "fragment_formulario_prestamos_grupales";

    /**
     * Fragmentos segundarios
     **/
    public static final String FRAGMENT_PROMOTORES = "fragment_promotores";
    public static final String FRAGMENT_CLIENTES = "fragment_clientes";
    public static final String FRAGMENT_GRUPOS = "fragment_grupos";
    public static final String FRAGMENT_PRESTAMOS_GRUPALES = "fragment_prestamos_grupales";
    public static final String FRAGMENT_PRESTAMOS_INDIVIDUALES = "fragment_prestamos_individuales";

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
        ITEM_FRAGMENT.put(R.id.btn_registrar_promotor, FRAGMENT_PROMOTORES_REGISTER);
        ITEM_FRAGMENT.put(R.id.btn_registrar_cliente, FRAGMENT_CLIENTES_REGISTER);
        ITEM_FRAGMENT.put(R.id.btn_registrar_grupo, FRAGMENT_GRUPOS_REGISTER);
        ITEM_FRAGMENT.put(R.id.btn_registrar_prestamo_grupal, FRAGMENT_PRESTAMOS_GRUPALES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_editar_promotor, FRAGMENT_PROMOTORES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_editar_cliente, FRAGMENT_CLIENTES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_editar_grupo, FRAGMENT_GRUPOS_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_editar_prestamo_grupal, FRAGMENT_PRESTAMOS_GRUPALES_REGISTER);
    }

    public static final HashMap<String, Fragment> TAG_FRAGMENT;

    static {
        TAG_FRAGMENT = new HashMap<>();
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_INICIO, new ListadoInicioFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_PROMOTORES, new ListadoPromotoresFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_CLIENTES, new ListadoClientesFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_GRUPOS, new ListadoGruposFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_PRESTAMOS_GRUPALES, new ListadoPrestamosGrupalesFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_PRESTAMOS_INDIVIDUALES, new ListadoPrestamosGrupalesFragment());
        TAG_FRAGMENT.put(FRAGMENT_PROMOTORES_REGISTER, new RegistroPromotoresFragment());
        TAG_FRAGMENT.put(FRAGMENT_CLIENTES_REGISTER, new RegistroClientesFragment());
        TAG_FRAGMENT.put(FRAGMENT_GRUPOS_REGISTER, new RegistroGruposFragment());
        TAG_FRAGMENT.put(FRAGMENT_PRESTAMOS_GRUPALES_REGISTER, new RegistroPrestamosGrupalesFragment());
    }

    public static final HashMap<Integer, Integer> TITLE_ACTIVITY;

    static {
        TITLE_ACTIVITY = new HashMap<>();
        TITLE_ACTIVITY.put(R.id.btn_registrar_promotor, R.string.default_activity_title_promotores);
        TITLE_ACTIVITY.put(R.id.btn_registrar_cliente, R.string.default_activity_title_clientes);
        TITLE_ACTIVITY.put(R.id.btn_registrar_grupo, R.string.default_activity_title_grupos);
        TITLE_ACTIVITY.put(R.id.btn_registrar_prestamo_grupal, R.string.default_activity_title_grupos);
        TITLE_ACTIVITY.put(R.id.item_btn_editar_promotor, R.string.default_activity_title_promotores);
        TITLE_ACTIVITY.put(R.id.item_btn_editar_cliente, R.string.default_activity_title_clientes);
        TITLE_ACTIVITY.put(R.id.item_btn_editar_grupo, R.string.default_activity_title_grupos);
    }

    public static final HashMap<Integer, Integer> TITLE_FORM_ACTION;

    static {
        TITLE_FORM_ACTION = new HashMap<>();
        TITLE_FORM_ACTION.put(Constants.ACCION_REGISTRAR, R.string.default_form_title_new);
        TITLE_FORM_ACTION.put(Constants.ACCION_EDITAR, R.string.default_form_title_edit);
    }

    /**
     * Identificadores
     **/
    public static final int WS_KEY_ELIMINAR_PROMOTORES = 10;
    public static final int WS_KEY_ELIMINAR_CLIENTES = 20;
    public static final int WS_KEY_ELIMINAR_GRUPOS = 30;
    public static final int WS_KEY_AUTORIZAR_GRUPOS = 31;
    public static final int WS_KEY_ELIMINAR_ASIGNACIONES_GRUPOS = 40;
    public static final int WS_KEY_AUTORIZAR_ASIGNACIONES_GRUPOS = 41;

}
