import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class for the Vehicle Rental Management System.
 * This program demonstrates the use of interfaces to enforce common behavior
 * across different types of vehicles in a rental agency context.
 */
public class Main {
    public static void main(String[] args) {
        VehicleRentalSystem system = new VehicleRentalSystem();
        system.start();
    }
}

/**
 * Common interface for all vehicle types
 * Defines the basic properties that all vehicles should have
 */
interface Vehicle {
    /**
     * Gets the make (manufacturer) of the vehicle
     * @return the vehicle's make as a String
     */
    String getMake();

    /**
     * Gets the model of the vehicle
     * @return the vehicle's model as a String
     */
    String getModel();

    /**
     * Gets the manufacturing year of the vehicle
     * @return the vehicle's year as an int
     */
    int getYear();

    /**
     * Sets the make (manufacturer) of the vehicle
     * @param make the vehicle's make to set
     */
    void setMake(String make);

    /**
     * Sets the model of the vehicle
     * @param model the vehicle's model to set
     */
    void setModel(String model);

    /**
     * Sets the manufacturing year of the vehicle
     * @param year the vehicle's year to set
     */
    void setYear(int year);

    /**
     * Display all details about the vehicle
     */
    void displayDetails();
}

/**
 * Interface specifically for cars, extends basic vehicle functionality
 */
interface CarVehicle {
    /**
     * Gets the number of doors in the car
     * @return number of doors as an int
     */
    int getNumDoors();

    /**
     * Gets the type of fuel the car uses
     * @return fuel type as a String
     */
    String getFuelType();

    /**
     * Sets the number of doors in the car
     * @param numDoors number of doors to set
     */
    void setNumDoors(int numDoors);

    /**
     * Sets the fuel type of the car
     * @param fuelType the fuel type to set
     */
    void setFuelType(String fuelType);
}

/**
 * Interface specifically for motorcycles, extends basic vehicle functionality
 */
interface MotorVehicle {
    /**
     * Gets the number of wheels on the motorcycle
     * @return number of wheels as an int
     */
    int getNumWheels();

    /**
     * Gets the type of the motorcycle
     * @return motorcycle type as a String
     */
    String getMotorcycleType();

    /**
     * Sets the number of wheels on the motorcycle
     * @param numWheels number of wheels to set
     */
    void setNumWheels(int numWheels);

    /**
     * Sets the type of the motorcycle
     * @param motorcycleType the motorcycle type to set
     */
    void setMotorcycleType(String motorcycleType);
}

/**
 * Interface specifically for trucks, extends basic vehicle functionality
 */
interface TruckVehicle {
    /**
     * Gets the cargo capacity of the truck
     * @return cargo capacity in tons as a double
     */
    double getCargoCapacity();

    /**
     * Gets the transmission type of the truck
     * @return transmission type as a String
     */
    String getTransmissionType();

    /**
     * Sets the cargo capacity of the truck
     * @param cargoCapacity cargo capacity in tons to set
     */
    void setCargoCapacity(double cargoCapacity);

    /**
     * Sets the transmission type of the truck
     * @param transmissionType the transmission type to set
     */
    void setTransmissionType(String transmissionType);
}

/**
 * Car class that implements the Vehicle and CarVehicle interfaces
 */
class Car implements Vehicle, CarVehicle {
    private String make;
    private String model;
    private int year;
    private int numDoors;
    private String fuelType;

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int getNumDoors() {
        return numDoors;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }

    @Override
    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public void displayDetails() {
        System.out.println("\n--- Car Details ---");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Number of doors: " + numDoors);
        System.out.println("Fuel Type: " + fuelType);
    }
}

/**
 * Motorcycle class that implements the Vehicle and MotorVehicle interfaces
 */
class Motorcycle implements Vehicle, MotorVehicle {
    private String make;
    private String model;
    private int year;
    private int numWheels;
    private String motorcycleType;

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int getNumWheels() {
        return numWheels;
    }

    @Override
    public String getMotorcycleType() {
        return motorcycleType;
    }

    @Override
    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    @Override
    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }

    @Override
    public void displayDetails() {
        System.out.println("\n--- Motorcycle Details ---");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Number of wheels: " + numWheels);
        System.out.println("Motorcycle Type: " + motorcycleType);
    }
}

/**
 * Truck class that implements the Vehicle and TruckVehicle interfaces
 */
class Truck implements Vehicle, TruckVehicle {
    private String make;
    private String model;
    private int year;
    private double cargoCapacity;
    private String transmissionType;

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public String getTransmissionType() {
        return transmissionType;
    }

    @Override
    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    @Override
    public void displayDetails() {
        System.out.println("\n--- Truck Details ---");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Cargo Capacity: " + cargoCapacity + " tons");
        System.out.println("Transmission Type: " + transmissionType);
    }
}

/**
 * Main system class that manages the user interface and vehicle operations
 */
class VehicleRentalSystem {
    private Scanner scanner;
    private ArrayList<Vehicle> vehicles;

