package com.example.tpo1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class ModoAvionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            boolean modoAvion = intent.getBooleanExtra("state", false);
            if (modoAvion) {
                // Modo de avión activado
                Toast.makeText(context, "Modo de avion activado", Toast.LENGTH_SHORT).show();
                // Intent implícito: abrir el marcador con el número
                Intent intentLlamada = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:2664553747"));
                // desde un receiver, agregá NEW_TASK
                intentLlamada.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentLlamada);
            } else {
                // Modo de avión desactivado
                Toast.makeText(context, "Modo de avión desactivado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}