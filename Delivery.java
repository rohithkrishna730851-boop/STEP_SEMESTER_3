import java.util.Scanner;

abstract class DeliveryBase {
    double weight;
    double distance;

    DeliveryBase(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    abstract double calculateFee();
}

class Standard extends DeliveryBase {

    Standard(double weight, double distance) {
        super(weight, distance);
    }

    @Override
    double calculateFee() {
        return 5 + (0.50 * weight) + (0.10 * distance);
    }
}

class Express extends DeliveryBase {

    Express(double weight, double distance) {
        super(weight, distance);
    }

    @Override
    double calculateFee() {
        return 15 + weight + (0.20 * distance);
    }
}

class International extends DeliveryBase {

    double customsFee;

    International(double weight, double distance, double customsFee) {
        super(weight, distance);
        this.customsFee = customsFee;
    }

    @SuppressWarnings("override")
    double calculateFee() {
        return 25 + (2.00 * weight) + (0.50 * distance) + customsFee;
    }
}

public class Delivery {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            double total = 0;
            
            for (int i = 0; i < n; i++) {
                
                String type = sc.next();
                double weight = sc.nextDouble();
                double distance = sc.nextDouble();
                
                DeliveryBase delivery;
                
                switch (type) {
                    case "STANDARD" -> delivery = new Standard(weight, distance);
                    case "EXPRESS" -> delivery = new Express(weight, distance);
                    default -> {
                        double customsFee = sc.nextDouble();
                        delivery = new International(weight, distance, customsFee);
                    }
                }
                
                double fee = delivery.calculateFee();
                
                System.out.printf("%s: %.2f%n", type, fee);
                
                total += fee;
            }
            
            System.out.printf("Total: %.2f%n", total);
        }
    }
}