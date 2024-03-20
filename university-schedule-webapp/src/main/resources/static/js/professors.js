$(document).ready(function() {

	$('.deleteProfessor').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled,', true);
		let professorId = $(this).val();
		deleteProfessor(professorId);
	});

	function deleteProfessor(professorId) {
		if (confirm('If any lessons are assigned to this professor, deleting the professor will also delete these associated lessons. Are you sure you want to delete Professor with following ID : ' + professorId + '?')) {
			$.ajax({
				type: 'DELETE',
				url: '/profile/dashboard/professors/delete/' + professorId,
				success: function(response) {
					alert(response);
					location.reload();
				},
				error: function(error) {
					alert('Error : ' + error.responseText)
				}
			})
		}
	}

	$('#updateProfessor').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let professorId = $('#professorId').val();
		let subjectId = $('#subjectId').val();
		let json = { "id": professorId, "name": $('#professorName').val(), "lastName": $('#professorLastName').val(), "subjects": [ { "id": subjectId } ] };
		sendProfessorData("PUT", "/profile/dashboard/professors/update/" + professorId, json, "Updating : [");

	});
	
	$('#createProfessor').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let subjectId = $('#subjectId').val();
		let json = {"login": $('#professorLogin').val(),"password": $('#professorPassword').val(), "name": $('#professorName').val(), "lastName": $('#professorLastName').val(), "subjects": [ { "id": subjectId } ] };
		sendProfessorData("POST", "/profile/dashboard/professors/create", json, "Creating : [");

	});

	function sendProfessorData(type, url, jsonData, successMsg) {
		$.ajax({
			type: type,
			url: url,
			data: JSON.stringify(jsonData),
			dataType: "json",
			contentType: "application/json",
			success: function(data) {
				let respContent = "<div class='successAlert alert alert-success'>" + "<span class='success'>" + successMsg + "<b>" + data.name + " " + data.lastName + "</b>] - Success! </span></div>";
				setTimeout(function() {
					window.location.href = "/profile/dashboard/professors"
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
				console.log("Error updating Professor", xhr.responseText);
			}
		});
	}
});