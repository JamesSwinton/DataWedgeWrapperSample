package com.zebra.jamesswinton.dwprofile0modifier;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.zebra.jamesswinton.datawedgewrapperlib.DataWedgeWrapper;
import com.zebra.jamesswinton.datawedgewrapperlib.configuration.MainBundle;
import com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins.BDFPlugin;
import com.zebra.jamesswinton.datawedgewrapperlib.configuration.plugins.KeystrokePlugin;
import com.zebra.jamesswinton.datawedgewrapperlib.interfaces.OnLastResultIntentListener;
import com.zebra.jamesswinton.datawedgewrapperlib.utilities.Constants;

public class MainActivity extends AppCompatActivity {

    // Debugging
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Plugin Bundles
        Bundle keyStrokePlugin = new KeystrokePlugin.Builder()
                .resetConfig(true)
                .setEnabled(true)
                .create();

        // Create BDF Plugin
        Bundle bdfPlugin = new BDFPlugin.Builder()
                .resetConfig(true)
                .setOutputPluginName(Constants.OutputPluginName.KEYSTROKE)
                .setEnabled(true)
                .sendEnter(true)
                .create();

        // Create Main Bundle
        Bundle mainBundle = new MainBundle.Builder()
                .setProfileEnabled(true)
                .setProfileName("Profile0 (default)")
                .setConfigMode(Constants.ConfigMode.CREATE_IF_NOT_EXIST)
                .addPluginBundle(bdfPlugin)
                .addPluginBundle(keyStrokePlugin)
                .create();

        // Send Intent With Result
        DataWedgeWrapper.sendIntentWithLastResult(this, Constants.IntentType.SET_CONFIG,
                mainBundle, "test", (b, bundle, s, s1, s2) -> {
                    Toast.makeText(MainActivity.this, "Profile0 Modified: " + b,
                            Toast.LENGTH_LONG).show();
                    finish();
                });
    }
}