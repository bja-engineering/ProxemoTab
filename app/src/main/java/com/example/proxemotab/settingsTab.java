package com.example.proxemotab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

import org.w3c.dom.Text;

import java.io.File;
import java.net.URI;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link settingsTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingsTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String logFile = "ProxemoTabLog.csv";

    AlertDialogAbout adAbout;
    AlertDialogDeleteLog adDeleteLog;
    EmojiInformation emojiInformation;

    Vibrator vibrator;

    ExternalStorageHelper esh;

    public EditText usr1, usr2;


    public settingsTab() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment settingsTab.
     */
    // TODO: Rename and change types and number of parameters
    public static settingsTab newInstance(String param1, String param2) {
        settingsTab fragment = new settingsTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adAbout = new AlertDialogAbout();
        adDeleteLog = new AlertDialogDeleteLog();
        vibrator = (Vibrator) this.getContext().getSystemService(Context.VIBRATOR_SERVICE);

        emojiInformation = new EmojiInformation();

        esh = new ExternalStorageHelper();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_tab, container, false);
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        Button aboutButton = getActivity().findViewById(R.id.aboutButton);
        Button deleteLogButton = getActivity().findViewById(R.id.deleteLogButton);
        Button updateUserName = getActivity().findViewById(R.id.updateUser);
        Button sendLog = getActivity().findViewById(R.id.sendButton);

        ImageButton setting_first = getActivity().findViewById(R.id.setting_first);
        ImageButton setting_second = getActivity().findViewById(R.id.setting_second);
        ImageButton setting_third = getActivity().findViewById(R.id.setting_third);
        ImageButton setting_fourth = getActivity().findViewById(R.id.setting_fourth);
        ImageButton setting_fifth = getActivity().findViewById(R.id.setting_fifth);


        sendLog.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // "pressed"
                    case MotionEvent.ACTION_DOWN:

                        return true;

                    // "released"
                    case MotionEvent.ACTION_UP:

                        esh.export(getActivity());

                        /*
                        URI fileUri;
                        File requestFile = new File(filename);

                        // Use the FileProvider to get a content URI
                        try {
                            fileUri = FileProvider.getUriForFile(
                                    settingsTab.this,
                                    "com.example.proxemotab.fileprovider",
                                    requestFile);
                        } catch (IllegalArgumentException e) {
                            Log.e("File Selector",
                                    "The selected file can't be shared: " + requestFile.toString());
                        }
                        */
                        return true;

                }

                return false;
            }
        });


        aboutButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // "pressed"
                    case MotionEvent.ACTION_DOWN:

                        return true;

                    // "released"
                    case MotionEvent.ACTION_UP:

                        adAbout.show(getFragmentManager(), "About Proxemo");

                        return true;

                }

                return false;
            }
        });

        deleteLogButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // "pressed"
                    case MotionEvent.ACTION_DOWN:

                        return true;

                    // "released"
                    case MotionEvent.ACTION_UP:

                        adDeleteLog.show(getFragmentManager(), "Delete emotion log");

                        return true;

                }

                return false;
            }
        });


        updateUserName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // "pressed"
                    case MotionEvent.ACTION_DOWN:

                        return true;

                    // "released"
                    case MotionEvent.ACTION_UP:

                        //KEINE AHNUNG OB DAS SO STIMMT
                        usr1 = getActivity().findViewById(R.id.editUser1);
                     //   el.getSharedPreferences().edit().putString("USER_1", usr1.getText().toString()).commit();
                       // el.setUser1(el.getSharedPreferences().getString("USER_1", usr1.getText().toString()));
                        TextView textView1 = getActivity().findViewById(R.id.leftUser);
                        textView1.setText(usr1.getText());
                        //dann auch noch den anderen user
                        TextView textView1_1 = getActivity().findViewById(R.id.leftUser_1);
                        textView1_1.setText(usr1.getText());


                        usr2 = getActivity().findViewById(R.id.editUser2);
                    //    el.getSharedPreferences().edit().putString("USER_2", usr2.getText().toString()).commit();
                      //  el.setUser2(el.getSharedPreferences().getString("USER_2", usr2.getText().toString()));
                        TextView textView2 = getActivity().findViewById(R.id.rightUser);
                        textView2.setText(usr2.getText());

                        Toast.makeText(getContext(), "Users updated", Toast.LENGTH_SHORT).show();
                        vibrator.vibrate(100);

                        return true;
                }

                return false;
            }
        });

        /**
         * hier kommen alle ATC-Emotionen
         */

        setting_first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // "pressed"
                    case MotionEvent.ACTION_DOWN:

                        return true;

                    // "released"
                    case MotionEvent.ACTION_UP:

                        emojiInformation.setEmoji(1);
                        emojiInformation.show(getFragmentManager(), "anger");

                        return true;
                }
                return false;
            }
        });

        setting_second.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // "pressed"
                    case MotionEvent.ACTION_DOWN:

                        return true;

                    // "released"
                    case MotionEvent.ACTION_UP:

                        emojiInformation.setEmoji(2);
                        emojiInformation.show(getFragmentManager(), "boredom");

                        return true;
                }
                return false;
            }
        });

        setting_third.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // "pressed"
                    case MotionEvent.ACTION_DOWN:

                        return true;

                    // "released"
                    case MotionEvent.ACTION_UP:

                        emojiInformation.setEmoji(3);
                        emojiInformation.show(getFragmentManager(), "stress");

                        return true;
                }
                return false;
            }
        });

        setting_fourth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // "pressed"
                    case MotionEvent.ACTION_DOWN:

                        return true;

                    // "released"
                    case MotionEvent.ACTION_UP:

                        emojiInformation.setEmoji(4);
                        emojiInformation.show(getFragmentManager(), "surprise");

                        return true;
                }
                return false;
            }
        });

        setting_fifth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // "pressed"
                    case MotionEvent.ACTION_DOWN:

                        return true;

                    // "released"
                    case MotionEvent.ACTION_UP:

                        emojiInformation.setEmoji(5);
                        emojiInformation.show(getFragmentManager(), "pride");

                        return true;
                }
                return false;
            }
        });


    }


    /*
                        Intent pickEmoji = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickEmoji, 1);

*/

/*
    protected void onActivityResult (int requestCode, int resultCode, Intent imageReturnedIntent){
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (requestCode == 1 && resultCode == RESULT_OK){
            Uri selectedImage = imageReturnedIntent.getData();
            //TODO set button and descriptive string
            getActivity().findViewById(R.id.setting_first).setBackgroundResource(selectedImage);
        }

    }
    */




}
