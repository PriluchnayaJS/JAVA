package com.example.drawshapes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  //      MyView myView = new MyView(getApplicationContext());
  //      setContentView(myView);

        MyView myView = findViewById(R.id.myView);
        RadioGroup shapesGroup = findViewById(R.id.shapesGroup);
        //спиннер
        AdapterView spinnerColors = findViewById(R.id.spinnerColors);

        class Listener implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener  {

            @Override
            public void onClick(View v) {
               // Log.i("onCLick", "вызван метод onClick в Listener");
                myView.undo();
            }

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               // Log.i("changeCheked",checkedId + "");
                switch (checkedId) {
                    case R.id.rectBtn: myView.setTypeShape(MyView.TYPE_RECT); break;
                    case R.id.circleBtn: myView.setTypeShape(MyView.TYPE_CIRCLE); break;
                    case R.id.triangleBtn: myView.setTypeShape(MyView.TYPE_TRAINGLE); break;
                }
            }


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] color = getResources().getStringArray(R.array.colors);
                //работа с элементами массива
                for (int i = 0; i < color.length ; i++) {
                    //преобразовать в код цвета
                   // color[i] = String.valueOf(Integer.parseInt(color[i], 16));

                }
            }

//            public void OnItemSelected(AdapterView parent) {
//
//            }
//
//            public void OnNothingSelected(AdapterView parent) {
//
//            }

        }


        // AdapterView.OnItemClickListener //спиннер два метода OnItemSelected OnNothingSelected
        //position  в массиве

        // String[] color = getResources().getStringArray(R.array.colors)
        // во View создать метод setColor();

        Listener listener = new Listener();

        Button undoBtn = findViewById(R.id.undoBtn);
        undoBtn.setOnClickListener(listener);
        // RadioGroup.OnCheckedChangeListener
        shapesGroup.setOnCheckedChangeListener(listener);

        spinnerColors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public void onClick(View v) {
     //   Log.i("onClick", "onClick");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
     //
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // String[] color = getResources().getStringArray(R.array.colors);
    }
}