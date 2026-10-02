import java.util.*;

abstract class Question {
    String correct, student;
    double points;

    Question(String correct, String student, double points) {
        this.correct = correct;
        this.student = student;
        this.points = points;
    }

    abstract double grade();
}

class MCQ extends Question {
    MCQ(String c, String s, double p) {
        super(c, s, p);
    }

    @Override
    double grade() {
        return correct.equals(student) ? points : 0;
    }
}

class TF extends Question {
    TF(String c, String s, double p) {
        super(c, s, p);
    }

    @Override
    double grade() {
        return correct.equals(student) ? points : 0;
    }
}

class Essay extends Question {
    Essay(String c, String s, double p) {
        super(c, s, p);
    }

    @Override
    double grade() {
        String[] keywords = correct.split(",");
        String answer = student.toLowerCase();

        int count = 0;

        for (String key : keywords) {
            if (answer.contains(key.trim().toLowerCase())) {
                count++;
            }
        }

        if (count >= 2)
            return points * 0.75;
        else if (count == 1)
            return points * 0.50;
        else
            return 0;
    }
}

public class Grade {

    @SuppressWarnings("CollectionsToArray")
    static String[] extractQuotes(String line) {
        ArrayList<String> list = new ArrayList<>();

        boolean inside = false;
        StringBuilder temp = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                if (inside) {
                    list.add(temp.toString());
                    temp.setLength(0);
                }
                inside = !inside;
            }
            else if (inside) {
                temp.append(c);
            }
        }

        return list.toArray(new String[0]);
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        double total = 0;

        for (int i = 0; i < n; i++) {

            String line = sc.nextLine();

            String type = line.substring(0, line.indexOf(" "));

            String[] data = extractQuotes(line);

            double points =
                Double.parseDouble(
                    line.substring(line.lastIndexOf(" ") + 1)
                );

            Question q;

            q = switch (type) {
                case "MCQ" -> new MCQ(data[1], data[2], points);
                case "TF" -> new TF(data[1], data[2], points);
                default -> new Essay(data[1], data[2], points);
            };

            double score = q.grade();

            System.out.printf("%s: %.2f%n", type, score);

            total += score;
        }

        System.out.printf("Total Score: %.2f%n", total);
    }
}