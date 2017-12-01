package com.skillcoders.diazfu.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapThumbnail;
import com.skillcoders.diazfu.data.model.Documentos;
import com.skillcoders.diazfu.utils.FileUtils;

/**
 * Created by jvier on 29/11/2017.
 */

public class DownloadService extends AsyncTask<Void, Void, Boolean> {

    private Context context;
    private Documentos documento;
    private BootstrapThumbnail thumbnail;
    private Bitmap path;

    private ProgressDialog pDialog;

    public DownloadService(Context context, BootstrapThumbnail thumbnail, Documentos documento) {
        this.context = context;
        this.documento = documento;
        this.thumbnail = thumbnail;
    }

    @Override
    protected void onPreExecute() {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Espere un momento ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        Boolean validOperation = false;
        try {
            path = FileUtils.downloadFile(context, documento.getURLDocumento());
            validOperation = (path != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return validOperation;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        pDialog.dismiss();
        try {
            if (aBoolean) thumbnail.setImageBitmap(path);
        } catch (Exception e) {
            Toast.makeText(context, "Se ha detectado un error intente nuevamente", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
