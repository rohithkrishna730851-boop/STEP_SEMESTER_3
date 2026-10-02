import java.time.*;
import java.util.*;

abstract class LibraryItem {

    LibraryItem(String title) {
    }

    abstract int getDays();
}

class Book extends LibraryItem {
    Book(String title) {
        super(title);
    }

    @Override
    int getDays() {
        return 14;
    }
}

class DVD extends LibraryItem {
    DVD(String title) {
        super(title);
    }

    @Override
    int getDays() {
        return 7;
    }
}

class Magazine extends LibraryItem {
    Magazine(String title) {
        super(title);
    }

    @Override
    int getDays() {
        return 3;
    }
}

public class Library {
    @SuppressWarnings("ConvertToStringSwitch")
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            LocalDate currentDate = LocalDate.of(2023, 10, 26);

            int n = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();

                String[] parts = line.split(" ", 2);
                String type = parts[0];
                String title = parts[1].replace("\"", "");

                LibraryItem item;

                if (type.equals("BOOK"))
                    item = new Book(title);
                else if (type.equals("DVD"))
                    item = new DVD(title);
                else
                    item = new Magazine(title);

                LocalDate dueDate =
                    currentDate.plusDays(item.getDays());

                System.out.println(title + ": " + dueDate);
            }
        }
    }
}