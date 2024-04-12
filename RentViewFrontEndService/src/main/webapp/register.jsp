<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.time.Year" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Register for RentView</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <style>
            /* Local styles for registration page */
            section {
                width: 100%;
                max-width: 400px;
                margin: 20px auto;
                padding: 20px;
                background: #f0f0f0;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            form > label {
                display: block;
                margin-top: 10px;
                margin-bottom: 5px;
                color: #333;
            }

            input, select {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 15px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 20px;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

            fieldset {
                margin-top: 20px;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
                background-color: #f8f8f8;
            }

            legend {
                font-weight: bold;
            }

            footer {
                margin-top: 20px;
                text-align: center;
            }

            footer a {
                color: #333;
                text-decoration: none;
            }

            footer a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <header>
            <nav>
                <form action="index.jsp">
                    <button type="submit">Home</button>
                </form>
                <form action="login.jsp">
                    <button type="submit">Login</button>
                </form>
            </nav>
        </header>

        <h2>Register for RentView</h2>
        <section>
            <form action="RegisterServlet" method="post">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" required>
                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" required>
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" required>
                <label for="memberType">Member Type:</label>
                <select name="memberType">
                    <option value="user">User</option>
                    <option value="manager">Manager</option>
                </select>

                <!-- Optional Card Details -->
                <fieldset>
                    <legend>Card Details (Optional)</legend>
                    <label for="cardType">Card Type:</label>
                        <input type="text" id="cardType" name="cardType" placeholder="e.g., Visa, MasterCard">
                    <label for="lastFourDigits">Last Four Digits:</label>
                        <input type="text" id="lastFourDigits" name="lastFourDigits" pattern="\d{4}" title="Please enter exactly four digits" placeholder="1234">
                    <label for="expirationMonth">Expiration Month:</label>
                        <select id="expirationMonth" name="expirationMonth">
                            <option value="">Month</option>
                            <option value="01">January</option>
                            <option value="02">February</option>
                            <option value="03">March</option>
                            <option value="04">April</option>
                            <option value="05">May</option>
                            <option value="06">June</option>
                            <option value="07">July</option>
                            <option value="08">August</option>
                            <option value="09">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select>
                    <label for="expirationYear">Expiration Year:</label>
                        <select id="expirationYear" name="expirationYear">
                            <option value="">Year</option>
                            <% 
                                int currentYear = java.time.Year.now().getValue();
                                for (int year = currentYear; year < currentYear + 20; year++) {
                                    out.println("<option value=\"" + year + "\">" + year + "</option>");
                                }
                            %>
                        </select>
                </fieldset>

                <input type="submit" value="Register">
            </form>
        </section>
        <footer>
            <a href="login.jsp">Already have an account? Log in here.</a>
        </footer>
        <script>
            document.getElementById('lastFourDigits').addEventListener('input', function (e) {
            var target = e.target;
            // Allow only digits, limit length to 4
            target.value = target.value.replace(/[^0-9]/g, '').slice(0, 4);
            });
        </script>
    </body>
</html>