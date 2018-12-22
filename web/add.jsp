<jsp:useBean id="user" class="ua.nure.kn.albasova.usermanagment.User" scope="session" />
<html>
<head>
    <title>User management/Add</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/add" method="post">
    First name <input type="text" name="firstName" value=""><br>
    Last name <input type="text" name="lastName" value=""><br>
    Date of birth <input type="text" name="birthday" value=""><br>
    <input type="submit" name="okButton" value="OK">
    <input type="submit" name='cancelButton' value="Cancel">
</form>
<c:if test="${requestScope.error != null}">
    <script>
        alert("${requestScope.error}]")
    </script>
</c:if>
</body>
</html>
