<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab07.beans.Employees" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<Employees> lista = (ArrayList<Employees>) request.getAttribute("lista");%>

<html>
<head>
    <title>Lista de Empleados</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="clearfix mt-3 mt-2">
        <a href="<%=request.getContextPath()%>/EmployeeServlet">
            <h1 class="float-start link-dark">Lista de Empleados</h1>
        </a>
        <a class="btn btn-primary float-end mt-1 " href="<%=request.getContextPath() %>/EmployeeServlet?action=new">Agregar Empleado</a>
        <!-- Enlace en la página para ver lista de trabajos -->
        <a class="btn btn-primary float-end mt-1 me-4" href="<%=request.getContextPath() %>/Home">Regresar</a>
    </div>
</div>
<hr/>
<form method="post" action="<%=request.getContextPath()%>/EmployeeServlet?action=s">
    <div class="form-floating mt-3">
        <input type="text" class="form-control" id="floatingInput"
               placeholder="Por ID o por nombre" name="textoBuscar" value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : "" %>">
        <label for="floatingInput">Buscar Empleado</label>
    </div>
</form>
<table class="table table-striped mt-3">
    <tr class="table-primary">
        <th>ID</th>
        <th>Nombre Completo</th>
        <th>Email</th>
        <th>Fecha de Contratación</th>
        <th>ID de Trabajo</th>
        <th></th>
        <th></th>
    </tr>
    <% for (Employees empleado : lista) { %>
    <tr>
        <td><%=empleado.getemployee_id() %></td>
        <td><%=empleado.getFullNameEmployee() %></td>
        <td><%=empleado.getEmail() %></td>
        <td><%=empleado.gethire_date() %></td>
        <td><%=empleado.getjob_id() %></td>
        <td><a class="btn btn-success" href="<%=request.getContextPath()%>/EmployeeServlet?action=edit&id=<%= empleado.getemployee_id() %>">Editar</a></td>
        <td><a onclick="return confirm('¿Está seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/EmployeeServlet?action=del&id=<%= empleado.getemployee_id() %>">Borrar</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>
