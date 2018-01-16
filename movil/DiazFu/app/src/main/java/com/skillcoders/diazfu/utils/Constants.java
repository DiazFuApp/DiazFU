package com.skillcoders.diazfu.utils;

import android.support.v4.app.Fragment;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.fragments.ClientesPlazosFragment;
import com.skillcoders.diazfu.fragments.IntegrantesPlazosEntregasFragment;
import com.skillcoders.diazfu.fragments.ListadoActividadesFragment;
import com.skillcoders.diazfu.fragments.ListadoClientesFragment;
import com.skillcoders.diazfu.fragments.ListadoComisionesFragment;
import com.skillcoders.diazfu.fragments.ListadoDocumentosFragment;
import com.skillcoders.diazfu.fragments.ListadoGruposFragment;
import com.skillcoders.diazfu.fragments.ListadoInicioFragment;
import com.skillcoders.diazfu.fragments.ListadoPrestamosGrupalesFragment;
import com.skillcoders.diazfu.fragments.ListadoPrestamosIndividualesFragment;
import com.skillcoders.diazfu.fragments.ListadoPromotoresFragment;
import com.skillcoders.diazfu.fragments.ListadoProximosPagosFragment;
import com.skillcoders.diazfu.fragments.PreListadoProximosPagosFragment;
import com.skillcoders.diazfu.fragments.RegistroActividadesFragment;
import com.skillcoders.diazfu.fragments.RegistroClientesFragment;
import com.skillcoders.diazfu.fragments.RegistroComisionesFragment;
import com.skillcoders.diazfu.fragments.RegistroGruposFragment;
import com.skillcoders.diazfu.fragments.RegistroPagosGrupalesFragment;
import com.skillcoders.diazfu.fragments.RegistroPagosIndividualesFragment;
import com.skillcoders.diazfu.fragments.RegistroPrestamosGrupalesFragment;
import com.skillcoders.diazfu.fragments.RegistroPrestamosIndividualessFragment;
import com.skillcoders.diazfu.fragments.RegistroPromotoresFragment;

import java.util.HashMap;

/**
 * Created by jvier on 10/08/2017.
 */

public class Constants {

    /**
     * Constantes Principales
     **/
    public static final int MONTO_MORATORIO = 50;
    public static final int TIPO_PRESTAMO_INDIVIDUAL = 1;
    public static final int TIPO_PRESTAMO_GRUPAL = 2;
    public static final int TIPO_REFERENCIA_CONOCIDO = 1;
    public static final int TIPO_REFERENCIA_AVAL = 2;
    public static final int ESTATUS_NO_RESPONSABLE = 0;
    public static final int ESTATUS_RESPONSABLE = 1;

    public static final int DIAZFU_WEB_ACTIVO = 1;
    public static final int DIAZFU_WEB_INACTIVO = 2;
    public static final int DIAZFU_WEB_SIN_AUTORIZACION = 3;
    public static final int DIAZFU_WEB_AUTORIZADO = 4;
    public static final int DIAZFU_WEB_ENTREGADO = 5;
    public static final int DIAZFU_WEB_PAGADO = 6;
    public static final int DIAZFU_WEB_PENDIENTE = 7;
    public static final int DIAZFU_WEB_FINALIZADO = 8;

    public static final int DIAZFU_WEB_TIPO_DOCUMENTO_IMAGEN_PERFIL = 0;
    public static final int DIAZFU_WEB_TIPO_DOCUMENTO_ACTA_NACIMIENTO = 1;
    public static final int DIAZFU_WEB_TIPO_DOCUMENTO_INE = 2;
    public static final int DIAZFU_WEB_TIPO_DOCUMENTO_CURP = 3;
    public static final int DIAZFU_WEB_TIPO_DOCUMENTO_CONSTANCIA_RESIDENCIA = 4;
    public static final int DIAZFU_WEB_TIPO_DOCUMENTO_COMPROBANTE_DOMICILIO = 5;
    public static final int DIAZFU_WEB_TIPO_DOCUMENTO_COMPROBANTE_INGRESOS = 6;
    public static final int DIAZFU_WEB_TIPO_DOCUMENTO_FOTO = 7;

