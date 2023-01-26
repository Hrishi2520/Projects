package test;

import newTry.Note;

import java.util.ArrayList;
import java.util.stream.Stream;

public class NotesList {
    ArrayList<Note> notes = new ArrayList<>();

//    public NotesList(ArrayList<Note> notes) {
//        this.notes = notes;
//    }

    public void getNotes() {
        Stream.of(notes).forEach(System.out::println);
    }

    public void addNote(Note note) {
        if (findNote(note)) {
            System.out.printf("\n%s is Already present\n", note.getTitle());
        } else {
            notes.add(note);
            System.out.printf("\n%s note is added\n", note.getTitle());
        }
        System.out.println("After adding " + note.getTitle() + ": \n"+notes);
    }

    public void removeNote(Note note) {
        if (findNote(note)) {
            notes.remove(note);
            System.out.printf("\n%s is Already present\n", note.getTitle());
        }
        System.out.println("after removing "+note.getTitle() +": \n"+notes);
    }



    public boolean findNote(Note note) {
        boolean isPresent = false;
        for (Note note1 : notes) {
            if (note1.getTitle().equals(note.getTitle())) {
                isPresent = true;
                System.out.printf("\n%s is found in list\n", note.getTitle());
            } else {
                isPresent = false;
                System.out.printf("\n%s is absent in list\n", note.getTitle());
            }
        }
        return isPresent;
    }
}
