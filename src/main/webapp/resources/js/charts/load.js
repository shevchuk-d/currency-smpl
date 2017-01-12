/**
 * Created by dmsh0216 on 12/01/2017.
 */
google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawAxisTickColors);

function drawAxisTickColors() {
    var data = new google.visualization.DataTable();
    data.addColumn('number', 'X');
    data.addColumn('number', 'Dogs');
    data.addColumn('number', 'Cats');
    data.addColumn('number', 'Rats');

    data.addRows([
        [0, 0, 0, 0],
        [1, 10, 5, 20],
        [2, 23, 15, 80],
        [3, 17, 9, 60],
        [4, 18, 10, 20],
        [5, 9, 5, 45],
        [6, 11, 3, 8],
        [7, 27, 19, 54],
        [8, 33, 25, 78],
        [9, 40, 32, 56],
        [10, 32, 24, 5]
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