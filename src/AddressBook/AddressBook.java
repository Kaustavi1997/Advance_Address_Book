package AddressBook;
import java.util.*;
public class AddressBook {
    Utility utility = new Utility();
    List<Person> records = new ArrayList<Person> ();
    Map<String, Boolean> phoneNoDict = new HashMap<>();
    Map<String, List<Person>> personByCityDict = new HashMap<>();
    Map<String, List<Person>> personByStateDict = new HashMap<>();

    public void addPerson() {
        System.out.println("Enter phone no:");
        String phoneNo = utility.scanner.nextLine();
        if(phoneNoDict.containsKey(phoneNo)) {
            System.out.println("Entry already exists! Can't add!");
        }
        else{
            System.out.println("Enter first name:");
            String firstName = utility.scanner.nextLine();
            System.out.println("Enter last name:");
            String lastName = utility.scanner.nextLine();
            System.out.println("Enter address:");
            String address = utility.scanner.nextLine();
            System.out.println("Enter city:");
            String city = utility.scanner.nextLine();
            System.out.println("Enter state:");
            String state = utility.scanner.nextLine();
            System.out.println("Enter zip:");
            int zip = utility.scanner.nextInt();
            Address addressObject = new Address(address,city,state,zip);

            // Eat the new line
            utility.scanner.nextLine();

            Person person = new Person(firstName,lastName,phoneNo,addressObject);
            records.add(person);
            if (personByCityDict.containsKey(city)) {
                List<Person> existingList;
                existingList = personByCityDict.get(city);
                existingList.add(person);
                personByCityDict.put(city,existingList);
            }
            else{
                List<Person> emptyList = new ArrayList<Person> ();
                emptyList.add(person);
                personByCityDict.put(city,emptyList);
            }

            if (personByStateDict.containsKey(state)) {
                List<Person> existingList;
                existingList = personByStateDict.get(state);
                existingList.add(person);
                personByStateDict.put(state,existingList);
            }
            else{
                List<Person> emptyList = new ArrayList<Person> ();
                emptyList.add(person);
                personByStateDict.put(state,emptyList);
            }
            phoneNoDict.put(phoneNo, Boolean.TRUE);
            System.out.println("Added Successfully");
        }
    }

