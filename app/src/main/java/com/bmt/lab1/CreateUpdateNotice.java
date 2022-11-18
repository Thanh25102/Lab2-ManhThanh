package com.bmt.lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bmt.lab1.pattern.SharedPreferencesEnum;
import com.bmt.lab1.util.DataUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateUpdateNotice extends AppCompatActivity {

    private ImageView check;

    private EditText titleTxt;
    private EditText contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_update_notice);

        titleTxt = findViewById(R.id.titleTxt);
        contentTxt = findViewById(R.id.contentTxt);
        NoteEntity noteEntityUpdate = (NoteEntity)getIntent().getSerializableExtra("UPDATE");
        int position = getIntent().getIntExtra("POSITION",-1);
        if(noteEntityUpdate !=null && position != -1){
            titleTxt.setText(noteEntityUpdate.getTitle());
            contentTxt.setText(noteEntityUpdate.getContent());
        }
        check = findViewById(R.id.check);
        check.setOnClickListener(v-> {
            DataUtil<NoteEntity> dataUtil = new DataUtil<>();
            List<NoteEntity> notes = dataUtil.loadNotes(SharedPreferencesEnum.KEY.getValue(),SharedPreferencesEnum.SHAREDPREFERENCESNAME.getValue(),this);
            if(notes == null)
                notes = new ArrayList<>();
            NoteEntity noteEntity = new NoteEntity(titleTxt.getText().toString(),contentTxt.getText().toString(),new Date());
            if(noteEntityUpdate !=null && position != -1){
                notes.set(position,noteEntity);
            }else {
                notes.add(noteEntity);
            }
            dataUtil.saveData(SharedPreferencesEnum.KEY.getValue(),SharedPreferencesEnum.SHAREDPREFERENCESNAME.getValue(),notes,this);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
    }

}