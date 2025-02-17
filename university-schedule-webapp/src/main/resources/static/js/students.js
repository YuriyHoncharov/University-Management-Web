
$(document).ready(function() {

	$('.deleteStudent').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let studentId = $(this).val();
		deleteStudent(studentId);

	});

	$('.editStudentSubjects').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let studentId = $(this).data('student-id');
		let subjectId = $(this).val();
		editStudentSubjects(studentId, subjectId);
	});
	
	$('.addSubject').click(function (e){
		e.preventDefault();
		$(this).prop('disabled', true);
		let studentId = $(this).data('student-id');
      let subjectId = $('#subjectId').val();
      editStudentSubjects(studentId, subjectId);
	});

	function editStudentSubjects(studentId, subjectId) {
		if (confirm('Are you sure you want to delete this subject for the student?')) {

			$.ajax({
				type: "POST",
				url: `/profile/dashboard/students/edit/${studentId}/subjects/${subjectId}`,
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
				url: '/profile/dashboard/students/delete/' + studentId,
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
		let json = { "id": studentId, "name": $('#studentName').val(), "lastName": $('#studentLastName').val(), "year": { "id": selectedYearId }, "house": {"id" : $('#studentHouse').val() }};
		sendStudentData("PUT", "/profile/dashboard/students/update/" + studentId, json, "Updating : [");
	});
	
		$('#createStudent').click(function(e) {
		e.preventDefault();
		$(this).prop('disabled', true);
		let selectedYearId = $('#studentYear').val();
		let json = { "login": $('#studentLogin').val(), "password": $('#studentPassword').val(), "name": $('#studentName').val(), "lastName": $('#studentLastName').val(), "year": { "id": selectedYearId }, "house": {"id" :$('#studentHouse').val() }};
		sendStudentData("POST", "/profile/dashboard/students/create", json, "Creating : [");
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
					window.location.href = "/profile/dashboard/students"
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