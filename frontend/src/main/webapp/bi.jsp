<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Incentive Management (BI)</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.min.js"></script>
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
        <h1>Business Intelligence <img
                src="https://icons-for-free.com/iconfiles/png/512/and+beverage+cocktail+drink+food+icon-1320167993528957027.png"
                width="60"></h1>
    </div>
    <div class="row">
        <a class="pull-right btn btn-primary" href="<%= request.getContextPath() %>/main.jsp"> back </a>
    </div>

    <div class="row">
        <div class="col-md-3"><h3>Total Revenue &euro;</h3></div>
        <div class="col-md-3"><h3>Revenue(No Incentive) &euro;</h3></div>
        <div class="col-md-3"><h3>Revenue(Trial) &euro;</h3></div>
        <div class="col-md-3"><h3>Revenue(Promotion) &euro;</h3></div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <h4><%= request.getAttribute("revenue") %>
                &euro;
            </h4>
        </div>
        <div class="col-md-3">
            <h4><%= request.getAttribute("revenueWithOutIncentive") %>
                &euro;
            </h4>
        </div>
        <div class="col-md-3">
            <h4><%= request.getAttribute("revenueWithTrial") %>
                &euro;
            </h4>
        </div>
        <div class="col-md-3">
            <h4><%= request.getAttribute("revenueWithPromotion") %>
                &euro;
            </h4>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6" style="width: 50%">
            <canvas id="myChart" width="400" height="400"></canvas>
        </div>
        <div class="col-md-6" style="width: 50%">
            <canvas id="priceChart" width="400" height="400"></canvas>
        </div>
    </div>
</div>
</body>

<script>
    let ROOT = {

        dynamicColors: function () {
            let r = Math.floor(Math.random() * 255);
            let g = Math.floor(Math.random() * 255);
            let b = Math.floor(Math.random() * 255);
            return "rgb(" + r + "," + g + "," + b + ")";
        },

        loadData: function () {
            let data;

            $.ajax({

                async: false,

                url: "/frontend/bi-api",

                dataType: "json",

                success: function (revenue) {
                    console.log(revenue);
                    data = revenue;
                }
            });
            return data;
        },

        createChartData: function (jsonData) {
            let revenueByIncentiveKey = [];
            let revenueByIncentiveValue = [];
            let incentiveColors = [];
            $.each(jsonData.revenueByIncentiveType, function (index, value) {
                revenueByIncentiveKey.push(index);
                revenueByIncentiveValue.push(value);
                incentiveColors.push(ROOT.dynamicColors());
            });
            return {
                labels: revenueByIncentiveKey,
                datasets: [{
                    label: "Orders",
                    backgroundColor: incentiveColors,
                    borderColor: 'rgba(200, 200, 200, 0.75)',
                    hoverBorderColor: 'rgba(200, 200, 200, 1)',
                    data: revenueByIncentiveValue
                }]
            };
        },

        createChartDataForPrice: function (jsonData) {
            let revenueByProductKey = [];
            let revenueByProductValue = [];
            let productColors = [];
            $.each(jsonData.revenueByProduct, function (index, value) {
                revenueByProductKey.push(index);
                revenueByProductValue.push(value);
                productColors.push(ROOT.dynamicColors());
            });
            return {
                labels: revenueByProductKey,
                datasets: [{
                    label: "Beverages",
                    backgroundColor: productColors,
                    borderColor: 'rgba(200, 200, 200, 0.75)',
                    hoverBorderColor: 'rgba(200, 200, 200, 1)',
                    data: revenueByProductValue
                }]
            };
        },


        renderChart: function (chartData, priceData) {

            let ctx = document.getElementById('myChart');
            let price = document.getElementById('priceChart');
            let priceChart = new Chart(price, {
                type: 'pie',
                data: priceData,
                options: {
                    title: {
                        display: true,
                        text: 'Revenue of all beverages sold broken down to beverages'
                    },
                    responsive: true,
                    maintainAspectRatio: false,
                }
            });

            let myChart = new Chart(ctx, {
                type: 'pie',
                data: chartData,
                options: {
                    title: {
                        display: true,
                        text: 'Revenue of all beverages sold broken down to the different incentive types'
                    },
                    responsive: true,
                    maintainAspectRatio: false,
                }
            });
        },

        initRadarChart: function () {

            let res = ROOT.loadData();

            let chartData = ROOT.createChartData(res);
            let priceData = ROOT.createChartDataForPrice(res);

            ROOT.renderChart(chartData, priceData);

        }
    };

    $(document).ready(function () {
        ROOT.initRadarChart();
    });
</script>
</html>