/*
 * Author: Riley Chai
 * Class: ICS4U
 * Program: Searching and Sorting Assignment
 */
package sortsearchchairiley;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 335480661
 */
public class SortSearchChaiRiley {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> listOfNames = new ArrayList<>();//Stores all the names from the file.
        Scanner keyboard = new Scanner(System.in);
        String userInput = "";//Stores the user input.
        boolean isRead = false;//Ensures the file has been read before the user can sort.
        boolean programLoop = true;//Keeps the program running until '9' is entered.

        while (programLoop) {//Repeats until '9' is entered.
            //Displays all avalible options to the user.
            System.out.printf("\n~Options: "
                    + "\n1 - Read Names from File and display the list"
                    + "\n2 - Selection Sort Ascending and display the list"
                    + "\n3 - Selection Sort Descending and display the list"
                    + "\n4 - Bubble Sort Ascending and display the list"
                    + "\n5 - Bubble Sort Descending and display the list"
                    + "\n6 - Insertion Sort Ascending and display the list"
                    + "\n7 - Insertion Sort Descending and display the list"
                    + "\n8 - Binary Search"
                    + "\n9 - Exit"
                    + "\nPlease choose an option: ");
            userInput = keyboard.nextLine();//Stores the user's choice.
            if (userInput.length() == 1) {//Ensures only one character is entered.
                //Only allows the user to read the file or exit until the file has been read.
                if (isRead == true || userInput.charAt(0) == '1' || userInput.charAt(0) == '9') {
                    switch (userInput.charAt(0)) {
                        case '1'://Read file.

                            System.out.println("~Reading file");
                            listOfNames = readFile();//Reads the file
                            printArrayList(listOfNames);//Displays the list of names.
                            isRead = true;//Allows the user to use the sort and searching methods.
                            break;

                        case '2'://Selection Sort Ascending.

                            System.out.println("~Using selection sort (Ascending)");
                            listOfNames = selectionSortAscending(listOfNames);//Passes the local array to the selectionSortAscending method to be sorted.
                            printArrayList(listOfNames);//Displays the list of names.
                            break;

                        case '3'://Selection Sort Descending

                            System.out.println("~Using selection sort (Descending)");
                            listOfNames = selectionSortDescending(listOfNames);//Passes the local array to the selectionSortDescending method to be sorted.
                            printArrayList(listOfNames);//Displays the list of names.
                            break;

                        case '4'://Bubble Sort Ascending

                            System.out.println("~Using bubble sort (Ascending)");
                            listOfNames = bubbleSortAscending(listOfNames);//Passes the local array to the bubbleSortAscending method to be sorted.
                            printArrayList(listOfNames);//Displays the list of names.
                            break;

                        case '5'://Bubble Sort Descending

                            System.out.println("~Using bubble sort (Descending)");
                            listOfNames = bubbleSortDescending(listOfNames);//Passes the local array to the bubbleSortDescending method to be sorted.
                            printArrayList(listOfNames);//Displays the list of names.
                            break;

                        case '6'://Insertion Sort Ascending

                            System.out.println("~Using insertion sort (Ascending)");
                            listOfNames = insertionSortAscending(listOfNames);//Passes the local array to the insertionSortAscending method to be sorted.
                            printArrayList(listOfNames);//Displays the list of names.
                            break;

                        case '7'://Insertion Sort Descending

                            System.out.println("~Using insertion sort (Descending)");
                            listOfNames = insertionSortDescending(listOfNames);//Passes the local array to the insertionSortDescending method to be sorted.
                            printArrayList(listOfNames);//Displays the list of names.
                            break;

                        case '8'://Binary Search

                            System.out.print("Please enter the name you are looking for: ");
                            userInput = keyboard.nextLine();//Stores the user's input.

                            //Passes the local array and the target name to the binarySearch method.
                            //Stores the integer output in index.
                            int index = binarySearch(listOfNames, userInput);
                            if (index == -1) {//If the name was not found.
                                System.out.println("**The name was not found**");
                            } else {//Outputs the index of the name in the sorted(Ascending) list.
                                System.out.println("~The name was found at the index: " + index);
                            }
                            break;

                        case '9'://Exit
                            System.out.println("~Program Off");
                            programLoop = false;//Exits the loop.
                            break;

                        default://If the user does not choose a valid option.
                            System.out.println("**Please Choose one of the options (1-9)**");
                            break;
                    }
                } else {//Informs the user to read the file before trying to sort/search.
                    System.out.println("**Please read the file first**");
                }
            } else {//Empty input or more than 1 character.
                System.out.println("**Please Choose one of the options (1-9)**");
            }
        }
        keyboard.close();//Closes the scanner.
    }

    /**
     * Reads the file of names into an array list.
     *
     * @return listOfNames - A String type array list of all the names.
     */
    static ArrayList<String> readFile() {
        File nameFile = new File("names.txt");//Creates the file object
        ArrayList<String> listOfNames = new ArrayList<>();//An array list that stores all the names from the file.
        try {
            Scanner input = new Scanner(nameFile); //Initializes the scanner for the file.
            while (input.hasNext()) {//Loops while there are still more names in the file to add.
                listOfNames.add(input.nextLine());//Adds the current name to the array list.
            }
            input.close();//Closes the scanner.
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SortSearchChaiRiley.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listOfNames;//Returns the array list.
    }

    /**
     * Displays the list of names.
     *
     * @param listOfNames A String type array list of all the names.
     */
    static void printArrayList(ArrayList listOfNames) {
        System.out.println("\n~List of Names: ");
        for (int i = 0; i < listOfNames.size(); i++) {//Loops through every index of the array list.
            System.out.println(listOfNames.get(i));//Displays the name at the current index.
        }
    }

    /**
     * Sort the list using selection sort. Sorts into into ascending order.
     *
     * @param listOfNames A String type array list of all the names.
     * @return Sorted array list.
     */
    static ArrayList<String> selectionSortAscending(ArrayList<String> listOfNames) {
        for (int i = 0; i < listOfNames.size(); i++) {//Loops through every index of the array list.
            int minVal = i;//Sets minVal to i. Increases the starting point of the next for loop.
            for (int j = i + 1; j < listOfNames.size(); j++) {//Loops through every index of the array list starting at i+1.
                if (listOfNames.get(j).compareTo(listOfNames.get(minVal)) < 0) {//Compares the current min value against the current value at j.
                    minVal = j;//If the value at j is smaller, set min value to the index of j 
                }
            }
            String temp = listOfNames.get(minVal);//Gets the value at the index of minVal.
            //Swaps the min value and the value at i
            listOfNames.set(minVal, listOfNames.get(i));//Sets the value at minVal to the value at i.
            listOfNames.set(i, temp);//Sets the value at i to the value in temp.
        }
        return listOfNames;
    }

    /**
     * Sort the list using selection sort. Sorts into into descending order.
     *
     * @param listOfNames A String type array list of all the names.
     * @return Sorted array list.
     */
    static ArrayList<String> selectionSortDescending(ArrayList<String> listOfNames) {
        for (int i = 0; i < listOfNames.size(); i++) {//Loops through every index of the array list.
            int maxVal = i;//Sets maxVal to i. Increases the starting point of the next for loop.
            for (int j = i + 1; j < listOfNames.size(); j++) {//Loops through every index of the array list starting at i+1.
                if (listOfNames.get(j).compareTo(listOfNames.get(maxVal)) > 0) {//Compares the current max value against the current value at j.
                    maxVal = j;//If the value at j is greater, set max value to the index of j 
                }
            }
            String temp = listOfNames.get(maxVal);//Stores the value at maxVal.
            //Swaps the max value with the value at i
            listOfNames.set(maxVal, listOfNames.get(i));//Sets the value at maxVal to the value at i.
            listOfNames.set(i, temp);//Sets the value at i to the value in temp.
        }
        return listOfNames;
    }

    /**
     * Sort the list using bubble sort. Sorts into into ascending order.
     *
     * @param listOfNames A String type array list of all the names.
     * @return Sorted array list.
     */
    static ArrayList<String> bubbleSortAscending(ArrayList<String> listOfNames) {
        boolean sorted = false;
        while (!sorted) {//Loops while sorted is false.
            sorted = true;//Set sorted to true. Assume the list is sorted on this pass.
            for (int i = 0; i < listOfNames.size() - 1; i++) {//Loops through every index of the array list except for the last.
                if (listOfNames.get(i).compareTo(listOfNames.get(i + 1)) > 0) {//If the current value is greater than the value at the next index.
                    sorted = false;//Sorted is set back to false because a swap is required.
                    //Swap the two values
                    String temp = listOfNames.get(i);//Stores the value at i.
                    listOfNames.set(i, listOfNames.get(i + 1));//Sets the value at i to the value at the next index.
                    listOfNames.set(i + 1, temp);//Sets the value at the second index to the value in temp.
                }
            }
        }
        return listOfNames;
    }

    /**
     * Sort the list using bubble sort. Sorts into into descending order.
     *
     * @param listOfNames A String type array list of all the names.
     * @return Sorted array list.
     */
    static ArrayList<String> bubbleSortDescending(ArrayList<String> listOfNames) {
        boolean sorted = false;
        while (!sorted) {//Loops while sorted is false.
            sorted = true;//Set sorted to true. Assume the list is sorted on this pass.
            for (int i = 0; i < listOfNames.size() - 1; i++) {//Loops through every index of the array list except for the last.
                if (listOfNames.get(i).compareTo(listOfNames.get(i + 1)) < 0) {//If the current value is less than the value at the next index.
                    sorted = false;//Sorted is set back to false because a swap is required.
                    //Swap the two values
                    String temp = listOfNames.get(i);//Stores the value at i.
                    listOfNames.set(i, listOfNames.get(i + 1));//Sets the value at i to the value at the next index.
                    listOfNames.set(i + 1, temp);//Sets the value at the second index to the value in temp.
                }
            }
        }
        return listOfNames;
    }

    /**
     * Sort the list using insertion sort. Sorts into into ascending order.
     *
     * @param listOfNames A String type array list of all the names.
     * @return Sorted array list.
     */
    static ArrayList<String> insertionSortAscending(ArrayList<String> listOfNames) {
        for (int pos = 0; pos < listOfNames.size(); pos++) {//Loops through every index of the array list.
            int pos2 = pos;
            //Repeats while the value at the previous index is greater than the value at the current index. The current index must also be greater than 0.
            while (pos2 > 0 && listOfNames.get(pos2 - 1).compareTo(listOfNames.get(pos2)) > 0) {
                //Swap the two items
                String temp = listOfNames.get(pos2);//Stores the value at the current posistion.
                listOfNames.set(pos2, listOfNames.get(pos2 - 1));//Sets the value at the current posisiton to the value at the previous index.
                listOfNames.set((pos2 - 1), temp);//Sets the value at the previous posistion to the value in temp.
                pos2--;//Goes backwards through the array list from the starting point(pos).
            }
        }
        return listOfNames;
    }

    /**
     * Sort the list using insertion sort. Sorts into into descending order.
     *
     * @param listOfNames A String type array list of all the names.
     * @return Sorted array list.
     */
    static ArrayList<String> insertionSortDescending(ArrayList<String> listOfNames) {
        for (int pos = 0; pos < listOfNames.size(); pos++) {//Loops through every index of the array list.
            int pos2 = pos;
            //Repeats while the value at the previous index is less than the value at the current index. The current index must also be greater than 0.
            while (pos2 > 0 && listOfNames.get(pos2 - 1).compareTo(listOfNames.get(pos2)) < 0) {
                //Swap the two items
                String temp = listOfNames.get(pos2);//Stores the value at the current posistion.
                listOfNames.set(pos2, listOfNames.get(pos2 - 1));//Sets the value at the current posisiton to the value at the previous index.
                listOfNames.set((pos2 - 1), temp);//Sets the value at the previous posistion to the value in temp.
                pos2--;//Goes backwards through the array list from the starting point(pos).
            }
        }
        return listOfNames;
    }

    /**
     * Finds the index of the target value using a binary search algorithm.
     *
     * @param list A String type array list of all the names.
     * @param target A String which denotes the target of the search.
     * @return The index of the target string.
     */
    public static int binarySearch(ArrayList<String> list, String target) {
        ArrayList<String> listOfNames = selectionSortAscending(list);//Sorts the list into ascending order using selection sort.

        int low = 0;//Lower boundary.
        int mid = 0;//Mid point of the two boundaries.
        //Sets the max value to the size of the array list.
        int high = listOfNames.size();//Upper boundary.

        while (low <= high) {//Loops while the lower boundary is less than or equal to the higher boundary.
            mid = (low + high) / 2;//Finds the mid point of the two boundaries.
            if (mid < listOfNames.size()) {//Prevents lowercase character from causing an array outofbounds exception
                if (listOfNames.get(mid).equals(target)) {//If the value at the mid point is the target value.
                    return mid;//Returns the value at mid.
                } else if (listOfNames.get(mid).compareTo(target) > 0) {//If the target value is in the lower half of the two boundaries.
                    high = mid - 1;//Sets the upper boundary to the current mid point minus 1.
                } else {//If the target value if in the upper half of the two boundaries.
                    low = mid + 1;//Sets the lower boundary to the current mid point plus 1.
                }
            } else {
                return -1;//If the target value is not found, returns -1.
            }
        }
        return -1;//If the target value is not found, returns -1.
    }

}
