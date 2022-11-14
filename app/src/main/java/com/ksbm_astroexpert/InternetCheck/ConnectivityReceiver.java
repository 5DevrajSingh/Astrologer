package com.ksbm_astroexpert.InternetCheck;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ksbm_astroexpert.App;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.home.HomeActivity;

import static com.ksbm_astroexpert.ui.home.HomeActivity.dialog;

public class ConnectivityReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent)
    {
        try
        {
            if (isOnline(context)) {
//                dialog(true,context);
                Log.e("Astro Experts", "Online Connect Intenet ");
                showDialogForInternetConnectivity(context,true);
            } else {

//                dialog(false,context);

                showDialogForInternetConnectivity(context,false);

//                AlertDialog.Builder alert = new AlertDialog.Builder(context);
//                alert.setCancelable(false);
//                alert.setTitle("Astro Experts alert");
//                alert.setMessage("You are offline. No data connection found. Please check internet connection and try again.");
//                alert.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface abc, int which) {
//                        if(isOnline(context))
//                        {
//                            Intent intent1 = new Intent(context, HomeActivity.class);
//                            context.startActivity(intent1);
//                            ((Activity)context).finish();
//                            abc.cancel();
//                        }
//                        else
//                        {
//                            Toast.makeText(context, "No data connection found", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
//
//                AlertDialog dialog = alert.create();
//                dialog.show();



                Log.e("Astro Experts", "Conectivity Failure !!! ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    public static androidx.appcompat.app.AlertDialog showDialogForInternetConnectivity(Context context,boolean internetPopupDismiss) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_internet_connectivity_check, null, false);
        TextView ivBtnClose = view.findViewById(R.id.try_again);
        builder.setView(view);


        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationUptoDown;


        ivBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline1(context))
                {
                    Intent intent1 = new Intent(context, HomeActivity.class);
                    context.startActivity(intent1);
                    ((Activity)context).finish();
                    alertDialog.dismiss();
                }
                else
                {
                    Toast.makeText(context, "No data connection found", Toast.LENGTH_SHORT).show();
                }
            }
        });


        if(internetPopupDismiss)
        {
            alertDialog.dismiss();
        }
        else
        {
            alertDialog.show();
        }




        return alertDialog;
    }




    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }



    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static boolean isOnline1(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

}
