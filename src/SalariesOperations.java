import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SalariesOperations { //all streams methods will be implemented here
    private String file;
    private SalaryDetails sd = new SalaryDetails(file);

    public SalariesOperations(SalaryDetails sd){
        this.sd = sd;
    }

    public void setFile(String file) {
        this.file = file;
    }
    public String getFile() {
        return file;
    }

    //distinct job titles for company locaton FR in ascending order (predicate) - DONE
    public List<String> JobTitlesInFR() {
        return sd.getStaff().
                stream().
                filter(loc -> Objects.equals(loc.getCompanyLocation(), "FR")).
                map(Employee::getJobTitle).
                distinct().
                sorted()
                .toList();
    }

    //average salary in USD of Data Analysts in the entire data set - DONE
    public double avgSalaryDataAnalysts() {
        return sd.getStaff().
                stream().
                filter(l -> Objects.equals(l.getJobTitle(), "Data Analyst")).
                mapToDouble(Employee::getSalaryInUSD).
                average().
                getAsDouble();
    }

    //number of small companies - DONE
    public int countSmallCompanies(){
        return (int) sd.getStaff().
                stream().
                filter(l -> l.getCompanySize() == CompanySize.S).
                count();
    }

    //number of medium companies - DONE
    public int countMediumCompanies(){
        return (int) sd.getStaff().
                stream().
                filter(l -> l.getCompanySize() == CompanySize.M).
                count();
    }

    //number of large companies - DONE
    public int countLargeCompanies() {
        return (int) sd.getStaff().
                stream().
                filter(l -> l.getCompanySize() == CompanySize.L).
                count();
    }

    //distinct job titles in the entire data set in descending order (comparator) - DONE
    public List<String> jobTitlesDesc(){
        return sd.getStaff().stream().map(Employee::getJobTitle).distinct().
                sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {return o2.compareTo(o1);}
                }).toList();
    }

    //job title, salary for the max salary with company location GB (predicate, supplier and comparator) - DONE
    public void getMaxSalaryGB(){
        sd.getStaff().stream().filter(l -> Objects.equals(l.getCompanyLocation(), "GB"))
                .max(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {return Double.compare(o1.getSalary(), o2.getSalary());}
                })
                .ifPresent(l -> System.out.println("jobTitle = " + l.getJobTitle() + ", salary = " + l.getSalary()));
    }


    //job title, salary for the min salary in USD in the entire data set (supplier and comparator) - DONE
    public void getMinSalaryInUSD(){
        sd.getStaff().stream().min(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {return Double.compare(o1.getSalaryInUSD(), o2.getSalaryInUSD());}
        }).ifPresent(
                l -> System.out.println("jobTitle = " + l.getJobTitle() +
                        ", salary = " + l.getSalary() +
                        ", companyLocation = " + l.getCompanyLocation())
        );
    }

    //Little + for better visuals
    public List<String> currencies(){
        return sd.getStaff().stream().map(Employee::getSalaryCurrency).distinct().toList();
    }
    public List<EmploymentType> employmentType(){
        return sd.getStaff().stream().map(Employee::getEmploymentType).distinct().toList();
    }

    public List<String> companiesLocations(){
        return sd.getStaff().stream().map(Employee::getCompanyLocation).distinct().toList();
    }

    public List<ExpLevel> expLevel(){
        return sd.getStaff().stream().map(Employee::getXPlvl).distinct().toList();
    }
}