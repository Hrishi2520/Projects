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
        // Adding note to notes_list...
        notesList.addNote(c);
        notesList.addNote(java);
        notesList.addNote(python);
        notesList.addNote(ruby);
        notesList.addNote(golang);
        notesList.addNote(html);
        notesList.getNotes();

        while (flag) {
            printActions();
            switch (scanner.nextLine().toUpperCase()) {
                case "ADD" -> notesList.addNote(new Note(new String()));
                case "LIST NOTES" -> System.out.println(notesList);
                case "FIND NOTE" -> notesList.findNote(new Note(new String()));
                case "REMOVE NOTE" -> notesList.removeNote(new Note(new String()));
                case "QUITE" -> flag = false;
            }
            System.out.println(notesList);
        }
    }

    public static void printActions() {
        String textBlock = """
                Available Actions:
                A-> add Note
                B-> get Notes
                C-> find Note
                D-> remove Note
                Q-> Quit
                Enter the action you want to perform:""";
        System.out.print(textBlock + " ");
    }
}