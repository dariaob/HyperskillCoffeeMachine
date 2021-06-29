import java.util.Scanner;

enum Status {
    CHOOSING, BUYING, FILLING, TAKING, REMAINING, EXIT
}

public class CoffeeMachine {
    private static int water = 400;
    private static int milk = 540;
    private static int coffeeBeans = 120;
    private static int cups = 9;
    private static int cash = 550;
    private static boolean exit = false;
    private static int takenCash = 0;

    static Scanner scanner = new Scanner(System.in);

    static Status status = Status.CHOOSING;
    public static void main(String[] args) {

        chooseAction();
    }

    private static void chooseAction() {
        System.out.println(status);
        System.out.println("Write action (buy, fill, take, remaining, exit): \n");
        String action;
        do {
            action = scanner.next();
            switch (action) {
                case "buy":
                    status = Status.BUYING;
                    System.out.println(status);
                    buy();
                    break;
                case "fill":
                    status = Status.FILLING;
                    System.out.println(status);
                    fill();
                    break;
                case "take":
                    status = Status.TAKING;
                    take();
                    break;
                case "remaining":
                    status = Status.REMAINING;
                    remaining();
                    break;
                case "exit":
                    exit();
                    break;
            }

        } while (!exit);


    }

    private static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: \n");
        String buyAction = scanner.next();
        switch (buyAction) {
            case "1":
                if (compareComponents(250, 0, 16)) {
                    updateComponents(250, 0, 16, 4);
                    break;
                }

            case "2":
                if (compareComponents(350, 75, 20)) {
                    updateComponents(350,75,20,7);
                    break;
                }
            case "3":
                if (compareComponents(200,100,12)) {
                    updateComponents(200, 100,12,6);
                    break;
                }
            case "back":
                break;
            default:
                System.out.println("Please, write the number between 1 and 3 or choose back");
                break;
        }
        status = Status.CHOOSING;
    }

    private static void fill() {
        System.out.println();
        System.out.println("Write how many ml of water you want to add: ");
        int waterAdd = scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: \n");
        int milkAdd = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: \n");
        int beans = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: \n");
        int cupsAdd = scanner.nextInt();

        water += waterAdd;
        milk += milkAdd;
        coffeeBeans += beans;
        cups += cupsAdd;
        status = Status.CHOOSING;
    }

    private static void take() {
        System.out.println("I gave you $" + cash);
        cash = takenCash;
        cash = 0;
        status = Status.CHOOSING;
    }

    private static void remaining() {
        System.out.println();
        System.out.println("The coffee machine has:\n");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println(cash + "$ of money");
        status = Status.CHOOSING;
    }

    private static void exit() {
        exit = true;
        status = Status.EXIT;
        System.out.println(status);
    }

    private static boolean compareComponents(int waterAdd, int milkAdd, int beansAdd) {
        if (waterAdd < water) {
            if (milkAdd < milk) {
                if (beansAdd < coffeeBeans) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    return true;
                } else {
                    System.out.println("Sorry, not enough coffee beans!");
                    return false;
                }
            } else {
                System.out.println("Sorry, not enough milk!");
                return false;
            }
        } else {
            System.out.println("Sorry, not enough water!");
            return false;
        }
    }

    private static void updateComponents(int mlOfWater, int mlOfMilk, int beans, int money) {
        water = water - mlOfWater;
        milk = milk - mlOfMilk;
        coffeeBeans = coffeeBeans - beans;
        cups--;
        cash += money;
    }
}