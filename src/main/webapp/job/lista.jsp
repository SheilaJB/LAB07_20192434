
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab07.beans.Jobs" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<Jobs> lista = (ArrayList<Jobs>) request.getAttribute("lista");%>

<html>
    <head>
        <title>Lista de Trabajos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="clearfix mt-3 mt-2">
                <a href="<%=request.getContextPath()%>/JobServlet">
                    <h1 class="float-start link-dark">Lista de Trabajos</h1>
                </a>
                <a class="btn btn-primary float-end mt-1" href="<%=request.getContextPath() %>/JobServlet?action=new">Crear trabajo</a>
                <!-- Enlace en la página para ver lista de empleados -->
                <a class="btn btn-primary float-end mt-1 me-4" href="<%=request.getContextPath() %>/Home">Regresar</a>
            </div>
            <hr/>
            <form method="post" action="<%=request.getContextPath()%>/JobServlet?action=s">
                <div class="form-floating mt-3">
                    <input type="text" class="form-control" id="floatingInput"
                           placeholder="Por ID o por nombre" name="textoBuscar" value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : "" %>">
                    <label for="floatingInput">Buscar trabajo</label>
                </div>
            </form>
            <table class="table table-striped mt-3">
                <tr class="table-primary">
                    <th>ID</th>
                    <th>Job Title</th>
                    <th>Min Salary</th>
                    <th>Max Salary</th>
                    <th></th>
                    <th></th>
                </tr>
                <% for (Jobs job : lista) { %>
                <tr>
                    <td><%=job.getjob_id()  %>
                    </td>
                    <td><%=job.getjob_title()%>
                    </td>
                    <td><%=job.getmin_salary()%>
                    </td>
                    <td><%=job.getmax_salary()%>
                    </td>
                    <td><a class="btn btn-success" href="<%=request.getContextPath()%>/JobServlet?action=edit&id=<%= job.getjob_id() %>">Editar</a></td>
                    <td><a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/JobServlet?action=del&id=<%= job.getjob_id() %>">Borrar</a></td>
                </tr>
                <% } %>
            </table>
        </div>
    </body>
</html>
