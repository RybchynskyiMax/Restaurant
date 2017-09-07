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
    <title>Role</title>
    <style type="text/css">
        body{
            background: url(http://septfon.ru/uploads/images/o/b/o/oboi_bezhevie_odnotonnie_foto_7.jpg) no-repeat;
            -moz-background-size: 100%;
            -webkit-background-size: 100%;
            -o-background-size: 100%;
            background-size: 100%;
        }
        .links{
            margin: 5px 100px;
            font-family: sans-serif;
            font-size: 25pt;
            display: inline-block;
            text-decoration: none;
            color: white;
        }
        .mainContent{
            margin-top: 50px;
            text-align: center;
        }
        h1{
            margin-top: 50px;
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
<h1>Виберіть роль:</h1>
<div class="mainContent">
<a class="links" href="/menuclient">Клієнт</a>
<a class="links" href="/menuadmin">Адмін</a>
</div>
</body>
</html>
