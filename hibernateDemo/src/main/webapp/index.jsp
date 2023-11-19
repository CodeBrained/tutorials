<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<html>
<body>

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

<table>
    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>${task}</td>
        </tr>
    </c:forEach>
</table>




</body>
</html>