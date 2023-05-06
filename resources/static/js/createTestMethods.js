var origin   = window.location.origin; 

function validateSection() {
	if ($.trim($('.descTitle').val()).length < 5) {
		alert("Va rugam introduceti un titlu");
		return false;
	}

	if ($.trim($('.descText').val()).length < 10) {
		alert("Va rugam introduceti o descriere");
		return false;
	}

	return true;
}

function createTest(title, description, callbackFunc) {
	var data = {
		quizName : title,
		quizDescription : description,
		quizValidation: false
	};
	$.ajax({
		type : 'POST',
		url : origin + '/quiz/',
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		data : JSON.stringify(data),
		success : function(data) {
			createQuestion(data.id, 'Inserati text intrebare', null);
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

function redirectToEdit(data)
{
	
	window.location.href = origin + "/editTest/"+data.id;
}

$(document).on('click', '.testCreate', function() {
	if (validateSection() == true) {
		createTest($('.descTitle').val(), $('.descText').val(), redirectToEdit)
	}
});