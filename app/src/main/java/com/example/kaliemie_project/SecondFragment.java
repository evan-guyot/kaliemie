package com.example.kaliemie_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


import com.example.kaliemie_project.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private Async mThreadCon = null;
    private EditText login;
    private EditText pass;
    private String url;
    private String[] mesparams;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText id = (EditText)getView().findViewById(R.id.etFragId);
        EditText pass = (EditText)getView().findViewById(R.id.etFragPassword);

        binding.bFragOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                url =   "https://www.btssio-carcouet.fr/ppe4/public/connect2/"
                        + id.getText().toString()
                        +"/"
                        + id.getText().toString()
                        +"/infirmiere";

                mesparams=new String[3];
                mesparams[0]="1";
                mesparams[1]=url;
                mesparams[2]="GET";
                mThreadCon = new Async ((MainActivity)getActivity());
                mThreadCon.execute(mesparams);
            }
        });
        binding.bFragCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(((MainActivity)getActivity()), R.id.nav_host_fragment_content_main).navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}