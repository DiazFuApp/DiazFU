package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skillcoders.diazfu.IntegrantesPlazosActivity;
import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.HistorialPagosGrupalesAdapter;
import com.skillcoders.diazfu.adapters.HistorialPagosIndividualesAdapter;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.IntegrantesGruposRest;
import com.skillcoders.diazfu.data.remote.rest.PagosRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.CommonUtils;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by saurett on 24/02/2017.
 */

public class HistorialPagosIndividualesFragment extends Fragment implements View.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    public static List<Pagos> pagosList;
    private static RecyclerView recyclerView;
    public static HistorialPagosIndividualesAdapter adapter;
    private static MainRegisterInterface activityInterface;

    public static List<Pagos> _pagosActuales;
    /**
     * Implementaciones REST
     */
    private static IntegrantesGruposRest integrantesRest;
    private static PagosRest pagosRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_clientes);
        adapter = new HistorialPagosIndividualesAdapter();
        adapter.setOnClickListener(this);

        pagosRest = ApiUtils.getPagosRest();
        integrantesRest = ApiUtils.getIntegrantesGruposRest();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public static void listadoIntegrantes(int idCliente) {

        final PrestamosIndividuales prestamoIndividual = (PrestamosIndividuales) _MAIN_DECODE.getDecodeItem().getItemModel();

        Pagos pago = new Pagos();
        pago.setIdTipoPrestamo(Constants.TIPO_PRESTAMO_INDIVIDUAL);
        pago.setIdPrestamo(prestamoIndividual.getId());
        pago.setIdCliente(idCliente);

        pagosRest.getPago(pago)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Pagos>>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Pagos> pagoses) {
                        /**_pagosActuales arreglo para el preguardado**/
                        _pagosActuales = new ArrayList<>();
                        adapter = new HistorialPagosIndividualesAdapter();
                        pagosList = new ArrayList<>();

                        Pagos plazoActual = pagoses.get(0);
                        Double montoRestante = 0.0;
                        Double montoPagado = 0.0;
                        Double morosidad = 0.0;

                        for (Pagos pago : pagoses) {
                            if (pago.getIdEstatus().equals(Constants.DIAZFU_WEB_PENDIENTE)) {
                                Double morosidadParcial = 0.0;
                                /**-------Suma los pagos pendientes-------**/
                                montoRestante += pago.getMontoAPagar();

                                /**-------Calculo de la morosidad-------**/
                                Calendar itemActualDate = DateTimeUtils.getParseTimeFromSQL(DateTimeUtils.getActualTime());
                                Calendar itemDate = DateTimeUtils.getParseTimeFromSQL(pago.getFechaProgramada());

                                if ((itemActualDate.compareTo(itemDate) > 0) && (itemActualDate.get(Calendar.YEAR) == itemDate.get(Calendar.YEAR))) {
                                    int dias = itemActualDate.get(Calendar.DAY_OF_YEAR) - itemDate.get(Calendar.DAY_OF_YEAR);
                                    morosidadParcial = (dias + 1.0) * (Constants.MONTO_MORATORIO);
                                    morosidad += (dias + 1) * (Constants.MONTO_MORATORIO);
                                }

                                /**-------Busqueda del plazo actual-------**/
                                if (plazoActual.getIdEstatus().equals(Constants.DIAZFU_WEB_PENDIENTE)) {
                                    plazoActual = (plazoActual.getId().compareTo(pago.getId()) < 0) ? plazoActual : pago;
                                } else {
                                    plazoActual = pago;
                                }

                                /**-------Cache del preguardado-------**/
                                pago.setMorosidad(morosidadParcial);
                                pago.setMontoAPagar(pago.getMontoAPagar());
                                montoPagado = montoRestante - pago.getMontoPagado();

                                _pagosActuales.add(pago);
                            } else {
                                morosidad += pago.getMorosidad();
                            }

                            pagosList.add(pago);
                        }

                        FormularioPagosIndividualesFragment.tilMontoRestante.getEditText().setText(CommonUtils.showMeTheMoney(montoPagado));
                        FormularioPagosIndividualesFragment.tilMorosidad.getEditText().setText(CommonUtils.showMeTheMoney(morosidad));
                        FormularioPagosIndividualesFragment.tilPlazoActual.getEditText().setText(plazoActual.getPlazo());

                        if (_pagosActuales.size() > 0) {
                            FormularioPagosIndividualesFragment.txtEstatus.setVisibility(View.GONE);
                        } else {
                            FormularioPagosIndividualesFragment.txtEstatus.setVisibility(View.VISIBLE);
                            FormularioPagosIndividualesFragment.txtEstatus.setText("No existe historial de plazos.");
                        }

                        onPreRenderListadoHistorialPagos();
                    }
                });
    }

    /**
     * Carga el listado predeterminado de firebase
     **/
    public static void onPreRenderListadoHistorialPagos() {

        Collections.sort(pagosList, new Comparator<Pagos>() {
            @Override
            public int compare(Pagos o1, Pagos o2) {
                return (o1.getCliente().compareTo(o2.getCliente()));
            }
        });

        adapter.addAll(pagosList);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityInterface = (MainRegisterActivity) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Boton de fletes, añadir fletes", Toast.LENGTH_SHORT).show();
    }

    public static void onListenerAction(DecodeItemHelper decodeItem) {
        /**Inicializa DecodeItem en la activity principal**/
        activityInterface.setDecodeItem(decodeItem);

        switch (decodeItem.getIdView()) {
            case R.id.item_btn_inspeccionar_integrante_plazo_entrega:
                activityInterface.openExternalActivity(Constants.ACCION_VER, IntegrantesPlazosActivity.class);
                break;
        }
    }
}
