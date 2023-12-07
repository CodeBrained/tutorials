<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<html>
<head>
    <title>To Do List Application</title>
    <link href="styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div>

    <h1>To Do List Application</h1>
    <p>Enter a task to add to the to do list.<br> Enter the ID number of the task you wish to remove.</p>

    <!-- Add form -->
    <form action="${pageContext.request.contextPath}/Add" method="post">
        <label for="add">Add a task</label><br>
        <input type="text" name="addTask" id="add"/>
        <input type="submit" value="Submit"/>
    </form>

    <!-- Delete form -->
    <form action="${pageContext.request.contextPath}/Remove" method="post">
        <label for="remove">Remove a task</label><br>
        <input type="text" name="removeTask" id="remove"/>
        <input type="submit" value="Submit"/>
    </form>

    <form action="${pageContext.request.contextPath}/ViewList" method="get">
        <input type="submit" value="Get List">
    </form>
    <p></p>

    <table>
        <c:forEach items="${tasks}" var="task">
            <tr>
                <td>${task}</td>
            </tr>
        </c:forEach>
    </table>

</div>


</body>
</html>