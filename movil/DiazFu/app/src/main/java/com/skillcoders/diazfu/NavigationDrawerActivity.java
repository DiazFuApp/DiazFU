package com.skillcoders.diazfu;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.skillcoders.diazfu.data.model.Actividades;
import com.skillcoders.diazfu.data.model.Clientes;
import com.skillcoders.diazfu.data.model.Grupos;
import com.skillcoders.diazfu.data.model.Promotores;
import com.skillcoders.diazfu.data.remote.ApiUtils;
import com.skillcoders.diazfu.data.remote.rest.ActividadesRest;
import com.skillcoders.diazfu.data.remote.rest.ClientesRest;
import com.skillcoders.diazfu.data.remote.rest.GruposRest;
import com.skillcoders.diazfu.data.remote.rest.PromotoresRest;
import com.skillcoders.diazfu.fragments.ActividadesFragment;
import com.skillcoders.diazfu.fragments.ClientesFragment;
import com.skillcoders.diazfu.fragments.GruposFragment;
import com.skillcoders.diazfu.fragments.PromotoresFragment;
import com.skillcoders.diazfu.fragments.interfaces.NavigationDrawerInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigationDrawerInterface, DialogInterface.OnClickListener {

    private static final String TAG = NavigationDrawerActivity.class.getSimpleName();

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    private static DecodeItemHelper _decodeItem;
    private ProgressDialog pDialog;
    private NavigationView navigationView;

    /**
     * Implementaciones REST
     */
    private PromotoresRest promotoresRest;
    private ClientesRest clientesRest;
    private GruposRest gruposRest;
    private ActividadesRest actividadesRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        promotoresRest = ApiUtils.getPromotoresRest();
        clientesRest = ApiUtils.getClientesRest();
        gruposRest = ApiUtils.getGruposRest();
        actividadesRest = ApiUtils.getActividadesRest();

        onPreRenderMenu(navigationView);
        checkAndRequestPermissions();
    }

    private boolean checkAndRequestPermissions() {
        int cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        int writeStoragePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int callPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
        }

        if (writeStoragePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (callPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }

        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                    this, listPermissionsNeeded.toArray(
                            new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {

                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put(android.Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CALL_PHONE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);

                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                            && perms.get(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                            showDialogOK("Permisos necesarios para esta aplicación",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    checkAndRequestPermissions();
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    break;
                                            }
                                        }
                                    });
                        }
                        //permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false
                        else {
                            closeNavigation();
                            Toast.makeText(this, getString(R.string.default_enable_permissions), Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                }
            }
        }

    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    @Override
    public void onBackPressed() {
        //TODO REGRESAR A INICIO CUANDO EXISTA OTRO FRAGMENTO
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_main_container);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (null != fragment.getFragmentManager().getFragments()) {
            MenuItem menuItem = navigationView.getMenu().getItem(0);
            onNavigationItemSelected(menuItem);
            navigationView.setCheckedItem(menuItem.getItemId());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_logout:
                closeNavigation();
                break;
            case R.id.action_soporte:
                openSoporte();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Abre la aplicación de gmail para enviar correo al equipo de soporte.
     */
    private void openSoporte() {

    }

    /**
     * Cierra la actividad actual y la sessión para ir al login.
     */
    private void closeNavigation() {
        Intent intent = new Intent(NavigationDrawerActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onPreRenderMenu(NavigationView navigationView) {
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        this.closeAllFragment();

        switch (id) {
            case R.id.menu_item_inicio:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_inicio));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_promotores:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_promotores));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_clientes:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_clientes));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_grupos:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_grupos));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_prestamos_grupales:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_prestamos_grupales));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_prestamos_individuales:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_prestamos_individuales));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_proximos_pagos:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_proximos_pagos));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_actividades:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_actividades));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
            case R.id.menu_item_comisiones:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_comisiones));
                this.openFragment(Constants.ITEM_FRAGMENT.get(id));
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Abre el fragmento mediante el tag seleccionado
     **/
    private void openFragment(String tag) {
        try {
            FragmentTransaction mainFragment = getSupportFragmentManager().beginTransaction();
            mainFragment.replace(R.id.fragment_main_container, Constants.TAG_FRAGMENT.get(tag), tag);
            mainFragment.addToBackStack(tag);
            mainFragment.commit();
        } catch (Exception e) {
            Toast.makeText(this, "Debe implementrar + " + tag, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Cierra todos los fragmentos actuales
     **/
    private void closeAllFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_main_container);
        if (null != fragment)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    @Override
    public void showQuestion(String titulo, String mensage) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setTitle(titulo);
        ad.setMessage(mensage);
        ad.setCancelable(false);
        ad.setNegativeButton("Cancelar", this);
        ad.setPositiveButton("Aceptar", this);
        ad.show().getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getColor(R.color.bootstrap_brand_danger));
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int operation = 0;

        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                switch (_decodeItem.getIdView()) {
                    case R.id.item_btn_eliminar_promotor:
                        operation = Constants.WS_KEY_ELIMINAR_PROMOTORES;
                        break;
                    case R.id.item_btn_eliminar_cliente:
                        operation = Constants.WS_KEY_ELIMINAR_CLIENTES;
                        break;
                    case R.id.item_btn_eliminar_grupo:
                        operation = Constants.WS_KEY_ELIMINAR_GRUPOS;
                        break;
                    case R.id.item_btn_autorizan_grupo:
                        operation = Constants.WS_KEY_AUTORIZAR_GRUPOS;
                        break;
                    case R.id.item_btn_finalizar_actividad:
                        operation = Constants.WS_KEY_FINALIZAR_ACTIVIDADES;
                        break;
                    case R.id.item_btn_eliminar_actividad:
                        operation = Constants.WS_KEY_ELIMINAR_ACTIVIDADES;
                        break;
                }

                this.webServiceOperations(operation);
        }

    }

    private void webServiceOperations(int operation) {
        pDialog = new ProgressDialog(NavigationDrawerActivity.this);
        pDialog.setMessage(getString(R.string.default_loading_msg));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        switch (operation) {
            case Constants.WS_KEY_ELIMINAR_PROMOTORES:
                this.webServiceDeletePromotor();
                break;
            case Constants.WS_KEY_ELIMINAR_CLIENTES:
                this.webServiceDeleteCliente();
                break;
            case Constants.WS_KEY_ELIMINAR_GRUPOS:
                this.webServiceDeleteGrupos();
                break;
            case Constants.WS_KEY_AUTORIZAR_GRUPOS:
                this.webServiceAutorizarGrupo();
                break;
            case Constants.WS_KEY_ELIMINAR_ASIGNACIONES_GRUPOS:
                this.webServiceAutorizarGrupo();
                break;
            case Constants.WS_KEY_AUTORIZAR_ASIGNACIONES_GRUPOS:
                this.webServiceAutorizarGrupo();
                break;
            case Constants.WS_KEY_ELIMINAR_ACTIVIDADES:
                this.webServiceDeleteActividad();
                break;
            case Constants.WS_KEY_FINALIZAR_ACTIVIDADES:
                this.webServiceFinalizarActividad();
                break;
        }
    }

    private void webServiceDeletePromotor() {
        Promotores promotor = (Promotores) _decodeItem.getItemModel();
        promotoresRest.eliminarPromotor(promotor).enqueue(new Callback<Promotores>() {
            @Override
            public void onResponse(Call<Promotores> call, Response<Promotores> response) {

                if (response.isSuccessful()) {
                    pDialog.dismiss();

                    Promotores promotor = response.body();

                    if (null != promotor.getId()) {
                        PromotoresFragment.listadoPromotores();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Promotores> call, Throwable t) {

            }
        });
    }


    private void webServiceDeleteCliente() {
        Clientes cliente = (Clientes) _decodeItem.getItemModel();
        clientesRest.eliminarCliente(cliente).enqueue(new Callback<Clientes>() {
            @Override
            public void onResponse(Call<Clientes> call, Response<Clientes> response) {

                if (response.isSuccessful()) {
                    pDialog.dismiss();

                    Clientes cliente = response.body();

                    if (null != cliente.getId()) {
                        ClientesFragment.listadoClientes();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Clientes> call, Throwable t) {

            }
        });
    }

    private void webServiceDeleteActividad() {
        Actividades actividad = (Actividades) _decodeItem.getItemModel();
        actividadesRest.eliminarActividad(actividad).enqueue(new Callback<Actividades>() {
            @Override
            public void onResponse(Call<Actividades> call, Response<Actividades> response) {

                if (response.isSuccessful()) {
                    pDialog.dismiss();

                    Actividades data = response.body();

                    if (null != data.getId()) {
                        ActividadesFragment.listadoActividades();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Actividades> call, Throwable t) {

            }
        });
    }

    private void webServiceDeleteGrupos() {
        Grupos grupo = (Grupos) _decodeItem.getItemModel();
        gruposRest.eliminarGrupo(grupo).enqueue(new Callback<Grupos>() {
            @Override
            public void onResponse(Call<Grupos> call, Response<Grupos> response) {

                if (response.isSuccessful()) {
                    pDialog.dismiss();

                    Grupos grupo = response.body();

                    if (null != grupo.getId()) {
                        GruposFragment.listadoGrupos();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Grupos> call, Throwable t) {

            }
        });
    }

    private void webServiceAutorizarGrupo() {
        Grupos grupo = (Grupos) _decodeItem.getItemModel();
        grupo.setIdEstatus(Constants.DIAZFU_WEB_AUTORIZADO);
        gruposRest.editarGrupo(grupo).enqueue(new Callback<Grupos>() {
            @Override
            public void onResponse(Call<Grupos> call, Response<Grupos> response) {

                if (response.isSuccessful()) {
                    pDialog.dismiss();

                    Grupos grupo = response.body();

                    if (null != grupo.getId()) {
                        GruposFragment.listadoGrupos();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Grupos> call, Throwable t) {

            }
        });

    }

    private void webServiceFinalizarActividad() {
        Actividades actividad = (Actividades) _decodeItem.getItemModel();
        actividad.setIdEstatus(Constants.DIAZFU_WEB_FINALIZADO);
        actividadesRest.editarActividad(actividad).enqueue(new Callback<Actividades>() {
            @Override
            public void onResponse(Call<Actividades> call, Response<Actividades> response) {

                if (response.isSuccessful()) {
                    pDialog.dismiss();

                    Actividades data = response.body();

                    if (null != data.getId()) {
                        ActividadesFragment.listadoActividades();
                    }

                    Log.i(TAG, "post submitted to API." + response.body().toString());
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "CODIGO: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Actividades> call, Throwable t) {

            }
        });
    }

    @Override
    public void setDecodeItem(DecodeItemHelper decodeItem) {
        _decodeItem = decodeItem;
    }

    @Override
    public void openExternalActivity(int action, Class<?> externalActivity) {
        DecodeExtraHelper extraParams = new DecodeExtraHelper();

        extraParams.setTituloActividad(getString(Constants.TITLE_ACTIVITY.get(_decodeItem.getIdView())));
        extraParams.setTituloFormulario(getString(Constants.TITLE_FORM_ACTION.get(action)));
        extraParams.setAccionFragmento(action);
        extraParams.setFragmentTag(Constants.ITEM_FRAGMENT.get(_decodeItem.getIdView()));
        extraParams.setDecodeItem(_decodeItem);

        Intent intent = new Intent(this, externalActivity);
        intent.putExtra(Constants.KEY_MAIN_DECODE, extraParams);
        //intent.putExtra(Constants.KEY_SESSION_USER, _SESSION_USER);
        startActivity(intent);
    }

    @Override
    public void showProgressDialog() {
        if (null == pDialog) {
            pDialog = new ProgressDialog(NavigationDrawerActivity.this);
            pDialog.setMessage(getString(R.string.default_loading_msg));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
    }

    @Override
    public void stopProgressDialog() {
        if (null != pDialog) pDialog.dismiss();
    }
}
