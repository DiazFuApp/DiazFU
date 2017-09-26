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
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.fragments.interfaces.NavigationDrawerInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by saurett on 24/02/2017.
 */

public class ClientesFragment extends Fragment implements View.OnClickListener {

    private static List<Promotores> administradoresList;
    private static RecyclerView recyclerViewClientes;
    private ClientesAdapter clientesAdapter;
    private static NavigationDrawerInterface navigationDrawerInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        recyclerViewClientes = (RecyclerView) view.findViewById(R.id.recycler_view_clientes);
        clientesAdapter = new ClientesAdapter();
        clientesAdapter.setOnClickListener(this);

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

        clientesAdapter = new ClientesAdapter();
        administradoresList = new ArrayList<>();

        Promotores p1 = new Promotores();
        p1.setNombre("Hola mundo");
        administradoresList.add(p1);
        Promotores p2 = new Promotores();
        p2.setNombre("Hola mundo2");
        administradoresList.add(p2);
        Promotores p3 = new Promotores();
        p3.setNombre("Hola mundo3");
        administradoresList.add(p3);

        onPreRenderClientes();

        /*
        listenerColaboradores = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                clientesAdapter = new ColaboradoresAdapter();
                administradoresList = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Administradores colaborador = postSnapshot.getValue(Administradores.class);
                    if (!colaborador.getEstatus().equals(Constants.FB_KEY_ITEM_ESTATUS_ELIMINADO)) {

                        if (colaborador.getTipoDeUsuario().equals(Constants.FB_KEY_ITEM_TIPO_USUARIO_ADMINISTRADOR)) continue;

                        administradoresList.add(colaborador);
                    }
                }

                onPreRenderClientes();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        drColaboradores.addValueEventListener(listenerColaboradores);
        */
    }

    /**
     * Carga el listado predeterminado de firebase
     **/
    private void onPreRenderClientes() {

        Collections.sort(administradoresList, new Comparator<Promotores>() {
            @Override
            public int compare(Promotores o1, Promotores o2) {
                return (o1.getNombre().compareTo(o2.getNombre()));
            }
        });

        clientesAdapter.addAll(administradoresList);
        recyclerViewClientes.setAdapter(clientesAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewClientes.setLayoutManager(linearLayoutManager);

        if (administradoresList.size() == 0) {
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
