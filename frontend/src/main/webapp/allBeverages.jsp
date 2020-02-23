<%@ page import="de.uniba.dsg.dsam.model.Beverage" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Incentive Management</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <style>

        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        .row img {
            width: 60%;
            margin-left: 20%;
        }

        .jumbotron {
            background-color: #1abc9c
        }

        .row a, .jumbotron, label {
            color: #222;
            text-shadow: 0 0 2px #222;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="jumbotron mt-3 text-center">
        <h1>All Beverages <img
                src="https://icons-for-free.com/iconfiles/png/512/and+beverage+cocktail+drink+food+icon-1320167993528957027.png"
                width="60"></h1>
    </div>
    <div class="row">
        <a class="pull-right btn btn-primary" href="<%= request.getContextPath() %>/beverages">Create new Beverage </a>
    </div>

    <div class="row">
        <div class="col-md-3"><h3>Name</h3></div>
        <div class="col-md-3"><h3>Manufacturer</h3></div>
        <div class="col-md-2"><h3>Quantity</h3></div>
        <div class="col-md-2"><h3>Price(&euro;)</h3></div>
        <div class="col-md-2"><h3>Incentive Type</h3></div>
    </div>

    <%
        List<Beverage> beverages = (List<Beverage>) request.getAttribute("beverageList");

        for (Beverage beverage : beverages) {
    %>
    <div class="row">
        <div class="col-md-3"><h4><%= beverage.getName() %>
        </h4></div>
        <div class="col-md-3"><h4><%= beverage.getManufacturer() %>
        </h4></div>
        <div class="col-md-2"><h4><%= beverage.getQuantity() %>
        </h4></div>
        <div class="col-md-2"><h4><%= beverage.getPrice() %>
        </h4></div>
        <div class="col-md-2">
            <h4><%=  beverage.getIncentive() != null ? beverage.getIncentive().getName() : "No Incentive Assigned" %>
            </h4></div>

    </div>
    <% } %>

</div>
</body>
</html>