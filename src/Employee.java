public final class Employee implements Comparable<Employee> {
    private final int id; //must be unique for each employee
    private int workYear, remoteRatio; //integer values of an employee
    private String jobTitle, residence, companyLocation, salaryCurrency; //string values of an employee
    private double salary, salaryInUSD; //double values
    private CompanySize companySize; //enum type for size of a company
    private EmploymentType employmentType;
    private ExpLevel xPlvl;
    public Employee(int id){
        this.id = id;
    } //Default constructor used in the SalaryDetails class

    public void setWorkYear(int workYear){this.workYear = workYear;}
    public void setSalaryCurrency(String salaryCurrency){this.salaryCurrency = salaryCurrency;}
    public void setSalary(double salary){this.salary = salary;}
    public void setSalaryInUSD(double salaryInUSD){this.salaryInUSD = salaryInUSD;}
    public void setXPlvl(ExpLevel xPlvl){this.xPlvl = xPlvl;}
    public void setEmploymentType(EmploymentType employmentType){this.employmentType = employmentType;}
    public void setJobTitle(String jobTitle){this.jobTitle = jobTitle;}
    public void setResidence(String residence){this.residence = residence;}
    public void setRemoteRatio(int remoteRatio){this.remoteRatio = remoteRatio;}
    public void setCompanyLocation(String companyLocation){this.companyLocation = companyLocation;}
    public void setCompanySize(CompanySize companySize){this.companySize = companySize;}
    public int getId(){return id;}
    public int getWorkYear(){return workYear;}
    public String getSalaryCurrency(){return salaryCurrency;}
    public double getSalary(){return salary;}
    public double getSalaryInUSD(){return salaryInUSD;}
    public ExpLevel getXPlvl(){return xPlvl;}
    public EmploymentType getEmploymentType(){return employmentType;}
    public String getJobTitle(){return jobTitle;}
    public String getResidence(){return residence;}
    public int getRemoteRatio(){return remoteRatio;}
    public String getCompanyLocation(){return companyLocation;}
    public CompanySize getCompanySize(){return companySize;}

    @Override
    public String toString(){
        return "{id = " + id + ", workYear = " + workYear + ", experience_level = " + xPlvl + ", employment_type = " + employmentType +
                ", job_title = " + jobTitle + ", salary = " + salary + ", salary_currency = " + salaryCurrency + ", salary_in_USD = " +
                salaryInUSD + ", employee_residence = " + residence + ", remote_ratio = " + remoteRatio +
                ", company_location = " + companyLocation + ", company_size = " + companySize + "}";
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Employee) return this.id == ((Employee) o).id;
        return false;
    }

    @Override
    public int hashCode(){
        int bound = 20, result = 0;
        result = id + bound * (jobTitle.length() + residence.length() + companyLocation.length());
        return result;
    }

    @Override
    public int compareTo(Employee o) {
        return this.id - o.id;
    } // this method is used to sort the employees by their id.
}
