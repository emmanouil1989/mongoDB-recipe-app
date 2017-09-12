package com.manos.spring5recipeapp.converters;

import com.manos.spring5recipeapp.commands.NotesCommand;
import com.manos.spring5recipeapp.models.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNote implements Converter<NotesCommand,Notes> {
    @Nullable
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if (notesCommand== null){
            return null;
        }
        final Notes notes = new Notes();
        notes.setId(notesCommand.getId());
        notes.setRecipeNotes(notesCommand.getRecipeNotes());
        return notes;
    }
}
