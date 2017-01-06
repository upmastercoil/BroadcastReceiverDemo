package tech.android.tcmp13.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText messageInput;
    private View bg;

    private BatteryBroadcastReceiver batteryBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bg = findViewById(R.id.bg);
        messageInput = (EditText) findViewById(R.id.messageInput);

        //Init the receiver for dynamic listening
        batteryBroadcastReceiver = new BatteryBroadcastReceiver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.shareActionItem) {
            //Start intent with the share (SEND) action
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            //Get the message the user types
            String message = messageInput.getText().toString();
            //add the message to the intent
            shareIntent.putExtra(Intent.EXTRA_TEXT, message);
            //Set the type of the shared data
            shareIntent.setType(Intent.normalizeMimeType("text/plain"));
            //To force the user to select an app to perform the action
            Intent chooser = Intent.createChooser(shareIntent, "Sharing is Caring!");
            //If there is an activity that can perform the action, go for it
            if (shareIntent.resolveActivity(getPackageManager()) != null)
                startActivity(chooser);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {

        super.onStart();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(batteryBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {

        super.onStop();
        unregisterReceiver(batteryBroadcastReceiver);
    }

    private class BatteryBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("fbi", intent.getAction());
            int black = ContextCompat.getColor(MainActivity.this, android.R.color.black);
            int white = ContextCompat.getColor(MainActivity.this, android.R.color.white);
            if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                bg.setBackgroundColor(white);
            } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
                bg.setBackgroundColor(black);
            }
        }
    }
}
