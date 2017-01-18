/**
 * Created by dmsh0216 on 18/01/2017.
 */
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
