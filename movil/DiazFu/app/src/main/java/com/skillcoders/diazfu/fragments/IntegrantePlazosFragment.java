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

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.PlazosAdapter;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.PagosRest;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by saurett on 24/02/2017.
 */

public class IntegrantePlazosFragment extends Fragment implements View.OnClickListener {

    private static DecodeExtraHelper _MAIN_DECODE;

    public static List<Pagos> pagosList;
    private static RecyclerView recyclerView;
    public static PlazosAdapter adapter;

    public static List<IntegrantesGrupos> integrantesGrupos;

    /**
     * Implementaciones REST
     */
    private static PagosRest pagosRest;
    private static ClientesRest clientesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        _MAIN_DECODE = (DecodeExtraHelper) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_DECODE);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_clientes);
        adapter = new PlazosAdapter();
        adapter.setOnClickListener(this);

        clientesRest = ApiUtils.getClientesRest();
        pagosRest = ApiUtils.getPagosRest();

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
        this.onPreRender();
    }

    private void onPreRender() {

        integrantesGrupos = new ArrayList<>();
        this.listadoIntegrantes();
    }

    private void listadoIntegrantes() {

        final IntegrantesGrupos integranteGrupo = (IntegrantesGrupos) _MAIN_DECODE.getDecodeItem().getItemModel();

        Pagos pago = new Pagos();
        pago.setIdTipoPrestamo(Constants.TIPO_PRESTAMO_GRUPAL);
        pago.setIdPrestamo(integranteGrupo.getId());

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
                    public void onNext(List<Pagos> pagos) {

                        adapter = new PlazosAdapter();
                        pagosList = new ArrayList<>();

                        for (Pagos pago :
                                pagos) {
                            if (integranteGrupo.getIdCliente().equals(pago.getIdCliente())) {
                                pagosList.add(pago);
                            }
                        }

                        onPreRenderListadoIntegrantes();

                        if (pagosList.size() > 0)
                            IntegrantesPlazosEntregasFragment.showMessageAsignacion(View.GONE, "");
                    }
                });
    }

    /**
     * Carga el listado predeterminado de firebase
     **/
    public static void onPreRenderListadoIntegrantes() {

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
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Boton de fletes, añadir fletes", Toast.LENGTH_SHORT).show();
    }
}
