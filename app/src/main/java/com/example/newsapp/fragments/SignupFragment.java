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

public class SignupFragment extends Fragment {

    private EditText firstName, lastName, email, password, confirmPassword;
    private Button btnSignup;
    private TextView tvGoLogin;
    private SharedPrefManager pref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        firstName = view.findViewById(R.id.et_first_name);
        lastName = view.findViewById(R.id.et_last_name);
        email = view.findViewById(R.id.et_email);
        password = view.findViewById(R.id.et_password);
        confirmPassword = view.findViewById(R.id.et_confirm_password);
        btnSignup = view.findViewById(R.id.btn_signup);
        tvGoLogin = view.findViewById(R.id.tv_go_login);

        pref = new SharedPrefManager(requireContext());

        btnSignup.setOnClickListener(v -> {
            String fn = firstName.getText().toString().trim();
            String ln = lastName.getText().toString().trim();
            String em = email.getText().toString().trim();
            String pw = password.getText().toString().trim();
            String cpw = confirmPassword.getText().toString().trim();

            if (TextUtils.isEmpty(fn) || TextUtils.isEmpty(ln) ||
                    TextUtils.isEmpty(em) || TextUtils.isEmpty(pw) || TextUtils.isEmpty(cpw)) {
                Toast.makeText(requireContext(), "لطفا همه فیلدها را پر کنید", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pw.equals(cpw)) {
                Toast.makeText(requireContext(), "رمز عبور با تکرار آن مطابقت ندارد", Toast.LENGTH_SHORT).show();
                return;
            }

            if (pref.register(em, pw, fn, ln)) {
                Toast.makeText(requireContext(), "ثبت‌نام موفق! خوش آمدید " + fn, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(requireContext(), MainActivity.class));
                requireActivity().finish();
            } else {
                Toast.makeText(requireContext(), "این ایمیل قبلاً ثبت شده است", Toast.LENGTH_SHORT).show();
            }
        });

        tvGoLogin.setOnClickListener(v -> {
            if (getActivity() instanceof AuthActivity) {
                ((AuthActivity) getActivity()).showFragment(new LoginFragment());
            }
        });

        return view;
    }
}
