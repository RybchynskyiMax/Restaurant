<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Rybchynskyi Max
  Date: 02.09.2017
  Time: 19:53
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
            margin-top: 15px;
            font-family: sans-serif;
            font-size: 12pt;
        }
        h1{
            margin-top: 25px;
            text-align: center;
            font-family: sans-serif;
            color: white;
        }
        .edit_delete{
            margin: 5px 30px;
            font-family: sans-serif;
            font-size: 12pt;
            display: inline-block;
            text-decoration: none;
            color: white;
        }
        .edit_delete:hover{
            color: yellow;
        }
        .new_dish{
            text-align: center;
            margin:  30px auto;
            border: solid 3px white;
            padding-top: 20px;
            width:500px;
            color: white;
            font-family: sans-serif;
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
<c:url value="/adddish" var="addURL"></c:url>
<c:url value="/updatemenu" var="editURL"></c:url>


<c:if test="${requestScope.dish eq null}">
  <div class="new_dish">
      <form action='<c:out value="${addURL}"></c:out>' method="post">
      Назва страви: <input type="text" name="name">

      Ціна: <input type="text" name="price" >

      Опис: <textarea style="margin-top: 15px" name="description" cols="57" rows="1"></textarea>

      <input class="button" type="submit" value="Додати нову страву до меню">

  </form>
  </div>
</c:if>

    <h1>Меню</h1>
    <table>
        <tbody>
        <tr>
            <th>Назва страви</th>
            <th>Опис страви</th>
            <th>Ціна</th>
            <th>Редагування</th>
            <th>Видалення</th>
        </tr>
        <c:forEach items="${requestScope.dishes}" var="dish">
            <c:url value="/updatemenu" var="editURL">
                <c:param name="id" value="${dish.id}"></c:param>
            </c:url>
            <c:url value="/deletedish" var="deleteURL">
                <c:param name="id" value="${dish.id}"></c:param>
            </c:url>
            <tr>
                <td><c:out value="${dish.name}"></c:out></td>
                <td><c:out value="${dish.description}"></c:out></td>
                <td><c:out value="${dish.price}"></c:out></td>
                <td><a class="edit_delete" href='<c:out value="${editURL}" escapeXml="true"></c:out>'>Редагувати</a></td>
                <td><a class="edit_delete" href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Видалити</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
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
