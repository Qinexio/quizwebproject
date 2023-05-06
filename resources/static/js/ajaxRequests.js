var origin   = window.location.origin; 

function deleteQuiz(userID, quizID, callbackFunc) {
	$.ajax({
		type : 'DELETE',
		url : origin + '/quiz/' + quizID,
		success : function(data) {
			callbackFunc(data);
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

function createQuestion(quizID, description, callbackFunc) {
	var data = {
		questionDescription : description
	};
	$.ajax({
		type : 'POST',
		url : origin + '/quiz/' + quizID + '/question/',
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		timeout : 100000,
		data : JSON.stringify(data),
		success : function(data) {
			callbackFunc(data);
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

function createAnswer(questionID, description, isCorrect, callbackFunc) {
	var data = {
		answerDescription : description,
		answerIsCorrect : isCorrect
	};
	$.ajax({
		type : 'POST',
		url : origin + '/question/' + questionID + '/answer/',
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		timeout : 100000,
		data : JSON.stringify(data),
		success : function(data) {
			callbackFunc(data);
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

function updateQuestion(quizID, qID, description) {
	var data = {
		questionDescription : description
	};
	$.ajax({
		type : 'PUT',
		url : origin + '/quiz/' + quizID + '/question/' + qID,
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		data : JSON.stringify(data),
		success : function(data) {
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

function updateAnswer(questionID, aID, description, isCorrect) {
	var data = {
		answerDescription : description,
		answerIsCorrect : isCorrect
	};
	$.ajax({
		type : 'PUT',
		url : origin + '/question/' + questionID + '/answer/'
				+ aID,
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		data : JSON.stringify(data),
		success : function(data) {
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

function deleteQuestion(quizID, qID) {
	$.ajax({
		type : 'DELETE',
		url : origin + '/quiz/' + quizID + '/question/' + qID,
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
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

function deleteAnswer(questionID, aID) {
	$.ajax({
		type : 'DELETE',
		url : origin + '/question/' + questionID + '/answer/'
				+ aID,
		contentType : 'application/json; charset=utf-8',
		success : function(data) {
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

function validateTest(userID, testID, callbackFunc) {
	$.ajax({
		type : 'PUT',
		url : origin + '/quiz/' + testID + '/validate/',
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		success : function(data) {
			callbackFunc(data);
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

function invalidateTest(userID, testID) {
	$.ajax({
		type : 'PUT',
		url : origin + '/quiz/' + testID + '/invalidate/',
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
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
