import java.io.*;
import java.util.*;

public class ShoppingListApp {

    private ArrayList<Item> shoppingList;
    private Scanner scan;

    public ShoppingListApp() {
        shoppingList = new ArrayList<>();
        scan = new Scanner(System.in);
    }

    public void start() {

        readData();  // read data in from the file

        System.out.println("---------------------------------");
        System.out.println("Welcome to the shopping list app!");
        System.out.println("---------------------------------");
        String option = "";
        while (!option.equals("q")) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("(v)iew shopping list");
            System.out.println("(a)dd item to list");
            System.out.println("(e)dit an item's price");
            System.out.println("(r)emove item from list");
            System.out.println("(q)uit");
            System.out.print("Enter choice: ");
            option = scan.nextLine();
            if (option.equals("v")) {
                viewShoppingList();
            } else if (option.equals("a")) {
                addItemToList();
            } else if (option.equals("e")) {
                editItemPrice();
            } else if (option.equals("r")) {
                removeItem();
            }
            saveData();
        }
        System.out.println("Goodbye!");
    }

    private void readData() {
        // TODO: write this method: load the shopping list data from your shoppinglist.txt file and populate shoppingList.
        //  note that this method gets called immediately at the start of the "start" method;
        //  you only need to read the data in one time to populate the shoppingList arraylist
        try {
            File shoppingList = new File("src/shoppinglist.txt");
            Scanner fileScanner = new Scanner(shoppingList);
            while (fileScanner.hasNext()) {
                String[] data = fileScanner.nextLine().split(",");
                String name = data[0];
                double price = Double.parseDouble(data[1]);
                this.shoppingList.add(new Item(name, price));            
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

    private void saveData() {
        // TODO: write this method: save the items in shoppingList to shoppinglist.txt
        try {
            PrintWriter dataFile = new PrintWriter("src/shoppinglist.txt");
            for (Item item : shoppingList) {
                dataFile.println(item.getName() + "," + item.getPrice());
            }
            dataFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewShoppingList() {
        // TODO: write this method: it should display all items on shoppingList, unless the list is empty,
        //  in which case, print "shopping list empty"
        //  (print out from the arraylist; you don't need to read the data back in again)
        if (shoppingList.size() == 0) {
            System.out.println("shopping list empty");
        } else {
            System.out.println(shoppingList);
        }
    }

    private void addItemToList() {
        // TODO: write this method: ask user for item name and price then add that item to shoppingList;
        scan = new Scanner(System.in);
        System.out.println("Enter Item Name:");
        String name = scan.nextLine();
        System.out.println("Enter Item Price:");
        Double price = scan.nextDouble();
        scan.nextLine();
        shoppingList.add(new Item(name, price));
        saveData();
    }

    private void editItemPrice() {
        // TODO: write this method: ask user for an item's name, then ask for an updated price,
        //  then edit that item's price; if the item isn't found, do nothing and print "item not found"
        System.out.println("Enter Item Name:");
        String name = scan.nextLine();
        System.out.println("Enter New Item Price:");
        Double price = scan.nextDouble();
        scan.nextLine();
        for (int i = 0; i < shoppingList.size(); i++) {
            if (shoppingList.get(i).getName().equalsIgnoreCase(name)) {
                shoppingList.set(i, new Item(name, price));
            }
            saveData();
        }
    }

    private void removeItem() {
        // TODO: write this method: ask user for an item's name, then remove that item from shoppingList;
        //  if the item isn't found, do nothing and print "item not found"
        System.out.println("Enter the name of the item you want to remove.");
        String item = scan.nextLine();
        for (int i = 0; i < shoppingList.size(); i++) {
            if (shoppingList.get(i).getName().equalsIgnoreCase(item)) {
                shoppingList.remove(i);
                i--;
            }
            saveData();
        }
    }
}