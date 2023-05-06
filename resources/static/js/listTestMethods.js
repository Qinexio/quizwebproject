var origin   = window.location.origin; 

function redirectToPage(data) {
	window.location.reload(true); // false would reload from cache instead
	// from server
}

$(document).on('click', '.deleteButton', function() {
	deleteQuiz(0, parseInt($(this).attr('id')), redirectToPage);
});

$(document).on('click', '.scoreStatsButton', function() {
	getQuizScores(parseInt($(this).attr('id')), drawStatisticChart);
});

$(document).on('click', '.visitStatsButton', function() {
	getQuizStats(parseInt($(this).attr('id')), drawStatisticChart);
});

$(document).ready(getMonthStats(drawStatisticChart));

function getMonthStats(callbackFunc) {
	$.ajax({
		type : 'GET',
		url : origin + '/stats/monthly',
		success : function(data) {
			callbackFunc(data.content, '#monthVisitDistribution');
		},
		error : function(e) {
			console.log("ERROR: ", e);
			alert(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function getQuizStats(quizID, callbackFunc) {
	$.ajax({
		type : 'GET',
		url : origin + '/stats/' + quizID,
		success : function(data) {
			callbackFunc(data.content, '#mostVisits');
		},
		error : function(e) {
			console.log("ERROR: ", e);
			alert(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function getQuizScores(quizID, callbackFunc) {
	$.ajax({
		type : 'GET',
		url : origin + '/score/' + quizID,
		success : function(data) {
			callbackFunc(data.content, '#scoreDistribution');
		},
		error : function(e) {
			console.log("ERROR: ", e);
			alert(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function drawStatisticChart(data, destination) {
	var chartCanvas = $(destination);

	var labelList = []
	var valueList = []

	$.each(data, function(index, element) {
		labelList.push(element.statName);
		valueList.push(element.statVisits);
	});

	var chartDraw = new Chart(chartCanvas, {
		type : 'pie',
		data : {
			labels : labelList,
			datasets : [ {
				label : '# valori',
				data : valueList,
				backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
						'rgba(255, 159, 64, 0.2)' ],
				borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)' ],
				borderWidth : 1
			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	});
}
