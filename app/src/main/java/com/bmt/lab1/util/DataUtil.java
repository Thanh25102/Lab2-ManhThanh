package com.bmt.lab1.util;

import android.content.*;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import com.bmt.lab1.NoteEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataUtil<T> {
    public void saveData(String key, String sharedPreferencesName, List<T> list, Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(sharedPreferencesName,Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key,json);
        editor.apply();
    }

    public List<T> loadNotes(String key, String sharedPreferencesName, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);
        Type type = new TypeToken<List<NoteEntity>>() {}.getType();
        List<T> notes = (json == null) ? null : gson.fromJson(json,type);
        if(notes == null) return new ArrayList<T>(); else return notes;
    }

    public void removeNote(Integer position,String key, String sharedPreferencesName, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);
        Type type = new TypeToken<List<NoteEntity>>() {}.getType();
        List<T> notes = (json == null) ? null : gson.fromJson(json,type);
        notes.remove(notes.get(position));

        saveData(key,sharedPreferencesName,notes,context);
    }
}
