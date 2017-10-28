<%--
  Created by IntelliJ IDEA.
  User: minhat
  Date: 28/10/2017
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .btn {
            color: #fff;
            padding: 10px 20px;
            border: 1px solid cornflowerblue;
            border-radius: 5px;
            background-color: cornflowerblue;
        }

        .btn:hover {
            background-color: aqua;
            color: cornflowerblue;
            transition: 0.5s;
        }
    </style>
</head>
<body>
    this is up load page
    <form action="handleUpload" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input class="btn" type="submit" value="UPLOAD FILE">
    </form>
</body>
</html>
