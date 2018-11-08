package com.terafiros.phi_estacionamento;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imv1;
    private ImageView imv2;
    private ImageView imv3;
    private ImageView imv4;
    private ImageView imv5;
    private ImageView imv6;
    private ImageView imv7;
    private ImageView imv8;
    private ImageView imv9;
    private ImageView imv10;
    private ImageView imv11;
    private ImageView imv12;

    private  ArrayList<ImageView> vagas;
    private SubTask subTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subTask = new SubTask(this);
        subTask.execute();

        imv1 = (ImageView) findViewById(R.id.imageView1);
        imv2 = (ImageView) findViewById(R.id.imageView2);
        imv3 = (ImageView) findViewById(R.id.imageView3);
        imv4 = (ImageView) findViewById(R.id.imageView4);
        imv5 = (ImageView) findViewById(R.id.imageView5);
        imv6 = (ImageView) findViewById(R.id.imageView6);
        imv7 = (ImageView) findViewById(R.id.imageView7);
        imv8 = (ImageView) findViewById(R.id.imageView8);
        imv9 = (ImageView) findViewById(R.id.imageView9);
        imv10 = (ImageView) findViewById(R.id.imageView10);
        imv11 = (ImageView) findViewById(R.id.imageView11);
        imv12 = (ImageView) findViewById(R.id.imageView12);


        imv1.setOnClickListener(new ReservaListener(this, "1"));
        imv2.setOnClickListener(new ReservaListener(this, "2"));
        imv3.setOnClickListener(new ReservaListener(this, "3"));
        imv4.setOnClickListener(new ReservaListener(this, "4"));
        imv5.setOnClickListener(new ReservaListener(this, "5"));
        imv6.setOnClickListener(new ReservaListener(this, "6"));
        imv7.setOnClickListener(new ReservaListener(this, "7"));
        imv8.setOnClickListener(new ReservaListener(this, "8"));
        imv9.setOnClickListener(new ReservaListener(this, "9"));
        imv10.setOnClickListener(new ReservaListener(this, "10"));
        imv11.setOnClickListener(new ReservaListener(this, "11"));
        imv12.setOnClickListener(new ReservaListener(this, "12"));

        vagas = new ArrayList<>();
        vagas.add(imv1);
        vagas.add(imv2);
        vagas.add(imv3);
        vagas.add(imv4);
        vagas.add(imv5);
        vagas.add(imv6);
        vagas.add(imv7);
        vagas.add(imv8);
        vagas.add(imv9);
        vagas.add(imv10);
        vagas.add(imv11);
        vagas.add(imv12);





    }


    public class SubTask extends AsyncTask<Void, Void, Void> {


        private Context context;

        public SubTask(Context context) {
            this.context = context;
        }


        @Override
        protected Void doInBackground(Void... voids) {

            MqttAndroidClient client = new MqttAndroidClient(context, "tcp://test.mosquitto.org:1883", "android");
            try {
                IMqttToken token = client.connect();
                token.waitForCompletion();

                String topic = "teste/app2";
                IMqttToken subToken = client.subscribe(topic, 2);

                subToken.getClient().setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                        String message = new String(mqttMessage.getPayload());
                        String[] vagaCor = message.split("/");
                        changeColor(Integer.valueOf(vagaCor[0]), vagaCor[1]);

                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });

            } catch (MqttException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private void changeColor(Integer vaga, String cor) {
        if(cor.equals("verde")){
            ImageView imageView = vagas.get(vaga - 1);
            imageView.setBackgroundColor(getResources().getColor(R.color.verde));

        }else if(cor.equals("vermelho")){
            ImageView imageView = vagas.get(vaga - 1);
            imageView.setBackgroundColor(getResources().getColor(R.color.vermelho));

        }

    }
}