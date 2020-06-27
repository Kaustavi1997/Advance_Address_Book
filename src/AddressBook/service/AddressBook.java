package AddressBook.service;
import AddressBook.model.*;
import AddressBook.utility.Utility;

import java.util.*;
import java.util.regex.Pattern;

public class AddressBook {
    Utility utility = new Utility();
    public List<Person> records = new ArrayList<Person> ();
    Map<String, Boolean> phoneNoDictionary = new HashMap<>();
    Map<String, List<Person>> personByCityDictionary = new HashMap<>();
    Map<String, List<Person>> personByStateDictionary = new HashMap<>();

    public static final String FIRST_NAME_PATTERN = "^[A-Z]{1}[a-z]{2,}$";
    public static final String LAST_NAME_PATTERN = "^[A-Z]{1}[a-z]{2,}$";
    public static final String CITY_PATTERN = "[a-z]$";
    public static final String STATE_PATTERN = "[a-z]$";
    public static final String PHONE_NO_PATTERN = "^[0-9]{10}$";

    public boolean checkValidity(String regex,String value){
        if(!Pattern.matches(regex,value))
            return false;
        return true;
    }

    public void addPerson() throws Exception {
        System.out.println("Enter phone no(10 digits):");
        String phoneNo = utility.scanner.nextLine();
        if (!checkValidity(PHONE_NO_PATTERN, phoneNo)) {
            throw new Exception("Enter valid phone number!");
        }
        if (phoneNoDictionary.containsKey(phoneNo)) {
            System.out.println("Entry already exists! Can't add!");
        } else {
            System.out.println("Enter first name(first letter is capital others small):");
            String firstName = utility.scanner.nextLine();
            if (!checkValidity(FIRST_NAME_PATTERN, firstName)) {
                throw new Exception("Enter valid firstname!");
            }
            System.out.println("Enter last name(first letter is capital others small):");
            String lastName = utility.scanner.nextLine();
            if (!checkValidity(LAST_NAME_PATTERN, lastName)) {
                throw new Exception("Enter valid lastname!");
            }
            System.out.println("Enter address:");
            String address = utility.scanner.nextLine();
            System.out.println("Enter city(small letters):");
            String city = utility.scanner.nextLine();
            if (!checkValidity(CITY_PATTERN, city)) {
                throw new Exception("Enter valid city!");
            }
            System.out.println("Enter state(small letters):");
            String state = utility.scanner.nextLine();
            if (!checkValidity(STATE_PATTERN, state)) {
                throw new Exception("Enter valid state!");
            }
            System.out.println("Enter zip:");
            int zip;
            try {
                zip = utility.scanner.nextInt();
                // Eat the new line
                utility.scanner.nextLine();
            } catch (Exception e) {
                // Eat the new line
                utility.scanner.nextLine();
                throw new Exception("Enter Integer for zip");
            }
            Address addressObject = new Address(address, city, state, zip);
            Person person = new Person(firstName, lastName, phoneNo, addressObject);
            records.add(person);
            phoneNoDictionary.put(phoneNo, Boolean.TRUE);
            addPersonCityWise(city, person);
            addPersonStateWise(state, person);
            System.out.println("Added Successfully");
        }
    }
    public void addPersonCityWise(String city, Person person){
        if (personByCityDictionary.containsKey(city)) {
            List<Person> existingList;
            existingList = personByCityDictionary.get(city);
            existingList.add(person);
            personByCityDictionary.put(city,existingList);
        }
        else{
            List<Person> emptyList = new ArrayList<Person> ();
            emptyList.add(person);
            personByCityDictionary.put(city,emptyList);
        }
    }
    public void addPersonStateWise(String state, Person person){
        if (personByStateDictionary.containsKey(state)) {
            List<Person> existingList;
            existingList = personByStateDictionary.get(state);
            existingList.add(person);
            personByStateDictionary.put(state,existingList);
        }
        else{
            List<Person> emptyList = new ArrayList<Person> ();
            emptyList.add(person);
            personByStateDictionary.put(state,emptyList);
        }

    }

