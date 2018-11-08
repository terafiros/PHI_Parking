package com.terafiros.phi_estacionamento;

import android.content.Context;
import android.os.AsyncTask;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PubTask extends AsyncTask<Void,Void,Void> {

    private Context context;

    public PubTask(Context ctx) {
        context = ctx;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        MqttAndroidClient client = new MqttAndroidClient(context, "tcp://test.mosquitto.org:1883", "android");
        try {
            IMqttToken token = client.connect();
            token.waitForCompletion();
            String topic = "teste/app2";
            String payload = "2/vermelho";
            byte[] encodedPayload = new byte[0];
            encodedPayload = payload.getBytes();
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, encodedPayload, 2, true);
            client.close();

        }catch (MqttException e) {
            e.printStackTrace();
        }



        return null;
    }
}
