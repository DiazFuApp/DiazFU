package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Documentos;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by saurett on 24/02/2017.
 */

public class ListadoDocumentosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static Usuarios _SESSION_USER;
    private static Documentos _MAIN_DOCUMENTOS;
    public static Button btnAbrir, btnAgegar;
    private Spinner spinnerTipoDocumento;

    private static List<String> tiposDocumentosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_documentos, container, false);

        _SESSION_USER = (Usuarios) getActivity().getIntent().getSerializableExtra(Constants.KEY_SESSION_USER);
        _MAIN_DOCUMENTOS = (Documentos) getActivity().getIntent().getSerializableExtra(Constants.KEY_MAIN_DOCUMENTOS);

        btnAgegar = (Button) view.findViewById(R.id.btn_agregar_documento);
        btnAgegar.setOnClickListener(this);

        spinnerTipoDocumento = (Spinner) view.findViewById(R.id.spinner_tipo_documento_documentos);
        spinnerTipoDocumento.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        this.listadoTiposDocumentos();

        getActivity().setTitle(getString(R.string.default_activity_title_documentos));
    }


    private void listadoTiposDocumentos() {
        tiposDocumentosList = new ArrayList<>();
        tiposDocumentosList.add("Seleccione ...");
        tiposDocumentosList.add(Constants.DIAZFU_WEB_TIPO_DOCUMENTO_ACTA_NACIMIENTO_STR);
        tiposDocumentosList.add(Constants.DIAZFU_WEB_TIPO_DOCUMENTO_INE_STR);
        tiposDocumentosList.add(Constants.DIAZFU_WEB_TIPO_DOCUMENTO_CURP_STR);
        tiposDocumentosList.add(Constants.DIAZFU_WEB_TIPO_DOCUMENTO_CONSTANCIA_RESIDENCIA_STR);
        tiposDocumentosList.add(Constants.DIAZFU_WEB_TIPO_DOCUMENTO_COMPROBANTE_DOMICILIO_STR);
        tiposDocumentosList.add(Constants.DIAZFU_WEB_TIPO_DOCUMENTO_COMPROBANTE_INGRESOS_STR);
        tiposDocumentosList.add(Constants.DIAZFU_WEB_TIPO_DOCUMENTO_COMPROBANTE_FOTO_STR);

        onCargarSpinnerTiposDocumentos();
    }

    private void onCargarSpinnerTiposDocumentos() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, tiposDocumentosList);

        spinnerTipoDocumento.setAdapter(adapter);
        spinnerTipoDocumento.setSelection(0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_registrar_cliente:
                DecodeExtraHelper extra = new DecodeExtraHelper();

                extra.setTituloActividad(getString(Constants.TITLE_ACTIVITY.get(v.getId())));
                extra.setTituloFormulario(getString(R.string.default_form_title_new));
                extra.setAccionFragmento(Constants.ACCION_REGISTRAR);
                extra.setFragmentTag(Constants.ITEM_FRAGMENT.get(v.getId()));

                Intent intent = new Intent(getActivity(), MainRegisterActivity.class);
                intent.putExtra(Constants.KEY_MAIN_DECODE, extra);
                intent.putExtra(Constants.KEY_SESSION_USER, _SESSION_USER);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_tipo_documento_documentos:
                this.listadoDocumentos(position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void listadoDocumentos(int position) {
        Documentos documento = _MAIN_DOCUMENTOS;
        documento.setIdTipoDocumento(position);

        getActivity().getIntent().putExtra(Constants.KEY_MAIN_DOCUMENTOS, documento);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.listado_documentos_container, new DocumentosFragment(), Constants.FRAGMENT_DOCUMENTOS);
        mainFragment.commit();
    }


}