    public static final String DIAZFU_WEB_TIPO_DOCUMENTO_ACTA_NACIMIENTO_STR = "Acta de Nacimiento";
    public static final String DIAZFU_WEB_TIPO_DOCUMENTO_INE_STR = "INE";
    public static final String DIAZFU_WEB_TIPO_DOCUMENTO_CURP_STR = "CURP";
    public static final String DIAZFU_WEB_TIPO_DOCUMENTO_CONSTANCIA_RESIDENCIA_STR = "Constancia de Residencia";
    public static final String DIAZFU_WEB_TIPO_DOCUMENTO_COMPROBANTE_DOMICILIO_STR = "Comprobante de Domicilio";
    public static final String DIAZFU_WEB_TIPO_DOCUMENTO_COMPROBANTE_INGRESOS_STR = "Comprobante de Ingresos";
    public static final String DIAZFU_WEB_TIPO_DOCUMENTO_COMPROBANTE_FOTO_STR = "Foto";

    public static final int DIAZFU_WEB_TIPO_RED_SOCIAL_FACEBOOK = 1;
    public static final int DIAZFU_WEB_TIPO_RED_SOCIAL_TWITTER = 2;
    public static final int DIAZFU_WEB_TIPO_RED_SOCIAL_INSTAGRAM = 3;

    public static final int DIAZFU_WEB_TIPO_ACTOR_ADMINISTRADOR = 1;
    public static final int DIAZFU_WEB_TIPO_ACTOR_PROMOTOR = 2;
    public static final int DIAZFU_WEB_TIPO_ACTOR_CLIENTE = 3;
    public static final int DIAZFU_WEB_TIPO_ACTOR_GRUPO = 4;
    public static final int DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PROMOTOR = 5;
    public static final int DIAZFU_WEB_TIPO_ACTOR_PRESTAMO = 6;
    public static final int DIAZFU_WEB_TIPO_ACTOR_REFERENCIA_PRESTAMO = 7;

    public static final int DIAZFU_WEB_PRIORIDAD_BAJA = 1;
    public static final int DIAZFU_WEB_PRIORIDAD_MEDIA = 2;
    public static final int DIAZFU_WEB_PRIORIDAD_ALTA = 3;
    public static final int DIAZFU_WEB_PRIORIDAD_URGENTE = 4;

    public static final String DIAZFU_WEB_ACTIVO_STR = "Activo";
    public static final String DIAZFU_WEB_INACTIVO_STR = "Inactivo";
    public static final String DIAZFU_WEB_SIN_AUTORIZACION_STR = "Sin Autorización";
    public static final String DIAZFU_WEB_AUTORIZADO_STR = "Autorizado";
    public static final String DIAZFU_WEB_ENTREGADO_STR = "Entregado";
    public static final String DIAZFU_WEB_PAGADO_STR = "Pagado";
    public static final String DIAZFU_WEB_PENDIENTE_STR = "Pendiente";
    public static final String DIAZFU_WEB_FINALIZADO_STR = "Finalizado";

    public static final String DIAZFU_WEB_PRIORIDAD_BAJA_STR = "Baja";
    public static final String DIAZFU_WEB_PRIORIDAD_MEDIA_STR = "Media";
    public static final String DIAZFU_WEB_PRIORIDAD_ALTA_STR = "Alta";
    public static final String DIAZFU_WEB_PRIORIDAD_URGENTE_STR = "Urgente";

    /**
     * Constantes traducidas
     **/
    public static final String ESTATUS_RESPONSABLE_STR = "Responsable";
    public static final String ESTATUS_AUTORIZADO_STR = "Autorizado";
    public static final String ESTATUS_NO_AUTORIZADO_STR = "No Autorizado";
    public static final String ESTATUS_TIPO_PRESTAMO_INDIVIDUAL = "Prestamo Individual";
    public static final String ESTATUS_TIPO_PRESTAMO_GRUPAL = "Prestamo Grupal";


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
    public static final int ACCION_AUTORIZAR = 4;
    public static final int ACCION_ENTREGAR = 5;
    public static final int ACCION_ELIMINAR = 6;
    public static final int ACCION_PAGAR = 7;

    /**
     * Key Preferences
     */
    public static final String KEY_PREF_CREDENCIALS = "key_pref_credencials";
    public static final String KEY_PREF_CREDENCIALS_USERNAME = "key_pref_credencials_username";
    public static final String KEY_PREF_CREDENCIALS_PASSWORD = "key_pref_credencials_password";
    public static final String KEY_PREF_CREDENCIALS_USER_ID = "key_pref_credencials_user_id";
    public static final String KEY_PREF_CREDENCIALS_TIPO_USER = "key_pref_credencials_tipo_user";
    public static final String KEY_PREF_CREDENCIALS_SESSION = "key_pref_credencials_session";

