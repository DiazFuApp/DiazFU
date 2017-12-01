package com.skillcoders.diazfu.fragments.interfaces;

import com.skillcoders.diazfu.helpers.DecodeItemHelper;
import com.skillcoders.diazfu.helpers.DocumentosHelper;

/**
 * Created by jvier on 03/10/2017.
 */

public interface DocumentosInterface {

    void openExternalActivity(int action, Class<?> externalActivity);

    void setDecodeItem(DecodeItemHelper decodeItem);

    void registrarDocumento(DocumentosHelper helper);
}
