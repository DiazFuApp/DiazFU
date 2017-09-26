package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.PromotoresAdapter;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.remote.ApiUtils;
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
 * Created by jvier on 05/09/2017.
 */

public class PromotoresFragment extends Fragment implements View.OnClickListener {

    private static List<Promotores> promotoresList;
    private static RecyclerView recyclerView;
    private PromotoresAdapter promotoresAdapter;
    private static NavigationDrawerInterface navigationDrawerInterface;

    /**
     * Implementaciones REST
     */
    private PromotoresRest promotoresRest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotores, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_promotores);
        promotoresAdapter = new PromotoresAdapter();
        promotoresAdapter.setOnClickListener(this);

        promotoresRest = ApiUtils.getPromotoresRest();

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
        listadoPromotores();
    }

    private void listadoPromotores() {
        promotoresRest.getPromotores().enqueue(new Callback<List<Promotores>>() {
            @Override
            public void onResponse(Call<List<Promotores>> call, Response<List<Promotores>> response) {

                if (response.isSuccessful()) {
                    promotoresAdapter = new PromotoresAdapter();
                    promotoresList = new ArrayList<>();
                    promotoresList.addAll(response.body());
                    onPreRender();
                }
            }

            @Override
            public void onFailure(Call<List<Promotores>> call, Throwable t) {

            }
        });
    }

    private void onPreRender() {
        Collections.sort(promotoresList, new Comparator<Promotores>() {
            @Override
            public int compare(Promotores o1, Promotores o2) {
                return (o1.getNombre().compareTo(o2.getNombre()));
            }
        });

        promotoresAdapter.addAll(promotoresList);
        recyclerView.setAdapter(promotoresAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
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
            //navigationDrawerInterface = (NavigationDrawerInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "debe implementar");
        }
    }

    @Override
    public void onClick(View v) {

    }
}
