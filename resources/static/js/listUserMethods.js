var origin   = window.location.origin; 

function redirectToPage(data) {
	window.location.reload(true); // false would reload from cache instead
	// from server
}

$(document).on('click', '.holdBack', function() {
	holdBackProfile(parseInt($(this).attr('id')), redirectToPage);
});

$(document).on('click', '.pushForward', function() {
	promoteProfile(parseInt($(this).attr('id')), redirectToPage);
});


function holdBackProfile(profileID, callbackFunc) {
	$.ajax({
		type : 'PUT',
		url : origin + '/profileHold/' + profileID,
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

function promoteProfile(profileID, callbackFunc) {
	$.ajax({
		type : 'PUT',
		url : origin + '/profileForward/' + profileID,
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

