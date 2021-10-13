package com.example.proxemotab;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class EmojiInformation extends DialogFragment {
    private int emoji;
    private String message;

    public void setEmoji(int emotion){
        emoji = emotion;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        switch (emoji){
            case (1):
               message = "ANGER: Frustration/Ärger\n\ngenervt sein, oder sich darüber aufregen, dass etwas schief lief, bzw. nicht erreicht wurde; am liebsten würde man fluchen, schimpfen";
               break;
            case (2):
                message = "BOREDOM: Langweile \n\nman ist unterfordert und ungeduldig, weil gerade nichts Interessantes passiert oder man nichts zu tun hat";
                break;
            case (3):
                message = "STRESS: Überforderung \n\nemotionale oder mentale Spannung ausgelöst z.B. durch drohenden Kontrolleverlust über die Situation";
                break;
            case (4):
                message = "SURPRISE: Überraschung \n\nkurz aus der Fassung kommen durch ein unerwartetes Ereignis";
                break;
            case (5):
                message = "PRIDE: Stolz \n\nFreude, ausgelöst durch etwas, was man selbst erreicht hat, Erfolg durch eigene Fähigkeiten; Auslöser kann ein Ereignis oder eine Erfahrung sein, in denen man Selbstwirksamkeit erlebt hat";
        }


        builder.setMessage(message)

                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();


    }
}
