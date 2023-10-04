import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class SalaryDetails extends Exception implements AutoCloseable {
    private final List<Employee> staff = new ArrayList<>();
    private final String file;
    private BufferedReader br = null;
    public SalaryDetails(String file){
        this.file = file;
    }
    public String getFile(){return file;}
    public List<Employee> getStaff() {return staff;}

    public void showFile() throws FileNotFoundException { //display the whole csv file's content
        br = new BufferedReader(new FileReader(this.file));
        br.lines().forEach(System.out::println);
    }

    public long countEmployees() throws FileNotFoundException{ //return the number of employees in the dataset
        br = new BufferedReader(new FileReader(this.file));
        return br.lines().count()-1;
    }

    public int size() throws FileNotFoundException { //logical size of the file to avoid harmful iteration
        br = new BufferedReader(new FileReader(this.file));
        return (int) countEmployees() + 1;
    }

    public String lineAt(int index) throws FileNotFoundException { //return the line of the specified id of an employee (headers included)
        br = new BufferedReader(new FileReader(this.file));
        return br.lines().toList().get(index);
    }

    public List<String> headers() throws FileNotFoundException { //return the headers of the csv file (useful for knowing their indexes)
        br = new BufferedReader(new FileReader(this.file));
        return Arrays.stream(br.lines().toList().get(0).split(",")).toList();
    }

    public Employee EmployeeAtLine(int index) throws FileNotFoundException { //each line is converted a new Employee object
        br = new BufferedReader(new FileReader(this.file));
        String l = lineAt(index + 1);
        String[] datas = l.split(",");
        Employee e = new Employee(Integer.parseInt(datas[0]));

        //Integer and Double values for the employee in the dataset
        e.setWorkYear(Integer.parseInt(datas[1]));
        e.setSalary(Double.parseDouble(datas[5]));
        e.setSalaryInUSD(Double.parseDouble(datas[7]));
        e.setRemoteRatio(Integer.parseInt(datas[9]));

        //String and enum values for the employee in the dataset
        e.setCompanyLocation(datas[10]);
        e.setXPlvl(ExpLevel.valueOf(datas[2]));
        e.setEmploymentType(EmploymentType.valueOf(datas[3]));
        e.setJobTitle(datas[4]);
        e.setSalaryCurrency(datas[6]);
        e.setResidence(datas[8]);
        e.setCompanySize(CompanySize.valueOf(datas[11]));
        return e;
    }

    public void addAllEmployees() throws FileNotFoundException {
        for(int i = 0; i < size()-1; i++) staff.add(EmployeeAtLine(i));
    }


    public void writeToJSONfile() throws IOException {
        System.out.println("Starting to write to JSON file...");
        BufferedWriter wr = new BufferedWriter(new FileWriter("salaries.json"));
        List<Employee> copy = getStaff();
        Gson gson = new Gson();
        copy.sort((o1, o2) -> Double.compare(o1.getSalaryInUSD(), o2.getSalaryInUSD()));
        wr.write("{" + '"' + "employees" + '"' + " :[\n");
        for(Employee e : copy){
            String json = gson.toJson(e);
            if(copy.indexOf(e) != copy.size()-1) wr.write("\t" + json + ",\n");
            else wr.write("\t" + json + "\n");
        }
        wr.write("]}");
        System.out.println("Finished writing to JSON file.");
        wr.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("SalariesDetails closed()");
    }
}
