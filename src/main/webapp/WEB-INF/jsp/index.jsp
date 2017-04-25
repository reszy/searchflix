<%--
  Created by IntelliJ IDEA.
  User: Rafał
  Date: 18.04.2017
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Flix</title>
    <script type="text/javascript" src="vendor/react.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../theme.css">
</head>
<body>
<div id="content">${content}</div>
<script type="text/javascript" src="components.js"></script>
<script type="text/javascript">
    $(function () {
        renderClient(${data});
    });
</script>
</body>
</html>