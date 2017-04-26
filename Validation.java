package com.example.keval.roomonrent;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by dell on 4/19/2016.
 */
public class Validation {


    public static boolean validate(LinearLayout l, Context focus) {
        for (int i = 0; i < l.getChildCount(); i++) {
            View child = l.getChildAt(i);
            if (child instanceof EditText) {

                EditText temp = ((EditText) child);

                if (temp.getText().toString().isEmpty()) {
                    temp.setError("This field can't be left blank");
                    return false;
                }


            }

            if (child instanceof RadioGroup) {
                RadioGroup temp;
                temp = (RadioGroup) child;
                if (temp.getCheckedRadioButtonId() < 0) {
                // do something
                    Toast.makeText(focus, "Select one radio button", Toast.LENGTH_SHORT);
                    return false;
                }

            }

        }


        return true;
    }

    public static boolean isEmail(EditText email) {
        if (email.getText().toString().contains("@")) {
            return true;
        }
        email.setError("Include @ in your email");
        return false;
    }

    public static boolean validate(LinearLayout l, Context focus, boolean prompt) {
        for (int i = 0; i < l.getChildCount(); i++) {
            View child = l.getChildAt(i);
            if (child instanceof EditText) {

                EditText temp = ((EditText) child);

                if (temp.getText().toString().isEmpty()) {
                    if (prompt)
                        temp.setError("This field can't be left blank");
                    return false;
                }


            }

            if (child instanceof RadioGroup) {
                RadioGroup temp;
                temp = (RadioGroup) child;
                if (temp.getCheckedRadioButtonId() < 0) {
// do something
                    if (prompt)
                        Toast.makeText(focus, "Select one radio button", Toast.LENGTH_SHORT);
                    return false;
                }

            }

        }


        return true;
    }


}
