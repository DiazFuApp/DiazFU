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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.model.PrestamosGrupales;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.PagosRest;
import com.skillcoders.diazfu.data.remote.rest.PrestamosGrupalesRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
import com.skillcoders.diazfu.fragments.interfaces.NavigationDrawerInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;
import com.skillcoders.diazfu.utils.DateTimeUtils;
import com.skillcoders.diazfu.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jvier on 03/10/2017.
 */

public class FormularioEntregaPrestamosGrupalesFragment extends Fragment {

    private static DecodeExtraHelper _MAIN_DECODE;

    private static TextInputLayout tilAnticipo;
    private View view;

    public static PrestamosGrupales _prestamoGrupalActual;

    /**
     * Implementaciones REST
     */
    private PrestamosGrupalesRest prestamosGrupalesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_entregas_prestamos_grupales_formulario, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        tilAnticipo = (TextInputLayout) view.findViewById(R.id.anticipo_entrega);

        prestamosGrupalesRest = ApiUtils.getPrestamosGrupalesRest();

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
                //RegistroPrestamosGrupalesFragment.frameEntrega.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
                break;
            case Constants.ACCION_AUTORIZAR:
            case Constants.ACCION_REGISTRAR:
                _prestamoGrupalActual = new PrestamosGrupales();
                //RegistroPrestamosGrupalesFragment.frameEntrega.setVisibility(View.GONE);
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
        PrestamosGrupales prestamoGrupal = ((PrestamosGrupales) _MAIN_DECODE.getDecodeItem().getItemModel());

        prestamosGrupalesRest.getPrestamoGrupal(Long.valueOf(prestamoGrupal.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PrestamosGrupales>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(PrestamosGrupales prestamoGrupal) {

                        _prestamoGrupalActual = prestamoGrupal;

                        tilAnticipo.getEditText().setText(prestamoGrupal.getAnticipo().toString());

                        onPreRenderUI();
                    }
                });
    }

    private void onPreRenderUI() {
        tilAnticipo.getEditText().setKeyListener(null);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.listado_intregantes_plazos_prestamos_grupales_container, new IntegrantesPlazosPrestamosGrupalesFragment(), Constants.FORMULARIO_PRESTAMOS_GRUPALES_INTEGRANTES_PLAZOS);
        mainFragment.commit();
    }

    public static boolean validarDatosEntrega() {
        boolean valido = false;

        String anticipo = tilAnticipo.getEditText().getText().toString();

        boolean a = ValidationUtils.esTextoValido(tilAnticipo, anticipo);

        if (a) {
            FormularioPrestamosGrupalesFragment._prestamoGrupalActual.setAnticipo(Double.valueOf(anticipo));
            FormularioPrestamosGrupalesFragment._prestamoGrupalActual.setFechaEntrega(DateTimeUtils.getActualTime());
            FormularioPrestamosGrupalesFragment._prestamoGrupalActual.setIdEstatus(Constants.DIAZFU_WEB_ENTREGADO);
            valido = true;
        }

        return valido;
    }
}
