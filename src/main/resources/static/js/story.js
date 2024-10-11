/**
 2. 스토리 페이지
 (1) 스토리 스크롤 페이징하기
 (2) 좋아요, 안좋아요
 (3) 댓글쓰기
 (4) 댓글삭제
 */

// (1) 스토리 스크롤 페이징하기
let page = 0;

function loadMoreStories() {
	page++;
	window.location.href = `/posting?page=${page}`;
}

// 스크롤 이벤트 리스너
$(window).scroll(() => {
	if ($(window).scrollTop() + $(window).height() > $(document).height() - 100) {
		loadMoreStories();
	}
});

// (2) 좋아요, 안좋아요
function toggleLike(postingId) {
	document.getElementById(`likeForm-${postingId}`).submit();
}

// (3) 댓글쓰기
function submitComment(postingId) {
	let commentForm = document.getElementById(`commentForm-${postingId}`);
	let commentInput = commentForm.querySelector('input[name="content"]');

	if (commentInput.value.trim() === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

	commentForm.submit();
}

// (4) 댓글 삭제
function deleteComment(commentId) {
	if (confirm("댓글을 삭제하시겠습니까?")) {
		document.getElementById(`deleteCommentForm-${commentId}`).submit();
	}
}

// 페이지 로드 시 이벤트 리스너 등록
$(document).ready(function() {
	// 좋아요 버튼 이벤트 리스너
	$(document).on('click', '.like-button', function(e) {
		e.preventDefault();
		let postingId = $(this).data('posting-id');
		toggleLike(postingId);
	});

	// 댓글 제출 버튼 이벤트 리스너
	$(document).on('click', '.comment-submit', function(e) {
		e.preventDefault();
		let postingId = $(this).data('posting-id');
		submitComment(postingId);
	});

	// 엔터 키로 댓글 추가
	$(document).on('keypress', '.comment-input', function(e) {
		if (e.which === 13) {  // 엔터 키 코드
			e.preventDefault();
			let postingId = $(this).data('posting-id');
			submitComment(postingId);
		}
	});
});