    /**
     * Key Extras
     **/
    public static final String KEY_MAIN_DECODE = "key_main_decode";
    public static final String KEY_MAIN_BUSQUEDA = "key_main_busqueda";
    public static final String KEY_MAIN_DOCUMENTOS = "key_main_documento";
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
    public static final String FRAGMENT_PRE_LISTADO_PROXIMOS_PAGOS = "fragment_pre_listado_proximos_pagos";
    public static final String FRAGMENT_LISTADO_PROXIMOS_PAGOS = "fragment_listado_proximos_pagos";
    public static final String FRAGMENT_LISTADO_ACTIVIDADES = "fragment_listado_actividades";
    public static final String FRAGMENT_LISTADO_COMISIONES = "fragment_listado_comisiones";
    public static final String FRAGMENT_LISTADO_DOCUMENTOS = "fragment_listado_documentos";

    /**
     * Fragmentos de registros
     **/
    public static final String FRAGMENT_PROMOTORES_REGISTER = "fragment_promotores_register";
    public static final String FRAGMENT_CLIENTES_REGISTER = "fragment_clientes_register";
    public static final String FRAGMENT_GRUPOS_REGISTER = "fragment_grupos_register";
    public static final String FRAGMENT_PRESTAMOS_GRUPALES_REGISTER = "fragment_prestamos_grupales_register";
    public static final String FRAGMENT_PRESTAMOS_GRUPALES_ENTREGAS_PLAZOS = "fragment_prestamos_grupales_entregas_plazos";
    public static final String FRAGMENT_PRESTAMOS_GRUPALES_PAGOS_REGISTER = "fragment_prestamos_grupales_pagos_register";
    public static final String FRAGMENT_PRESTAMOS_INDIVIDUALES_REGISTER = "fragment_prestamos_individuales_register";
    public static final String FRAGMENT_PRESTAMOS_INDIVIDUALES_ENTREGAS_PLAZOS = "fragment_prestamos_individuales_entregas_plazos";
    public static final String FRAGMENT_PRESTAMOS_INDIVIDUALES_PAGOS_REGISTER = "fragment_prestamos_individuales_pagos_register";
    public static final String FRAGMENT_ACTIVIDADES_REGISTER = "fragment_actividades_register";
    public static final String FRAGMENT_COMISIONES_REGISTER = "fragment_comisiones_register";

    /**
     * Formularios
     **/
    public static final String FORMULARIO_PROMOTORES = "fragment_formulario_promotores";
    public static final String FORMULARIO_PROMOTORES_CREDENCIALES = "fragment_formulario_promotores_credenciales";
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
    public static final String FORMULARIO_PRESTAMOS_GRUPALES_AVAL = "fragment_formulario_prestamos_grupales_aval";
    public static final String FORMULARIO_PRESTAMOS_GRUPALES_PRIMER_REFERENCIA = "fragment_formulario_prestamos_grupales_primer_referencia";
    public static final String FORMULARIO_PRESTAMOS_GRUPALES_SEGUNDA_REFERENCIA = "fragment_formulario_prestamos_grupales_segunda_referencia";
    public static final String FORMULARIO_PRESTAMOS_GRUPALES_AUTORIZACION = "fragment_formulario_prestamos_grupales_autorizacion";
    public static final String FORMULARIO_PRESTAMOS_GRUPALES_ENTREGA = "fragment_formulario_prestamos_grupales_entrega";
    public static final String FORMULARIO_PRESTAMOS_GRUPALES_ACCIONES = "fragment_formulario_prestamos_grupales_acciones";
    public static final String FORMULARIO_PRESTAMOS_GRUPALES_INTEGRANTES_PLAZOS = "fragment_formulario_prestamos_grupales_integrantes_plazos";
    public static final String FORMULARIO_PRESTAMOS_GRUPALES_INTEGRANTES_PLAZOS_ENTREGAS = "fragment_formulario_prestamos_grupales_integrantes_plazos_entregas";
    public static final String FORMULARIO_PRESTAMOS_GRUPALES_PAGOS = "fragment_formulario_prestamos_grupales_pagos";
    public static final String FORMULARIO_PRESTAMOS_GRUPALES_PAGOS_HISTORIAL_LISTADO = "fragment_formulario_prestamos_grupales_pagos_historial_listado";
    public static final String FORMULARIO_PRESTAMOS_GRUPALES_INTEGRANTES_CANTIDADES = "fragment_formulario_prestamos_grupales_integrantes_cantidades";
    public static final String FORMULARIO_PRESTAMOS_INDIVIDUALES = "fragment_formulario_prestamos_individuales";
    public static final String FORMULARIO_PRESTAMOS_INDIVIDUALES_AVAL = "fragment_formulario_prestamos_individuales_aval";
    public static final String FORMULARIO_PRESTAMOS_INDIVIDUALES_PRIMER_REFERENCIA = "fragment_formulario_prestamos_individuales_primer_referencia";
    public static final String FORMULARIO_PRESTAMOS_INDIVIDUALES_SEGUNDA_REFERENCIA = "fragment_formulario_prestamos_individuales_segunda_referencia";
    public static final String FORMULARIO_PRESTAMOS_INDIVIDUALES_AUTORIZACION = "fragment_formulario_prestamos_individuales_autorizacion";
    public static final String FORMULARIO_PRESTAMOS_INDIVIDUALES_ENTREGA = "fragment_formulario_prestamos_individuales_entrega";
    public static final String FORMULARIO_PRESTAMOS_INDIVIDUALES_INTEGRANTES_PLAZOS = "fragment_formulario_prestamos_individuales_integrantes_plazos";
    public static final String FORMULARIO_PRESTAMOS_INDIVIDUALES_INTEGRANTES_PLAZOS_ENTREGAS = "fragment_formulario_prestamos_individuales_integrantes_plazos_entregas";
    public static final String FORMULARIO_PRESTAMOS_INDIVIDUALES_ACCIONES = "fragment_formulario_prestamos_individuales_acciones";
    public static final String FORMULARIO_PRESTAMOS_INDIVIDUALES_PAGOS = "fragment_formulario_prestamos_individuales_pagos";
    public static final String FORMULARIO_PRESTAMOS_INDIVIDUALES_PAGOS_HISTORIAL_LISTADO = "fragment_formulario_prestamos_individuales_pagos_historial_listado";
    public static final String FORMULARIO_ACTIVIDADES = "fragment_formulario_actividades";
    public static final String FORMULARIO_ACTIVIDADES_ACCIONES = "fragment_formulario_actividades_acciones";
    public static final String FORMULARIO_COMISIONES = "fragment_formulario_comisiones";
    public static final String FORMULARIO_COMISIONES_ACCIONES = "fragment_formulario_comisiones_acciones";

