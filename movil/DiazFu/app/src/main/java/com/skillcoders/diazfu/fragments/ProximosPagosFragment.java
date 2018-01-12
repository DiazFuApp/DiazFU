package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.ProximosPagosAdapter;
import com.skillcoders.diazfu.data.model.Pagos;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.PagosRest;
import com.skillcoders.diazfu.fragments.interfaces.MainRegisterInterface;
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

public class ProximosPagosFragment extends Fragment implements View.OnClickListener {

    private static List<Pagos> pagosList;
    private static RecyclerView recyclerView;
    private static ProximosPagosAdapter adapter;
    private static MainRegisterInterface activityInterfaxe;
    private View view;
    public static LinearLayout linearLayout;


    /**
     * Implementaciones REST
     */
    private static PagosRest pagosRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_actividades, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.view_no_resultados);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_actividades);
        adapter = new ProximosPagosAdapter();
        adapter.setOnClickListener(this);

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
        proximosPagos();
    }

    public void proximosPagos() {

        Pagos pago = (Pagos) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_MAIN_BUSQUEDA);

        pagosRest.getProximosPagos(pago).subscribeOn(Schedulers.io())
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

                        adapter = new ProximosPagosAdapter();
                        pagosList = new ArrayList<>();
                        pagosList.addAll(pagos);
                        onPreRenderActividades();

                        if (pagosList.size() == 0) {
                            linearLayout.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    /**
     * Carga el listado predeterminado de firebase
     **/
    private static void onPreRenderActividades() {

        Collections.sort(pagosList, new Comparator<Pagos>() {
            @Override
            public int compare(Pagos o1, Pagos o2) {
                return (o1.getId().compareTo(o2.getId()));
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
            activityInterfaxe = (MainRegisterActivity) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Boton de fletes, añadir fletes", Toast.LENGTH_SHORT).show();
    }
}
