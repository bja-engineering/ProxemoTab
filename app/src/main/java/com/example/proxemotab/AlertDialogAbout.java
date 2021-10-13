package com.example.proxemotab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class AlertDialogAbout extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Proxemo, ProxemoTab (2019-2021)\n\n(C) Original concept by Stephan Huber\n\nEmoji icons provided free by EmojiOne (emojione.com)\n\nCode is based on 'EmoMem' implementation for Wear OS by Alexander Bejan")

                .setNegativeButton("Cool!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();


    }
}
