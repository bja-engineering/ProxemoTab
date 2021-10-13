package com.example.proxemotab;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link docuScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class docuScreen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    TransitionDrawable btnTransition;
    ExternalStorageHelper esh;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String logFile = "ProxemoTabLog.csv";


    Vibrator vibrator;

    //Animation path for imagebtn on press
    PropertyValuesHolder pvhScaleXPosFull = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.5f, 1f);
    PropertyValuesHolder pvhScaleYPosFull = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.5f, 1f);


    public docuScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment docuScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static docuScreen newInstance(String param1, String param2) {
        docuScreen fragment = new docuScreen();
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
        esh = new ExternalStorageHelper();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_docu_screen_double, container, false);


    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        final TextView usrname1 = getActivity().findViewById(R.id.leftUser);
        final TextView usrname2 = getActivity().findViewById(R.id.rightUser);

        vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

        //defining image buttons
        ImageButton left_first = getActivity().findViewById(R.id.left_first);
        ImageButton left_second = getActivity().findViewById(R.id.left_second);
        ImageButton left_third = getActivity().findViewById(R.id.left_third);
        ImageButton left_fourth = getActivity().findViewById(R.id.left_fourth);
        ImageButton left_fifth = getActivity().findViewById(R.id.left_fifth);

        ImageButton right_first = getActivity().findViewById(R.id.right_first);
        ImageButton right_second = getActivity().findViewById(R.id.right_second);
        ImageButton right_third = getActivity().findViewById(R.id.right_third);
        ImageButton right_fourth = getActivity().findViewById(R.id.right_fourth);
        ImageButton right_fifth = getActivity().findViewById(R.id.right_fifth);


        //TODO: second user

        PropertyValuesHolder pvhScaleXPosFull = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.5f, 1f);
        PropertyValuesHolder pvhScaleYPosFull = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.5f, 1f);
        //final PorterDuffColorFilter greenFilter = new PorterDuffColorFilter(0xe031FF42, PorterDuff.Mode.SRC_ATOP);


//stehen jetzt oben und werden nicht mehr benötigt
        //  final ObjectAnimator imageButtonAnimatorLeft_first = ObjectAnimator.ofPropertyValuesHolder(left_first, pvhScaleXPosFull, pvhScaleYPosFull).setDuration(200);
        // final ObjectAnimator imageButtonAnimatorLeft_second = ObjectAnimator.ofPropertyValuesHolder(left_first, pvhScaleXPosFull, pvhScaleYPosFull).setDuration(200);


        // Set OnTouchListeners to ImageButtons and start the animations
        left_first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    // "pressed"
                    case MotionEvent.ACTION_DOWN:

                        return true;

                    // "released"
                    case MotionEvent.ACTION_UP:

                        imageButtonAnimator(v);
