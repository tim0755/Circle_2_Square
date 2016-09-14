package com.example.thinkpad.circle_2_square;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "calcArray";

    private final int W = 960;
    private final int H = 960;
    private final int radius = 480;
    private final int centerX = 480;
    private final int centerY = 480;

    private CPoint[][] tableArray;

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

    private class CPoint {
        public int u;
        public int v;

        public CPoint() {
        }

    }
}
