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

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/><br>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
        </div>

    </form>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <div id="chart_div"></div>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<script>
    google.charts.load('current', {packages: ['corechart', 'line']});
    google.charts.setOnLoadCallback(drawAxisTickColors);

    function drawAxisTickColors() {
        var data = new google.visualization.DataTable();
        data.addColumn('number', 'X');
        data.addColumn('number', 'Dogs');
//        data.addColumn('number', 'Cats');

        data.addRows([
            [20161111, 0.91709],
            [20161111, 0.91709],
            [20161111, 0.91709],
            [20161114, 0.92790],
            [20161115, 0.92894],
            [20161116, 0.93440],
            [20161117, 0.93310],
            [20161118, 0.94082],
            [20161118, 0.94082],
            [20161118, 0.94082],
            [20161121, 0.94065],
            [20161122, 0.94189],
            [20161123, 0.94322],
            [20161124, 0.94805],
            [20161125, 0.94411],
            [20161125, 0.94411],
            [20161125, 0.94411],
            [20161128, 0.94447],
            [20161129, 0.94554],
            [20161130, 0.94029],
            [20161201, 0.94100]
        ]);

        var options = {
            hAxis: {
                title: 'Time',
                textStyle: {
                    color: '#01579b',
                    fontSize: 20,
                    fontName: 'Arial',
                    bold: true,
                    italic: true
                },
                titleTextStyle: {
                    color: '#01579b',
                    fontSize: 16,
                    fontName: 'Arial',
                    bold: false,
                    italic: true
                }
            },
            vAxis: {
                title: 'Popularity',
                textStyle: {
                    color: '#1a237e',
                    fontSize: 24,
                    bold: true
                },
                titleTextStyle: {
                    color: '#1a237e',
                    fontSize: 24,
                    bold: true
                }
            },
            colors: ['#a52714', '#097138', 'blue']
        };
        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }
</script>

</body>
</html>