package com.manos.spring5recipeapp.converters;

import com.manos.spring5recipeapp.commands.NotesCommand;
import com.manos.spring5recipeapp.models.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes,NotesCommand> {
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if(notes == null){
            return null;
        }

        final NotesCommand notesCommand= new NotesCommand();
        notesCommand.setId(notes.getId());
        notesCommand.setRecipeNotes(notes.getRecipeNotes());
        return notesCommand;
    }
}
