<%@ page import="de.uniba.dsg.dsam.model.Beverage" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Beverage Store Demo</title>

    <!-- Bootstrap -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

    <link rel="stylesheet"
          href="cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">

    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

    <script src="cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react-dom.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.21.1/babel.min.js"></script>
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
        <h1>Add New Order <img
                src="https://icons-for-free.com/iconfiles/png/512/and+beverage+cocktail+drink+food+icon-1320167993528957027.png"
                width="60"></h1>
    </div>
    <div class="row">
        <div id="processOrder"></div>
    </div>

</div>
<script type="text/babel">

    class OrderList extends React.Component {
        state = {
            beverages: [],
            isSubmitDisabled: true
        }
        _onCLickAdd = (id) => {
            let index = this.state.beverages.findIndex(item => item.id === id);
            let beverages = [...this.state.beverages];
            if (index > -1) {
                beverages[index] = {
                    id: id,
                    quantity: beverages[index].quantity + 1
                }
                this.setState({beverages: beverages}, () => {
                    this._checkSubmit();
                });
            } else {
                this._addNewBev(id);
            }
        }
        _addNewBev = (id) => {
            let tmp = {};
            tmp.id = id;
            tmp.quantity = 1;
            this.setState({beverages: [...this.state.beverages, tmp]}, () => {
                this._checkSubmit();
            });
        }
        _onClickRemove = (id) => {
            let index = this.state.beverages.findIndex(item => item.id === id);
            let beverages = [...this.state.beverages];
            if (index > -1) {
                beverages[index] = {
                    id: id,
                    quantity: beverages[index].quantity - 1
                }
                this.setState({beverages: beverages}, () => {
                    this._checkSubmit();
                });
            }
        }
        _onSubmit = () => {
            let beverages = [...this.state.beverages];
            let filtered = beverages.filter(item => item.quantity > 0);
            fetch("/frontend/orders", {
                method: 'post',
                body: JSON.stringify({data: filtered}),
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                }
            }).then(() => {
                window.location.replace("<%= request.getContextPath() %>/main.jsp");
            });
        }
        _checkSubmit = () => {
            let b = [...this.state.beverages];
            let filtered = b.filter(item => item.quantity > 0);
            if (filtered.length > 0) {
                this.setState({isSubmitDisabled: false});
            } else {
                this.setState({isSubmitDisabled: true});
            }
        }

        render() {
            let {beverages} = this.state;
            return (
                <div>
                    <table className="table table-striped">
                        <thead>
                        <tr>
                            <th>Manufacturer</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price(&euro;)</th>
                            <th>Incentive</th>
                            <th>Manage Order</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Beverage> beverages = (List<Beverage>) request.getAttribute("beverageList");
                            for (Beverage b : beverages) {
                        %>
                        <tr>
                            <td><%=b.getManufacturer()%></td>
                            <td><%=b.getName()%></td>
                            <td><%=b.getQuantity()%></td>
                            <td><%=b.getPrice()%></td>
                            <td><%=b.getIncentive() != null ? b.getIncentive().getName() : "No Incentive"%></td>
                            <td>
                                <div class="btn-group" role="group" aria-label="...">
                                    <button
                                        disabled={
                                            beverages.find(item => item.id ===<%=b.getId()%>)
                                            && beverages.find(item => item.id ===<%=b.getId()%>).quantity < 1
                                                ? true
                                                : false
                                        }
                                        onClick={() => {
                                            if (<%=b.getQuantity()%> ===
                                            0
                                        )
                                            return;
                                            this._onClickRemove(<%=b.getId()%>)
                                        }}
                                        type="button"
                                        className="btn btn-primary">-
                                    </button>
                                    <button type="button" className="btn btn-default">
                                        {
                                            beverages.find(item => item.id ===<%=b.getId()%>)
                                                ? beverages.find(item => item.id ===<%=b.getId()%>).quantity
                                                : 0
                                        }
                                    </button>
                                    <button
                                        disabled={
                                            beverages.find(item => item.id ===<%=b.getId()%>)
                                            && beverages.find(item => item.id ===<%=b.getId()%>).quantity >= <%=b.getQuantity()%>
                                                ? true
                                                : false
                                        }
                                        onClick={() => {
                                            if (<%=b.getQuantity()%> ===
                                            0
                                        )
                                            return;
                                            this._onCLickAdd(<%=b.getId()%>)
                                        }} type="button" className="btn btn-primary">+
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <%}%>
                        <br/>

                        </tbody>
                    </table>
                    <div class="text-center">
                        <button
                            disabled={
                                this.state.isSubmitDisabled
                            }
                            onClick={this._onSubmit} type="button" className="pull-right btn btn-success">Submit Order
                        </button>
                    </div>
                </div>
            );
        }
    }

    const el = document.getElementById('processOrder');
    ReactDOM.render(<OrderList/>, el)

</script>
</body>
</html>