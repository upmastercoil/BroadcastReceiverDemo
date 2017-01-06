package tech.android.tcmp13.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by tcmp13-t on 1/4/2017.
 * <p>
 * Wake when there is an incoming phone call
 */
public class FbiPhoneCallBroadcastReceiver extends BroadcastReceiver {

    /**
     * Phone call received, do as you please with the data
     *
     * @param context the current context
     * @param intent  the data
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle phoneCallInfo = intent.getExtras();
        if (phoneCallInfo == null)
            return;
        String state = phoneCallInfo.getString(TelephonyManager.EXTRA_STATE);
        if (state == null)
            return;
        Log.d("fbi", state);
        if (!state.equals(TelephonyManager.EXTRA_STATE_RINGING))
            return;
        String phoneNumber = phoneCallInfo.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        Log.d("fbi", phoneNumber);
    }
}
