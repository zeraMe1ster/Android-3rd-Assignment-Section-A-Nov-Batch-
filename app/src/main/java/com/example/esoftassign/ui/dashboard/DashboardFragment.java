package com.example.esoftassign.ui.dashboard;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.esoftassign.DashboardActivity;
import com.example.esoftassign.R;
import com.example.esoftassign.model.Student;

public class DashboardFragment extends Fragment implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{

    private DashboardViewModel mViewModel;
    private String fullname,gender,address,age;
    private EditText editTextFullName,editTextAddress,editTextAge;
    private RadioGroup radioG;
    private Button btnsubmit;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.add_student, container, false);

        editTextFullName = root.findViewById(R.id.fullname);
        editTextAddress = root.findViewById(R.id.address);
        editTextAge = root.findViewById(R.id.age);
        radioG=root.findViewById(R.id.gender);
        btnsubmit=root.findViewById(R.id.btn_save);

        radioG.setOnCheckedChangeListener(this);
        btnsubmit.setOnClickListener(this);

        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.btn_save)
        {

            Toast.makeText(getActivity(), "We have clicked", Toast.LENGTH_SHORT).show();
            fullname = editTextFullName.getText().toString();
            age=editTextAge.getText().toString();
            address=editTextAddress.getText().toString();
            if(validate())
            {
                DashboardActivity.st.add(new Student(fullname,gender,age,address));
                Toast.makeText(getContext(),"Student added", Toast.LENGTH_SHORT).show();


            }



        }

    }

    private boolean validate(){
        if(TextUtils.isEmpty(fullname))
        {
            editTextFullName.setError("Please enter a name");
            editTextFullName.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(age))
        {
            editTextAge.setError("Please enter age");
            editTextAge.requestFocus();
            return false;
        }

        if(!TextUtils.isDigitsOnly(age))
        {
            editTextAge.setError("Please enter age");
            editTextAge.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(address))
        {
            editTextAddress.setError("Please enter an address");
            editTextAddress.requestFocus();
            return false;
        }



        if(TextUtils.isEmpty(gender))
        {
            Toast.makeText(getContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i== R.id.male_radio_btn)
        {
            gender="Male";
            //Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
        }
        if(i == R.id.female_radio_btn)
        {
            gender = "Female";
            //Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show();
        }
        if(i== R.id.other_radio_btn)
        {
            gender ="Other";
            //Toast.makeText(this, "Other", Toast.LENGTH_SHORT).show();
        }
    }
}