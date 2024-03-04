
$(document).ready(function() {

	$('.deleteStudent').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let studentId = $(this).val();
		deleteStudent(studentId);

	});

	$('.deleteStudentSubject').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let studentId = $(this).data('student-id');
		let subjectId = $(this).val();
		deleteStudentSubject(studentId, subjectId);
	});

	function deleteStudentSubject(studentId, subjectId) {
		if (confirm('Are you sure you want to delete this subject for the student?')) {

			$.ajax({
				type: "DELETE",
				url: `/headmaster/dashboard/students/${studentId}/subjects/${subjectId}`,
				success: function(response) {
					alert(response);
					location.reload();
				},
				error: function(error) {
					alert('Error: ' + error.responseText);
				}
			});
		}
	}

	function deleteStudent(studentId) {
		if (confirm('Are you sure you want to delete the student with following ID : ' + studentId + '?')) {
			$.ajax({
				type: "DELETE",
				url: '/headmaster/dashboard/students/delete/' + studentId,
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



	$('#updateStudent').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let studentId = $('#studentId').val();
		let selectedYearId = $('#studentYear').val();
		let json = { "id": studentId, "name": $('#studentName').val(), "lastName": $('#studentLastName').val(), "year": { "id": selectedYearId }, "house": $('#studentHouse').val() };
		sendStudentData("PUT", "/headmaster/dashboard/students/update/" + studentId, json, "Updating : [");
	});

	function sendStudentData(type, url, jsonData, successMsg) {
		$.ajax({
			type: type,
			url: url,
			data: JSON.stringify(jsonData),
			dataType: "json",
			contentType: "application/json",
			success: function(data) {
				let respContent = "<div class='successAlert alert alert-success'>" + "<span class='success'>" + successMsg + "<b>" + data.name + data.lastName + "</b>] - Success! </span></div>";
				setTimeout(function() {
					window.location.href = "/headmaster/dashboard/students"
				}, 2000);
				$("#successAlert").html(respContent).show();

			},
			error: function(xhr) {
				let respContent = "<div class='errorAlert alert alert-danger'>" +
					"<span class='error'>" + xhr.responseText + "</span></div>";
				setTimeout(function() {
					$('#saveStudent').prop('disabled', false);
					$('#updateStudent').prop('disabled', false);
					$("#errorAlert").hide();
				}, 2000);
				$("#errorAlert").html(respContent).show();
				console.log("Error updating house", xhr.responseText);
			}
		});
	}
});