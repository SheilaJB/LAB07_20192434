package com.example.lab07.daos;
import com.example.lab07.beans.Employees;
import com.example.lab07.beans.Jobs;

import java.sql.*;
import java.util.ArrayList;

public class DaoEmployee {

    //Listar los empleados
    public static ArrayList<Employees> listarEmpleados(){

        ArrayList<Employees> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String sql ="SELECT \n" +
                "    e.employee_id, \n" +
                "    CONCAT(e.first_name, ' ', e.last_name) AS full_name,\n" +
                "    e.email,\n" +
                "    DATE(e.hire_date) AS hire_date,\n" +
                "    j.job_id\n" +
                "FROM \n" +
                "    hr.employees e\n" +
                "LEFT JOIN \n" +
                "    hr.jobs j ON e.job_id = j.job_id;\n";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()){

                Employees empleado = new Employees();

                empleado.setemployee_id(rs.getInt("employee_id"));
                empleado.setFullNameEmployee(rs.getString("full_name"));
                empleado.setEmail(rs.getString("email"));
                empleado.sethire_date(rs.getString("hire_date"));
                empleado.setjob_id(rs.getString("job_id"));

                lista.add(empleado);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    //Buscar Employee por su ID
    public static Employees buscarEmployeePorId(String idEmployee){

        Employees empleado = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String sql ="SELECT \n" +
                "    e.employee_id, \n" +
                "    CONCAT(e.first_name, ' ', e.last_name) AS full_name,\n" +
                "    e.email,\n" +
                "    DATE(e.hire_date) AS hire_date,\n" +
                "    j.job_id\n" +
                "FROM \n" +
                "    hr.employees e\n" +
                "LEFT JOIN \n" +
                "    hr.jobs j ON e.job_id = ?;\n";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,idEmployee);

            try(ResultSet rs = pstmt.executeQuery()){

                if (rs.next()){
                    empleado = new Employees();

                    empleado.setemployee_id(rs.getInt("employee_id"));
                    empleado.setFullNameEmployee(rs.getString("full_name"));
                    empleado.setEmail(rs.getString("email"));
                    empleado.sethire_date(rs.getString("hire_date"));
                    empleado.setjob_id(rs.getString("job_id"));
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  empleado;
    }

    //Buscar por Id o Nombre
    public static ArrayList<Employees> buscarIdOrName(String name){

        ArrayList<Employees> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "SELECT CONCAT(e.first_name, ' ', e.last_name) AS full_name, e.employee_id, e.email, DATE(e.hire_date) AS hire_date, e.job_id\n" +
                "FROM hr.employees e\n" +
                "WHERE e.employee_id = ? \n" +
                "   OR LOWER(CONCAT(e.first_name, ' ', e.last_name)) LIKE LOWER(?);\n";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,name);
            pstmt.setString(2,"%" + name + "%");

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Employees employee = new Employees();

                    employee.setemployee_id(rs.getInt("employee_id"));
                    employee.setFullNameEmployee(rs.getString("full_name"));
                    employee.setEmail(rs.getString("email"));
                    employee.sethire_date(rs.getString("hire_date"));
                    employee.setjob_id(rs.getString("job_id"));
                    lista.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }


    //Crear un empleado
    public  void  crearEmpleado(Employees employee ){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String  sql ="INSERT INTO employees (first_name, last_name, email, hire_date,job_id ) VALUES (?, ?, ?, ?,?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            String[] names = employee.getFullNameEmployee().split(" ");
            pstmt.setString(1, names[0]);
            pstmt.setString(2, names.length > 1 ? names[1] : "");
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.gethire_date());
            pstmt.setString(5, employee.getjob_id());

            //pstmt.executeUpdate();
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating employee failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    employee.setemployee_id(id);
                } else {
                    throw new SQLException("Creating employee failed, no ID obtained.");
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Actualizar un empleado
    public void  actualizarEmpleado (Employees employee){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String sql = "update employees set first_name = ?, last_name = ?, email = ?, job_id = ? where employee_id = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            String[] name = employee.getFullNameEmployee().split(" ");
            pstmt.setString(1, name[0]);
            pstmt.setString(2, name.length > 1 ? name[1] : "");
            pstmt.setString(3,employee.getEmail());
            pstmt.setString(4,employee.getjob_id());
            pstmt.setInt(5, employee.getemployee_id());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Borrar un employee
    public static void  borrarEmpleado(String  idEmployee) throws  SQLException{
        try{
            String url = "jdbc:mysql://localhost:3306/hr";
            String username = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            try(Connection conn = DriverManager.getConnection(url, username, password)){

                String sql ="DELETE FROM employees WHERE employee_id =?";

                try (PreparedStatement pstmt = conn.prepareStatement(sql)){
                    pstmt.setString(1, idEmployee);
                    pstmt.executeUpdate();
                }

            }
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
