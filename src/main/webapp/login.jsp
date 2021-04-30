<%@include file="header.jsp" %>

<div>
    <h3>Sign In</h3>
    <form action="login" method="GET">

        <label for="emailSI">Email</label><br>
        <input type="email" id="emailSI" name="emailSI" placeholder="Enter Your Email" required><br>
        <label for="passwordSI">Password</label><br>
        <input type="password" id="passwordSI" name="passwordSI" placeholder="Enter Your Password" required><br>
        <c:if test="${userErrors.login == 1}">
            <small>Invalid Credentials!</small><br>
        </c:if>
        <input type="submit" value="Login" name="loginButton">
    </form>
</div>

<div>
    <h3>Sign Up</h3>
    <form action="login" method="POST">
        <label for="name">Name</label><br>
        <input type="text" id="name" name="name" placeholder="Enter Your Name" required><br>
        <label for="addressSU">Address</label><br>
        <input type="text" id="addressSU" name="addressSU" placeholder="Enter Your Address" required><br>
        <label for="emailSU">Email</label><br>
        <input type="email" id="emailSU" name="emailSU" placeholder="Enter Your Email" required><br>
        <c:if test="${userErrors.signUpMail == 1}">
            <small>The email you had entered is already registered!</small><br>
        </c:if>
        <label for="numberSU">Phone Number</label><br>
        <input type="tel" id="numberSU" name="numberSU" placeholder="Enter Your Phone Number" required><br>
        <c:if test="${userErrors.signUpPhone == 1}">
            <small>The phone you had entered is already registered!</small><br>
        </c:if>
        <label for="passwordSU">Password</label><br>
        <input type="password" id="passwordSU" name="passwordSU" placeholder="Enter Your Password" required><br>
        <label for="passwordSUC">Password</label><br>
        <input type="password" id="passwordSUC" name="passwordSUC" placeholder="Confirm Your Password" required><br>
        <input type="submit" value="Register" name="registerButton">
    </form>
</div>

<%@include file="footer.jsp" %>