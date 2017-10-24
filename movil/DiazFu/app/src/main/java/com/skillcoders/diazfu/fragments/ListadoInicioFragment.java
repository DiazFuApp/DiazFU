package com.skillcoders.diazfu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.skillcoders.diazfu.R;

/**
 * Created by jvier on 04/09/2017.
 */

public class ListadoInicioFragment extends Fragment implements View.OnClickListener {

    private Button btnPlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_inicio, container, false);
        btnPlay = (Button) view.findViewById(R.id.play_test);
        btnPlay.setOnClickListener(this);

        btnPlay.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onStart() {

        getActivity().setTitle(getString(R.string.default_item_menu_title_inicio));

        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_test:

                break;
        }

    }

    /*
    private void playVideo() {
        try {
            String videopath = "https://sec.ch9.ms/ch9/6444/cf94e114-a959-4317-a22b-c00024d06444/AzureMediaServicesPlatformPromo_mid.mp4";
            progressDialog = ProgressDialog.show(getActivity(), "",
                    "Buffering video...", false);
            progressDialog.setCancelable(true);
            getActivity().getWindow().setFormat(PixelFormat.OPAQUE);

            mediaController = new MediaController(getContext());

            Uri video = Uri.parse(videopath);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    progressDialog.dismiss();
                    videoView.start();
                }
            });

        } catch (Exception e) {
            progressDialog.dismiss();
            System.out.println("Video Play Error :" + e.getMessage());
        }
    }
    */
}
