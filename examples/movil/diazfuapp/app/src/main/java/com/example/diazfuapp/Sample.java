package com.example.diazfuapp;

/**
 * Created by Fred Gz on 19/07/2017.
 */

public class Sample {
    /**
     * Item text
     */
    @com.google.gson.annotations.SerializedName("nombre")
    private String mNombre;

    /**
     * Item Id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    /**
     * Indicates if the item is completed
     */
    @com.google.gson.annotations.SerializedName("estatus")
    private int mEstatus;

    /**
     * ToDoItem constructor
     */
    public Sample() {

    }

    @Override
    public String toString() {
        return getNombre();
    }

    /**
     * Initializes a new ToDoItem
     *
     * @param nombre
     *            The item text
     * @param id
     *            The item id
     * @param Estatus
     *
     */
    public Sample(String nombre, String id, int Estatus) {
        this.setNombre(nombre);
        this.setId(id);
        this.setEstatus(Estatus);
    }

    /**
     * Returns the item nombre
     */
    public String getNombre() {
        return mNombre;
    }

    /**
     * Sets the item nombre
     *
     * @param nombre
     *            nombre to set
     */
    public final void setNombre(String nombre) {
        mNombre = nombre;
    }

    /**
     * Returns the item id
     */
    public String getId() {
        return mId;
    }

    /**
     * Sets the item id
     *
     * @param id
     *            id to set
     */
    public final void setId(String id) {
        mId = id;
    }

    /**
     * Indicates if the item is marked as completed
     */
    public int getEstatus() {
        return mEstatus;
    }

    /**
     * Marks the item as completed or incompleted
     */
    public final void setEstatus(int Estatus) {
        mEstatus = Estatus;
    }
}
