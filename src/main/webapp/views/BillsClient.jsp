<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rybchynskyi Max
  Date: 02.09.2017
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bills</title>
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
        .pay{
            margin: 5px 30px;
            font-family: sans-serif;
            font-size: 12pt;
            display: inline-block;
            text-decoration: none;
            color: white;
        }
        .pay:hover{
            color: yellow;
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
<c:if test="${requestScope.error ne null}">
    <h1 style="color: red;"><c:out
            value="${requestScope.error}"></c:out></h1>
</c:if>
<c:if test="${requestScope.success ne null}">
    <h1 style="color: green;"><c:out
            value="${requestScope.success}"></c:out></h1>
</c:if>
<h1>Рахунки</h1>
    <table>
        <tbody>
        <tr>
            <th>Опис замовлення</th>
            <th>Сума</th>
            <th>Оплата</th>
        </tr>
        <c:forEach items="${requestScope.bills}" var="bill">
            <c:url value="/deletebill" var="deleteURL">
                <c:param name="id" value="${bill.id}"></c:param>
            </c:url>
            <tr>
                <td><c:out value="${bill.description}"></c:out></td>
                <td><c:out value="${bill.money}"></c:out></td>
                <td><a class="pay" href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Оплатити</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
