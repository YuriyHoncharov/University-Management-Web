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
				url: '/headmaster/dashboard/lessons/delete/' + lessonId,
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
	

});
