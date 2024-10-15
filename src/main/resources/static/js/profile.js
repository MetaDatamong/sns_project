document.addEventListener('DOMContentLoaded', function() {
	const followBtn = document.getElementById('followBtn');
	const unfollowBtn = document.getElementById('unfollowBtn');
	const userId = document.querySelector('.profile-container').dataset.userId;

	if (followBtn && unfollowBtn) {
		followBtn.addEventListener('click', function() {
			fetch(`/api/follow/${userId}`, {
				method: 'POST',
				credentials: 'same-origin'
			})
				.then(response => response.json())
				.then(data => {
					followBtn.style.display = 'none';
					unfollowBtn.style.display = 'inline-block';
				})
				.catch(error => console.error('Error:', error));
		});

		unfollowBtn.addEventListener('click', function() {
			fetch(`/api/unfollow/${userId}`, {
				method: 'POST',
				credentials: 'same-origin'
			})
				.then(response => response.json())
				.then(data => {
					unfollowBtn.style.display = 'none';
					followBtn.style.display = 'inline-block';
				})
				.catch(error => console.error('Error:', error));
		});
	}
});