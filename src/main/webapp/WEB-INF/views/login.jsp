<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <script src="${contextPath}/resources/js/jquery-3.1.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script language="JavaScript" type="text/javascript"  src="https://www.gstatic.com/charts/loader.js"></script>
    <script language="JavaScript" type="text/javascript"  src="${contextPath}/resources/js/bootstrap.min.js"></script>


    <![endif]-->
</head>

<body>
<div class="jumbotron text-center">
    <h1>My First Bootstrap Page</h1>
    <p>Resize this responsive page to see the effect!</p>
</div>

<div class="container">
<%--<div class="container">--%>

    <%--<form method="POST" action="${contextPath}/login" class="form-signin">--%>
        <%--<h2 class="form-heading">Log in</h2>--%>

        <%--<div class="form-group ${error != null ? 'has-error' : ''}">--%>
            <%--<span>${message}</span>--%>
            <%--<input name="username" type="text" class="form-control" placeholder="Username"--%>
                   <%--autofocus="true"/><br>--%>
            <%--<input name="password" type="password" class="form-control" placeholder="Password"/>--%>
            <%--<span>${error}</span>--%>
            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <%--<br>--%>
            <%--<button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>--%>
            <%--<h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>--%>
        <%--</div>--%>

    <%--</form>--%>


<%--</div>--%>
<!-- /container -->



<div class="table-bordered col-lg-12">
    <div class="col-lg-4">
        <form:form method="GET" modelAttribute="currencySelector" class="form-signin">
            <div class="form-group">
                <table class="table-hover">
                    <tr>
                        <td>
                            <form:label path="base">
                                <spring:message text="Base currency: "/>
                            </form:label>
                        </td>
                        <td>
                            <form:select cssClass="form-control" path="base" items="${currencies}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="target">
                                <spring:message text="Target currency: "/>
                            </form:label>
                        </td>
                        <td>
                            <form:select cssClass="form-control" path="target" items="${currencies}" />
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <form:label path="period">
                                <spring:message text="Select period: "/>
                            </form:label>
                        </td>
                        <td>
                            <form:select cssClass="form-control" path="period" items="${periodSelect}" />
                        </td>
                    </tr>
                </table>
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit" >Submit</button>
        </form:form>
    </div>
    <div class="vert-align">
        <div class="table-hover table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Firstname</th>
                    <th>Lastname</th>
                    <th>Age</th>
                    <th>City</th>
                    <th>Country</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>Anna</td>
                    <td>Pitt</td>
                    <td>35</td>
                    <td>New York</td>
                    <td>USA</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td><span class="glyphicon glyphicon-arrow-down"></span></td>
                    <td><span class="glyphicon glyphicon-arrow-up"></span></td>
                    <td><span class="glyphicon glyphicon-refresh"></span></td>
                    <td><span class="glyphicon glyphicon-floppy-save"></span></td>
                    <td><span class="glyphicon glyphicon-floppy-saved"></span></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>

<table class="col-lg-12"><tr><td>
    <div class="table-bordered col-lg-12" id="chart_div"></div>
</td></tr></table>


<script>
    google.charts.load('current', {packages: ['corechart', 'line']});
    google.charts.setOnLoadCallback(drawAxisTickColors);

    function drawAxisTickColors() {
        var data = new google.visualization.DataTable();
        data.addColumn('date', 'X');
        data.addColumn('number', '${currencyCurrentView.base}');

        data.addRows([
            ${chart}
        ]);

        var options = {
            backgroundColor: {
                fill: '#eee'
            },
            chartArea: {
                backgroundColor: '#eee'
            },
            hAxis: {
                format: 'yyyy-MM-dd',
                textStyle: {
                    color: '#01579b',
                    fontSize: 14,
                    fontName: 'Calibri',
                    bold: false,
                    italic: false
                },
                titleTextStyle: {
                    color: '#01579b',
                    fontSize: 16,
                    fontName: 'Calibri',
                    bold: false,
                    italic: false
                }
            },
            vAxis: {
                title: '${currencyCurrentView.target}',
                textStyle: {
                    color: '#01579b',
                    fontSize: 14,
                    bold: false,
                    italic: false
                },
                titleTextStyle: {
                    color: '#01579b',
                    fontSize: 16,
                    bold: false,
                    italic: false
                }
            },
            colors: ['#FFAB91', 'blue', '#FFAB91']
        };
        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }
</script>
</div>
</body>
</html>