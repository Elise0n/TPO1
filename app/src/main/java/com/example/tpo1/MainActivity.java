package com.example.tpo1;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ModoAvionReceiver eventoModoAvion;

    @Override
    protected void onStart() {
        super.onStart();
        if (eventoModoAvion == null) eventoModoAvion = new ModoAvionReceiver();

        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        // Para API 33+ usar la versión con flags; para anteriores, la clásica
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(eventoModoAvion, filter, Context.RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(eventoModoAvion, filter);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (eventoModoAvion != null) {
            try {
                unregisterReceiver(eventoModoAvion);
            } catch (IllegalArgumentException ignore) { /* ya estaba desregistrado */ }
            eventoModoAvion = null; // evita doble registro en el próximo onStart
        }
    }
}