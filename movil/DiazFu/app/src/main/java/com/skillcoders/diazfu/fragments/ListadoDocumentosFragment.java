package com.skillcoders.diazfu.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Documentos;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.services.DocumentosService;
import com.skillcoders.diazfu.services.SharedPreferencesService;
import com.skillcoders.diazfu.utils.FileUtils;
import com.skillcoders.diazfu.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * Created by saurett on 24/02/2017.
 */

public class ListadoDocumentosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    /**
     * Variables de la camara
     **/
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String mCurrentPhotoPath;

    private static Usuarios _SESSION_USER;
    private static Documentos _MAIN_DOCUMENTOS;
    public static Button btnGuardar, btnAgegar;
    private Spinner spinnerTipoDocumento;

    private static List<String> tiposDocumentosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_documentos, container, false);

        _SESSION_USER = SharedPreferencesService.getUsuarioActual(getContext());
        _MAIN_DOCUMENTOS = (Documentos) getActivity().getIntent().getSerializableExtra(Constants.KEY_MAIN_DOCUMENTOS);

        btnAgegar = (Button) view.findViewById(R.id.btn_agregar_documento);
        btnGuardar = (Button) view.findViewById(R.id.btn_guardar_documento);
        btnAgegar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

        spinnerTipoDocumento = (Spinner) view.findViewById(R.id.spinner_tipo_documento_documentos);
        spinnerTipoDocumento.setOnItemSelectedListener(this);

        this.listadoTiposDocumentos();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
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
            case R.id.btn_agregar_documento:
                this.checkCameraPermission();
                break;
            case R.id.btn_guardar_documento:
                this.sincronizarDocumento();
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
        _MAIN_DOCUMENTOS.setIdTipoDocumento(position);

        getActivity().getIntent().putExtra(Constants.KEY_MAIN_DOCUMENTOS, _MAIN_DOCUMENTOS);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.listado_documentos_container, new DocumentosFragment(), Constants.FRAGMENT_DOCUMENTOS);
        mainFragment.commit();

        btnGuardar.setVisibility(View.GONE);
        btnAgegar.setVisibility((position > 0) ? View.VISIBLE : View.GONE);
    }

    private void checkCameraPermission() {

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    101);
        } else {
            dispatchTakePictureIntent();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = FileUtils.createImageFile(getContext());
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                mCurrentPhotoPath = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                FileUtils.setPic(DocumentosFragment.btDocumento, mCurrentPhotoPath);
                btnGuardar.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                Toast.makeText(getContext(), "No es posible obtener la foto, intente nuevamente...", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    private void sincronizarDocumento() {
        Documentos documento = DocumentosFragment._documentoActual;
        documento.setIdTipoDocumento(_MAIN_DOCUMENTOS.getIdTipoDocumento());
        documento.setIdActor(_MAIN_DOCUMENTOS.getIdActor());
        documento.setIdTipoActor(_MAIN_DOCUMENTOS.getIdTipoActor());
        documento.setIdUsuario(_SESSION_USER.getId());

        new DocumentosService(getActivity(),
                mCurrentPhotoPath, documento).execute();
    }
}
