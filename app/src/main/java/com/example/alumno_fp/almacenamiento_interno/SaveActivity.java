package com.example.alumno_fp.almacenamiento_interno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SaveActivity extends AppCompatActivity {

    EditText textPlace;
    Button buttonSave;
    private final int CODE_SAVE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        initUI();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place = textPlace.getText().toString().trim();
                if (validate(place)){
                    Intent intent = new Intent();
                    intent.putExtra("Place",place);
                    setResult(CODE_SAVE,intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"¡Campo obligatorio!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initUI(){
        textPlace = findViewById(R.id.text_place);
        buttonSave = findViewById(R.id.button_save);
    }

    private boolean validate(String place){
        boolean isValid = true;
        if (place.isEmpty()){
            isValid = false;
        }

        return isValid;
    }
}
