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

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.GruposAdapter;
import com.skillcoders.diazfu.data.model.Grupos;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.GruposRest;
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
 * Created by jvier on 05/09/2017.
 */

public class GruposFragment extends Fragment implements View.OnClickListener {

    private static List<Grupos> gruposList;
    private static RecyclerView recyclerView;
    private static GruposAdapter gruposAdapter;
    private static NavigationDrawerInterface activityInterface;
    public static LinearLayout linearLayout;

    /**
     * Implementaciones REST
     */
    private static GruposRest gruposRest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grupos, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.view_no_resultados);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_grupos);
        gruposAdapter = new GruposAdapter();
        gruposAdapter.setOnClickListener(this);

        gruposRest = ApiUtils.getGruposRest();

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
        listadoGrupos();
    }

    public static void listadoGrupos() {
        activityInterface.showProgressDialog();
        gruposRest.getGrupos().enqueue(new Callback<List<Grupos>>() {
            @Override
            public void onResponse(Call<List<Grupos>> call, Response<List<Grupos>> response) {

                activityInterface.stopProgressDialog();
                if (response.isSuccessful()) {
                    gruposAdapter = new GruposAdapter();
                    gruposList = new ArrayList<>();
                    gruposList.addAll(response.body());
                    onPreRender();

                    if (gruposList.size() == 0) {
                        linearLayout.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Grupos>> call, Throwable t) {
                activityInterface.stopProgressDialog();
            }
        });
    }

    private static void onPreRender() {
        Collections.sort(gruposList, new Comparator<Grupos>() {
            @Override
            public int compare(Grupos o1, Grupos o2) {
                return (o1.getNombre().compareTo(o2.getNombre()));
            }
        });

        gruposAdapter.addAll(gruposList);
        recyclerView.setAdapter(gruposAdapter);

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

    }

    public static void onListenerAction(DecodeItemHelper decodeItem) {
        /**Inicializa DecodeItem en la activity principal**/
        activityInterface.setDecodeItem(decodeItem);

        switch (decodeItem.getIdView()) {
            case R.id.item_btn_editar_grupo:
                activityInterface.openExternalActivity(Constants.ACCION_EDITAR, MainRegisterActivity.class);
                break;
            case R.id.item_btn_eliminar_grupo:
                activityInterface.showQuestion("Eliminar", "¿Esta seguro que desea elminar?");
                break;
            case R.id.item_btn_autorizan_grupo:
                activityInterface.showQuestion("Autorizar", "¿Esta seguro que desea autorizar?");
                break;
        }
    }
}
