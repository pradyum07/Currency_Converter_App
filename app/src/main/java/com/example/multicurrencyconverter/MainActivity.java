package com.example.multicurrencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText myeditText;
    private TextView mytext;
    private Spinner from,to;
    private String []fromlist = {"SELECT","INR","USD","EUR"};
    private String []inr = {"USD","EUR"};
    private String []usd = {"INR","EUR"};
    private String []eur = {"INR","USD"};
    private ArrayAdapter<String> toadapter;
    private String []dft = {"SELECT"};
    private Double amount;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        from = findViewById(R.id.spinfrom);
        to = findViewById(R.id.spinto);
        ArrayAdapter<String> fromadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,fromlist);
        from.setAdapter(fromadapter);

        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i) {
                    case 0:
                        toadapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, dft);
                        break;
                    case 1:
                        toadapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, inr);
                        break;
                    case 2:
                        toadapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, usd);
                        break;
                    case 3:
                        toadapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, eur);
                        break;
                    default:
                        toadapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, dft);
                }
                to.setAdapter(toadapter);
        }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                toadapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,dft);
                to.setAdapter(toadapter);
            }
        });

    }
    public void convert(View view){
        myeditText = findViewById(R.id.edittxt);
        mytext = findViewById(R.id.txt);
        try {
            amount = Double.parseDouble(myeditText.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
            result = "Please provide valid Inputs";
            amount = 0.0;
        }
        if(from.getSelectedItemPosition() == 1)
        {
            if(to.getSelectedItemPosition() == 0)
            {
                result = String.format("%.2f",amount*0.013);
            }
            else if(to.getSelectedItemPosition() == 1)
            {
                result = String.format("%.2f",amount*0.012);
            }
            else
                result = "Please provide valid Inputs";
        }

        else if(from.getSelectedItemPosition() == 2)
        {
            if(to.getSelectedItemPosition() == 0)
            {
                result = String.format("%.2f",amount*75.11);
            }
            else if(to.getSelectedItemPosition() == 1)
            {
                result = String.format("%.2f",amount*0.88);
            }
            else
                result = "Please provide valid Inputs";
        }

        else if(from.getSelectedItemPosition() == 3)
        {
            if(to.getSelectedItemPosition() == 0)
            {
                result = String.format("%.2f",amount*85.74);
            }
            else if(to.getSelectedItemPosition() == 1)
            {
                result = String.format("%.2f",amount*1.14);
            }
            else
                result = "Please provide valid Inputs";
        }

        else
            result = "Please provide valid Inputs";
        mytext.setText(result);
        hideSoftKeyboard(view);

    }
    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}