    /**
     * Fragmentos segundarios
     **/
    public static final String FRAGMENT_INICIOS = "fragment_inicios";
    public static final String FRAGMENT_PROMOTORES = "fragment_promotores";
    public static final String FRAGMENT_CLIENTES = "fragment_clientes";
    public static final String FRAGMENT_GRUPOS = "fragment_grupos";
    public static final String FRAGMENT_PRESTAMOS_GRUPALES = "fragment_prestamos_grupales";
    public static final String FRAGMENT_PRESTAMOS_INDIVIDUALES = "fragment_prestamos_individuales";
    public static final String FRAGMENT_BUSQUEDA_PROXIMOS_PAGOS = "fragment_busqueda_proximos_pagos";
    public static final String FRAGMENT_ACTIVIDADES = "fragment_actividades";
    public static final String FRAGMENT_COMISIONES = "fragment_comisiones";
    public static final String FRAGMENT_DOCUMENTOS = "fragment_documentos";

    public static final HashMap<Integer, String> ITEM_FRAGMENT;

    static {
        ITEM_FRAGMENT = new HashMap<>();
        ITEM_FRAGMENT.put(R.id.menu_item_inicio, FRAGMENT_LISTADO_INICIO);
        ITEM_FRAGMENT.put(R.id.menu_item_promotores, FRAGMENT_LISTADO_PROMOTORES);
        ITEM_FRAGMENT.put(R.id.menu_item_clientes, FRAGMENT_LISTADO_CLIENTES);
        ITEM_FRAGMENT.put(R.id.menu_item_grupos, FRAGMENT_LISTADO_GRUPOS);
        ITEM_FRAGMENT.put(R.id.menu_item_prestamos_grupales, FRAGMENT_LISTADO_PRESTAMOS_GRUPALES);
        ITEM_FRAGMENT.put(R.id.menu_item_prestamos_individuales, FRAGMENT_LISTADO_PRESTAMOS_INDIVIDUALES);
        ITEM_FRAGMENT.put(R.id.menu_item_proximos_pagos, FRAGMENT_PRE_LISTADO_PROXIMOS_PAGOS);
        ITEM_FRAGMENT.put(R.id.menu_item_actividades, FRAGMENT_LISTADO_ACTIVIDADES);
        ITEM_FRAGMENT.put(R.id.menu_item_comisiones, FRAGMENT_LISTADO_COMISIONES);
        ITEM_FRAGMENT.put(R.id.btn_registrar_promotor, FRAGMENT_PROMOTORES_REGISTER);
        ITEM_FRAGMENT.put(R.id.btn_registrar_cliente, FRAGMENT_CLIENTES_REGISTER);
        ITEM_FRAGMENT.put(R.id.btn_registrar_actividad, FRAGMENT_ACTIVIDADES_REGISTER);
        ITEM_FRAGMENT.put(R.id.btn_registrar_grupo, FRAGMENT_GRUPOS_REGISTER);
        ITEM_FRAGMENT.put(R.id.btn_registrar_prestamo_grupal, FRAGMENT_PRESTAMOS_GRUPALES_REGISTER);
        ITEM_FRAGMENT.put(R.id.btn_registrar_prestamo_individual, FRAGMENT_PRESTAMOS_INDIVIDUALES_REGISTER);
        ITEM_FRAGMENT.put(R.id.btn_registrar_comision, FRAGMENT_COMISIONES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_editar_promotor, FRAGMENT_PROMOTORES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_editar_cliente, FRAGMENT_CLIENTES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_editar_grupo, FRAGMENT_GRUPOS_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_ver_prestamo_grupal, FRAGMENT_PRESTAMOS_GRUPALES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_autorizar_prestamo_grupal, FRAGMENT_PRESTAMOS_GRUPALES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_entregar_prestamo_grupal, FRAGMENT_PRESTAMOS_GRUPALES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_pagar_prestamo_grupal, FRAGMENT_PRESTAMOS_GRUPALES_PAGOS_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_inspeccionar_integrante_plazo_entrega, FRAGMENT_PRESTAMOS_GRUPALES_ENTREGAS_PLAZOS);
        ITEM_FRAGMENT.put(R.id.item_btn_inspeccionar_cliente_plazo_entrega, FRAGMENT_PRESTAMOS_INDIVIDUALES_ENTREGAS_PLAZOS);
        ITEM_FRAGMENT.put(R.id.item_btn_ver_prestamo_individual, FRAGMENT_PRESTAMOS_INDIVIDUALES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_autorizar_prestamo_individual, FRAGMENT_PRESTAMOS_INDIVIDUALES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_entregar_prestamo_individual, FRAGMENT_PRESTAMOS_INDIVIDUALES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_pagar_prestamo_individual, FRAGMENT_PRESTAMOS_INDIVIDUALES_PAGOS_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_editar_actividad, FRAGMENT_ACTIVIDADES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_ver_actividad, FRAGMENT_ACTIVIDADES_REGISTER);
        ITEM_FRAGMENT.put(R.id.item_btn_pagar_comision, FRAGMENT_COMISIONES_REGISTER);
        ITEM_FRAGMENT.put(R.id.btn_buscar_proximo_pago, FRAGMENT_LISTADO_PROXIMOS_PAGOS);
        ITEM_FRAGMENT.put(R.id.btn_documentos_cliente, FRAGMENT_LISTADO_DOCUMENTOS);
        ITEM_FRAGMENT.put(R.id.btn_documentos_promotor, FRAGMENT_LISTADO_DOCUMENTOS);
        ITEM_FRAGMENT.put(R.id.btn_documentos_primera_referencia_promotor, FRAGMENT_LISTADO_DOCUMENTOS);
        ITEM_FRAGMENT.put(R.id.btn_documentos_segunda_referencia_promotor, FRAGMENT_LISTADO_DOCUMENTOS);
        ITEM_FRAGMENT.put(R.id.btn_documentos_aval_prestamos_grupales, FRAGMENT_LISTADO_DOCUMENTOS);
        ITEM_FRAGMENT.put(R.id.btn_documentos_primera_referencia_grupal, FRAGMENT_LISTADO_DOCUMENTOS);
        ITEM_FRAGMENT.put(R.id.btn_documentos_segunda_referencia_grupal, FRAGMENT_LISTADO_DOCUMENTOS);
        ITEM_FRAGMENT.put(R.id.btn_documentos_aval_prestamo_individual, FRAGMENT_LISTADO_DOCUMENTOS);
        ITEM_FRAGMENT.put(R.id.btn_documentos_primera_referencia_prestamo_individual, FRAGMENT_LISTADO_DOCUMENTOS);
        ITEM_FRAGMENT.put(R.id.btn_documentos_segunda_referencia_prestamo_individual, FRAGMENT_LISTADO_DOCUMENTOS);
    }

