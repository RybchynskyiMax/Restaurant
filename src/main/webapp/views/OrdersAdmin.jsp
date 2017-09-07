<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rybchynskyi Max
  Date: 02.09.2017
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
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
        h1{
            margin-top: 25px;
            text-align: center;
            font-family: sans-serif;
            color: white;
        }
        tr,th,td{
            padding: 3px;
            border:1px  solid white;
        }
        table{
            text-align: center;
            margin: auto;

            color: white;
            font-family: sans-serif;

        }
        .confirm_bill{
            margin: 5px 30px;
            font-family: sans-serif;
            font-size: 12pt;
            display: inline-block;
            text-decoration: none;
            color: white;
        }
        .confirm_bill:hover{
            color: yellow;
        }
        a:hover{
            color:yellow;
        }
    </style>
</head>
<body>
<div class="nav">
    <a class="links" href="/menuadmin">Меню</a>
    <a class="links" href="/ordersadmin">Замовлення</a>
    <a class="links" href="/billsadmin">Рахунки</a>
    <a class="links" href="/changerole">Змінити роль</a>
</div>
<h1>Не опрацьовані замовлення</h1>
<table>
    <tbody>
    <tr>
        <th>Опис замовлення</th>
        <th>Сума до сплати</th>
        <th>Підтвердження</th>
    </tr>
    <c:forEach items="${requestScope.notConfirmedOrders}" var="notConfirmedOrder">
        <c:url value="/confirmorder" var="confirmURL">
            <c:param name="id" value="${notConfirmedOrder.id}"></c:param>
        </c:url>
        <tr>
            <td><c:forEach items="${notConfirmedOrder.dishes}" var="dish"><c:out value="${dish.name}, "></c:out></c:forEach></td>
            <td><c:out value="${notConfirmedOrder.money}"></c:out></td>
            <td><a class="confirm_bill" href='<c:out value="${confirmURL}" escapeXml="true"></c:out>'>Підтвердити</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h1>Опрацьовані замовлення</h1>
<table>
    <tbody>
    <tr>
        <th>Опис замовлення</th>
        <th>Сума</th>
    </tr>
    <c:forEach items="${requestScope.confirmedOrders}" var="confirmedOrder">
        <c:url value="/addbill" var="addURL">
            <c:param name="id" value="${confirmedOrder.id}"></c:param>
        </c:url>
        <tr>
            <form action="<c:out value="${addURL}"></c:out>"method="post">
            <td><c:forEach items="${confirmedOrder.dishes}" var="dish"><c:out value="${dish.name}, "></c:out></c:forEach></td>
            <td><c:out value="${confirmedOrder.money}"></c:out></td>
            <td style="border: none !important;"> <input  type="submit" value="Виписати чек"></td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
