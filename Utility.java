package com.example.keval.roomonrent;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by keval on 02-04-2017.
 */

public class Utility {

    private Activity activity;
    private ArrayList<String> permissions = new ArrayList<>();

    public static int REQUEST_CODE = 45;
    public static int TYPE_WRONG = R.drawable.wrong;

    public void addPermission(String permission) {
        if (!permissions.contains(permission)) {
            permissions.add(permission);
        }

    }

    public void clearPermissionList() {
        permissions.clear();
    }


    public Utility(Activity activity, ArrayList<String> permissions) {
        this.activity = activity;
        this.permissions = permissions;
    }

    public Utility(Activity activity) {

        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public boolean isNetworkConnected() {
        clearPermissionList();

        if (!checkPermission(Manifest.permission.ACCESS_NETWORK_STATE)) {
            addPermission(Manifest.permission.ACCESS_NETWORK_STATE);
            askPermission();
            Toast.makeText(getActivity(), "sdfg", Toast.LENGTH_LONG).show();
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    public boolean checkPermission(String permission) {
        if (ActivityCompat.checkSelfPermission(getActivity(), permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermission(Boolean doRequest) {

        Iterator<String> permits = permissions.iterator();
        while (permits.hasNext()) {
            if (checkPermission(permits.next())) {
                permits.remove();
            }
        }
        if (doRequest) {
            askPermission();
        }
        return permissions.isEmpty();
    }

    public void askPermission() {
        String[] permissionArray = (String[]) permissions.toArray(new String[permissions.size()]);
        ActivityCompat.requestPermissions(getActivity(), permissionArray, REQUEST_CODE);
    }

    public void error(String message) {
        message(TYPE_WRONG, "Error", message);
    }

    public void message(int type, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage(message);
        alertDialog.setIcon(type);
        alertDialog.setTitle(title);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        alertDialog.show();
    }

    public void onResultTrue() {
        permissions.clear();
    }

    public void onResultTrue(int i) {
        permissions.remove(i);
    }
}
