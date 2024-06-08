package com.example.lab07.servlet;

import com.example.lab07.beans.Employees;
import com.example.lab07.beans.Jobs;
import com.example.lab07.daos.DaoEmployee;
import com.example.lab07.daos.DaoJobs;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

//  http://localhost:8080/EmployeeServlet
@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        DaoEmployee employeeDao = new DaoEmployee();

        switch (action){

            case "lista":
                //saca del modelo
                ArrayList<Employees> list = employeeDao.listarEmpleados();

                //mandar la lista a la vista -> job/lista.jsp
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("employee/listar.jsp");
                rd.forward(request,response);
                break;
            case "new":
                request.getRequestDispatcher("employee/form_new.jsp").forward(request,response);
                break;
            case "edit":
                String id = request.getParameter("id");
                Employees employee = employeeDao.buscarEmployeePorId(id);

                if(employee != null){

                    request.setAttribute("employee",employee);
                    request.getRequestDispatcher("employee/form_edit.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                }
                break;
            case "del":
                String idd = request.getParameter("id");
                Employees trabajador = DaoEmployee.buscarEmployeePorId(idd);
                if(trabajador != null){
                    try {
                        DaoEmployee.borrarEmpleado(idd);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
            case "MostarListaJob":
                request.getRequestDispatcher("job/lista.jsp").forward(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        DaoEmployee employeeDao = new DaoEmployee();

        switch (action) {
            case "save":
                // Recolectar los datos del formulario
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                String hireDate = request.getParameter("hireDate");
                String jobId = request.getParameter("jobId");


                Employees newEmployee = new Employees();
                newEmployee.setFullNameEmployee(fullName);
                newEmployee.setEmail(email);
                newEmployee.sethire_date(hireDate);
                newEmployee.setjob_id(jobId);

                // Crear el nuevo empleado
                employeeDao.crearEmpleado(newEmployee);

                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
            case "e":
                // Recolectar los datos del formulario
                int updateEmployeeId  = Integer.parseInt(request.getParameter("employeeId"));
                String updateFullName  = request.getParameter("fullName");
                String updateEmail  = request.getParameter("email");
                String updateHireDate  = request.getParameter("hireDate");
                String updateJobId  = request.getParameter("jobId");

                Employees updateEmployee = new Employees();

                updateEmployee.setemployee_id(updateEmployeeId);
                updateEmployee.setFullNameEmployee(updateFullName);
                updateEmployee.setEmail(updateEmail);
                updateEmployee.sethire_date(updateHireDate);
                updateEmployee.setjob_id(updateJobId );

                // Actualizar el empleado
                employeeDao.actualizarEmpleado(updateEmployee);

                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
            case "s":
                String textBuscar = request.getParameter("textoBuscar");
                ArrayList<Employees> lista = DaoEmployee.buscarIdOrName(textBuscar);

                request.setAttribute("lista",lista);
                request.setAttribute("busqueda",textBuscar);
                request.getRequestDispatcher("employee/listar.jsp").forward(request,response);

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }

    }
}