package com.terafiros.phi_estacionamento;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.security.acl.LastOwnerException;

public class ReservaListener implements View.OnClickListener {


    private Context ctx;
    private String imageview;
    public ReservaListener(Context context, String imv){
        ctx = context;
        imageview = imv;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(ctx, "tudo pelo certo!\n" + imageview, Toast.LENGTH_SHORT).show();
        Log.d("TUDO CERTO", "deu tudo cerrto!" + imageview);

        //PubTask task = new PubTask(ctx);
        //task.execute();



    }
}
