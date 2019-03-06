<%--
  Created by IntelliJ IDEA.
  User: roudder
  Date: 2019-03-06
  Time: 05:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <style>
        .green {
            color: green;
        }

        .red {
            color: red;
        }
    </style>

</head>
<body>

<table border="1">
    <tr>
        <th>Date</th>
        <th>Calories</th>
        <th>Description</th>
    </tr>

    <c:forEach var="meal" items="${filteredWithExcess}">
        <c:choose>
            <c:when test="${meal.isExcess() eq false}">
                <tr>
                    <td class="red"> ${meal.getDateTime()}</td>
                    <td class="red"> ${meal.getCalories()}</td>
                    <td class="red"> ${meal.getDescription()}</td>
                </tr>

            </c:when>
            <c:otherwise>
                <tr>
                    <td class="green"> ${meal.getDateTime()}</td>
                    <td class="green"> ${meal.getCalories()}</td>
                    <td class="green"> ${meal.getDescription()}</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</table>
</body>
</html>

