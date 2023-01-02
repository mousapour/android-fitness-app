package com.example.android_fitness_app.ui.settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_fitness_app.Model.User;
import com.example.android_fitness_app.R;
import com.example.android_fitness_app.databinding.FragmentSettingsBinding;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsFragment extends Fragment {

    private User user;
    private FragmentSettingsBinding binding;
    private EditText nameEditText;
    private EditText weightEditText;
    private EditText heightEditText;
    private RadioGroup radioGroup;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private SwitchMaterial darkModeSwitch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = User.getInstance();
        nameEditText = view.findViewById(R.id.nameEditText);
        weightEditText = view.findViewById(R.id.weightEditText);
        heightEditText = view.findViewById(R.id.heightEditText);
        radioGroup = view.findViewById(R.id.radioGroup);
        maleRadioButton = view.findViewById(R.id.maleRadioButton);
        femaleRadioButton = view.findViewById(R.id.femaleRadioButton);
        darkModeSwitch = view.findViewById(R.id.darkModeSwitch);
        // init fields
        String name = user.getName();
        if (name != null) nameEditText.setText(name);
        double weight = user.getWeight();
        if (weight != -1) weightEditText.setText(Double.toString(weight));
        double height = user.getHeight();
        if (height != -1) weightEditText.setText(Double.toString(height));
        User.Sex sex = user.getSex();
        if (sex == User.Sex.MALE) {
            maleRadioButton.setChecked(true);
        } else if (sex == User.Sex.FEMALE) {
            femaleRadioButton.setChecked(true);
        }
        // add methods to components
        TextWatcher userInfoTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = nameEditText.getText().toString();
                if (!name.equals("")) user.setName(name);
                String weight = weightEditText.getText().toString();
                if (!weight.equals("")) user.setWeight(Double.parseDouble(weight));
                String height = heightEditText.getText().toString();
                if (!height.equals("")) user.setHeight(Double.parseDouble(weight));
            }
        };
        nameEditText.addTextChangedListener(userInfoTextWatcher);
        weightEditText.addTextChangedListener(userInfoTextWatcher);
        heightEditText.addTextChangedListener(userInfoTextWatcher);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.maleRadioButton) {
                    user.setSex(User.Sex.MALE);
                } else if (checkedId == R.id.femaleRadioButton) {
                    user.setSex(User.Sex.FEMALE);
                }
            }
        });

        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (darkModeSwitch.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
