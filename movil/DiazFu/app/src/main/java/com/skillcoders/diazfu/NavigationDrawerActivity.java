package com.skillcoders.diazfu;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.skillcoders.diazfu.fragments.interfaces.NavigationDrawerInterface;
import com.skillcoders.diazfu.helpers.DecodeExtraHelper;
import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.utils.Constants;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigationDrawerInterface, DialogInterface.OnClickListener {

    private static DecodeItemHelper _decodeItem;
    private ProgressDialog pDialog;

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        //TODO REGRESAR A INICIO CUANDO EXISTA OTRO FRAGMENTO
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_main_container);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
            case R.id.menu_item_registro_pagos:
                getSupportActionBar().setTitle(getString(R.string.default_item_menu_title_registro_pagos));
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
    public void showQuestion() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setTitle("Eliminar");
        ad.setMessage("¿Esta seguro que desea elminar?");
        ad.setCancelable(false);
        ad.setNegativeButton("Cancelar", this);
        ad.setPositiveButton("Aceptar", this);
        ad.show().getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getColor(R.color.bootstrap_brand_danger));

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

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


}
