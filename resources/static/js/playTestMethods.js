var origin   = window.location.origin; 

var questionRow = new Array();

var qCount = 0;

var eval = new Array();

var quizID;
var userID;

$(document).ready(initTest);

function validateChoice() {
	var selectedACount = 0;
	$('#answerContainer').children('.answerEntry').each(
			function() {
				if ($(this).children('.answerButton').first().hasClass(
						'selectedAnswer') == true) {
					selectedACount += 1;
				}
			});

	if (selectedACount == 0) {
		alert('Please select atleast one question');
		return false;
	}

	return true;
}

function redirectPage() {
	window.location.href = origin + "/showTests/";
}

function initTest() {
	quizID = parseInt($('#quizID').text());
	userID = parseInt($('#userID').text());
	$.ajax({
		type : 'GET',
		url : origin + '/quiz/' + quizID + '/questionsRAND/',
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		timeout : 100000,
		async : false,
		success : function(data) {
			$.each(data.content, function(index, element) {
				questionRow.push(element.id);
			});
		},
		error : function(e) {
			console.log("ERROR: ", e);
			alert(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
	setMaxQuestions(questionRow.length);
	getQuestion(quizID, questionRow[qCount]);
	getAnswers(questionRow[qCount]);

}


function setMaxQuestions(size){
 $('#maxQuestion').text(size);
}

function getQuestion(testID, questionID) {
	$.ajax({
		type : 'GET',
		url : origin + '/quiz/' + testID + '/question/'
				+ questionID,
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			loadQuestion(data.id, data.questionDescription);
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

function getAnswers(questionID) {
	$
			.ajax({
				type : 'GET',
				url : origin + '/question/' + questionID
						+ '/answers/',
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					$.each(data.content, function(index, element) {
						loadAnswer(element.id, element.answerDescription);
					});
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

function getRightAnswers(questionID) {
	$.ajax({
		type : 'GET',
		url : origin + '/question/' + questionID
				+ '/answersfull/',
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			$.each(data.content, function(index, element) {
				loadRightAnswer(element.id, element.answerIsCorrect);
			});
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

function sendResults(userID, testID, eval) {
	$.ajax({
		type : 'POST',
		url : origin + '/user/' + userID + '/quiz/' + testID
				+ '/score',
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		async : false,
		timeout : 100000,
		data : JSON.stringify(eval),
		success : function(data) {
			loadScore(data.score);
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

function loadAnswer(id, description) {
	if ($('#answerContainer div').length < 10) {
		$('#answerContainer').append(
				'<div class="answerEntry"><a id="' + id
						+ '"class="answerButton form-control alignCenter">' + description
						+ '</a></div>');
	}
}

function loadRightAnswer(id, isCorrect) {
	var setClass = 'wrongAnswer';

	if (isCorrect == true) {
		setClass = 'rightAnswer';
	}

	$('#answerContainer').find('#' + id).addClass(setClass);
	$('#answerContainer').find('#' + id).prop('disabled', true);
}

function loadScore(score) {
	$('#ensambleContainer').children().remove();
	$('#ensambleContainer')
			.append(
					'<div class="finalScore alignCenter"><h2>Final testare cunostinte</h2><hr><h3>Raspunsuri corecte: ' + score
							+ '</h3><hr><button class="goHome btn btn-light alignCenter">Alegeti alt test?</button></div>');
}

function loadQuestion(id, description) {
	$('.questionText').attr('id', id);
	$('.questionText').text(description);
}

$(document).on('click', '.goHome', function() {
	redirectPage();
});

$(document).on('click', '.answerButton', function() {
	$(this).toggleClass('selectedAnswer');
});

$(document).on(
		'click',
		'.submitAnswer',
		function() {
			if (validateChoice() == false) {
				return;
			}

			var answers = [];

			$('#answerContainer').children('.answerEntry').each(
					function() {
						if ($(this).children('.answerButton').first().hasClass(
								'selectedAnswer')) {
							answers.push(parseInt($(this).children(
									'.answerButton').first().attr('id')));
						}
					});

			var obj = {
				"evalQuestion" : parseInt($('.questionText').attr('id')),
				"evalAnswers" : answers
			}

			eval.push(obj);
			qCount += 1;

			getRightAnswers(parseInt($('.questionText').attr('id')));
			$(this).attr('class', 'nextQuestion btn btn-light alignCenter');
			$(this).html('Urmatoarea Intrebare');

			if (qCount > questionRow.length - 1) {
				$(this).html('Finalizare test');
			}
		});

$(document).on('click', '.nextQuestion', function() {
	$(this).attr('class', 'submitAnswer btn btn-light alignCenter');
	$(this).html('Raspunde');
	$('#questionCount').text(qCount + 1);
	$('#answerContainer').children().remove();

	if (qCount > questionRow.length - 1) {
		sendResults(userID, quizID, eval);
		return;
	}
	getQuestion(quizID, questionRow[qCount]);
	getAnswers(questionRow[qCount]);
});