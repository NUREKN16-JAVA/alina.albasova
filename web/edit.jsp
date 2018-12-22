<jsp:useBean id="user" class="ua.nure.kn.albasova.usermanagment.User" scope="session" />
<html>
<head>
    <title>User management/Edit</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/edit" method="post">
    <input type="hidden" name="id" value="${user.id}">
    First name <input type="text" name="firstName" value="${user.firstName}"><br>
    Last name <input type="text" name="lastName" value="${user.lastName}"><br>
    Date of birth <input type="text" name="birthday"
                         value="<fmt:formatDate value="${user.date}" pattern="dd/MM/yyyy"/>"><br>
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