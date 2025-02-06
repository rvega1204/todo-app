<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <link href="webjars/bootstrap/5.2.3/css/bootstrap.min.css" rel="stylesheet" />
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>List Todos Page</title>
    </head>

    <body>
        <%@ include file="/WEB-INF/jsp/fragments/nav.jspf" %>
            <div class="container">
                <div>
                    <h1>Your Todos</h1>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Description</th>
                                <th>Target Date</th>
                                <th>Is it Done?</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${todos}" var="todo">
                                <tr>
                                    <td>${todo.description}</td>
                                    <td>${todo.targetDate}</td>
                                    <td>${todo.done}</td>
                                    <td>
                                        <a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a>
                                    </td>
                                    <td>
                                        <a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <a href="add-todo" class="btn btn-success">Add Todo</a>
                </div>
            </div>
            
            <%@ include file="/WEB-INF/jsp/fragments/footer.jspf" %>