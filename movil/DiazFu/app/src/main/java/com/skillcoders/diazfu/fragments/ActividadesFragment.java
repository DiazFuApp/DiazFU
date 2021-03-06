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
import com.skillcoders.diazfu.adapters.ActividadesAdapter;
import com.skillcoders.diazfu.data.model.Actividades;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ActividadesRest;
import com.skillcoders.diazfu.fragments.interfaces.NavigationDrawerInterface;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by saurett on 24/02/2017.
 */

public class ActividadesFragment extends Fragment implements View.OnClickListener {

    private static Usuarios _SESSION_USER;

    private static List<Actividades> actividadesList;
    private static RecyclerView recyclerView;
    private static ActividadesAdapter actividadesAdapter;
    private static NavigationDrawerInterface activityInterface;
    private View view;
    public static LinearLayout linearLayout;


    /**
     * Implementaciones REST
     */
    private static ActividadesRest actividadesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_actividades, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.view_no_resultados);

        _SESSION_USER = (Usuarios) getActivity().getIntent().getExtras().getSerializable(Constants.KEY_SESSION_USER);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_actividades);
        actividadesAdapter = new ActividadesAdapter();
        actividadesAdapter.setOnClickListener(this);

        actividadesRest = ApiUtils.getActividadesRest();

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
        listadoActividades();
    }

    public static void listadoActividades() {
        activityInterface.showProgressDialog();
        actividadesRest.getActividades().enqueue(new Callback<List<Actividades>>() {
            @Override
            public void onResponse(Call<List<Actividades>> call, Response<List<Actividades>> response) {

                activityInterface.stopProgressDialog();
                if (response.isSuccessful()) {
                    actividadesAdapter = new ActividadesAdapter();
                    actividadesList = new ArrayList<>();

                    for (Actividades actividad :
                            response.body()) {

                        switch (_SESSION_USER.getIdTipoActor()) {
                            case Constants.DIAZFU_WEB_TIPO_ACTOR_ADMINISTRADOR:
                                actividadesList.add(actividad);
                                break;
                            case Constants.DIAZFU_WEB_TIPO_ACTOR_PROMOTOR:
                                if (actividad.getIdPromotor().equals(_SESSION_USER.getIdActor())) {
                                    actividadesList.add(actividad);
                                }
                                break;
                        }
                    }


                    onPreRenderActividades();

                    if (actividadesList.size() == 0) {
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Actividades>> call, Throwable t) {
                activityInterface.stopProgressDialog();
            }
        });
    }

    /**
     * Carga el listado predeterminado de firebase
     **/
    private static void onPreRenderActividades() {

        Collections.sort(actividadesList, new Comparator<Actividades>() {
            @Override
            public int compare(Actividades o1, Actividades o2) {
                return (o1.getTitulo().compareTo(o2.getTitulo()));
            }
        });

        actividadesAdapter.addAll(actividadesList);
        recyclerView.setAdapter(actividadesAdapter);

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
            activityInterface = (NavigationDrawerInterface) getActivity();
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
            case R.id.item_btn_finalizar_actividad:
                activityInterface.showQuestion("Finalizar", "¿Esta seguro que desea finalizar?");
                break;
            case R.id.item_btn_editar_actividad:
                activityInterface.openExternalActivity(Constants.ACCION_EDITAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_ver_actividad:
                activityInterface.openExternalActivity(Constants.ACCION_VER, MainRegisterActivity.class);
                break;
            case R.id.item_btn_eliminar_actividad:
                activityInterface.showQuestion("Eliminar", "¿Esta seguro que desea eliminar?");
                break;
        }
    }
}
