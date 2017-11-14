package com.skillcoders.diazfu.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.PrestamosIndividuales;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.PrestamosIndividualesRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;
import com.skillcoders.diazfu.utils.ValidationUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioEntregaPrestamosIndividualesFragment extends Fragment {

    private static DecodeExtraHelper _MAIN_DECODE;

    private static TextInputLayout tilAnticipo;
    private View view;

    public static PrestamosIndividuales _prestamosIndividuales;

    /**
     * Implementaciones REST
     */
    private PrestamosIndividualesRest prestamosIndividualesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_entregas_prestamos_individuales_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        tilAnticipo = (TextInputLayout) view.findViewById(R.id.anticipo_entrega_individual);

        prestamosIndividualesRest = ApiUtils.getPrestamosIndividualesRest();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.onPreRender();
    }

    private void onPreRender() {
        switch (_MAIN_DECODE.getAccionFragmento()) {
            case Constants.ACCION_EDITAR:
            case Constants.ACCION_VER:
            case Constants.ACCION_ENTREGAR:
                this.obtenerGrupo();
                view.setVisibility(View.VISIBLE);
                break;
            case Constants.ACCION_AUTORIZAR:
            case Constants.ACCION_REGISTRAR:
                _prestamosIndividuales = new PrestamosIndividuales();
                view.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void obtenerGrupo() {
        PrestamosIndividuales prestamoIndividual = ((PrestamosIndividuales) _MAIN_DECODE.getDecodeItem().getItemModel());

        prestamosIndividualesRest.getPrestamosIndividuales(Long.valueOf(prestamoIndividual.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PrestamosIndividuales>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(PrestamosIndividuales prestamoIndividual) {

                        _prestamosIndividuales = prestamoIndividual;

                        tilAnticipo.getEditText().setText(prestamoIndividual.getAnticipo().toString());

                        onPreRenderUI();
                    }
                });
    }

    private void onPreRenderUI() {
        tilAnticipo.getEditText().setKeyListener(null);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.listado_intregantes_plazos_prestamos_individuales_container, new IntegrantesPlazosPrestamosIndividualesFragment(), Constants.FORMULARIO_PRESTAMOS_INDIVIDUALES_INTEGRANTES_PLAZOS);
        mainFragment.commit();
    }

    public static boolean validarDatosEntrega() {
        boolean valido = false;

        String anticipo = tilAnticipo.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilAnticipo, anticipo);

        if (a) {
            FormularioPrestamosIndividualesFragment._prestamoIndividualActual.setAnticipo(Double.valueOf(anticipo));
            FormularioPrestamosIndividualesFragment._prestamoIndividualActual.setFechaEntrega(DateTimeUtils.getActualTime());
            FormularioPrestamosIndividualesFragment._prestamoIndividualActual.setIdEstatus(Constants.DIAZFU_WEB_ENTREGADO);
            valido = true;
        }

        return valido;
    }
}