    /**
     * Constructor initializes the system
     */
    public VehicleRentalSystem() {
        scanner = new Scanner(System.in);
        vehicles = new ArrayList<>();
    }

    /**
     * Starts the rental system application
     */
    public void start() {
        System.out.println("===== Welcome to the Vehicle Rental Management System =====");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    addMotorcycle();
                    break;
                case 3:
                    addTruck();
                    break;
                case 4:
                    displayAllVehicles();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using the Vehicle Rental Management System!");
                    break;
            }
        }
        scanner.close();
    }

    /**
     * Displays the main menu options
     */
    private void displayMenu() {
        System.out.println("\n----- Menu Options -----");
        System.out.println("1. Add a Car");
        System.out.println("2. Add a Motorcycle");
        System.out.println("3. Add a Truck");
        System.out.println("4. Display All Vehicles");
        System.out.println("5. Exit");
    }

    /**
     * Gets common vehicle information from user input
     * @param vehicle the vehicle to set the information for
     */
    private void getCommonVehicleInfo(Vehicle vehicle) {
        System.out.println("\nEnter vehicle information:");
        vehicle.setMake(getStringInput("Make: "));
        vehicle.setModel(getStringInput("Model: "));
        int currentYear = java.time.Year.now().getValue();
        vehicle.setYear(getIntInput("Year of manufacture: ", 1900, currentYear));
    }

    /**
     * Adds a new car to the system
     */
    private void addCar() {
        Car car = new Car();
        getCommonVehicleInfo(car);

        car.setNumDoors(getIntInput("Number of doors: ", 1, 5));

        System.out.println("Available fuel types:");
        System.out.println("1. Petrol");
        System.out.println("2. Diesel");
        System.out.println("3. Electric");

        int fuelChoice = getIntInput("Select fuel type (1-3): ", 1, 3);
        String fuelType;
        switch (fuelChoice) {
            case 1:
                fuelType = "Petrol";
                break;
            case 2:
                fuelType = "Diesel";
                break;
            case 3:
                fuelType = "Electric";
                break;
            default:
                fuelType = "Unknown";
                break;
        }

        car.setFuelType(fuelType);
        vehicles.add(car);
        System.out.println("Car added successfully!");
    }

    /**
     * Adds a new motorcycle to the system
     */
    private void addMotorcycle() {
        Motorcycle motorcycle = new Motorcycle();
        getCommonVehicleInfo(motorcycle);

        motorcycle.setNumWheels(getIntInput("Number of wheels: ", 2, 3));

        System.out.println("Available motorcycle types:");
        System.out.println("1. Sport");
        System.out.println("2. Cruiser");
        System.out.println("3. Off-road");

        int typeChoice = getIntInput("Select motorcycle type (1-3): ", 1, 3);
        String motorcycleType;
        switch (typeChoice) {
            case 1:
                motorcycleType = "Sport";
                break;
            case 2:
                motorcycleType = "Cruiser";
                break;
            case 3:
                motorcycleType = "Off-road";
                break;
            default:
                motorcycleType = "Unknown";
                break;
        }

        motorcycle.setMotorcycleType(motorcycleType);
        vehicles.add(motorcycle);
        System.out.println("Motorcycle added successfully!");
    }

    /**
     * Adds a new truck to the system
     */
    private void addTruck() {
        Truck truck = new Truck();
        getCommonVehicleInfo(truck);

        truck.setCargoCapacity(getDoubleInput("Cargo capacity (in tons): ", 0.5, 50.0));

        System.out.println("Available transmission types:");
        System.out.println("1. Manual");
        System.out.println("2. Automatic");

        int transmissionChoice = getIntInput("Select transmission type (1-2): ", 1, 2);
        String transmissionType;
        switch (transmissionChoice) {
            case 1:
                transmissionType = "Manual";
                break;
            case 2:
                transmissionType = "Automatic";
                break;
            default:
                transmissionType = "Unknown";
                break;
        }

        truck.setTransmissionType(transmissionType);
        vehicles.add(truck);
        System.out.println("Truck added successfully!");
    }

    /**
     * Displays all vehicles in the system
     */
    private void displayAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles in the system yet.");
            return;
        }

        System.out.println("\n===== All Vehicles =====");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println("\nVehicle #" + (i + 1));
            vehicles.get(i).displayDetails();
        }
    }

    /**
     * Gets a string input from the user
     * @param prompt the message to display to the user
     * @return the user's input as a String
     */
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Gets an integer input from the user with validation
     * @param prompt the message to display to the user
     * @param min minimum valid value
     * @param max maximum valid value
     * @return the validated integer input
     */
    private int getIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Error: Please enter a value between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    /**
     * Gets a double input from the user with validation
     * @param prompt the message to display to the user
     * @param min minimum valid value
     * @param max maximum valid value
     * @return the validated double input
     */
    private double getDoubleInput(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Error: Please enter a value between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }
}