    public static final HashMap<String, Fragment> TAG_FRAGMENT;

    static {
        TAG_FRAGMENT = new HashMap<>();
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_INICIO, new ListadoInicioFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_PROMOTORES, new ListadoPromotoresFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_CLIENTES, new ListadoClientesFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_GRUPOS, new ListadoGruposFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_PRESTAMOS_GRUPALES, new ListadoPrestamosGrupalesFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_PRESTAMOS_INDIVIDUALES, new ListadoPrestamosIndividualesFragment());
        TAG_FRAGMENT.put(FRAGMENT_PRE_LISTADO_PROXIMOS_PAGOS, new PreListadoProximosPagosFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_PROXIMOS_PAGOS, new ListadoProximosPagosFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_ACTIVIDADES, new ListadoActividadesFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_COMISIONES, new ListadoComisionesFragment());
        TAG_FRAGMENT.put(FRAGMENT_PROMOTORES_REGISTER, new RegistroPromotoresFragment());
        TAG_FRAGMENT.put(FRAGMENT_CLIENTES_REGISTER, new RegistroClientesFragment());
        TAG_FRAGMENT.put(FRAGMENT_GRUPOS_REGISTER, new RegistroGruposFragment());
        TAG_FRAGMENT.put(FRAGMENT_PRESTAMOS_GRUPALES_REGISTER, new RegistroPrestamosGrupalesFragment());
        TAG_FRAGMENT.put(FRAGMENT_PRESTAMOS_INDIVIDUALES_REGISTER, new RegistroPrestamosIndividualessFragment());
        TAG_FRAGMENT.put(FRAGMENT_PRESTAMOS_GRUPALES_PAGOS_REGISTER, new RegistroPagosGrupalesFragment());
        TAG_FRAGMENT.put(FRAGMENT_PRESTAMOS_GRUPALES_ENTREGAS_PLAZOS, new ClientesPlazosFragment());
        TAG_FRAGMENT.put(FRAGMENT_PRESTAMOS_INDIVIDUALES_PAGOS_REGISTER, new RegistroPagosIndividualesFragment());
        TAG_FRAGMENT.put(FRAGMENT_PRESTAMOS_INDIVIDUALES_ENTREGAS_PLAZOS, new IntegrantesPlazosEntregasFragment());
        TAG_FRAGMENT.put(FRAGMENT_ACTIVIDADES_REGISTER, new RegistroActividadesFragment());
        TAG_FRAGMENT.put(FRAGMENT_COMISIONES_REGISTER, new RegistroComisionesFragment());
        TAG_FRAGMENT.put(FRAGMENT_LISTADO_DOCUMENTOS, new ListadoDocumentosFragment());
    }

    public static final HashMap<Integer, Integer> TITLE_ACTIVITY;

    static {
        TITLE_ACTIVITY = new HashMap<>();
        TITLE_ACTIVITY.put(R.id.btn_registrar_promotor, R.string.default_activity_title_promotores);
        TITLE_ACTIVITY.put(R.id.btn_registrar_cliente, R.string.default_activity_title_clientes);
        TITLE_ACTIVITY.put(R.id.btn_registrar_grupo, R.string.default_activity_title_grupos);
        TITLE_ACTIVITY.put(R.id.btn_registrar_prestamo_grupal, R.string.default_activity_title_grupos);
        TITLE_ACTIVITY.put(R.id.btn_registrar_prestamo_individual, R.string.default_activity_title_prestamos_individuales);
        TITLE_ACTIVITY.put(R.id.item_btn_editar_promotor, R.string.default_activity_title_promotores);
        TITLE_ACTIVITY.put(R.id.item_btn_editar_cliente, R.string.default_activity_title_clientes);
        TITLE_ACTIVITY.put(R.id.item_btn_editar_actividad, R.string.default_activity_title_actividades);
        TITLE_ACTIVITY.put(R.id.item_btn_ver_actividad, R.string.default_activity_title_actividades);
        TITLE_ACTIVITY.put(R.id.item_btn_editar_grupo, R.string.default_activity_title_grupos);
        TITLE_ACTIVITY.put(R.id.item_btn_ver_prestamo_grupal, R.string.default_activity_title_prestamos_grupales);
        TITLE_ACTIVITY.put(R.id.item_btn_autorizar_prestamo_grupal, R.string.default_activity_title_prestamos_grupales);
        TITLE_ACTIVITY.put(R.id.item_btn_entregar_prestamo_grupal, R.string.default_activity_title_prestamos_grupales);
        TITLE_ACTIVITY.put(R.id.item_btn_pagar_prestamo_grupal, R.string.default_activity_title_prestamos_grupales);
        TITLE_ACTIVITY.put(R.id.item_btn_inspeccionar_integrante_plazo_entrega, R.string.default_activity_title_prestamos_grupales);
        TITLE_ACTIVITY.put(R.id.item_btn_inspeccionar_cliente_plazo_entrega, R.string.default_activity_title_prestamos_individuales);
        TITLE_ACTIVITY.put(R.id.item_btn_ver_prestamo_individual, R.string.default_activity_title_prestamos_individuales);
        TITLE_ACTIVITY.put(R.id.item_btn_autorizar_prestamo_individual, R.string.default_activity_title_prestamos_individuales);
        TITLE_ACTIVITY.put(R.id.item_btn_entregar_prestamo_individual, R.string.default_activity_title_prestamos_individuales);
        TITLE_ACTIVITY.put(R.id.item_btn_pagar_prestamo_individual, R.string.default_activity_title_prestamos_individuales);
        TITLE_ACTIVITY.put(R.id.btn_registrar_actividad, R.string.default_activity_title_actividades);
        TITLE_ACTIVITY.put(R.id.btn_registrar_comision, R.string.default_activity_title_comisiones);
        TITLE_ACTIVITY.put(R.id.item_btn_pagar_comision, R.string.default_activity_title_comisiones);
        TITLE_ACTIVITY.put(R.id.btn_buscar_proximo_pago, R.string.default_activity_title_proximos_pagos);
        TITLE_ACTIVITY.put(R.id.btn_documentos_cliente, R.string.default_activity_title_documentos);
        TITLE_ACTIVITY.put(R.id.btn_documentos_promotor, R.string.default_activity_title_documentos);
        TITLE_ACTIVITY.put(R.id.btn_documentos_primera_referencia_promotor, R.string.default_activity_title_documentos);
        TITLE_ACTIVITY.put(R.id.btn_documentos_segunda_referencia_promotor, R.string.default_activity_title_documentos);
        TITLE_ACTIVITY.put(R.id.btn_documentos_aval_prestamos_grupales, R.string.default_activity_title_documentos);
        TITLE_ACTIVITY.put(R.id.btn_documentos_primera_referencia_grupal, R.string.default_activity_title_documentos);
        TITLE_ACTIVITY.put(R.id.btn_documentos_segunda_referencia_grupal, R.string.default_activity_title_documentos);
        TITLE_ACTIVITY.put(R.id.btn_documentos_aval_prestamo_individual, R.string.default_activity_title_documentos);
        TITLE_ACTIVITY.put(R.id.btn_documentos_primera_referencia_prestamo_individual, R.string.default_activity_title_documentos);
        TITLE_ACTIVITY.put(R.id.btn_documentos_segunda_referencia_prestamo_individual, R.string.default_activity_title_documentos);
    }

    public static final HashMap<Integer, Integer> TITLE_FORM_ACTION;

    static {
        TITLE_FORM_ACTION = new HashMap<>();
        TITLE_FORM_ACTION.put(Constants.ACCION_REGISTRAR, R.string.default_form_title_new);
        TITLE_FORM_ACTION.put(Constants.ACCION_EDITAR, R.string.default_form_title_edit);
        TITLE_FORM_ACTION.put(Constants.ACCION_VER, R.string.default_form_title_ver);
        TITLE_FORM_ACTION.put(Constants.ACCION_AUTORIZAR, R.string.default_form_title_autorizar);
        TITLE_FORM_ACTION.put(Constants.ACCION_ENTREGAR, R.string.default_form_title_entregar);
        TITLE_FORM_ACTION.put(Constants.ACCION_PAGAR, R.string.default_form_title_pagar);
    }

    public static final HashMap<Integer, String> TITLE_STATUS_DIAZFU_WEB;

    static {
        TITLE_STATUS_DIAZFU_WEB = new HashMap<>();
        TITLE_STATUS_DIAZFU_WEB.put(DIAZFU_WEB_ACTIVO, DIAZFU_WEB_ACTIVO_STR);
        TITLE_STATUS_DIAZFU_WEB.put(DIAZFU_WEB_INACTIVO, DIAZFU_WEB_INACTIVO_STR);
        TITLE_STATUS_DIAZFU_WEB.put(DIAZFU_WEB_SIN_AUTORIZACION, DIAZFU_WEB_SIN_AUTORIZACION_STR);
        TITLE_STATUS_DIAZFU_WEB.put(DIAZFU_WEB_AUTORIZADO, DIAZFU_WEB_AUTORIZADO_STR);
        TITLE_STATUS_DIAZFU_WEB.put(DIAZFU_WEB_ENTREGADO, DIAZFU_WEB_ENTREGADO_STR);
        TITLE_STATUS_DIAZFU_WEB.put(DIAZFU_WEB_PAGADO, DIAZFU_WEB_PAGADO_STR);
        TITLE_STATUS_DIAZFU_WEB.put(DIAZFU_WEB_PENDIENTE, DIAZFU_WEB_PENDIENTE_STR);
        TITLE_STATUS_DIAZFU_WEB.put(DIAZFU_WEB_FINALIZADO, DIAZFU_WEB_FINALIZADO_STR);
    }

    public static final HashMap<Integer, String> TITLE_STATUS_DIAZFU_WEB_PRIORIDADES;

    static {
        TITLE_STATUS_DIAZFU_WEB_PRIORIDADES = new HashMap<>();
        TITLE_STATUS_DIAZFU_WEB_PRIORIDADES.put(DIAZFU_WEB_PRIORIDAD_BAJA, DIAZFU_WEB_PRIORIDAD_BAJA_STR);
        TITLE_STATUS_DIAZFU_WEB_PRIORIDADES.put(DIAZFU_WEB_PRIORIDAD_MEDIA, DIAZFU_WEB_PRIORIDAD_MEDIA_STR);
        TITLE_STATUS_DIAZFU_WEB_PRIORIDADES.put(DIAZFU_WEB_PRIORIDAD_ALTA, DIAZFU_WEB_PRIORIDAD_ALTA_STR);
        TITLE_STATUS_DIAZFU_WEB_PRIORIDADES.put(DIAZFU_WEB_PRIORIDAD_URGENTE, DIAZFU_WEB_PRIORIDAD_URGENTE_STR);
    }

    public static final HashMap<String, Integer> TITLE_STATUS_DIAZFU_WEB_PRIORIDADES_STR;

    static {
        TITLE_STATUS_DIAZFU_WEB_PRIORIDADES_STR = new HashMap<>();
        TITLE_STATUS_DIAZFU_WEB_PRIORIDADES_STR.put(DIAZFU_WEB_PRIORIDAD_BAJA_STR, DIAZFU_WEB_PRIORIDAD_BAJA);
        TITLE_STATUS_DIAZFU_WEB_PRIORIDADES_STR.put(DIAZFU_WEB_PRIORIDAD_MEDIA_STR, DIAZFU_WEB_PRIORIDAD_MEDIA);
        TITLE_STATUS_DIAZFU_WEB_PRIORIDADES_STR.put(DIAZFU_WEB_PRIORIDAD_ALTA_STR, DIAZFU_WEB_PRIORIDAD_ALTA);
        TITLE_STATUS_DIAZFU_WEB_PRIORIDADES_STR.put(DIAZFU_WEB_PRIORIDAD_URGENTE_STR, DIAZFU_WEB_PRIORIDAD_URGENTE);
    }

    public static final HashMap<Integer, String> TITLE_TIPO_PRESTAMO;

    static {
        TITLE_TIPO_PRESTAMO = new HashMap<>();
        TITLE_TIPO_PRESTAMO.put(Constants.TIPO_PRESTAMO_INDIVIDUAL, Constants.ESTATUS_TIPO_PRESTAMO_INDIVIDUAL);
        TITLE_TIPO_PRESTAMO.put(Constants.TIPO_PRESTAMO_GRUPAL, Constants.ESTATUS_TIPO_PRESTAMO_GRUPAL);
    }

    /**
     * Identificadores
     **/
    public static final int WS_KEY_ELIMINAR_PROMOTORES = 10;
    public static final int WS_KEY_ELIMINAR_CLIENTES = 20;
    public static final int WS_KEY_AUTORIZAR_CLIENTES = 21;
    public static final int WS_KEY_ELIMINAR_GRUPOS = 30;
    public static final int WS_KEY_AUTORIZAR_GRUPOS = 31;
    public static final int WS_KEY_ELIMINAR_ASIGNACIONES_GRUPOS = 40;
    public static final int WS_KEY_AUTORIZAR_ASIGNACIONES_GRUPOS = 41;
    public static final int WS_KEY_ELIMINAR_ACTIVIDADES = 50;
    public static final int WS_KEY_FINALIZAR_ACTIVIDADES = 51;

}
