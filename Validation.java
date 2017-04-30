package com.example.keval.mywatercanadmin;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by keval Choudhary on 4/19/2016.
 * For more visit www.creativek.me
 * https://github.com/creativo123
 */
public class Validation {

    public static boolean validate(LinearLayout l) {
        return validate(l, true);
    }

    public static boolean validate(EditText editText, boolean alert) {

        String text = editText.getText().toString();
        Pattern pattern = null;
        Matcher matcher = null;

        switch (editText.getInputType()) {
            case InputType.TYPE_NULL:
                return true;

            case InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS + 1:
            case InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS + 1:
                pattern = Pattern.compile("[A-Za-z0-9_]+@[A-Za-z0-9_]+\\.[A-Za-z].{1,6}");
                matcher = pattern.matcher(text);
                if (matcher.matches()) {
                    return true;
                } else {
                    if (alert)
                        editText.setError("Please enter a valid email address");
                    return false;
                }

            case InputType.TYPE_TEXT_VARIATION_PERSON_NAME + 1:
                pattern = Pattern.compile("[A-Za-z0-9]+\\s[A-Za-z0-9]+");
                matcher = pattern.matcher(text);
                if (matcher.matches()) {
                    return true;
                } else {
                    if (alert)
                        editText.setError("Please enter a valid name!");
                    return false;
                }

            case InputType.TYPE_CLASS_PHONE:
                pattern = Pattern.compile("[0-9].{9,13}");
                matcher = pattern.matcher(text);
                if (matcher.matches()) {
                    return true;
                } else {
                    if (alert)
                        editText.setError("Please enter a valid 10 digit mobile number!");
                    return false;
                }

            case InputType.TYPE_TEXT_VARIATION_PASSWORD + 1:
            case InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD + 1:
            case InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD + 1:
                if (editText.getTag() == null) {
                    pattern = Pattern.compile("(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[$_@#]).{6,20}");
                    matcher = pattern.matcher(text);
                    if (matcher.matches()) {
                        return true;
                    } else {
                        if (alert)
                            editText.setError("Please include capital and small alphabets, special symbol and a number in your password");
                        return false;
                    }
                } else {

                    try {
                        pattern = Pattern.compile(editText.getTag().toString());
                        matcher = pattern.matcher(text);
                        if (matcher.matches()) {
                            return true;
                        } else {
                            if (alert)
                                editText.setError("Please include alphabets, special symbol and a number in your password of minimum length of 6!");
                            return false;
                        }
                    } catch (PatternSyntaxException e) {
                        e.printStackTrace();
                    }
                }
            default:
                if (editText.getText().toString().isEmpty()) {
                    if (alert)
                        editText.setError("You can't leave this field empty!");
                    return false;
                } else {
                    return true;
                }
        }
    }

    public static boolean isEmail(EditText email) {

        Pattern pattern = Pattern.compile("[A-Za-z0-9]+@[A-za-z0-9]\\.[A-Za-z].{2,6}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email.getText().toString());
        if (matcher.matches()) {
            return true;
        }

        email.setError("Include @ in your email");
        return false;
    }

    public static boolean validate(LinearLayout l, boolean prompt) {
        for (int i = 0; i < l.getChildCount(); i++) {
            View child = l.getChildAt(i);
            if (child instanceof EditText) {
                EditText temp = ((EditText) child);
                if (!validate(temp, prompt)) {
                    return false;
                }
            }
        }
        return true;
    }
}
