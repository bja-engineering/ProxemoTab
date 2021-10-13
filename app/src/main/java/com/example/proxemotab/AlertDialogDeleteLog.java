package com.example.proxemotab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class AlertDialogDeleteLog extends DialogFragment {

    ExternalStorageHelper esh;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        esh = new ExternalStorageHelper();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Do you really want to delete the log file?")

                .setPositiveButton("Yes, do it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Yes, do it!
                        esh.deleteFile("ProxemoTabLog.csv");

                        Toast.makeText(getContext(), "Log file deleted", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("No, cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();

    }
}
