package com.skillcoders.diazfu.helpers;

import com.skillcoders.diazfu.data.model.Grupos;
import com.skillcoders.diazfu.data.model.IntegrantesGrupos;

import java.util.List;

/**
 * Created by jvier on 16/10/2017.
 */

public class GruposHelper {

    private Grupos grupo;
    private List<IntegrantesGrupos> integrantesGrupos;

    public GruposHelper() {
    }

    public Grupos getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupos grupo) {
        this.grupo = grupo;
    }

    public List<IntegrantesGrupos> getIntegrantesGrupos() {
        return integrantesGrupos;
    }

    public void setIntegrantesGrupos(List<IntegrantesGrupos> integrantesGrupos) {
        this.integrantesGrupos = integrantesGrupos;
    }
}
