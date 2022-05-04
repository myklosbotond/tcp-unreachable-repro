package com.example.networkunreachablerepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import gotcp.Gotcp;

public class MainActivity extends AppCompatActivity {
    public final static String SSID = "<Wifi SSID>";
    public final static String WIFI_PASS = "<Wifi Pass>";

    private ConnectivityManager connectivityManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textView = findViewById(R.id.dialError);

        this.connectivityManager = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public void onWifiConnect(View button) {
        try {
            WifiNetworkSpecifier specifier = new WifiNetworkSpecifier
                    .Builder()
                    .setSsid(SSID)
                    .setWpa2Passphrase(WIFI_PASS)
                    .build();


            NetworkRequest networkRequest = new NetworkRequest
                    .Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .setNetworkSpecifier(specifier)
                    .build();

            connectivityManager.requestNetwork(networkRequest, new ConnectivityManager.NetworkCallback() {

            });
        } catch (Exception e) {
            Log.e("NetUnreach", "Wifi connect failed", e);
        }
    }

    public void onDialClick(View button) {
        try {
            this.textView.setText("...");

            Gotcp.doDial();
        } catch (Exception e) {
            Log.e("NetUnreach", "Dial failed", e);

            this.textView.setText(e.getMessage());
        }
    }
}