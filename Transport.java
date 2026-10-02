import java.util.Scanner;

abstract class TransportBase {
    double distance;

    TransportBase(double distance) {
        this.distance = distance;
    }

    abstract double calculateFare();
}

class Bus extends TransportBase {

    Bus(double distance) {
        super(distance);
    }

    @Override
    double calculateFare() {
        return Math.min(2 + (0.10 * distance), 10);
    }
}

class Train extends TransportBase {

    Train(double distance) {
        super(distance);
    }

    @Override
    double calculateFare() {
        return 3 + (0.15 * distance);
    }
}

class Metro extends TransportBase {

    double peakHourFactor;

    Metro(double distance, double peakHourFactor) {
        super(distance);
        this.peakHourFactor = peakHourFactor;
    }

    @Override
    double calculateFare() {
        return (1.50 + (0.20 * distance)) * peakHourFactor;
    }
}

public class Transport {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            double total = 0;
            
            for (int i = 0; i < n; i++) {
                
                String type = sc.next();
                double distance = sc.nextDouble();
                
                TransportBase transport;
                
                switch (type) {
                    case "BUS" -> transport = new Bus(distance);
                    case "TRAIN" -> transport = new Train(distance);
                    default -> {
                        double factor = sc.nextDouble();
                        transport = new Metro(distance, factor);
                    }
                }
                
                double fare = transport.calculateFare();
                
                System.out.printf("%s: %.2f%n", type, fare);
                
                total += fare;
            }
            
            System.out.printf("Total: %.2f%n", total);
        }
    }
}