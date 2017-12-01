package com.skillcoders.diazfu.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.skillcoders.diazfu.DocumentosActivity;
import com.skillcoders.diazfu.data.model.Documentos;
import com.skillcoders.diazfu.fragments.DocumentosFragment;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by jvier on 29/11/2017.
 */
// Include the following imports to use blob APIs.

public class DocumentosService extends AsyncTask<Void, Void, Boolean> {

    private Context context;
    private Documentos documento;
    private String path;
    private ProgressDialog pDialog;

    private static final String store_account_name = "diazfu;";
    private static final String storage_account_key = "1e4ermzcSYuCxE9kA9PuwM5pCf6wTxdCkbJ98orW8LqXB/94ZDLFAKWVXX7wCEUNy8EOl3dYVPyMxB3FRXrXZQ==";
    private static String container_reference = "archivos";

    public static final String storageConnectionString =
            "DefaultEndpointsProtocol=http;" +
                    "AccountName=" + store_account_name +
                    "AccountKey=" + storage_account_key;

    public DocumentosService(Context context, String path, Documentos documento) {
        this.context = context;
        this.documento = documento;
        this.path = path;
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
            /**Setup the cloud storage account.**/
            CloudStorageAccount account = CloudStorageAccount
                    .parse(storageConnectionString);

            /**Create a blob service client**/
            CloudBlobClient blobClient = account.createCloudBlobClient();

            /**Get a reference to a container
             The container name must be lower case
             Append a random UUID to the end of the container name so that
             this sample can be run more than once in quick succession.
             **/
            CloudBlobContainer container = blobClient.getContainerReference(container_reference);
            // Make the container public
            // Create a permissions object
            BlobContainerPermissions containerPermissions = new BlobContainerPermissions();

            // Include public access in the permissions object
            containerPermissions
                    .setPublicAccess(BlobContainerPublicAccessType.CONTAINER);

            // Set the permissions on the container
            container.uploadPermissions(containerPermissions);

            // Create the container if it does not exist
            container.createIfNotExists();

            // Upload an image file.
            String nombreArchivo = documento.getIdTipoDocumento() + "_"
                    + documento.getIdActor() + "_"
                    + documento.getIdTipoActor() + ".jpg";

            CloudBlockBlob blob = container.getBlockBlobReference(nombreArchivo);
            File sourceFile = new File(path);
            blob.upload(new FileInputStream(sourceFile), sourceFile.length());

            documento.setURLDocumento(blob.getStorageUri().getPrimaryUri().toString());

            validOperation = true;
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
            if (null != documento.getId()) {
                DocumentosFragment.actualizar(documento);
            } else {
                DocumentosFragment.registrar(documento);
            }
        } catch (Exception e) {
            Toast.makeText(context, "Se ha detectado un error intente nuevamente", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
