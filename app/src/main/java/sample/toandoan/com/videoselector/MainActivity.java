package sample.toandoan.com.videoselector;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String WRITE_EXTENAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final int REQUEST_PERMISSION = 1;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isPermissionGranted()) {
            getData();
        }
    }

    private boolean isPermissionGranted() {
        if (ContextCompat.checkSelfPermission(
                this, WRITE_EXTENAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, WRITE_EXTENAL_STORAGE)) {
                showDialogRequestPermission();
                return false;
            }
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{WRITE_EXTENAL_STORAGE},
                    REQUEST_PERMISSION);
            return false;
        }
        return true;
    }

    private void showDialogRequestPermission() {
        new AlertDialog.Builder(this)
                .setTitle("Request permission")
                .setMessage("We want acceess to external storage to get all video files")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                MainActivity.this,
                                new String[]{WRITE_EXTENAL_STORAGE},
                                REQUEST_PERMISSION);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != REQUEST_PERMISSION) {
            return;
        }
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getData();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void getData() {
        List<VideoModel> videoPath = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String sortOder = MediaStore.Video.Media.DATE_TAKEN + " DESC";
        Cursor cursor = getContentResolver().query(uri, null, null, null, sortOder, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                VideoModel videoModel = new VideoModel(cursor);
                Log.d(TAG, "getData: " + videoModel.toString());
                videoPath.add(videoModel);
            }
            cursor.close();
        }
    }
}
