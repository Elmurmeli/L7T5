package com.example.l7t1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;

    TextView text;
    EditText textInput, textInput2;
    String textString, textString2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        System.out.println("Kansion sijainti" + context.getFilesDir());

        Button printButton = (Button) findViewById(R.id.button4);
        //this.hello();
        text = (TextView) findViewById(R.id.textView);
        textInput = (EditText) findViewById(R.id.input);
        textInput2 = (EditText) findViewById(R.id.input2);
        textString = textInput.getText().toString();
        textString2 = textInput2.getText().toString();

        textInput2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textString2 = textInput2.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textString = textInput.getText().toString();
                text.setText(textString);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        printButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                System.out.println("HELLO WORLD!");

            }
        });
    }

    public void readFile(View v){
        try {
            InputStream ins = context.openFileInput(textString2);


            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";


            while ((s=br.readLine()) != null){
                text.setText(s);
                textInput.setText(s);
            }
            ins.close();
        } catch (IOException e) {
                Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("LUETTU");
        }
    }

    public void writeFile(View v){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("testi.txt", Context.MODE_PRIVATE));

            String s = "";

            s = textString;
            osw.write(s);
            osw.close();
        } catch (IOException e) {
            Log.e("IOExceptioon", "Virhe syötteessä");
        } finally {
            System.out.println("KIRJOITETTU");
        }
    }


}