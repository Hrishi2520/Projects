package test;

import java.util.ArrayList;
import java.util.stream.Stream;

public class NotesList {
    ArrayList<Note> notes = new ArrayList<>();

    public void getNotes() {
        Stream.of(notes).forEach(System.out::println);
    }

    public void addNote(Note note) {

        if (!findNote(note))
            notes.add(note);
    }

    public void removeNote(Note note) {
        if (findNote(note))
            notes.remove(note);
    }



    public boolean findNote(Note note) {
        boolean isPresent = false;
        for (Note note1 : notes) {
            isPresent = note1.getTitle().equals(note.getTitle());
        }
        return isPresent;
    }
}
