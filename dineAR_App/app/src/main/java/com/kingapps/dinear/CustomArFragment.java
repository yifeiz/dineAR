package com.kingapps.dinear;

import android.util.Log;

import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ux.ArFragment;


public class CustomArFragment extends ArFragment {
    @Override
    protected Config getSessionConfiguration(Session session) {
        getPlaneDiscoveryController().setInstructionView(null);
        //instead of waving phone around to find plane, now we want to find plane based off of an image
        Config config = new Config(session);
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        session.configure(config);
        getArSceneView().setupSession(session);
        if ((((AugmentReality) getActivity()).setupAugmentedImagesDb(config, session))) {
            Log.d("SetupAugImgDb", "Success");
        } else {
            Log.e("SetupAugImgDb","Faliure setting up db");
        }
        return config;
    }


}