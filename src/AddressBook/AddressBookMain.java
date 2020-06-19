package AddressBook;

public class AddressBookMain {
    AddressBook addressBook = new AddressBook();
    Utility utility = new Utility();
    public void takingInputsForSearching(){
        utility.scanner.nextLine();
        System.out.println("Enter Firstname:");
        String firstName = utility.scanner.nextLine();
        System.out.println("Enter City:");
        String value = utility.scanner.nextLine();
        addressBook.searchInCityOrState(firstName, value, 0);
        System.out.println("Enter State:");
        value = utility.scanner.nextLine();
        addressBook.searchInCityOrState(firstName, value, 1);
    }
    public void viewByCityOrStateHelper(){
        System.out.println("Persons by City:");
        addressBook.viewByCityOrState(0);
        System.out.println("Persons by State:");
        addressBook.viewByCityOrState(1);
    }
    public void displayOptions() {
        String choice = "y";
        while (choice.equals("y")) {
            System.out.println("Select option:");
            System.out.println("1.Add Person to AddressBook");
            System.out.println("2.Edit Person to AddressBook");
            System.out.println("3.Delete Person from AddressBook");
            System.out.println("4.Sort Address Book");
            System.out.println("5.View a person by City and State");
            System.out.println("6.Search a person in City or State");
            System.out.println("7.Print the AddressBook");
            System.out.println("8.To quit");
            int a = utility.scanner.nextInt();
            switch (a) {
                case 1 -> {
                    addressBook.addPerson();
                }
                case 2 -> {
                    addressBook.editPerson();
                    System.out.println("Edited Successfully");
                }
                case 3 -> {
                    addressBook.deletePerson();
                    System.out.println("Deleted Successfully");
                }
                case 4 -> {
                    addressBook.sort();
                }
                case 5 -> {
                    viewByCityOrStateHelper();
                }
                case 6 -> {
                    takingInputsForSearching();
                }
                case 7 -> addressBook.PrintMailinglabelformat(addressBook.records);
                case 8 -> {
                    System.out.println("Quitting!");
                    System.exit(0);

                }
                default -> System.out.println("Invalid choice!");
            }
        }

    }
    public static void main(String[] args) {
        AddressBookMain addressBookApplication = new AddressBookMain();
        addressBookApplication.displayOptions();
    }
}
