<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rybchynskyi Max
  Date: 02.09.2017
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Order</title>
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
            margin: 50px 510px;
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
<tr/>
<c:if test="${requestScope.order ne null}">
    <c:if test="${not empty requestScope.order}">
        <form action='<c:out value="${editURL}"></c:out>' method="post">
            <h1>Меню</h1>
            <table>
                <tbody>
                <tr>
                    <th>Назва страви</th>
                    <th>Опис</th>
                    <th>Ціна</th>
                    <th>Додати до замовлення</th>
                </tr>
                <c:forEach items="${requestScope.dishes}" var="dish">
                    <tr>
                        <td><c:out value="${dish.name}"></c:out></td>
                        <td><c:out value="${dish.description}"></c:out></td>
                        <td><c:out value="${dish.price}"></c:out></td>
                        <td> <input type="checkbox" id="addToOrder" name="dishes" value="${dish.id}">
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <input class="button" type="submit" value="Зберегти редаговане замовлення">
        </form>
    </c:if>
</c:if>
</body>
</html>
