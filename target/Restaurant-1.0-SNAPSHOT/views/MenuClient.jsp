<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rybchynskyi Max
  Date: 02.09.2017
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <style type="text/css">
        body{
            background: url(http://septfon.ru/uploads/images/o/b/o/oboi_bezhevie_odnotonnie_foto_7.jpg) no-repeat;
            -moz-background-size: 100%;
            -webkit-background-size: 100%;
            -o-background-size: 100%;
            background-size: 100%;
        }
        .nav{
            text-align: center;
            height: 50px;
            margin-top: 10px;
            border-bottom:5px white solid;
        }
        .links{
            margin: 5px 30px;
            font-family: sans-serif;
            font-size: 15pt;
            display: inline-block;
            text-decoration: none;
            color: white;
        }
        tr,th,td{
            padding: 5px;
            border:1px  solid white;
        }
        table{
            text-align: center;
            margin: auto;

            color: white;
            font-family: sans-serif;

        }
        .button{
            background-image: url(http://septfon.ru/uploads/images/o/b/o/oboi_bezhevie_odnotonnie_foto_7.jpg);
            margin: 50px 560px;
            font-family: sans-serif;
            font-size: 25pt;
            display: inline-block;
            text-decoration: none;
            color: yellow;
        }
        h1{
            margin-top: 25px;
            text-align: center;
            font-family: sans-serif;
            color: white;
        }
        a:hover{
            color:yellow;
        }
    </style>
</head>
<body>
<div class="nav">
    <a class="links" href="/menuclient">Меню</a>
    <a class="links" href="/ordersclient">Замовлення</a>
    <a class="links" href="/billsclient">Рахунки</a>
    <a class="links" href="/changerole">Змінити роль</a>
</div>
<c:url value="/addorder" var="addURL"></c:url>

<form action='<c:out value="${addURL}"></c:out>' method="post">
   <h1>Меню</h1>
    <table>
        <tbody>
        <tr>
            <th>Назва</th>
            <th>Опис</th>
            <th>Ціна</th>
            <th>Додати до замовлення</th>
        </tr>
        <c:forEach items="${requestScope.dishes}" var="dish">
            <tr>
                <td><c:out value="${dish.name}"></c:out></td>
                <td><c:out value="${dish.description}"></c:out></td>
                <td><c:out value="${dish.price}"></c:out></td>
                <td> <input type="checkbox" id="addToOrder" name="dishes" value="${dish.id}"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input class="button" type="submit" value="Створити замовлення">
</form>
<c:if test="${requestScope.error ne null}">
    <h1 style="color: red !important;"><c:out
            value="${requestScope.error}"></c:out></h1>
</c:if>
<c:if test="${requestScope.success ne null}">
    <h1 style="color: green !important;"><c:out
            value="${requestScope.success}"></c:out></h1>
</c:if>
</body>
</html>
