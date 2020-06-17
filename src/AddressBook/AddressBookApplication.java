package AddressBook;
import java.util.*;
public class AddressBookApplication {
    AddressBookMain obj = new AddressBookMain();

    public void displayOptions() {
        Scanner sc = new Scanner(System.in);
        String choice = "y";
        while (choice.equals("y")) {
            System.out.println("Select option:");
            System.out.println("1.Add Person to AddressBook");
            System.out.println("2.Edit Person to AddressBook");
            System.out.println("3.Delete Person from AddressBook");
            System.out.println("4.Sort By Name");
            System.out.println("5.Sort By City");
            System.out.println("6.Sort By State");
            System.out.println("7.Sort By Zip");
            System.out.println("8.View a person by City and State");
            System.out.println("9.Search a person in City or State");
            System.out.println("10.Print the AddressBook");
            System.out.println("11.To quit");
            int a = sc.nextInt();
            switch (a) {
                case 1 -> {
                    obj.addPerson();
                    System.out.println("Added Successfully");
                }
                case 2 -> {
                    obj.editPerson();
                    System.out.println("Edited Successfully");
                }
                case 3 -> {
                    obj.deletePerson();
                    System.out.println("Deleted Successfully");
                }
                case 4 -> {
                    System.out.println("Sorted records by Name:");
                    obj.sortByName();
                }
                case 5 -> {
                    System.out.println("Sorted records by City:");
                    obj.sortByCity();
                }
                case 6 -> {
                    System.out.println("Sorted records by State:");
                    obj.sortByState();
                }
                case 7 -> {
                    System.out.println("Sorted records by Zip:");
                    obj.sortByZip();
                }
                case 8 -> {
                    System.out.println("Persons by City:");
                    obj.viewByCityOrState(0);
                    System.out.println("Persons by State:");
                    obj.viewByCityOrState(1);
                }
                case 9 -> {
                    sc.nextLine();
                    System.out.println("Enter Firstname:");
                    String firstName = sc.nextLine();
                    System.out.println("Enter City:");
                    String value = sc.nextLine();
                    obj.searchInCityOrState(firstName, value, 0);
                    System.out.println("Enter State:");
                    value = sc.nextLine();
                    obj.searchInCityOrState(firstName, value, 1);
                }
                case 10 -> obj.PrintMailinglabelformat(obj.records);
                case 11 -> {
                    System.out.println("Quitting!");
                    choice = "n";
                }
            }
        }

    }
    public static void main(String[] args) {
        AddressBookApplication obj = new AddressBookApplication();
        obj.displayOptions();
    }
}
