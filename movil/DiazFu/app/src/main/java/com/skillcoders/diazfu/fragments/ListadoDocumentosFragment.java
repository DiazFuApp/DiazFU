package com.skillcoders.diazfu.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapThumbnail;
import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.adapters.DocumentosAdapter;
import com.skillcoders.diazfu.data.model.Documentos;
import com.skillcoders.diazfu.data.model.Usuarios;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.services.FileService;
import com.skillcoders.diazfu.utils.Constants;

import org.w3c.dom.DocumentFragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * Created by saurett on 24/02/2017.
 */

public class ListadoDocumentosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int CROP_IMAGE_ACTIVITY_REQUEST_CODE = 2;
    private Boolean CHANGE_PHOTO = false;
    private String mCurrentPhotoPath;
    private String mCurrentPhotoPathCROP;

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
                photoFile = FileService.createImageFile(getContext());
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
                FileService.setPic(DocumentosFragment.btDocumento, mCurrentPhotoPath);
                galleryAddPic();
            } catch (Exception e) {
                Toast.makeText(getContext(), "No es posible obtener la foto, intente nuevamente...", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    private void setPic() {
        ImageView mImageView = DocumentosFragment.btDocumento;

        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
        mImageView.setVisibility(View.VISIBLE);
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }
}
