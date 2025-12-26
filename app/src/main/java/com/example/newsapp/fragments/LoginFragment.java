package com.example.newsapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newsapp.AuthActivity;
import com.example.newsapp.MainActivity;
import com.example.newsapp.R;
import com.example.newsapp.utils.SharedPrefManager;

public class LoginFragment extends Fragment {

    private EditText email, password;
    private Button btnLogin;
    private TextView tvGoSignup;
    private SharedPrefManager pref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.et_email);
        password = view.findViewById(R.id.et_password);
        btnLogin = view.findViewById(R.id.btn_login);
        tvGoSignup = view.findViewById(R.id.tv_go_signup);

        pref = new SharedPrefManager(requireContext());

        btnLogin.setOnClickListener(v -> {
            String em = email.getText().toString().trim();
            String pw = password.getText().toString().trim();

            if (TextUtils.isEmpty(em) || TextUtils.isEmpty(pw)) {
                Toast.makeText(requireContext(), "لطفا همه فیلدها را پر کنید", Toast.LENGTH_SHORT).show();
                return;
            }

            if (pref.login(em, pw)) {
                Intent intent = new Intent(requireContext(), MainActivity.class);
                Toast.makeText(requireContext(), "خوش آمدید، " + pref.getUserFullName(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(requireContext(), MainActivity.class));
                requireActivity().finish();
            } else {
                Toast.makeText(requireContext(), "ایمیل یا رمز عبور اشتباه است", Toast.LENGTH_SHORT).show();
            }
        });

        tvGoSignup.setOnClickListener(v -> {
            if (getActivity() instanceof AuthActivity) {
                ((AuthActivity) getActivity()).showFragment(new SignupFragment());

            }
        });

        return view;
    }
}
