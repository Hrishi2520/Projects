package test;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flag = true;
//        ArrayList<Note> notes = new ArrayList<>();
//
        Note c = new Note("C");
        Note java = new Note("Java");
        Note python = new Note("Python");
        Note ruby = new Note("Ruby");
        Note golang = new Note("GoLang");
        Note html = new Note("HTML");

        NotesList notesList = new NotesList();
// Added note to notes...
        notesList.addNote(c);
        notesList.addNote(java);
        notesList.addNote(python);
        notesList.addNote(ruby);
        notesList.addNote(golang);
        notesList.addNote(html);
        while(flag) {
            printActions();
            switch (scanner.nextLine().toUpperCase()) {
                case "A" -> notesList.addNote(new Note(scanner.nextLine()));
                case "B" -> notesList.getNotes();
                case "C" -> notesList.findNote(new Note(scanner.nextLine()));
                case "D" -> notesList.removeNote(new Note(scanner.nextLine()));
                case "Q" -> flag = false;
//                default -> System.out.println("pls Enter Action from [A,B,C,D,Q]");
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
        System.out.print(textBlock+" ");
    }
}