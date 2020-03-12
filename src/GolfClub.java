import java.util.*;
import java.util.Map.Entry;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class GolfClub {
    public static Map<String,Integer> details = new HashMap<String,Integer>();       //defined hashmap to store strings as keys and integers as values
    public static Map<String,Integer> restore = new HashMap<String,Integer>();       //defined hashmap to restore deleted data
    public static void main(String[]args) {
        menu();                                                                      //calling menu method inside the main method
    }

    public static void menu() {                                                      //method menu
        Scanner scanInMenu = new Scanner(System.in);                                 // scanner to get input in the menu method
        int option;                                                                  //initialized option variable
        try {                                                                        //try-catch block starts here
            System.out.println("Welcome to Springfield Golf Club.");
            System.out.println("Select Option:");
            System.out.println("1)  Enter Scores");
            System.out.println("2)  Find Golfer");
            System.out.println("3)  Display Scoreboard");
            System.out.println("4)  Exit Program");
            System.out.println(">");
            option = scanInMenu.nextInt();

            switch (option) {
                case 1:
                    enterScoresOptions();
                    break;
                case 2:
                    findGolfer();
                    break;
                case 3:
                    displayScoreboard();
                    break;
                case 4:
                    exitProgram();
                    break;
                default:
                    menu();
            } //switch block ends here
        }catch (InputMismatchException ex){
            System.out.println("Invalid input");
            menu();
        }
    } //mene method ends here

    public static void enterScoresOptions() {                        // method enterScoresOption
        Scanner inputTwo = new Scanner(System.in);
        int select;                                                  ////initialized select variable
        try {
            System.out.println("1)enter player details ");
            System.out.println("2)edit data ");
            System.out.println("3)delete details ");
            System.out.println("4)restore data ");
            System.out.println("5)exit ");
            select = inputTwo.nextInt();
            switch (select){
                case 1:
                    enterScores();
                    break;
                case 2:
                    editDetails();
                    break;
                case 3:
                    deleteDetails();
                    break;
                case 4:
                    restoreData();
                    break;
                case 5:
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid input");
                    enterScoresOptions();
            } // switch block ends here
        }catch (InputMismatchException ex){
            System.out.println("Invalid input");
            enterScoresOptions();
        }
    } //enterScoresOption method ends here

        public static void enterScores()  {                                             //enterScores method
            Scanner scanInEnterScore = new Scanner(System.in);
            System.out.print("How many players: ");
            int noOfPlayers=0;                                                          //declared variable noOfPlayers as an integer and initializing the variable to zero
            try {
                noOfPlayers = scanInEnterScore.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input enter a valid input");
                enterScores();
            }

            for (int i = 1; i <= noOfPlayers; i++) {                                    //for loop to initialize the variable,to add the condition and add an increment
                Scanner scanName = new Scanner(System.in);

                System.out.print("Name: ");
                String name = scanName.nextLine();                                     //declared name variable as a String
                if(details.containsKey(name)){
                    System.out.println("This name is already existing and the results will be altered.");
                    i--;
                }

                System.out.print("Result: ");
                int score = 0;                                                       //declared variable score as an integer and initializing the variable to zero
                try {
                    score = scanInEnterScore.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input");
                    System.out.println("Please enter scores between the range of 18 to 108 ");
                    System.out.println("You may have to start the process from the beginning");
                    enterScores();
                }

                while (score < 18 || score > 108) {                                  //while loop is checking the conditions are true if it is it will start looping
                    if (score < 18 || score > 108) {
                        System.out.println("Enter scores between the range of 18 to 108");
                        System.out.print("Result: ");
                        score = scanInEnterScore.nextInt();
                        details.put(name, score);                                     //assign key and value to the hash map
                    }
                }
                details.put(name, score);
            } //for loop ends here
            menu();
        } //enterScores method ends here


    public static void findGolfer(){                                                      //method findGolfer
        Scanner scanInFind = new Scanner(System.in);
        System.out.print("Enter the name: ");
        String findName = scanInFind.nextLine();                                          //declared findName variable as a String
        if (details.containsKey(findName)){                                               //to check the entered name contains in the hashmap
            System.out.println("Entered name has following results!");
            System.out.println("The results for the entered name "+findName+" is "+details.get(findName));
            menu();
        }else {
            System.out.println("No results has been found for the entered name "+findName);
            menu();
        }
    }  //findGolfer method ends here

    public static void displayScoreboard(){
        Map<String,Integer> sorted = details.entrySet().stream().sorted(comparingByValue()).collect(toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2) -> 2,LinkedHashMap::new));  //to sort the hashmap by value
        System.out.println("****SCORE BOARD****");
        for(Entry sort : sorted.entrySet()) {
            System.out.println(sort.getKey()+"  :  "+sort.getValue());
        }
        menu();
    } //displayScoreboard method ends here

    public static void exitProgram(){                                                   //method exitProgramme
        System.out.println("The program will exit now!");
        System.exit(0);                                                          //to exit the programme
    } //exitProgramme method ends here

    public static void editDetails(){
        System.out.println(details);                                                    //display the key and values in the hash map
        System.out.println("1)edit results");
        System.out.println(">");
        Scanner editData = new Scanner(System.in);
        int choice=0;                                                                   //declared variable choice as an integer and initializing the variable to zero
        try {
            choice = editData.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input enter a valid input");
            editDetails();
        }

        if (choice==1){                                                                 //if block starts here
            Scanner inputOne = new Scanner(System.in);
            System.out.print("Enter the name: ");
            String editName = inputOne.nextLine();                                      //declared editName variable as a String
            if (details.containsKey(editName)) {
                System.out.print("Enter results: ");
                Scanner inputResult = new Scanner(System.in);
                int editResult=0;                                                       //declared variable editResults as an integer and initializing the variable to zero
                try {
                    editResult = inputResult.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input");
                    editDetails();
                }
                details.replace(editName, editResult);                                  //replacing the new key and value to the hashmap

                while (editResult < 18 || editResult > 108) {
                    if (editResult < 18 || editResult > 108) {
                        System.out.println("Enter scores between the range of 18 to 108");
                        System.out.println("Enter the score: ");
                        editResult = inputResult.nextInt();
                        details.replace(editName, editResult);
                }
            } //while loop ends here
                System.out.println("Do you want to edit more data? (y/n)");
                String moreData = editData.next();                                       //declared moreData variable as a String
                if(moreData.toLowerCase().equals("y")){
                    editDetails();
                }else{
                    menu();
                }

            }else {
                System.out.println("Entered Name "+ editName + " Can Not Be Found");
                System.out.println("Do you want to search another name? (y/n)");
                String searchAgain = editData.next();                                    //declared searchAgain variable as a String
                if(searchAgain.toLowerCase().equals("y")){
                    editDetails();
                }else{
                    menu();
                }
            }
        }else{
            System.out.println("Invalid input");
            editDetails();
        }
    }



    public static void deleteDetails(){                                             //deleteDetails method
        System.out.print("Enter name you want to delete: ");
        Scanner inputDeleteName = new Scanner(System.in);
        String deleteName = inputDeleteName.nextLine();                             //declared deleteName variable as a String
        if (details.containsKey(deleteName)){                                       //to check the entered name contains in the details hashmap
            restore.put(deleteName,details.get(deleteName));                        //to add the name and the value to the other restore hashmap
            System.out.println("All the data for the entered name was removed");
            details.remove(deleteName);                                             //to remove the entered name from the details hashmap
            System.out.println("Do you want to delete another name?(y/n)");
            String deleteAgain = inputDeleteName.nextLine();
            if(deleteAgain.toLowerCase().equals("y")){                              //to check the entered string is 'y' or 'Y'
                deleteDetails();
            }
            else if(deleteAgain.toLowerCase().equals("n")){
                menu();
            }
            else{
                System.out.println("Invalid input");
                menu();
            }
        }else {
            System.out.println("Your name doesn't exists ");
            System.out.println("do you want to search another name? (y/n)");
            String reTry = inputDeleteName.next();                                    //declared reTry variable as a String
            if(reTry.toLowerCase().equals("y")){
                deleteDetails();
            }else if(reTry.equals("n")){
                menu();
            }else{
                System.out.println("You have entered an invalid input");
                menu();
            }
        }
    } //deleteDetails method ends here

    public static void restoreData(){                                                    // method restoreData
        System.out.println("Recovered data");
        System.out.println(restore);                                                     //display the deleted data from the previous hashmap
        details.putAll(restore);                                                         //adding all the details to the hashmap including the restored data
        menu();
    }

}


