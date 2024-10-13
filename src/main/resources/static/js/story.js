/**
 2. 스토리 페이지
 (1) 좋아요, 안좋아요
 (2) 댓글쓰기
 (3) 댓글삭제
 (4) 게시물 옵션 (수정, 삭제)
 */

// (1) 좋아요, 안좋아요
function toggleLike(postingId) {
	document.getElementById(`likeForm-${postingId}`).submit();
}

// (2) 댓글쓰기
function submitComment(postingId) {
	let commentForm = document.getElementById(`commentForm-${postingId}`);
	let commentInput = commentForm.querySelector('input[name="content"]');

	if (commentInput.value.trim() === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

	commentForm.submit();
}

// (3) 댓글 삭제
function deleteComment(commentId) {
	if (confirm("댓글을 삭제하시겠습니까?")) {
		document.getElementById(`deleteCommentForm-${commentId}`).submit();
	}
}

// (4) 게시물 옵션 (수정, 삭제)
function togglePostOptions(button) {
	const dropdown = button.nextElementSibling;
	dropdown.style.display = dropdown.style.display === 'none' ? 'block' : 'none';
}

function deletePost(postId) {
	if (confirm('정말로 이 게시물을 삭제하시겠습니까?')) {
		// 서버에 삭제 요청을 보내는 form을 생성하고 제출
		let form = document.createElement('form');
		form.method = 'POST';
		form.action = `/post/${postId}/delete`;
		document.body.appendChild(form);
		form.submit();
	}
}

// 페이지 로드 시 이벤트 리스너 등록
document.addEventListener('DOMContentLoaded', function() {
	// 좋아요 버튼 이벤트 리스너
	document.querySelectorAll('.like-button').forEach(button => {
		button.addEventListener('click', function(e) {
			e.preventDefault();
			let postingId = this.dataset.postingId;
			toggleLike(postingId);
		});
	});

	// 댓글 제출 버튼 이벤트 리스너
	document.querySelectorAll('.comment-submit').forEach(button => {
		button.addEventListener('click', function(e) {
			e.preventDefault();
			let postingId = this.dataset.postingId;
			submitComment(postingId);
		});
	});

	// 엔터 키로 댓글 추가
	document.querySelectorAll('.comment-input').forEach(input => {
		input.addEventListener('keypress', function(e) {
			if (e.key === 'Enter') {
				e.preventDefault();
				let postingId = this.dataset.postingId;
				submitComment(postingId);
			}
		});
	});

	// 게시물 옵션 버튼 이벤트 리스너
	document.querySelectorAll('.post-options-button').forEach(button => {
		button.addEventListener('click', function(e) {
			e.stopPropagation();
			togglePostOptions(this);
		});
	});

	// 페이지의 다른 부분을 클릭하면 드롭다운 닫기
	document.addEventListener('click', function(e) {
		if (!e.target.closest('.post-options')) {
			document.querySelectorAll('.post-options-dropdown').forEach(dropdown => {
				dropdown.style.display = 'none';
			});
		}
	});

	// "더 보기" 버튼 클릭 이벤트 (필요한 경우)
	const loadMoreButton = document.querySelector('.load-more-button');
	if (loadMoreButton) {
		loadMoreButton.addEventListener('click', function(e) {
			e.preventDefault();
			window.location.href = this.getAttribute('href');
		});
	}
});