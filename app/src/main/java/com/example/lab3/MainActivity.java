package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText etFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFunction = findViewById(R.id.input);
        etFunction.setShowSoftInputOnFocus(false);

        etFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(etFunction.getText().toString())){
                    etFunction.setText("");
                }
            }
        });

    }

    private void updateText(String strToAdd) {
        String oldStr = etFunction.getText().toString();
        int cursorPos = etFunction.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(etFunction.getText().toString())){
            etFunction.setText(strToAdd);
            etFunction.setSelection(cursorPos + 1);

        }
        else {
            etFunction.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            etFunction.setSelection(cursorPos + 1);
        }
    }

    public void zeroBTN (View view) {
        updateText("0");
    }

    public void oneBTN (View view) {
        updateText("1");
    }

    public void twoBTN (View view) {
        updateText("2");
    }

    public void threeBTN (View view) {
        updateText("3");
    }

    public void fourBTN (View view) {
        updateText("4");
    }

    public void fiveBTN (View view) {
        updateText("5");
    }

    public void sixBTN (View view) {
        updateText("6");
    }

    public void sevenBTN (View view) {
        updateText("7");
    }

    public void eightBTN (View view) {
        updateText("8");
    }

    public void nineBTN (View view) {
        updateText("9");
    }

    public void clearBTN (View view) {
        etFunction.setText("");
    }

    public void parenthesesBTN (View view) {
        int cursorPos = etFunction.getSelectionStart();
        int openParentheses = 0;
        int closeParentheses = 0;
        int textLen = etFunction.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (etFunction.getText().toString().substring(i, i + 1).equals("(")) {
                openParentheses += 1;
            }

            if (etFunction.getText().toString().substring(i, i + 1).equals(")")) {
                closeParentheses += 1;
            }
        }
            if (openParentheses == closeParentheses || etFunction.getText().toString().substring(textLen-1, textLen).equals("(")) {
                updateText("(");
            }

            else if (closeParentheses < openParentheses && !etFunction.getText().toString().substring(textLen-1, textLen).equals(")")) {
                updateText(")");
            }
            etFunction.setSelection(cursorPos + 1);
        }

    public void exponentBTN (View view) {
        updateText("^");
    }

    public void divideBTN (View view) {
        updateText("÷");
    }

    public void multiplyBTN (View view) {
        updateText("×");
    }

    public void subtractBTN (View view) {
        updateText("-");
    }

    public void addBTN (View view) {
        updateText("+");
    }

    public void equalsBTN (View view) {
        String userExp = etFunction.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        etFunction.setText(result);
        etFunction.setSelection(result.length());
    }

    public void pointBTN (View view) {
        updateText(".");
    }

    public void plusMinusBTN (View view) {
        Toast.makeText(this, "This operation is not active", Toast.LENGTH_SHORT).show();
    }

    public void backspaceBTN (View view) {
        int cursorPos = etFunction.getSelectionStart();
        int textLen = etFunction.getText().length();

        if(cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) etFunction.getText();
            selection.replace(cursorPos -1 , cursorPos,"");
            etFunction.setText(selection);
            etFunction.setSelection(cursorPos - 1);
        }
    }
}