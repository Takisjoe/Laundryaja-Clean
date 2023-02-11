package com.takisjoeapp.laundryaja.database.room;

import androidx.room.TypeConverter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Note;

import java.lang.reflect.Type;
import java.util.List;

public class NoteTypeConverter {
    @TypeConverter
    public String fromNoteList(List<Note> notes) {
        Gson gson = new Gson();
        String json = gson.toJson(notes);
        return json;
    }

    @TypeConverter
    public List<Note> toNoteList(String notesString) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Note>>() {}.getType();
        List<Note> notesList = gson.fromJson(notesString, type);
        return notesList;
    }
}

