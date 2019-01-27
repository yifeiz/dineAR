package com.example.test;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {




    // This is checking whether or not device is compatible with ARCore
//    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
//            Log.e(TAG, "Sceneform requires Android N or later");
//            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
//            activity.finish();
//            return false;
//        }
//        String openGlVersionString =
//                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
//                        .getDeviceConfigurationInfo()
//                        .getGlEsVersion();
//        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
//            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
//            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
//                    .show();
//            activity.finish();
//            return false;
//        }
//        return true;
//    }

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;


    ArFragment arFragment;
    ModelRenderable hamburgerRenderable;

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!checkIsSupportedDeviceOrFinish(this)) {
//            return;
//        }
        setContentView(R.layout.activity_main);
        // we are finding a plane for our models to rest on by moving phone around (to detect a plane)
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);

        ModelRenderable.builder()
                .setSource(this, Uri.parse("yukari.sfb"))
                .build()
                .thenAccept(renderable -> hamburgerRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast toast =
                            Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return null;
                });


        arFragment.setOnTapArPlaneListener(
                (HitResult hitresult, Plane plane, MotionEvent motionevent) -> {
                    if (hamburgerRenderable == null){
                        return;
                    }

                    Anchor anchor = hitresult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    TransformableNode burger = new TransformableNode(arFragment.getTransformationSystem());
                    burger.setParent(anchorNode);
                    burger.setRenderable(hamburgerRenderable);
                    burger.select();
                }
        );
    }

}
