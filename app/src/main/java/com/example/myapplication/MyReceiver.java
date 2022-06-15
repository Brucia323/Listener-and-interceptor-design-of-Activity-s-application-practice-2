//MyReceiver.java
package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private String phone="";
    private String content="";

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        StringBuilder number=new StringBuilder();
        StringBuilder body=new StringBuilder();
        Bundle bundle=intent.getExtras();
        if (bundle!=null){
            Object[] objects= (Object[]) bundle.get("pdus");
            SmsMessage[] messages=new SmsMessage[objects.length];
            for (int i=0;i<objects.length;i++){
                messages[i]=SmsMessage.createFromPdu((byte[]) objects[i]);
            }
            for (SmsMessage message:messages){
                number.append(message.getDisplayOriginatingAddress());
                body.append(message.getDisplayMessageBody());
            }
            String smsnum=number.toString();
            String smsbody=body.toString();
            if (smsnum.equals(phone)){
                Toast.makeText(context,"你的电话号码被拦截",Toast.LENGTH_LONG).show();
                abortBroadcast();
            }
            if (smsbody.contains(content)){
                Toast.makeText(context,"你的敏感词汇被拦截",Toast.LENGTH_LONG).show();
                abortBroadcast();
            }
        }
    }
}