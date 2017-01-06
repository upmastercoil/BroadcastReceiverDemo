package tech.android.tcmp13.broadcastreceiverdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        TextView showMessage = (TextView) findViewById(R.id.showMessage);
        Intent intent = getIntent();
        //if the activity was started by a share(SEND) action intent display the message at the share key
        if (intent.getAction().equals(Intent.ACTION_SEND))
            showMessage.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        //if the activity was started by a custom action intent display the message at the custom key
        else if (intent.getAction().equals("tech.android.tcmp13.FACEBOOK169"))
            showMessage.setText(intent.getStringExtra("169"));
        //if the activity was started by an explicit intent
        else
            showMessage.setText("No Message");
    }
}
