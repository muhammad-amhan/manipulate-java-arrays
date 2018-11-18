package arraycrud;

import java.util.NoSuchElementException;
import java.util.logging.*;

/* 
* NOTE - Zero will cause misbehaviour to functionalities. Numbers (apart from zero) can be used
* Either we can transform the array into an array of String.
* Or using GENERIC type array.
* This is outside the scope of this program.
*/ 

public class Arraycrud {

    private static final Logger LOGGER = Logger.getLogger(Arraycrud.class.getName());
    private final int[] array;
    private int array_size;
    private int count_elem;

    public static void main(String[] args) {
        Arraycrud newArray = new Arraycrud(9);
        // Adding elements
        newArray.add_elem(1);
        newArray.add_elem(3);
        newArray.add_elem(3);
        newArray.add_elem(5);
        newArray.add_elem(8);
        newArray.print_array();
        System.out.println("\n");
        System.out.println(newArray.count_elem());
        // ***
        System.out.println("\n");
        // ***
        System.out.println(newArray.get_element_at_index(2));
        System.out.println("\n");
        System.out.println(newArray.get_element_at_index(-1));
        System.out.println("\n");
        // ***
        newArray.delete_index(6);
        System.out.println("\n");
        newArray.delete_index(-10);
        System.out.println("\n");
        // ***
        newArray.delete_element(1);
        System.out.println(newArray.count_elem());
        System.out.println("\n");
        newArray.delete_element(100);
        System.out.println("\n");
        // ***
        System.out.println(newArray.check_element_exists(8));
        System.out.println("\n");
        System.out.println(newArray.check_element_exists(20));
        System.out.println("\n");
        // ***
        System.out.println(newArray.search_match(3));
        System.out.println("\n");
        System.out.println(newArray.search_match(19));
        System.out.println("\n");
        // ***
        newArray.add_elem_to_index(6, 7);
        System.out.println(newArray.count_elem());
        System.out.println("\n");
        newArray.add_elem_to_index(1, 7);
        System.out.println("\n");
        newArray.add_elem_to_index(1, 16);
        // ***
        System.out.println("\n");
        System.out.println(newArray.count_elem());
        // ***
        System.out.println("\n");
        newArray.add_elem(4);
        newArray.add_elem(9);
        newArray.add_elem(2);
        newArray.add_elem(7);
        System.out.println("\n");
        // ***
        newArray.delete_element(4);
        System.out.println(newArray.count_elem());
        System.out.println(newArray.array_size());
        
    }

    public Arraycrud(int size) {
        count_elem = 0; // number of elements inside the array
        array_size = size; // the size of the array
        array = new int[size];
    }

    public String array_size() {
        return "Array size is: " + this.array_size;
    }
    
    // Get number of element in the array
    public String count_elem() {
        return "There are " + this.count_elem + " elements in the array.";
    }

    // Inserting an element in the array, without overriding indexes with values. It will look for available space to insert.
    public void add_elem(int value) {
        System.out.println("Adding '[" + value + "]' to array...");
        try {
            if (count_elem < array_size) {
                if (array[count_elem] == array[array_size - 1]) {
                    for(int i = 0; i < array_size; i++) {
                        if(array[i] == 0) {
                            array[i] = value;
                            count_elem++;
                            break;
                        }
                    }
                }
                else {
                    array[count_elem] = value;
                    count_elem++;
                }
                System.out.println("Done!");
            }
            else {
                throw new IndexOutOfBoundsException();
            }
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Array is full!");
        }
    }
    
    // Add element to a the array at the specified index, without overriding existing values in that index.
    public void add_elem_to_index(int value, int index) {
        System.out.println("Adding '[" + value + "]' in index '[" + index + "]'...");
        try {
            if(index < array_size) {
                if(array[index] == 0) {
                    array[index] = value;
                    count_elem++;
                    System.out.println("Done!");
                }
                else {
                    System.out.println("Failed...");
                    System.out.println("Index '[" + index + "]' already has the "
                            + "value '[" + array[index] + "]' stored in it.");
                }
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Index'[" + index + "]'doesn't exists.");
        }
        this.print_array();
    }
    
    // Delete all found values but keep their indexes.
    public void delete_element(int value) {
        System.out.println("Deleting element '[" + value + "]' from array...");
        int elemets_deleted = 0;
        
        try {
            for (int i = 0; i < array_size; i++) {
                if (array[i] == value) {
                    array[i] = 0;
                    count_elem--;
                    elemets_deleted++;
                    if (elemets_deleted > 0){
                        break;
                    }
                    else {
                        throw new NoSuchElementException();
                    }
                }
            }
        } catch(NoSuchElementException e) {
            System.out.println("'[" + value + "]' doesn't exist in the array!");
        }
        this.print_array();
    }
    
    // Get element at specific index.
    public int get_element_at_index(int index) {
        System.out.println("Retrieving element at index '[" + index + "]'...");
        try {
            if (index < array_size) {
                return array[index];
            }
            else {
                throw new IndexOutOfBoundsException();
            }
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Index'[" + index + "]'doesn't exists.");
        }
        this.print_array();
        return 0;
    }
   
    
    // Delete index with its value.
    public void delete_index(int index) {
        System.out.println("Deleting index '[" + index + "]' from array...");
        try {
            if (index < array_size) {
                for (int i = index; i < array_size - 1; i++) {
                    array[i] = array[i + 1];
                }
                array_size--;
                if (array[index] != 0) {
                    count_elem--;
                }
            } 
            else {
                throw new IndexOutOfBoundsException();
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Index '[" + index + "]' does not exist!");

        }
        this.print_array();
    }

    // Check if value exists in the array, return true otherwise false.
    public Boolean check_element_exists(int value) {
        System.out.println("Checking if '[" + value + "]' exists in array...");
        
        boolean exists = false;
        for (int i = 0; i < array_size; i++) {
            if (array[i] == value) {
                exists = true;
                break;
            }
        }
        this.print_array();
        return exists;
    }
    
    // Print the array.
    public void print_array() {
        System.out.println("Printing array:");
        
        for (int i = 0; i < array_size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }

    // Search matching values and print them along with their indexes.
    public String search_match(int value) {
        System.out.println("Finding match for '[" + value + "]' in array...");

        boolean found_match = false;
        String message = "";

        for (int i = 0; i < array_size; i++) {
            if (array[i] == value) {
                found_match = true;
                message += "Match found for '[" + value + "]' at index '[" + i + "]'\n";
            }
        }
        if (!found_match) {
            message = "No match found for '[" + value + "]' in array.";
        }
        this.print_array();
        return message;
    }
}
