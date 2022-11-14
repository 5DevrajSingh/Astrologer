package com.ksbm_astroexpert.notification;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ksbm_astroexpert.Astrologer.Astrologer;
import com.ksbm_astroexpert.Astrologer.CallRequest.Call_request;
import com.ksbm_astroexpert.R;
import com.ksbm_astroexpert.ui.home.HomeActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    // private Handler handler;
    boolean isCalling = false;
    private Context context;
    private MediaPlayer mp;

    public static boolean CallRejected=false;


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("sssss",s);
    }
  /*  @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        sendNotification(remoteMessage.getNotification().getBody());
        Log.d("Frommesage", remoteMessage.getFrom());
        Log.d(TAG, "Notification_Message_Body" + remoteMessage.getNotification().getBody());
    }*/


    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        context = getApplicationContext();
        Log.d(TAG, "Bodyasaprovider :>>>" + remoteMessage.getData().toString());
        boolean isAppOpen = false;
        if (isAppIsInBackground(getApplicationContext())) {
            isAppOpen = false;
        } else {
            isAppOpen = true;
        }
        try {
            if (isAppOpen) {
                Log.d("isopen2",remoteMessage.getData().toString());
                JSONObject jsonObject = new JSONObject(remoteMessage.getData());
                if (jsonObject.optString("type").equals("Request"))
                {

                    if(jsonObject.optString("title").equals("Chat Status"))
                    {
//                        Intent intent = new Intent(getBaseContext(), Astrologer.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("title", "" + "Received Accept");
////
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
//                                | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                        intent.putExtras(bundle);
//                        getApplicationContext().startActivity(intent);


                        if(jsonObject.optString("status").equals("REJECTED"))
                        {
                            CallRejected=true;
                        }
                        else
                        {
                            Intent intent = new Intent(getBaseContext(), Call_request.class);
                            Bundle bundle = new Bundle();
                            Log.d(TAG, "Inner_Body : >>>>" + jsonObject.toString());
                            bundle.putString("title", "" + jsonObject.optString("title"));
                            bundle.putString("booking_id",jsonObject.optString("booking_id"));
                            bundle.putString("filename", "" + jsonObject.optString("image"));
                            bundle.putString("type", "" + jsonObject.optString("type"));
                            bundle.putString("image", "" + jsonObject.optString("image"));
                            bundle.putString("name", "" + jsonObject.optString("username"));
                            bundle.putString("message", "" + jsonObject.optString("message"));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtras(bundle);
                            getApplicationContext().startActivity(intent);

                        }


                    }
                    else
                    {
//                        Intent intent = new Intent(getBaseContext(), NotificationDialog.class);
//                        Bundle bundle = new Bundle();
//                        Log.d(TAG, "Inner_Body : >>>>" + jsonObject.toString());
//                        bundle.putString("title", "" + jsonObject.optString("title"));
//                        bundle.putString("booking_id",jsonObject.optString("booking_id"));
//                        bundle.putString("filename", "" + jsonObject.optString("image"));
//                        bundle.putString("type", "" + jsonObject.optString("type"));
//
//                        bundle.putString("message", "" + jsonObject.optString("message"));
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
//                                | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                        intent.putExtras(bundle);
//                        getApplicationContext().startActivity(intent);
                    }


                }
                else if (jsonObject.optString("type").equals("Call"))
                {
//                    Intent intent = new Intent(getBaseContext(), NotificationCallsDialog.class);
//                    Bundle bundle = new Bundle();
//                    Log.d(TAG, "Inner_Body : >>>>" + jsonObject.toString());
//                    bundle.putString("title", "" + jsonObject.optString("title"));
//                    bundle.putString("booking_id",jsonObject.optString("booking_id"));
//                    bundle.putString("filename", "" + jsonObject.optString("image"));
//                    bundle.putString("type", "" + jsonObject.optString("type"));
//                    bundle.putString("message", "" + jsonObject.optString("message"));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                            | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
//                            | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                    intent.putExtras(bundle);
//                    getApplicationContext().startActivity(intent);
                }
                else
                {
                   // sendNotification(remoteMessage.getData(), new Intent());
                }


            }
            else
            {
                sendNotification(remoteMessage.getData(), new Intent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (appSession.isNotification()) {
//
//        }
    }

    private void sendNotification(Map<String, String> data2, Intent intent) {
        Log.i(TAG, "Notification >>>" + data2.toString());
        JSONObject jsonObject = null;

        jsonObject = new JSONObject(data2);



        //  int count = appSession.getBadgeCount();
        // count = count + 1;
        // appSession.setBadgeCount(count);
        try {
            //   ShortcutBadger.applyCount(getApplicationContext(), count);'

            Bundle bundle = new Bundle();


            bundle.putString("title", "" + jsonObject.optString("title"));
            bundle.putString("booking_id",jsonObject.optString("booking_id"));
            bundle.putString("filename", "" + jsonObject.optString("image"));
            bundle.putString("type", "" + jsonObject.optString("type"));

            bundle.putString("image", "" + jsonObject.optString("image"));
            bundle.putString("name", "" + jsonObject.optString("username"));

            bundle.putString("message", "" + jsonObject.optString("message"));

//            bundle.putString("type", "" + data.get("type"));
//            bundle.putString("user_type", "" + data.get("user_type"));
//            bundle.putString("new", "" + "new");

//            bundle.putString("type", "" + jsonObject.optJSONObject("payload").optString("type"));
//
//            if (jsonObject.optJSONObject("payload").optString("type").equalsIgnoreCase("accept")) {
//                bundle.putString("to_user_id", "" + jsonObject.optJSONObject("payload").optString("user_id"));
//                bundle.putString("userfullname", "" + jsonObject.optJSONObject("payload").optString("userfullname"));
//                bundle.putString("profile_image", "" + jsonObject.optJSONObject("payload").optString("profile_image"));
//            }

            mp = MediaPlayer.create(getApplicationContext(), R.raw.new_ringtone);
            mp.setLooping(false);
           // mp.start();


            if(jsonObject.optString("title").toString().equals("Chat Status"))
            {
                SharedPreferences sharedPreferences = getSharedPreferences("GetRequest", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("getrequest",true);
                editor.commit();
                editor.apply();
                intent = new Intent(getApplicationContext(), Call_request.class);
            }
            else
            {
                intent = new Intent(getApplicationContext(), HomeActivity.class);
            }


            intent.putExtras(bundle);

        } catch (Exception e) {
            e.printStackTrace();
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent,
                PendingIntent.FLAG_ONE_SHOT);

//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
//                0);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext());
//        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher1);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
            notificationBuilder.setColor(getResources().getColor(R.color.transparent_color));
            notificationBuilder.setLargeIcon(bitmap);

        } else {
            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher1);
        }


        try {
            notificationBuilder.setContentTitle(jsonObject.optString("title").replaceAll("%20",""));
            notificationBuilder.setContentText(" "+jsonObject.optString("message").replaceAll("%20"," "));

//            Log.i(getClass().getName(), "Name is >>>>>>>"+data.get("title"));
//            Log.i(getClass().getName(), "Message >>>>>>>"+data.get("message"));
//             notificationBuilder.setLargeIcon(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bitmap bitmap = null;
//        if (!data.get("image").equals("")) {
        try {
            if (jsonObject.getString("image") != null && !jsonObject.getString("image").equals(""))
                bitmap = getBitmapFromURL(jsonObject.getString("image"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        notificationBuilder.setAutoCancel(true);
        // notificationBuilder.setSound(defaultSoundUri);
//        if (appSession.isSound()) {
        //notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setLights(Color.WHITE, 1000, 5000);
//        } else {
//            notificationBuilder.setSound(null);
//        }

//        if (appSession.isVibration()) {
        long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
        notificationBuilder.setVibrate(pattern);
//        } else {
//            notificationBuilder.setVibrate(new long[]{0, 0});
//        }


        notificationBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationBuilder.setContentIntent(pendingIntent);

        try {
            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(jsonObject.getString("message").replaceAll("%20"," ")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (bitmap != null) {
            NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(bitmap);
            try {
                s.setSummaryText(jsonObject.getString("message").replaceAll("%20"," "));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            notificationBuilder.setStyle(s);
        }

        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        notificationBuilder.setChannelId(CHANNEL_ID);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setSound(defaultSoundUri, attributes);
            mChannel.setLightColor(Color.WHITE);

            notificationManager.createNotificationChannel(mChannel);
        }
        notificationManager.notify(11, notificationBuilder.build());
    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp != null && mp.isPlaying()) {
            mp.stop();
        }
    }



}

