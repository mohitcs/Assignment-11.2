package com.example.mohit.assignment_112;

import android.content.ContentValues;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohit.assignment_112.utils.CommonUtilities;
import com.example.mohit.assignment_112.utils.Constants;
import com.example.mohit.assignment_112.database.DBHelper;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    TextView lbliten;
    EditText txtproduct;
    String pname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper= CommonUtilities.getDBObject(this);

        lbliten= (TextView)findViewById(R.id.lbliten);
        txtproduct= (EditText) findViewById(R.id.txtproduct);

        int count= dbHelper.getFullCount(Constants.PRODUCT_TABLE, null);
        if(count==0) {
           lbliten.setText("Product Not Found");
        }
        else
        {
            lbliten.setText("Total Product in Store is : " + count);

        }

        //Creating the instance of ArrayAdapter containing list of product


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,
                        dbHelper.getValues(false,Constants.PRODUCT_TABLE,Constants.PRODUCT_NAME,
                                Constants.PRODUCT_NAME + " <> ''" , Constants.PRODUCT_NAME
                                ));



        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);

    }

    public void Savedata(View v){

        String typedText = txtproduct.getText().toString();
         Toast.makeText(getApplicationContext(), typedText, Toast.LENGTH_LONG).show();

        ContentValues vals = new ContentValues();
        vals.put(Constants.PRODUCT_NAME, typedText);
        dbHelper.insertContentVals(Constants.PRODUCT_TABLE, vals);


        int count= dbHelper.getFullCount(Constants.PRODUCT_TABLE, null);
        if(count==0) {
            lbliten.setText("Product Not Found");
        }
        else
        {
            lbliten.setText("Total Product in Store is : " + count);
        }


    }
}
