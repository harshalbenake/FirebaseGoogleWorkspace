package com.harshalbenake.fitnesss;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

public class ActivityRecognizedService extends IntentService {

    private String strConfidence="";

    public String getStrConfidence() {
        return strConfidence;
    }

    public void setStrConfidence(String strConfidence) {
        this.strConfidence = strConfidence;
    }
    public ActivityRecognizedService() {
        super("ActivityRecognizedService");
    }

    public ActivityRecognizedService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities( result.getProbableActivities() );
        }
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        //   mContext =   ((MainActivity) getApplicationContext());
        return super.onStartCommand(intent, flags, startId);
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        for( DetectedActivity activity : probableActivities ) {
            System.out.println("activity.getConfidence() : "+activity.getConfidence() );
            setStrConfidence("activity.getConfidence() : "+activity.getConfidence());
            String strType="";
            switch( activity.getType() ) {
                case DetectedActivity.IN_VEHICLE: {
                    strType="IN_VEHICLE";
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    strType="ON_BICYCLE";
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    strType="ON_FOOT";
                    break;
                }
                case DetectedActivity.RUNNING: {
                    strType="RUNNING";
                    break;
                }
                case DetectedActivity.STILL: {
                    strType="STILL";
                    break;
                }
                case DetectedActivity.TILTING: {
                    strType="TILTING";
                    break;
                }
                case DetectedActivity.WALKING: {
                    strType="WALKING";
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    strType="UNKNOWN";
                    break;
                }
            }

            Intent intent = new Intent(MainActivity.TAG);
            // You can also include some extra data.
            intent.putExtra("message",strType+" : "+activity.getConfidence());
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }


}