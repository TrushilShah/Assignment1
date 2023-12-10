package ThirdQuestion;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Order {
    private int cargoWeight;
    private String destination;
    private boolean canceled;

    public Order(int cargoWeight, String destination) {
        this.cargoWeight = cargoWeight;
        this.destination = destination;
        this.canceled = false;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void cancelOrder() {
        this.canceled = true;
    }
}

class Ship {
    private int totalTrips;
    private int cargo;
    private boolean inMaintenance;

    public Ship() {
        this.totalTrips = 0;
        this.cargo = 0;
        this.inMaintenance = false;
    }

    public int getTotalTrips() {
        return totalTrips;
    }

    public int getCargo() {
        return cargo;
    }

    public boolean isInMaintenance() {
        return inMaintenance;
    }

    public void makeTrip(int cargoWeight, String destination) {
        // Simulate a trip
        cargo += cargoWeight;
        totalTrips++;

        if (destination.equals("Gotham")) {
            System.out.println("Ship arrived at Gotham with cargo: " + cargoWeight);
        } else {
            System.out.println("Ship arrived at Atlanta with cargo: " + cargoWeight);
        }

        // Simulate picking up return cargo
        cargo += cargoWeight;

        if (totalTrips % 5 == 0) {
            // Send the ship to maintenance after every 5 trips
            inMaintenance = true;
            System.out.println("Ship is in maintenance for 1 minute.");
            try {
                Thread.sleep(60000); // 1 minute in milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inMaintenance = false;
        }
    }
}

class Customer implements Runnable {
    private BlockingQueue<Order> orderQueue;
    private Random random = new Random();

    public Customer(BlockingQueue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int cargoWeight = random.nextInt(41) + 10; // Random cargo weight between 10 and 50
                String destination = (random.nextBoolean()) ? "Gotham" : "Atlanta";

                Order order = new Order(cargoWeight, destination);
                orderQueue.put(order);

                System.out.println("Customer placed an order to " + destination + " with cargo: " + cargoWeight);

                // Simulate a short wait time before placing the next order
                Thread.sleep(random.nextInt(500) + 500); // Random wait time between 500 and 1000 milliseconds
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Shipping implements Runnable {
    private BlockingQueue<Order> orderQueue;
    private Ship ship;
    private Random random = new Random();

    public Shipping(BlockingQueue<Order> orderQueue, Ship ship) {
        this.orderQueue = orderQueue;
        this.ship = ship;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = orderQueue.take();

                if (!order.isCanceled()) {
                    // Process the order
                    ship.makeTrip(order.getCargoWeight(), order.getDestination());
                } else {
                    System.out.println("Order canceled. Ship set back by $250.");
                }

                // Check if the ship has reached $1 million
                if (ship.getTotalTrips() * 1000 >= 1000000) {
                    System.out.println("Simulation completed. Total orders delivered: " + ship.getTotalTrips());
                    System.exit(0);
                }

                // Simulate a short wait time before processing the next order
                Thread.sleep(random.nextInt(500) + 500); // Random wait time between 500 and 1000 milliseconds
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class WayneEnterpriseSimulation {
    public static void main(String[] args) {
        BlockingQueue<Order> orderQueue = new ArrayBlockingQueue<>(10);
        Ship ship = new Ship();

        // Create and start 7 customer threads
        for (int i = 0; i < 7; i++) {
            Thread customerThread = new Thread(new Customer(orderQueue));
            customerThread.start();
        }

        // Create and start 5 shipping threads
        for (int i = 0; i < 5; i++) {
            Thread shippingThread = new Thread(new Shipping(orderQueue, ship));
            shippingThread.start();
        }
    }
}
