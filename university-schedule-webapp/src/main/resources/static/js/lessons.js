$(document).ready(function() {

	$('.deleteLesson').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let lessonId = $(this).val();
		deleteLesson(lessonId);
	});

	function deleteLesson(lessonId) {
		if (confirm('Are you sure you want to delete the lesson with following ID : ' + lessonId + '?')) {
			$.ajax({
				type: 'DELETE',
				url: '/profile/dashboard/lessons/delete/' + lessonId,
				success: function(response) {
					alert(response);
					location.reload();
				},
				error: function(error) {
					alert('Error : ' + error.responseText);
				}
			})
		}
	}
	
	$('#updateLesson').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let lessonId = $('#lessonId').val();
		let subjectId = $('#lessonSubject').val();
		let professorId = $('#lessonProfessor').val();
		let lessonDate = $('#lessonDate').val();
		let lessonTime = $('#lessonTime').val();
		let lessonAuditorium = $('#lessonAuditorium').val();
		let lessonHouse = $('#lessonHouse').val();
		let lessonYear = $('#lessonYear').val();
		
		
		let json = { "id": lessonId, 
		"subject": { "id": subjectId } ,
		"professor": { "id": professorId } ,
		"date": lessonDate,
		"time": lessonTime,
		"auditorium": { "id": lessonAuditorium } ,
		"house": { "id": lessonHouse } ,
		"year": { "id": lessonYear } };
		sendLessonData("PUT", "/profile/dashboard/lessons/update/" + lessonId, json, "Updating : [");

	});
	
	$('#createLesson').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let subjectId = $('#lessonSubject').val();
		let professorId = $('#lessonProfessor').val();
		let lessonDate = $('#lessonDate').val();
		let lessonTime = $('#lessonTime').val();
		let lessonAuditorium = $('#lessonAuditorium').val();
		let lessonHouse = $('#lessonHouse').val();
		let lessonYear = $('#lessonYear').val();
		
		
		let json = { 
	   "subject": { "id": subjectId } ,
		"professor": { "id": professorId } ,
		"date": lessonDate,
		"time": lessonTime,
		"auditorium": { "id": lessonAuditorium } ,
		"house": { "id": lessonHouse } ,
		"year": { "id": lessonYear } };
		sendLessonData("POST", "/profile/dashboard/lessons/create", json, "Creating : [");

	});

	function sendLessonData(type, url, jsonData, successMsg) {
		$.ajax({
			type: type,
			url: url,
			data: JSON.stringify(jsonData),
			dataType: "json",
			contentType: "application/json",
			success: function(data) {
				let respContent = "<div class='successAlert alert alert-success'>" + "<span class='success'>" + successMsg + "<b>" + data.id + "</b>] - Success! </span></div>";
				setTimeout(function() {
					window.location.href = "/profile/dashboard/lessons"
				}, 2000);
				$('#successAlert').html(respContent).show();
			},
			error: function(xhr) {
				let respContent = "<div class='errorAlert alert alert-danger'>" +
					"<span class='error'>" + xhr.responseText + "</span></div>";
				setTimeout(function() {
					$('#saveProfessor').prop('disabled', false);
					$('#updateProfessor').prop('disabled', false);
					$('#errorAlert').hide();
				}, 2000);
				$('#errorAlert').html(respContent).show();
				console.log("Error updating Lesson", xhr.responseText);
			}
		});
	}
	
	
	

});
