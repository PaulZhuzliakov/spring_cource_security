<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>

<h2>information for all employees</h2>
<br><br>
<security:authorize access="hasRole('HR')">
<input type="button" value="salary" onclick="window.location.href = 'hr-info'">
only for HR staff
</security:authorize>
<br><br>
<security:authorize access="hasRole('MANAGER')">
<input type="button" value="performanse" onclick="window.location.href = 'manager-info'">
only for manager staff
</security:authorize>
<br><br>
</body>
</html>