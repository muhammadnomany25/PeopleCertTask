package com.alphaapps.peoplecerttask.presentation.live_frames;

import static com.alphaapps.peoplecerttask.constants.Constants.CAPTURE_INTERVAL;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.databinding.ObservableField;

import com.alphaapps.peoplecerttask.R;
import com.alphaapps.peoplecerttask.constants.Constants;
import com.alphaapps.peoplecerttask.databinding.LayoutLiveCameraFramesBinding;
import com.alphaapps.peoplecerttask.presentation.base.BaseVmActivity;
import com.alphaapps.peoplecerttask.presentation.base.Status;
import com.alphaapps.peoplecerttask.utils.ImageUtils;

import java.nio.ByteBuffer;

/**
 * Wrap and handle the live camera feed
 * <p>
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
public class LiveCameraFramesActivity extends BaseVmActivity<LayoutLiveCameraFramesBinding, LiveFramesVM> implements ImageReader.OnImageAvailableListener {

    protected final ObservableField<Bitmap> capturedFrameBitmap = new ObservableField<>();
    protected int previewHeight = 0, previewWidth = 0;
    protected int sensorOrientation;
    private boolean isProcessingFrame = false;
    private final byte[][] yuvBytes = new byte[3][];
    private int[] rgbBytes = null;
    private int yRowStride;
    private Runnable postInferenceCallback;
    private Runnable imageConverter;
    private Handler sendCapturedHandler;
    private int sentFrames = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSendFrameHandler();
        handlePermissions();
        getBinding().setSentFrames(sentFrames);
        observeData();
    }

    /**
     * Observe data change
     */
    private void observeData() {
        getViewModel().getFramesUiModel().getStatus().observe(this, status -> {
            if (status != null && status == Status.SUCCESS.getValue()) {
                getBinding().progressBar.setVisibility(View.GONE);
                finish();
            } else if (status != null && status == Status.LOADING.getValue()) {
                getBinding().progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Initialization of the frame handler
     */
    private void initSendFrameHandler() {
        sendCapturedHandler = new Handler(getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Bitmap frameToSend = capturedFrameBitmap.get(); // ready to be sent to any server
                if (frameToSend != null) {
                    ++sentFrames;
                }
                getBinding().setSentFrames(sentFrames);
                sendCapturedHandler.postDelayed(this, CAPTURE_INTERVAL);
            }
        };
        sendCapturedHandler.postDelayed(runnable, CAPTURE_INTERVAL);
    }

    /**
     * Check if needed are granted so show the camera or not so ask for the permissions
     */
    private void handlePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA}, 121);
        } else {
            showCameraFragment();
        }
    }


    /**
     * Show the live camera fragment
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void showCameraFragment() {
        final CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraId = null;
        try {
            cameraId = manager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        CameraConnectionFragment camera2Fragment = getCameraInstance();
        camera2Fragment.setCamera(cameraId);
        getFragmentManager().beginTransaction().replace(R.id.container, camera2Fragment).commit();
    }

    /**
     * Build the live camera fragment instance
     */
    CameraConnectionFragment getCameraInstance() {
        return CameraConnectionFragment.newInstance(
                (size, rotation) -> {
                    previewHeight = size.getHeight();
                    previewWidth = size.getWidth();
                    Log.d("tryOrientation", "rotation: " + rotation + "   orientation: " + getScreenOrientation() + "  " + previewWidth + "   " + previewHeight);
                    sensorOrientation = rotation - getScreenOrientation();
                },
                this,
                R.layout.camera_fragment,
                new Size(640, 480));
    }

    /**
     * generate a bitmap from an ImageReader Object
     */
    protected Bitmap getBitmap(ImageReader mImageReader) {
        if (previewWidth == 0 || previewHeight == 0) {
            return null;
        }
        if (rgbBytes == null) {
            rgbBytes = new int[previewWidth * previewHeight];
        }
        try {
            final Image image = mImageReader.acquireLatestImage();

            if (image == null) {
                return null;
            }

            if (isProcessingFrame) {
                image.close();
                return null;
            }
            isProcessingFrame = true;
            final Image.Plane[] planes = image.getPlanes();
            fillBytes(planes, yuvBytes);
            yRowStride = planes[0].getRowStride();
            final int uvRowStride = planes[1].getRowStride();
            final int uvPixelStride = planes[1].getPixelStride();

            imageConverter =
                    new Runnable() {
                        @Override
                        public void run() {
                            ImageUtils.convertYUV420ToARGB8888(
                                    yuvBytes[0],
                                    yuvBytes[1],
                                    yuvBytes[2],
                                    previewWidth,
                                    previewHeight,
                                    yRowStride,
                                    uvRowStride,
                                    uvPixelStride,
                                    rgbBytes);
                        }
                    };

            postInferenceCallback =
                    new Runnable() {
                        @Override
                        public void run() {
                            image.close();
                            isProcessingFrame = false;
                        }
                    };

            return processImage();

        } catch (final Exception e) {
            Log.d("tryError", e.getMessage());
            return null;
        }
    }

    /**
     * Obtaining of current device screen orientation
     */
    protected int getScreenOrientation() {
        switch (getWindowManager().getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_270:
                return Constants.ScreenOrientation.ORIENTATION_270_DEGREE.getValue();
            case Surface.ROTATION_180:
                return Constants.ScreenOrientation.ORIENTATION_180_DEGREE.getValue();
            case Surface.ROTATION_90:
                return Constants.ScreenOrientation.ORIENTATION_90_DEGREE.getValue();
            default:
                return Constants.ScreenOrientation.ORIENTATION_ZERO_DEGREE.getValue();
        }
    }


    /**
     * Getting the live camera frames feed
     *
     * @param reader => the captured feed
     */
    @Override
    public void onImageAvailable(ImageReader reader) {
        capturedFrameBitmap.set(getBitmap(reader));
    }

    /**
     * @return Bitmap represents the captured frame
     */
    private Bitmap processImage() {
        imageConverter.run();
        Bitmap bitmap = Bitmap.createBitmap(previewWidth, previewHeight, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(rgbBytes, 0, previewWidth, 0, 0, previewWidth, previewHeight);
        postInferenceCallback.run();
        return bitmap;
    }


    protected void fillBytes(final Image.Plane[] planes, final byte[][] yuvBytes) {
        for (int i = 0; i < planes.length; ++i) {
            final ByteBuffer buffer = planes[i].getBuffer();
            if (yuvBytes[i] == null) {
                yuvBytes[i] = new byte[buffer.capacity()];
            }
            buffer.get(yuvBytes[i]);
        }
    }

    /**
     * Check the requested permissions result
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showCameraFragment();
        } else {
            Toast.makeText(this, "You must accept permissions first", Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }


    /**
     * Save the sent frames session to the database
     */
    @Override
    public void onBackPressed() {
        if (getViewModel() != null && sentFrames > 0) {
            getViewModel().insertFrameSession(sentFrames);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_live_camera_frames;
    }

    @NonNull
    @Override
    protected Class<?> getVmClass() {
        return LiveFramesVM.class;
    }
}