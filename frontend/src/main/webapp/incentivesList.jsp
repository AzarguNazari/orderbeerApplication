<%@ page import="de.uniba.dsg.dsam.model.Incentive" %>
<%@ page import="de.uniba.dsg.dsam.model.PromotionalGift" %>
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
        <h1>All Incentives <img
                src="https://icons-for-free.com/iconfiles/png/512/and+beverage+cocktail+drink+food+icon-1320167993528957027.png"
                width="60"></h1>
    </div>
    <div class="row">
        <a class="pull-right btn btn-primary" href="<%= request.getContextPath() %>/addNewIncentive.jsp">Create new
            Incentive </a>
    </div>
    <div class="row">
        <div class="col-md-4"><h3>Incentive Name</h3></div>
        <div class="col-md-4"><h3>Incentive Type</h3></div>
        <div class="col-md-4"></div>
    </div>

    <%
        List<Incentive> incentives = (List<Incentive>) request.getAttribute("incentiveList");

        for (Incentive incentive : incentives) {
    %>
    <div class="row">
        <div class="col-md-4"><h4><%= incentive.getName() %>
        </h4></div>
        <div class="col-md-4"><h4><%= incentive instanceof PromotionalGift ? "Promotional" : "Trial" %>
        </h4></div>
        <div class="col-md-4">
            <div class="btn-group" role="group">
                <a href="<%= request.getContextPath() %>/incentives/edit?id=<%= incentive.getId() %>"
                   class="btn btn-primary">Edit incentive</a>
                <a id="<%= incentive.getId() %>" href="" class="delete btn btn-danger">Delete incentive</a>
            </div>
        </div>
    </div>
    <% } %>

</div>

<script>
    $(document).ready(function () {
        $(".delete").click(function () {
            event.preventDefault();

            $.ajax({
                url: '/frontend/incentives?id=' + event.target.id,
                type: 'DELETE',
                success: function (response) {
                    window.location.reload();
                }
            });
        });
    });
</script>
</body>
</html>