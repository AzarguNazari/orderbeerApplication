<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Beverage Store Demo</title>

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

        .row a, .jumboton {
            color: #222;
            text-shadow: 0 0 2px #222;
        }

        .row a:hover {
            text-decoration: none;
        }

        .placeholder {
            padding: 20px;
        }

        .placeholder:hover {
            background-color: #eee;
            box-shadow: 0px 0px 5px #222;
        }


    </style>
</head>
<body>
<div class="container">
</div>


<div class="container" style="margin-top: 50px">
    <div class="jumbotron mt-3 text-center">
        <h1>Beverage Store</h1>

    </div>
</div>


<div class="container">

    <div class="row text-center">
        <div class="col-md-3">
            <a href="<%= request.getContextPath() %>/addNewIncentive.jsp">
                <div class="placeholder">
                    <img src="https://icon-library.net/images/add-image-icon-png/add-image-icon-png-18.jpg"
                         class="img-responsive" alt="Add New Incentive">
                    <h2>Add New Incentive</h2>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="<%= request.getContextPath() %>/beverages">
                <div class="placeholder">
                    <img src="https://icon-library.net/images/add-image-icon-png/add-image-icon-png-18.jpg"
                         class="img-responsive" alt="Add New Beverage">
                    <h2>Add New Beverage</h2>
                </div>
            </a>
        </div>

        <div class="col-md-3">
            <a href="<%= request.getContextPath() %>/orders">
                <div class="placeholder">
                    <img src="https://icon-library.net/images/new-order-icon/new-order-icon-17.jpg"
                         class="img-responsive"
                         alt="Order Beverage">
                    <h2>Order Beverage</h2>
                </div>
            </a>
        </div>
        <div class="col-md-3">
            <a href="<%= request.getContextPath() %>/bi">
                <div class="placeholder">
                    <img src="https://www.freeiconspng.com/uploads/summary-icon-png-notebooks-may-just-be-the-4.png"
                         class="img-responsive" alt="Show Sales Summary">
                    <h2>Show Sales Summary</h2>
                </div>
            </a>
        </div>
    </div>

    <div class="row text-center">
        <div class="col-md-3">
            <a href="<%= request.getContextPath() %>/incentives">
                <div class="placeholder">
                    <img src="https://icon-library.net/images/shopping-list-icon/shopping-list-icon-27.jpg"
                         class="img-responsive" alt="All Incentives">
                    <h2>All Incentives</h2>
                </div>
            </a>
        </div>
        <div class="col-md-3">
            <a href="<%= request.getContextPath() %>/beverage-list">
                <div class="placeholder">
                    <img src="https://icon-library.net/images/273380-200.png"
                         class="img-responsive" alt="All Beverages">
                    <h2>All Beverages</h2>
                </div>
            </a>
        </div>
    </div>
</div>
</body>