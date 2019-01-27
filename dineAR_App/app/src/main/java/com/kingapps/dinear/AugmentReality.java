package com.kingapps.dinear;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
public class AugmentReality extends AppCompatActivity {

    ArFragment arFragment;
    boolean shouldAddModel = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_augment_reality);

        arFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
        arFragment.getPlaneDiscoveryController().hide();
        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdateFrame);

    }

    // places model over node (unless some bad things happen)
    private void placeObject(ArFragment arFragment, Anchor anchor, Uri uri) {
        ModelRenderable.builder()
                .setSource(arFragment.getContext(), uri)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene(arFragment, anchor, modelRenderable))
                .exceptionally(throwable -> {
                            Toast.makeText(arFragment.getContext(), "Error:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            return null;
                        }
                );
    }

    //checks whether or not reference image is in frame; if it is, moves on to show model
    private void onUpdateFrame(FrameTime frameTime) {
        Frame frame = arFragment.getArSceneView().getArFrame();
        Collection<AugmentedImage> augmentedImages = frame.getUpdatedTrackables(AugmentedImage.class);
        for (AugmentedImage augmentedImage : augmentedImages) {
            if (augmentedImage.getTrackingState() == TrackingState.TRACKING) {
                if (augmentedImage.getIndex() == 0 && shouldAddModel) {
                    placeObject(arFragment, augmentedImage.createAnchor(augmentedImage.getCenterPose()), Uri.parse("Hamburger.sfb"));
                    shouldAddModel = false;
                }
            }
        }
    }

    //sets up the database and sets it up before opening the app (just reads from premade one)
    public boolean setupAugmentedImagesDb(Config config, Session session) {

        try {
            InputStream inputStream = getAssets().open("imgdatabase.imgdb");
            AugmentedImageDatabase augmentedImageDatabase = AugmentedImageDatabase.deserialize(session, inputStream);
            config.setAugmentedImageDatabase(augmentedImageDatabase);
            session.configure(config);
            return true;
        } catch (IOException e){
            return false;
        }

    }

    // creates a node to set the model on
    private void addNodeToScene(ArFragment arFragment, Anchor anchor, Renderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
    }
}