    public int findIndex(String phoneNo){
        int index=-1;
        for (int i = 0; i < records.size(); i++){
            String phoneNoTemporary = records.get(i).getPhoneNo();
            if(phoneNoTemporary.equals(phoneNo)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public void editPersonHelper(String value,String option,int index){
        Person person = records.get(index);
        if(option=="a"){
            person.getAddress().setAddress(value);
        }
        else if(option=="c"){
            person.getAddress().setCity(value);
        }
        else if(option=="s"){
            person.getAddress().setState(value);
        }
        else if(option=="p"){
            person.setPhoneNo(value);
        }

        records.set(index,person);
    }

    public void editPersonHelper(int value,int index){
        Person person = records.get(index);
        person.getAddress().setZip(value);
        records.set(index,person);
    }

    public void editPerson() {
        System.out.println("Edit");
        System.out.println("Enter phone number:");
        String phoneNo= utility.scanner.nextLine();

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

            String address,state,city;
            int zip;
            // Eat the new line
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter address:");
                    address = utility.scanner.nextLine();
                    editPersonHelper(address, "a", index);
                }
                case 2 -> {
                    System.out.println("Enter state:");
                    state = utility.scanner.nextLine();
                    editPersonHelper(state, "s", index);
                }
                case 3 -> {
                    System.out.println("Enter city:");
                    city = utility.scanner.nextLine();
                    editPersonHelper(city, "c", index);
                }
                case 4 -> {
                    System.out.println("Enter zip:");
                    zip = utility.scanner.nextInt();
                    utility.scanner.nextLine();
                    editPersonHelper(zip, index);
                }
                case 5 -> {
                    System.out.println("Enter phone no:");
                    phoneNo = utility.scanner.nextLine();
                    editPersonHelper(phoneNo, "p", index);
                }
                case 6 -> System.out.println("Done!");
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
    }

    static class sortByNameHelper implements Comparator<Person>
    {
        public int compare(Person a, Person b)
        {
            String aFirstName = a.getFirstName();
            String bFirstName = b.getFirstName();

            String aLastName = a.getLastName();
            String bLastName = b.getLastName();

            if (aLastName.compareTo(bLastName) == 0){
                return aFirstName.compareTo(bFirstName);
            }
            else{
                return aLastName.compareTo(bLastName);
            }
        }
    }
    static class sortByCityHelper implements Comparator<Person> {
        public int compare(Person a, Person b) {
            String aCity = a.getAddress().getCity();
            String bCity = b.getAddress().getCity();

            String aLastName = a.getLastName();
            String bLastName = b.getLastName();

            if (aCity.compareTo(bCity) == 0) {
                return aLastName.compareTo(bLastName);
            }
            else{
                return aCity.compareTo(bCity);
            }
        }
    }
    static class sortByStateHelper implements Comparator<Person> {
        public int compare(Person a, Person b) {
            String aState = a.getAddress().getState();
            String bState = b.getAddress().getState();

            String aLastName = a.getLastName();
            String bLastName = b.getLastName();

            if (aState.compareTo(bState) == 0) {
                return aLastName.compareTo(bLastName);
            }
            else{
                return aState.compareTo(bState);
            }
        }
    }
    static class sortByZipHelper implements Comparator<Person>
    {
        public int compare(Person a, Person b)
        {
            int aZip = a.getAddress().getZip();
            int bZip = b.getAddress().getZip();

            String aLastName = a.getLastName();
            String bLastName = b.getLastName();

            if (aZip - bZip == 0){
                return aLastName.compareTo(bLastName);
            }
            else{
                return aZip - bZip;
            }
        }
    }
    public void sortByName(){
        records.sort(new sortByNameHelper());
        PrintMailinglabelformat(records);
    }

    public void sortByCity(){
        records.sort(new sortByCityHelper());
        PrintMailinglabelformat(records);
    }
    public void sortByState(){
        records.sort(new sortByStateHelper());
        PrintMailinglabelformat(records);
    }
    public void sortByZip(){
        records.sort(new sortByZipHelper());
        PrintMailinglabelformat(records);
    }

    public void searchInCityOrState(String firstName,String value,int choice){
        boolean found=false;
        Map<String, List<Person>> commonDict;
        if(choice==0){
            commonDict=personByCityDict;
        }
        else{
            commonDict=personByStateDict;
        }
        for (String k : commonDict.keySet()) {
            if(k.compareTo(value) == 0) {
                List<Person> v = commonDict.get(k);
                for (int i = 0; i < v.size(); i++) {
                    if(v.get(i).getFirstName().compareTo(firstName) == 0) {
                        found = true;
                        System.out.println(v.get(i).getFirstName());
                        System.out.println(v.get(i).getLastName());
                        System.out.println(v.get(i).getAddress());
                        System.out.println(v.get(i).getPhoneNo());
                    }
                }
            }
        }
        if(!found){ //found != true)
            if(choice==0) {
                System.out.println("Person not found in given city");
            }
            else{
                System.out.println("Person not found in given state");
            }
        }
    }

    public void PrintMailinglabelformat(List<Person> recordList){
        for (int i = 0; i < recordList.size(); i++){
            System.out.println(recordList.get(i).getLastName() + " " + recordList.get(i).getFirstName());
            System.out.println(recordList.get(i).getAddress().getAddress());
            System.out.println(recordList.get(i).getAddress().getCity());
            System.out.println(recordList.get(i).getAddress().getState());
            System.out.println(recordList.get(i).getAddress().getZip());
            System.out.println(recordList.get(i).getPhoneNo());
            System.out.println("");
        }
    }

    public void viewByCityOrState(int choice){
        Map<String, List<Person>> commonDict;
        if(choice==0){
            commonDict = personByCityDict;
        }
        else{
            commonDict = personByStateDict;
        }
        for (String k : commonDict.keySet()) {
            List<Person> v = commonDict.get(k);
                System.out.println(k);
                PrintMailinglabelformat(v);
                System.out.println("\n");
        }
    }

}
