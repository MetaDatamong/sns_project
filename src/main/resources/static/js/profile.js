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

	// 모달 관련 코드 시작
	const followerCount = document.getElementById('followerCount');
	const modal = document.getElementById('followerModal');
	const closeBtn = document.getElementsByClassName('close')[0];
	const followerList = document.getElementById('followerList');
	const followerSearch = document.getElementById('followerSearch');

	followerCount.addEventListener('click', function() {
		fetch(`/api/${userId}/followers`)
			.then(response => response.json())
			.then(data => {
				followerList.innerHTML = '';
				data.forEach(follower => {
					const li = document.createElement('li');
					li.innerHTML = `
                        <img src="/images/person.jpeg" alt="${follower.username}'s profile">
                        <div class="follower-info">
                            <div class="follower-username">${follower.username}</div>
                            <div class="follower-name">${follower.nickname}</div>
                        </div>
                    `;
					followerList.appendChild(li);
				});
				modal.style.display = 'block';
			})
			.catch(error => console.error('Error:', error));
	});

	closeBtn.onclick = function() {
		modal.style.display = 'none';
	}

	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = 'none';
		}
	}
	// 모달 관련 코드 끝
});