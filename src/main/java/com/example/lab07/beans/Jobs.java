package com.example.lab07.beans;

public class Jobs {
    private  String job_id;
    private  String job_title;
    private int min_salary;
    private  int max_salary;

    public String getjob_id() {
        return job_id;
    }
    public void setjob_id(String job_id) {
        this.job_id = job_id;
    }
    public String getjob_title() {
        return job_title;
    }
    public void setjob_title(String job_title) {
        this.job_title = job_title;
    }
    public int getmin_salary() {
        return min_salary;
    }
    public void setmin_salary(int min_salary) {
        this.min_salary = min_salary;
    }
    public int getmax_salary() {
        return max_salary;
    }
    public void setmax_salary(int max_salary) {
        this.max_salary = max_salary;
    }
    
}
