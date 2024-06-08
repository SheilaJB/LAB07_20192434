package com.example.lab07.beans;

public class Employees {
    private  int employee_id;
    private  String  fullNameEmployee;
    private  String email;
    private  String password;
    private  String phone_number;
    private  String hire_date;
    private  String job_id;
    private double salary;
    private double commission_pct;
    private int manager_id;
    private  int department_id;
    private  int enabled;
    private  Jobs jobsNombre;

    public Jobs getjobsNombre() {
        return jobsNombre;
    }

    public void setjobsNombre(Jobs jobsNombre) {
        this.jobsNombre = jobsNombre;
    }

    public int getemployee_id() {
        return employee_id;
    }

    public void setemployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public  String getFullNameEmployee() {
        return fullNameEmployee;
    }

    public void setFullNameEmployee(String fullNameEmployee) {
        this.fullNameEmployee = fullNameEmployee;
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getphone_number() {
        return phone_number;
    }

    public void setphone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public  String gethire_date() {
        return hire_date;
    }

    public void sethire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public  String getjob_id() {
        return job_id;
    }

    public void setjob_id(String job_id) {
        this.job_id = job_id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getcommission_pct() {
        return commission_pct;
    }

    public void setcommission_pct(int commission_pct) {
        this.commission_pct = commission_pct;
    }

    public int getmanager_id() {
        return manager_id;
    }

    public void setmanager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public int getdepartment_id() {
        return department_id;
    }

    public void setdepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}

