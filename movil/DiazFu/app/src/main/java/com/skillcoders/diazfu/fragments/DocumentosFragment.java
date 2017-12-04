package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapThumbnail;
import com.skillcoders.diazfu.MainRegisterActivity;
import com.skillcoders.diazfu.R;
import com.skillcoders.diazfu.data.model.Documentos;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.DocumentosRest;
import com.skillcoders.diazfu.fragments.interfaces.DocumentosInterface;
import com.skillcoders.diazfu.helpers.ClientesHelper;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.helpers.DocumentosHelper;
import com.skillcoders.diazfu.services.DocumentosService;
import com.skillcoders.diazfu.services.DownloadService;
import com.skillcoders.diazfu.utils.FileUtils;
import com.skillcoders.diazfu.utils.Constants;

import java.io.IOException;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by saurett on 24/02/2017.
 */

public class DocumentosFragment extends Fragment implements View.OnClickListener {

    private static Documentos _MAIN_DOCUMENTOS;
    private static DocumentosInterface activityInterface;

    public static BootstrapThumbnail btDocumento;

    public static Documentos _documentoActual;

    /**
     * Implementaciones REST
     */
    private static DocumentosRest documentosRest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_documentos, container, false);

        _MAIN_DOCUMENTOS = (Documentos) getActivity().getIntent().getSerializableExtra(Constants.KEY_MAIN_DOCUMENTOS);

        btDocumento = (BootstrapThumbnail) view.findViewById(R.id.imagenView_documento);

        documentosRest = ApiUtils.getDocumentosRest();
        listadoDocumentos();

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
    }


    public void listadoDocumentos() {

        documentosRest.getDocumentos(_MAIN_DOCUMENTOS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Documentos>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Documentos> data) {
                        _documentoActual = new Documentos();
                        ListadoDocumentosFragment.btnAgegar.setText("Actualizar");
                        if (data.size() == 0) {
                            btDocumento.setImageDrawable(btDocumento.getContext().getDrawable(R.drawable.diazfu_logo));
                            ListadoDocumentosFragment.btnAgegar.setText("Agregar");
                        } else {
                            _documentoActual = data.get(0);
                            new DownloadService(getActivity(), btDocumento, _documentoActual).execute();
                        }

                    }
                });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityInterface = (DocumentosInterface) getActivity();
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
            case R.id.item_btn_editar_cliente:
                activityInterface.openExternalActivity(Constants.ACCION_EDITAR, MainRegisterActivity.class);
                break;
        }
    }

    public static void registrar(Documentos documento) {
        DocumentosHelper helper = new DocumentosHelper();
        helper.setDocumento(documento);

        activityInterface.registrarDocumento(helper);
    }

    public static void actualizar(Documentos documento) {
        DocumentosHelper helper = new DocumentosHelper();
        helper.setDocumento(documento);

        activityInterface.actualizarDocumento(helper);
    }
}