/*
                        getActivity().findViewById(R.id.left_first).setBackgroundResource(android.R.color.holo_green_light);

                        //TODO auslagern in extra Methode
                        btnTransition = (TransitionDrawable) getActivity().findViewById(R.id.left_first).getBackground();
                        btnTransition.startTransition(100);
                        btnTransition.reverseTransition(100);

*/
                        // getActivity().findViewById(R.id.masterFrame).setBackgroundResource(R.drawable.td_emomem_bg_filled_top);

                        esh.appendEntryToFile(logFile, timeStamp(v.getContentDescription().toString(), usrname1.getText().toString()));

                        //Toast.makeText(getContext(), v.getContentDescription().toString() +" logged!", Toast.LENGTH_LONG).show();

                        //Snackbar confirming the logged emotion and offering an UNDO button
                        Snackbar snackbar = Snackbar.make(v, v.getContentDescription().toString() + " logged!", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                try {
                                    esh.deleteLastEntryFromFile(logFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //TODO hier noch die gelöschte Emotion nennen
                                Snackbar snackbar1 = Snackbar.make(v1, v.getContentDescription().toString().toUpperCase() + " undone successful", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                        snackbar.show();

                        return true;

                }

                return false;
            }
        });

        left_second.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_UP:

                        imageButtonAnimator(v);
                        esh.appendEntryToFile(logFile, timeStamp(v.getContentDescription().toString(), usrname1.getText().toString()));

                        //Toast.makeText(getContext(), v.getContentDescription().toString() +" logged!", Toast.LENGTH_SHORT).show();
                        //Snackbar confirming the logged emotion and offering an UNDO button
                        Snackbar snackbar = Snackbar.make(v, v.getContentDescription().toString() + " logged!", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                try {
                                    esh.deleteLastEntryFromFile(logFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //TODO hier noch die gelöschte Emotion nennen
                                Snackbar snackbar1 = Snackbar.make(v1, v.getContentDescription().toString().toUpperCase() + " undone successful", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                        snackbar.show();

                        return true;
                }
                return false;
            }
        });

        left_third.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_UP:

                        imageButtonAnimator(v);
                        esh.appendEntryToFile(logFile, timeStamp(v.getContentDescription().toString(), usrname1.getText().toString()));

                        //Toast.makeText(getContext(), v.getContentDescription().toString() + " logged!", Toast.LENGTH_SHORT).show();

                        //Snackbar confirming the logged emotion and offering an UNDO button
                        Snackbar snackbar = Snackbar.make(v, v.getContentDescription().toString() +" logged!", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                try {
                                    esh.deleteLastEntryFromFile(logFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //TODO hier noch die gelöschte Emotion nennen
                                Snackbar snackbar1 = Snackbar.make(v1, v.getContentDescription().toString().toUpperCase() + " undone successful", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                        snackbar.show();

                        return true;
                }
                return false;
            }
        });

        left_fourth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_UP:

                        imageButtonAnimator(v);
                        esh.appendEntryToFile(logFile, timeStamp(v.getContentDescription().toString(), usrname1.getText().toString()));

                       // Toast.makeText(getContext(), v.getContentDescription().toString() + " logged!", Toast.LENGTH_SHORT).show();

                        //Snackbar confirming the logged emotion and offering an UNDO button
                        Snackbar snackbar = Snackbar.make(v, v.getContentDescription().toString() +" logged!", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                try {
                                    esh.deleteLastEntryFromFile(logFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //TODO hier noch die gelöschte Emotion nennen
                                Snackbar snackbar1 = Snackbar.make(v1, v.getContentDescription().toString().toUpperCase() + " undone successful", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                        snackbar.show();

                        return true;
                }
                return false;
            }
        });

        left_fifth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_UP:

                        imageButtonAnimator(v);
                        esh.appendEntryToFile(logFile, timeStamp(v.getContentDescription().toString(), usrname1.getText().toString()));

                        //Toast.makeText(getContext(), v.getContentDescription().toString() + " logged!", Toast.LENGTH_SHORT).show();

                        //Snackbar confirming the logged emotion and offering an UNDO button
                        Snackbar snackbar = Snackbar.make(v, v.getContentDescription().toString() +" logged!", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                try {
                                    esh.deleteLastEntryFromFile(logFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //TODO hier noch die gelöschte Emotion nennen
                                Snackbar snackbar1 = Snackbar.make(v1, v.getContentDescription().toString().toUpperCase() + " undone successful", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                        snackbar.show();

                        return true;
                }
                return false;
            }
        });


        right_first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_UP:

                        imageButtonAnimator(v);
                        esh.appendEntryToFile(logFile, timeStamp(v.getContentDescription().toString(), usrname2.getText().toString()));

                        //Toast.makeText(getContext(), v.getContentDescription().toString() + " logged!", Toast.LENGTH_SHORT).show();

                        //Snackbar confirming the logged emotion and offering an UNDO button
                        Snackbar snackbar = Snackbar.make(v, v.getContentDescription().toString() +" logged!", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                try {
                                    esh.deleteLastEntryFromFile(logFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //TODO hier noch die gelöschte Emotion nennen
                                Snackbar snackbar1 = Snackbar.make(v1, v.getContentDescription().toString().toUpperCase() + " undone successful", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                        snackbar.show();

                        return true;
                }
                return false;
            }
        });

        right_second.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_UP:

                        imageButtonAnimator(v);
                        esh.appendEntryToFile(logFile, timeStamp(v.getContentDescription().toString(), usrname2.getText().toString()));

                        //Toast.makeText(getContext(), v.getContentDescription().toString() + " logged!", Toast.LENGTH_SHORT).show();

                        //Snackbar confirming the logged emotion and offering an UNDO button
                        Snackbar snackbar = Snackbar.make(v, v.getContentDescription().toString() +" logged!", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                try {
                                    esh.deleteLastEntryFromFile(logFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //TODO hier noch die gelöschte Emotion nennen
                                Snackbar snackbar1 = Snackbar.make(v1, v.getContentDescription().toString().toUpperCase() + " undone successful", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                        snackbar.show();

                        return true;
                }
                return false;
            }
        });

        right_third.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_UP:

                        imageButtonAnimator(v);
                        esh.appendEntryToFile(logFile, timeStamp(v.getContentDescription().toString(), usrname2.getText().toString()));

                        //Toast.makeText(getContext(), v.getContentDescription().toString() + " logged!", Toast.LENGTH_SHORT).show();

                        //Snackbar confirming the logged emotion and offering an UNDO button
                        Snackbar snackbar = Snackbar.make(v, v.getContentDescription().toString() +" logged!", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                try {
                                    esh.deleteLastEntryFromFile(logFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //TODO hier noch die gelöschte Emotion nennen
                                Snackbar snackbar1 = Snackbar.make(v1, v.getContentDescription().toString().toUpperCase() + " undone successful", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                        snackbar.show();

                        return true;
                }
                return false;
            }
        });

        right_fourth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_UP:

                        imageButtonAnimator(v);
                        esh.appendEntryToFile(logFile, timeStamp(v.getContentDescription().toString(), usrname2.getText().toString()));

                        //Toast.makeText(getContext(), v.getContentDescription().toString() + " logged!", Toast.LENGTH_SHORT).show();

                        //Snackbar confirming the logged emotion and offering an UNDO button
                        Snackbar snackbar = Snackbar.make(v, v.getContentDescription().toString() +" logged!", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                try {
                                    esh.deleteLastEntryFromFile(logFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //TODO hier noch die gelöschte Emotion nennen
                                Snackbar snackbar1 = Snackbar.make(v1, v.getContentDescription().toString().toUpperCase() + " undone successful", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                        snackbar.show();

                        return true;
                }
                return false;
            }
        });

        right_fifth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_UP:

                        imageButtonAnimator(v);
                        esh.appendEntryToFile(logFile, timeStamp(v.getContentDescription().toString(), usrname2.getText().toString()));

                        //Toast.makeText(getContext(), v.getContentDescription().toString() + " logged!", Toast.LENGTH_SHORT).show();

                        //Snackbar confirming the logged emotion and offering an UNDO button
                        Snackbar snackbar = Snackbar.make(v, v.getContentDescription().toString() +" logged!", Snackbar.LENGTH_SHORT).setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v1) {
                                try {
                                    esh.deleteLastEntryFromFile(logFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //TODO hier noch die gelöschte Emotion nennen
                                Snackbar snackbar1 = Snackbar.make(v1, v.getContentDescription().toString().toUpperCase() + " undone successful", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });
                        snackbar.show();

                        return true;
                }
                return false;
            }
        });


    }


    /**
     * Assistive method to create the perfect cross device output string for Proxemo [todo]
     *
     * @param emotion
     * @param user
     * @return
     */
    public String timeStamp(String emotion, String user) {

        return System.currentTimeMillis() + ";" + Calendar.getInstance().getTime().toString() + "; " + user + ";" + emotion;
    }

    /**
     * animates all imagebuttons [todo: mit Farbanimation]
     *
     * @param v
     */
    public void imageButtonAnimator(View v) {
        //ist zwar cool, aber auch ganz schön laut... vielleicht in den Settings de-aktivierbar?
        //vibrator.vibrate(50);
        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(v, pvhScaleXPosFull, pvhScaleYPosFull).setDuration(200);
        anim.start();
    }


}
