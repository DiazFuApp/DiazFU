package com.skillcoders.diazfu.fragments.interfaces;

import com.skillcoders.diazfu.helpers.DecodeItemHelper;

/**
 * Created by jvier on 03/10/2017.
 */

public interface DocumentosInterface {

    void openExternalActivity(int action, Class<?> externalActivity);

    void showQuestion(String titulo, String mensage);

    void setDecodeItem(DecodeItemHelper decodeItem);
}
