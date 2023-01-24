import java.util.*;

public class Main {
    public static void main(String[] args) {
        Note note1 = new Note("hello");
        note1.setDescription("hello guys");
//
        Note java = new Note("Java");
        java.setDescription("i'm the father of all the languages");

        Note python = new Note("Python");
        java.setDescription("i'm the mother of all the languages");

        Note c = new Note("C");
        java.setDescription("i'm the best of all the languages");

        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note1);
        notes.add(java);
        notes.add(python);
        notes.add(c);
//        System.out.println(notes);
//
//        findNote(notes, java);
//        findNote(notes, python);
//
//        getNote(notes, note1);
//
//        removeNote(notes, note1);
//        System.out.println(notes);
//
////        getNote(notes,java);

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the action number");
        int input = scanner.nextInt();
        boolean flag = false;
        while(!flag) {
            getActions();
            switch (input) {
                case 1 -> addNote(notes, note1);
                case 2 -> getNote(notes, java);
                case 3 -> findNote(notes, python);
                case 4 -> removeNote(notes, java);
                default -> flag = true;
            }
        }

    }
//    private static void

    private static void getActions(){
        System.out.println("""
                Available Actions (select word):
                1-> addNote
                2-> getNote
                3-> findNote
                4-> removeNote
                0-> Quit""");
    }

    private static void getNote(ArrayList<Note> notes, Note note) {
        boolean isPresent = findNote(notes, note);
        if (isPresent) {
            System.out.println(note);
        } else {
            System.out.printf("%s is not present in list", note.getTitle());
        }
    }
    private static boolean findNote(ArrayList<Note> list, Note note) {
        boolean isPresent = false;
        for (Note n : list) {
            if (n.getTitle().equals(note.getTitle())) {
                isPresent = true;
                System.out.printf("%s is present in list\n", note.getTitle());
            }
        }
        if (isPresent == false)
            System.out.printf("%s is not present in list\n", note.getTitle());
        return  isPresent;
    }

    private static void addNote(ArrayList<Note> list,Note note) {
        boolean isPresent = findNote(list, note);
        if (!isPresent) {
            list.add(note);
            System.out.printf("%s is added in list\n", note.getTitle());
        }
        System.out.printf("%s is already present in list\n", note.getTitle());
    }

    private static void removeNote(ArrayList<Note> list, Note note) {
        boolean isPresent = findNote(list, note);
        if (isPresent) {
            list.remove(note);
            System.out.printf("%s is removed from list\n", note.getTitle());
        } else {
            System.out.printf("%s is not found in list\n", note.getTitle());
        }
    }


}