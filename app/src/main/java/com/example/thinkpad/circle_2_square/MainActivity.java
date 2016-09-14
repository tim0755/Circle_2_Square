package com.example.thinkpad.circle_2_square;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "calcArray";
    private static final boolean DEBUG = true;    // TODO set false on release

    private final int W = 960;
    private final int H = 960;
    private final int radius = 480;
    private final int centerX = 480;
    private final int centerY = 480;

    private CPoint[][] tableArray;

    private byte[] mUvcCameraData0_;

    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableArray = new CPoint[H][W];
        for (int y = 0; y < H; y++) {
            tableArray[y] = new CPoint[W];
            for (int x = 0; x < W; x++) {
                tableArray[y][x] = new CPoint();
            }
        }

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if ((int) Math.pow(Math.pow(x - centerX, 2.0) + Math.pow(y - centerY, 2.0), 1 / 2.0) > radius)
                    continue;
                if (x == W / 2) {
                    tableArray[y][x].u = x;
                    tableArray[y][x].v = y;
                } else if (Math.abs(y - H / 2) <= Math.abs(x - W / 2)) {
                    tableArray[y][x].u = Math.min((int) (Math.pow(Math.pow(x - W / 2.0, 2.0) + Math.pow(y - H / 2.0, 2.0), 1.0 / 2.0) * Integer.signum(x - W / 2) + W / 2), W-1);
                    tableArray[y][x].v = Math.min((int) (Math.abs(y - H / 2.0) / Math.abs(x - W / 2.0) * Math.pow(Math.pow(x - W / 2.0, 2.0) + Math.pow(y - H / 2.0, 2.0), 1.0 / 2.0) * Integer.signum(y - H / 2) + H / 2), H-1);
                } else {
                    tableArray[y][x].u = Math.min((int) (Math.abs(x - W / 2.0) / Math.abs(y - H / 2.0) * Math.pow(Math.pow(x - W / 2.0, 2.0) + Math.pow(y - H / 2.0, 2.0), 1.0 / 2.0) * Integer.signum(x - W / 2) + W / 2), W-1);
                    tableArray[y][x].v = Math.min((int) (Math.pow(Math.pow(x - W / 2.0, 2.0) + Math.pow(y - H / 2.0, 2.0), 1.0 / 2.0) * Integer.signum(y - H / 2) + H / 2), H-1);
                }
            }
        }

        /*for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if ((int) Math.pow(Math.pow(x - centerX, 2.0) + Math.pow(y - centerY, 2.0), 1.0 / 2.0) > radius)
                    continue;
                Log.d(TAG, "" + x + "*" + y + ":{" + tableArray[y][x].u + "," + tableArray[y][x].v + "}");
            }
        }*/

    }

    /*private void circle2Square(byte[] src) {
        int offset = 0;
        int yuvOffset = W * H;
        if (DEBUG)Log.v(TAG, "circle2Square start!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if ((int) Math.pow(Math.pow(x - centerX, 2.0) + Math.pow(y - centerY, 2.0), 1 / 2.0) > radius)
                    continue;
                mUvcCameraData0_[tableArray[y][x].v * W + tableArray[y][x].u] = src[offset + x];
                if (y % 2 == 0 && x % 2 == 0) {
                    if((tableArray[y][x].v / 2) * W + tableArray[y][x].u / 2 >= yuvOffset/2)
                        Log.v(TAG, "x*y:"+x+"*"+y+" u*v:"+tableArray[y][x].u+"*"+tableArray[y][x].v+" circle2Square "+((tableArray[y][x].v / 2) * W + tableArray[y][x].u / 2)+" "+(yuvOffset/2));
                    else {
                        mUvcCameraData0_[yuvOffset + W * (tableArray[y][x].v / 2) + tableArray[y][x].u / 2]     = src[yuvOffset + W * (y / 2) + x / 2];
                        mUvcCameraData0_[yuvOffset + W * (tableArray[y][x].v / 2) + tableArray[y][x].u / 2 + 1] = src[yuvOffset + W * (y / 2) + x / 2 + 1];
                    }
                }
            }
            offset += W;
        }

        {
            if (DEBUG)Log.v(TAG, "circle2Square done!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!0");
            Mat frameChain = new Mat(mPreviewHeight + (mPreviewHeight / 2), mPreviewWidth, CvType.CV_8UC1);
            frameChain.put(0, 0, mUvcCameraData0_);
            Mat srcMat = new Mat();
            Imgproc.cvtColor(frameChain, srcMat, Imgproc.COLOR_YUV2BGRA_NV21, 4);
            Imgcodecs.imwrite(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/uvc_camera_2_square_snapshot_one_rgba.jpg", srcMat);
        }
    }*/

    private class CPoint {
        public int u;
        public int v;

        public CPoint() {
        }

    }
}
