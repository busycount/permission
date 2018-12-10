package com.busycount.permission.sample;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.busycount.permission.PermissionListener;
import com.busycount.permission.PermissionUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.text);
        textView.setText(mParam1);
        Button button = view.findViewById(R.id.button);
        button.setText(mParam2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
        Button button1 = view.findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request2();
            }
        });
    }

    private void request() {
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionUtil.request(this, permissions, new PermissionListener() {
            @Override
            public void onGranted() {
                Toast.makeText(getContext(), "onGranted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String[] denied) {
                StringBuilder builder = new StringBuilder();
                for (String str : denied) {
                    builder.append(str).append(" , ");
                }
                Log.d("console", "onDenied: " + builder.toString());
                Toast.makeText(getContext(), "onDenied " + builder.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void request2() {
        String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        PermissionUtil.request(this, permissions, new PermissionListener() {
            @Override
            public void onGranted() {
                Toast.makeText(getContext(), "onGranted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String[] denied) {
                StringBuilder builder = new StringBuilder();
                for (String str : denied) {
                    builder.append(str).append(" , ");
                }
                Log.d("console", "onDenied: " + builder.toString());
                Toast.makeText(getContext(), "onDenied " + builder.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
