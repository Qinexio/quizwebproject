var questionRow = new Array();

var quizID;

$(document).ready(initTest);

var qLocation;

function initTest() {
	quizID = parseInt($('#quizID').text());
	invalidateTest(0, quizID);
	$.ajax({
		type : 'GET',
		url : origin + '/quiz/' + quizID + '/questions/',
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		success : function(data) {
			$.each(data.content, function(index, element) {
				questionRow.push(element.id);
			});
			initTestFollowFunc(0);
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

function initTestFollowFunc(selectedQuestion) {
	if (questionRow.length > 4) {
		$('.finishCreation').removeAttr('disabled');
	}

	if (questionRow.length < 5) {
		$('.finishCreation').attr('disabled', true);
	}

	if (questionRow.length > 30) {
		$('.nextQuestion').attr('disabled', true);
	}

	if (questionRow.length < 29) {
		$('.nextQuestion').removeAttr('disabled');
	}

	generateQuestionNavbar(selectedQuestion)
	getQuestion(quizID, questionRow[selectedQuestion]);
	getAnswers(questionRow[selectedQuestion]);
}

function generateQuestionNavbar(currentQuestion) {
	var i;
	qLocation = currentQuestion;
	$('#questionNav').children().remove();
	$('#questionCount').text(currentQuestion + 1);

	for (i = 0; i < questionRow.length; i++) {
		toShow = i + 1;
		if (i != currentQuestion) {
			$('#questionNav').append(
					'<button class="movePage btn btn-info">' + toShow
							+ '</button>');
		} else {
			$('#questionNav').append(
					'<button class="movePage btn btn-info active" disabled>'
							+ toShow + '</button>');
		}
	}
}

$(document).on('click', '.movePage', function() {
	if (validateSection() == true) {
		$('#answerContainer').children().remove();
		initTestFollowFunc(parseInt($(this).text()) - 1);
	}
});

function getQuestion(testID, questionID) {
	$.ajax({
		type : 'GET',
		url : origin + '/quiz/' + testID + '/question/'
				+ questionID,
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
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
	$.ajax({
		type : 'GET',
		url : origin + '/question/' + questionID
				+ '/answersfull/',
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			$.each(data.content, function(index, element) {
				loadAnswer(element.id, element.answerDescription,
						element.answerIsCorrect);
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

function loadAnswer(id, description, isCorrect) {
	var checked = "";

	if (isCorrect == true) {
		checked = "checked";
	}

	if ($('#answerContainer div').length < 10) {
		$('#answerContainer')
				.append(
						'<div id="'
								+ id
								+ '" class="answerEntry form-inline" ><button class="removeAnswer btn btn-danger" title="Stergeti raspuns">-</button><input class="answerText form-control" value = "'
								+ description
								+ '"></input><input type="checkbox" class="isCorrect"'
								+ checked + '></input> <label>Este corect? </label></div>');
	}
}
function loadQuestion(id, description) {
	$('.questionText').attr('id', id);
	$('.questionText').val(description);
}

function validateSection() {
	var checkBoxCount = 0;
	var emptyInputCount = 0;

	if ($('#answerContainer').find('.answerEntry').length < 2) {
		alert("Aveti nevoie de cel putin doua intrebari");
		return false;
	}

	if ($.trim($('.questionText').val()).length < 10) {
		alert("Intrebarea trebuie sa aiba macar 10 caractere");
		return false;
	}

	$(".answerText").each(function() {
		if ($.trim($(this).val()) == '') {
			emptyInputCount = 1;
		}
	})

	if ($(".isCorrect:checked").length > 0) {
		checkBoxCount = 1;
	}

	if (emptyInputCount > 0) {
		alert("Una dintre intrebari este fara continut");
		return false;
	}
	if (checkBoxCount == 0) {
		alert("Trebuie macar o casuta sa fie selectata");
		return false;
	}

	return true;
}

function nextQuestion() {

	if (validateSection() == false) {
		return;
	}

	createQuestion(quizID, 'Inserati text intrebare', createNewQuestion);

}

// works
$(document).on(
		'click',
		'.removeAnswer',
		function() {
			deleteAnswer(parseInt($('.questionText').attr('id')), parseInt($(
					this).parent().attr('id')));
			$(this).parent().remove();
		});

$(document).on('click', '.deleteQuestion', function() {
	deleteQuestion(quizID, parseInt($('.questionText').attr('id')));
	questionRow.splice(qLocation, 1);
	if (qLocation == 0) {
		qLocation = 1;
	}
	if (questionRow.length > 0) {
		$('#answerContainer').children().remove();
		initTestFollowFunc(qLocation - 1);
	} else {
		$('#answerContainer').children().remove();
		createQuestion(quizID, 'Inserati text intrebare', createNewQuestion);
	}

});

// works

function createNewAnswer(data) {
	var checked = '';
	if (data.answerIsCorrect == true) {
		checked = 'checked';
	}
	$('#answerContainer')
			.append(
					'<div id='
							+ data.id
							+ ' class="answerEntry form-inline"><button class="removeAnswer btn btn-danger" title="Stergeti raspuns">-</button><input type="text" class="answerText form-control" val="'
							+ data.answerDescription
							+ '"></input><input type="checkbox" class="isCorrect btn btn-warn" '
							+ checked + '></input><label>Este corect? </label></div>');
}

function createNewQuestion(data) {
	$('.questionText').attr('id', data.id);
	$('.questionText').text(data.questionDescription);
	questionRow.push(data.id);
	$('#answerContainer').children().remove();
	initTestFollowFunc(questionRow.length - 1);
}

$(document).on(
		'click',
		'.addAnswer',
		function() {
			if ($('#answerContainer div').length < 10) {
				createAnswer(parseInt($('.questionText').attr('id')),
						'Inserati text raspuns', false, createNewAnswer)
			}
		});

// works
$(document).on('click', '.finishCreation', function() {
	if (validateSection() == true) {
		validateTest(0, quizID, redirectPage);
	}
});

function redirectPage() {
	window.location.href = origin +"/showEditTest/";
}

$(document).on('click', '.nextQuestion', nextQuestion);

$('.answerEntry').on("click", function() {
	$(this).find('.answerText').focus();
	$(this).off();
});

$(document).on("focusout", ".answerText", function() {
	var description = $(this).parent().children('.answerText').first().val();
	var aID = parseInt($(this).parent().attr('id'));
	var qID = parseInt($('.questionText').attr('id'));
	var isCorrect;
	if ($(this).parent().children('.isCorrect').first().is(':checked')) {
		isCorrect = true;
	} else {
		isCorrect = false;
	}

	updateAnswer(qID, aID, description, isCorrect);

});

$('#questionContainer').on("click", function() {
	$(this).find('.questionText').focus();
	$(this).off();
});

$(document).on("focusout", ".questionText", function() {
	var description = $('.questionText').val();
	var qID = parseInt($('.questionText').attr('id'));

	updateQuestion(quizID, qID, description);

});

$(document).on("change", ".isCorrect", function() {
	var description = $(this).parent().children('.answerText').first().val();
	var aID = parseInt($(this).parent().attr('id'));
	var qID = parseInt($('.questionText').attr('id'));
	var isCorrect;

	if ($(this).is(':checked')) {
		isCorrect = true;
	} else {
		isCorrect = false;
	}

	updateAnswer(qID, aID, description, isCorrect);

});
