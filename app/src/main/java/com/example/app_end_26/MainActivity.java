package com.example.app_end_26;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final static String text = "com.givemepass.sendmessage";
    private Button send_broadcast;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_broadcast = (Button)findViewById(R.id.button);
        send_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                registerReceiver(mBroadcast, new IntentFilter(text));
                Intent intent = new Intent();
                intent.setAction(text);
                sendBroadcast(intent);
            }
        });
    }
    private BroadcastReceiver mBroadcast =  new BroadcastReceiver() {
        private final static String text = "com.givemepass.sendmessage";
        @Override
        public void onReceive(Context mContext, Intent mIntent) {

            if(text.equals(mIntent.getAction())){
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("收到訊息!")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                unregisterReceiver(mBroadcast);
                            }
                        })
                        .show();
            }
        }
    };
}