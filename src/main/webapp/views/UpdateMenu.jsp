<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rybchynskyi Max
  Date: 02.09.2017
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update menu</title>
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
        .edit_dish{
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
<c:if test="${requestScope.error ne null}">
    <h1 style="color: red;"><c:out
            value="${requestScope.error}"></c:out></h1>
</c:if>
<c:if test="${requestScope.success ne null}">
    <h1 style="color: green;"><c:out
            value="${requestScope.success}"></c:out></h1>
</c:if>

<c:if test="${requestScope.dish ne null}">
   <div class="edit_dish">
       <form action='<c:out value="${editURL}"></c:out>' method="post">
           Назва страви: <input type="text" value="${requestScope.dish.name}" name="name">

           Ціна: <input type="text" value="${requestScope.dish.price}" name="price">

           Опис: <textarea style="margin-top: 15px" name="description" cols="57" rows="1">${requestScope.dish.description}</textarea>
           <input class="button"  type="submit" value="Редагувати страву">
       </form>
   </div>
</c:if>
</body>
</html>
