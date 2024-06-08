package com.example.lab07.daos;
import com.example.lab07.beans.Jobs;

import java.sql.*;
import java.util.ArrayList;

public class DaoJobs {

    //Listar los trabajos
    public ArrayList<Jobs> listarTrabajos(){

        ArrayList<Jobs> lista = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String sql = "select * from jobs;";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                Jobs trabajo = new Jobs();
                trabajo.setjob_id(rs.getString(1));
                trabajo.setjob_title(rs.getString(2));
                trabajo.setmin_salary(rs.getInt(3));
                trabajo.setmax_salary(rs.getInt(4));
                lista.add(trabajo);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  lista;
    }

    //Buscar un trabajo por su ID
    public Jobs buscarPorId(String id){

        Jobs job = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from jobs where job_id = ?;";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    job = new Jobs();
                    job.setjob_id(rs.getString(1));
                    job.setjob_title(rs.getString(2));
                    job.setmin_salary(rs.getInt(3));
                    job.setmax_salary(rs.getInt(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return job;
    }

    //Crear un trabajo
    public void crearTrabajo(String jobId, String jobTitle, int minSalary, int maxSalary){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "insert into jobs (job_id, job_title, min_salary,max_salary) values (?,?,?,?);";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,jobId);
            pstmt.setString(2,jobTitle);
            pstmt.setInt(3,minSalary);
            pstmt.setInt(4,maxSalary);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Actualizar un trabajo
    public void actualizarTrabajo(Jobs job){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "update jobs set job_title = ?, min_salary = ?, max_salary = ? where job_id = ?;";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,job.getjob_title());
            pstmt.setInt(2,job.getmin_salary());
            pstmt.setInt(3,job.getmax_salary());
            pstmt.setString(4,job.getjob_id());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Borrar un trabajo
    public void borrarTrabajo(String jobId) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "delete from jobs where job_id = ?;";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,jobId);
            pstmt.executeUpdate();

        }
    }

    //Buscar trabajo por Id o Titulo
    public ArrayList<Jobs> buscarIdOrTitle(String name){

        ArrayList<Jobs> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from jobs where job_id = ? or lower(job_title) like lower(?);";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,name);
            pstmt.setString(2,"%" + name + "%");

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Jobs job = new Jobs();
                    job.setjob_id(rs.getString(1));
                    job.setjob_title(rs.getString(2));
                    job.setmin_salary(rs.getInt(3));
                    job.setmax_salary(rs.getInt(4));

                    lista.add(job);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}
