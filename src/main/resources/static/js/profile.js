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

	// 팔로워 모달 관련 코드 시작
	const followerCount = document.getElementById('followerCount');
	const followerModal = document.getElementById('followerModal');
	const followerCloseBtn = followerModal.querySelector('.close');
	const followerList = document.getElementById('followerList');

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
				followerModal.style.display = 'block';
			})
			.catch(error => console.error('Error:', error));
	});

	followerCloseBtn.onclick = function() {
		followerModal.style.display = 'none';
	}
	// 팔로워 모달 관련 코드 끝

	// following 모달창 관련 코드 시작
	const followingCount = document.getElementById('followingCount');
	const followingModal = document.getElementById('followingModal');
	const followingCloseBtn = followingModal.querySelector('.close');
	const followingList = document.getElementById('followingList');

	followingCount.addEventListener('click', function() {
		fetch(`/api/${userId}/followings`)
			.then(response => response.json())
			.then(data => {
				followingList.innerHTML = '';
				data.forEach(following => {
					const li = document.createElement('li');
					li.innerHTML = `
                        <img src="/images/person.jpeg" alt="${following.username}'s profile">
                        <div class="following-info">
                            <div class="following-username">${following.username}</div>
                            <div class="following-name">${following.nickname}</div>
                        </div>
                    `;
					followingList.appendChild(li);
				});
				followingModal.style.display = 'block';
			})
			.catch(error => console.error('Error:', error));
	});

	followingCloseBtn.onclick = function() {
		followingModal.style.display = 'none';
	}
	// following 모달창 관련 코드 끝

	window.onclick = function(event) {
		if (event.target == followerModal) {
			followerModal.style.display = 'none';
		}
		if (event.target == followingModal) {
			followingModal.style.display = 'none';
		}
	}
});