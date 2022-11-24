package com.bmt.lab2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bmt.lab2.pattern.SharedPreferencesEnum;
import com.bmt.lab2.util.DataUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private RecyclerView notes;
    private NoteRecyclerAdapter noteRecyclerAdapter;

    private FloatingActionButton addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes = findViewById(R.id.notes);

        noteRecyclerAdapter = new NoteRecyclerAdapter((noteEntity,position) ->{
            Intent intent = new Intent(this,CreateUpdateNotice.class);
            intent.putExtra("UPDATE",noteEntity);
            intent.putExtra("POSITION",position);
            startActivity(intent);
        });
        DataUtil<NoteEntity> dataUtil = new DataUtil<>();
        List<NoteEntity> notice = dataUtil.loadNotes(SharedPreferencesEnum.KEY.getValue(),SharedPreferencesEnum.SHAREDPREFERENCESNAME.getValue(),this);

        noteRecyclerAdapter.setData(notice);
        notes.setAdapter(noteRecyclerAdapter);
        notes.setLayoutManager(new LinearLayoutManager(this));

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(v->{
            Intent intent = new Intent(this,CreateUpdateNotice.class);
            startActivity(intent);
        });
    }


}