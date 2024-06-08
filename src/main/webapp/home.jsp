
<%@ page import="com.example.lab07.beans.Employees" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
  </head>
  <body>
    <div class="container">
      <div class="clearfix mt-3 mt-2">
        <a href="<%=request.getContextPath()%>/EmployeeServlet">
          <h1 class="float-start link-dark">¡Bienvenido!</h1>
        </a>
      </div>
      <div>
        <a class="btn btn-primary float-end mt-1 " href="<%=request.getContextPath() %>/EmployeeServlet?action=lista">Ver Empleados</a>
        <!-- Enlace en la página para ver lista de trabajos -->
        <a class="btn btn-primary float-end mt-1 me-4" href="<%=request.getContextPath() %>/JobServlet?action=lista">Ver Trabajos</a>
      </div>
    </div>
  </body>
</html>