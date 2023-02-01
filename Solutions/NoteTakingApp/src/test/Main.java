package test;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean flag = true;

        Note c = new Note("C");
        Note java = new Note("Java");
        Note python = new Note("Python");
        Note ruby = new Note("Ruby");
        Note golang = new Note("GoLang");
        Note html = new Note("HTML");

        NotesList notesList = new NotesList();
        // Adding note to noteslist...
        notesList.addNote(c);
        notesList.addNote(java);
        notesList.addNote(python);
        notesList.addNote(ruby);
        notesList.addNote(golang);
        notesList.addNote(html);
        while (flag) {
            printActions();
            switch (scanner.nextLine().toUpperCase()) {
                case "ADD" -> notesList.addNote(new Note());
                case "LIST_NOTES" -> notesList.getNotes();
                case "FIND_NOTE" -> notesList.findNote(new Note());
                case "REMOVE_NOTE" -> notesList.removeNote(new Note());
                case "QUITE" -> flag = false;
            }
            System.out.println(notesList);
        }
    }

    public static void printActions() {
        String textBlock = """
                Available Actions:
                A-> addNote
                B-> getNotes
                C-> findNote
                D-> removeNote
                Q-> Quit
                Enter the action you want to perform:""";
        System.out.print(textBlock + " ");
    }
}