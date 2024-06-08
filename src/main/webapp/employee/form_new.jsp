<%@ page import="com.example.lab07.beans.Jobs" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <head>
        <title>Agregar Nuevo Empleado</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
    </head>
    <body>
    <div class="container">
        <h1>Agregar Nuevo Empleado</h1>
        <form method="post" action="<%=request.getContextPath()%>/EmployeeServlet?action=save">
            <%--Nombre completo--%>
            <div class="mb-3">
                <label for="fullName" class="form-label">Nombre Completo</label>
                <input type="text" class="form-control" id="fullName" name="fullName" required>
            </div>
            <%--Correo--%>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
                <%--Hire Date--%>
            <div class="mb-3">
                <label for="hireDate" class="form-label">Fecha de Contratación</label>
                <input type="datetime-local" class="form-control" id="hireDate" name="hireDate" required>
            </div>
                <%--Job ID--%>
                <div class="mb-3">
                    <label for="jobId" class="form-label">ID del Trabajo</label>
                    <input type="text" class="form-control" id="jobId" name="jobId" required>
                </div>
            <div class="mb-3">
                <a class="btn btn-secondary" href="<%=request.getContextPath()%>/EmployeeServlet">Regresar</a>
                <button type="submit" class="btn btn-primary" >Submit</button>
            </div>
        </form>
    </div>
    </body>
</html>
