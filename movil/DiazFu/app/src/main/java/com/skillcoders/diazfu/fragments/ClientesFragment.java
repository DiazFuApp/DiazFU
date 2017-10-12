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
import com.skillcoders.diazfu.adapters.ClientesAdapter;
import com.skillcoders.diazfu.adapters.PromotoresAdapter;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.fragments.interfaces.NavigationDrawerInterface;

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

public class ClientesFragment extends Fragment implements View.OnClickListener {

    private static List<Clientes> clientesList;
    private static RecyclerView recyclerViewClientes;
    private ClientesAdapter clientesAdapter;
    private static NavigationDrawerInterface navigationDrawerInterface;


    /**
     * Implementaciones REST
     */
    private ClientesRest clientesRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        recyclerViewClientes = (RecyclerView) view.findViewById(R.id.recycler_view_clientes);
        clientesAdapter = new ClientesAdapter();
        clientesAdapter.setOnClickListener(this);

        clientesRest = ApiUtils.getClientesRest();

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
        this.listadoClientes();
    }

    private void listadoClientes() {
        clientesRest.getClientes().enqueue(new Callback<List<Clientes>>() {
            @Override
            public void onResponse(Call<List<Clientes>> call, Response<List<Clientes>> response) {

                if (response.isSuccessful()) {
                    clientesAdapter = new ClientesAdapter();
                    clientesList = new ArrayList<>();
                    clientesList.addAll(response.body());
                    onPreRender();
                }
            }

            @Override
            public void onFailure(Call<List<Clientes>> call, Throwable t) {

            }
        });
    }

    /**
     * Carga el listado predeterminado de firebase
     **/
    private void onPreRender() {

        Collections.sort(clientesList, new Comparator<Clientes>() {
            @Override
            public int compare(Clientes o1, Clientes o2) {
                return (o1.getNombre().compareTo(o2.getNombre()));
            }
        });

        clientesAdapter.addAll(clientesList);
        recyclerViewClientes.setAdapter(clientesAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewClientes.setLayoutManager(linearLayoutManager);

        if (clientesList.size() == 0) {
            Toast.makeText(getActivity(), "La lista se encuentra vacía", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //navigationDrawerInterface = (NavigationDrawerInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Boton de fletes, añadir fletes", Toast.LENGTH_SHORT).show();
    }
}
