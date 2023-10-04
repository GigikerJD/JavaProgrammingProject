import com.google.gson.Gson;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner key = new Scanner(System.in);
        String file = "data_science_salaries.csv";
        SalaryDetails sd = new SalaryDetails(file);
        SalariesOperations so = new SalariesOperations(sd);
        String answer = "";

        sd.addAllEmployees();

        System.out.print("What do you wish to do ?\n\tStream operations - (SO)\tI/O files - (IO)\n\t");
        answer = key.next();
        key.nextLine();
        while(true){
            switch (answer) {
                case "SO" -> {
                    System.out.println("""
                            \tDistinct job titles for company location 'FR' in asc order : 1
                            \tJob title, salary for the max salary with company location 'GB' : 2
                            \tJob title, salary and company location for the min salary in USD : 3
                            \tAverage salary in USD of Data Analysts : 4
                            \tDistinct job titles in desc order : 5
                            \tNumber of small, medium and large companies : 6
                            """);
                    System.out.print("\tYour choice ? > ");
                    int operation = key.nextInt();
                    key.nextLine();
                    if (operation == 1) System.out.println(so.JobTitlesInFR());
                    else if (operation == 2) so.getMaxSalaryGB();
                    else if (operation == 3) so.getMinSalaryInUSD();
                    else if (operation == 4) System.out.println("Average salary of Data Analysts : " + so.avgSalaryDataAnalysts());
                    else if (operation == 5) System.out.println(so.jobTitlesDesc());
                    else if (operation == 6) System.out.println(
                            "Number of small companies : " + so.countSmallCompanies()
                                    + "\nNumber of medium companies : " + so.countMediumCompanies()
                                    + "\nNumber of large companies : " + so.countLargeCompanies()
                    );
                    break;
                }
                case "IO" -> {
                    System.out.println("""
                            \tShow file content : 1
                            \tHeaders : 2
                            \tConvert into JSON file : 3
                            """);
                    System.out.print("\tYour choice ? > ");
                    int choice = key.nextInt();
                    key.nextLine();
                    if (choice == 1) sd.showFile();
                    else if (choice == 2) System.out.println(sd.headers());
                    else if (choice == 3) sd.writeToJSONfile();
                    break;
                }
            }

            System.out.print("\nDo you wish to continue ? y/n > ");
            answer = key.next();
            if(answer.equals("n") || answer.equals("N")){
                System.out.println("Good bye, then");
                break;
            }else if(answer.equals("y") || answer.equals("Y")){
                System.out.print("What do you wish to do ?\n\tStream operations - (SO)\tI/O files - (IO)\n\t");
                answer = key.next();
            }
        }
    }
}