    public int findIndex(String phoneNo){
        int index = -1;
        for (int i = 0; i < records.size(); i++){
            String phoneNoTemporary = records.get(i).getPhoneNo();
            if(phoneNoTemporary.equals(phoneNo)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public void editPerson() {
        System.out.println("Edit");
        System.out.println("Enter phone number:");
        String phoneNo = utility.scanner.nextLine();
        int index = findIndex(phoneNo);
        if (index == -1) {
            System.out.println("Entry not found!");
            return;
        }
        int choice = 0;
        while (choice != 6) {
            System.out.println("1 for editing address");
            System.out.println("2 for editing state");
            System.out.println("3 for editing city");
            System.out.println("4 for editing by zip");
            System.out.println("5 for editing by phone no");
            System.out.println("6 for exit");
            System.out.println("Enter choice");
            choice = utility.scanner.nextInt();
            // Eat the new line
            utility.scanner.nextLine();
            // Eat the new line
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter address:");
                    String address = utility.scanner.nextLine();
                    records.get(index).getAddress().setAddress(address);
                }
                case 2 -> {
                    System.out.println("Enter state:");
                    String state = utility.scanner.nextLine();
                    records.get(index).getAddress().setState(state);
                }
                case 3 -> {
                    System.out.println("Enter city:");
                    String city = utility.scanner.nextLine();
                    records.get(index).getAddress().setCity(city);
                }
                case 4 -> {
                    System.out.println("Enter zip:");
                    int zip = utility.scanner.nextInt();
                    utility.scanner.nextLine();
                    records.get(index).getAddress().setZip(zip);
                }
                case 5 -> {
                    System.out.println("Enter phone no:");
                    phoneNo = utility.scanner.nextLine();
                    records.get(index).setPhoneNo(phoneNo);
                }
                case 6 -> {
                    System.out.println("Done!");
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    public void deletePerson(){
        System.out.println("Delete");
        System.out.println("Enter phone number:");
        String phoneNo= utility.scanner.nextLine();
        int index = findIndex(phoneNo);
        if (index == -1) {
            System.out.println("Entry not found!");
            return;
        }
        records.remove(index);
        System.out.println("Deleted Successfully");
    }
    public static void printEachRecord(Person record){
        System.out.println(record.getLastName() + " " + record.getFirstName());
        System.out.println(record.getAddress().getAddress());
        System.out.println(record.getAddress().getCity());
        System.out.println(record.getAddress().getState());
        System.out.println(record.getAddress().getZip());
        System.out.println(record.getPhoneNo());
        System.out.println("");
    }

    public void sortByName() {
        records.stream().sorted((person1, person2)->person1.getFirstName().
                compareTo(person2.getFirstName())).forEach(AddressBook::printEachRecord);
    }
    public void sortByState() {
        records.stream().sorted((person1, person2)->person1.getAddress().getState().
                compareTo(person2.getAddress().getState())).forEach(AddressBook::printEachRecord);
    }
    public void sortByZip() {
        records.stream().sorted((person1, person2)->person1.getAddress().getZip() - person2.getAddress().getZip()).
                forEach(AddressBook::printEachRecord);
    }
    public void sortByCity() {
        records.stream().sorted((person1, person2)->person1.getAddress().getCity().
                compareTo(person2.getAddress().getCity())).forEach(AddressBook::printEachRecord);

    }
    public void sort() {
        System.out.println("1.sort by name");
        System.out.println("2.sort by state");
        System.out.println("3.sort by city");
        System.out.println("4.sort by zip");
        int choice=utility.scanner.nextInt();
        utility.scanner.nextLine();
        switch (choice) {
            case 1 -> {
                sortByName();
            }
            case 2 -> {
                sortByState();
            }
            case 3 -> {
                sortByCity();
            }
            case 4 -> {
                sortByZip();
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    public void searchInCityOrState(String firstName,String value,int choice){
        boolean found = false;
        Map<String, List<Person>> commonDict;
        if(choice == 0){
            commonDict = personByCityDictionary;
        }
        else{
            commonDict = personByStateDictionary;
        }
        List<Person> foundPersons = new ArrayList<Person> ();
        for (String k : commonDict.keySet()) {
            if(k.compareTo(value) == 0) {
                List<Person> v = commonDict.get(k);
                for (int i = 0; i < v.size(); i++) {
                    if(v.get(i).getFirstName().compareTo(firstName) == 0) {
                        found = true;
                        foundPersons.add(v.get(i));
                    }
                }
            }
        }
        if(!found){
            if(choice == 0) {
                System.out.println("Person not found in given city");
            }
            else{
                System.out.println("Person not found in given state");
            }
        }
        else{
            foundPersons.stream().forEach(AddressBook::printEachRecord);
        }
    }

    public void viewByCityOrState(int choice){
        Map<String, List<Person>> commonDict;
        if(choice == 0){
            commonDict = personByCityDictionary;
        }
        else{
            commonDict = personByStateDictionary;
        }
        for (String k : commonDict.keySet()) {
            List<Person> v = commonDict.get(k);
            System.out.println(k);
            v.stream().forEach(AddressBook::printEachRecord);
            System.out.println("\n");
        }
    }

}