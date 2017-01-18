<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
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
    <script src="https://www.gstatic.com/charts/loader.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>


</head>
<body>

<div id="chart_div"></div>
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


<p>
    123
</p><p>
    456
</p><p>
    789
</p>

</body>
</html>
