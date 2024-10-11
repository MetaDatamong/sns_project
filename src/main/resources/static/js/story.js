/**
 2. 스토리 페이지
 (1) 스토리 로드하기
 (2) 스토리 스크롤 페이징하기
 (3) 좋아요, 안좋아요
 (4) 댓글쓰기
 (5) 댓글삭제
 */

// (0) 현재 로그인한 사용자 아이디
// 주석: principalId를 서버에서 받아오는 방식으로 변경해야 할 수 있습니다.
let principalId = $("#principalId").val();

// (1) 스토리 로드하기
let page = 0;

function storyLoad() {
	$.ajax({
		// 주석: URL을 현재 프로젝트 구조에 맞게 수정
		url: `/posting?page=${page}`,
		dataType: "json"
	}).done(res => {
		//console.log(res);
		// 주석: 서버 응답 구조에 맞게 수정
		res.content.forEach((post) => {
			let storyItem = getStoryItem(post);
			$("#storyList").append(storyItem);
		});
	}).fail(error => {
		console.log("오류", error);
	});
}

storyLoad();

function getStoryItem(post) {
	// 주석: Posts 엔티티 구조에 맞게 수정
	let item = `<div class="story-list__item">
    <div class="sl__item__header">
        <div>
            <img class="profile-image" src="/images/person.jpeg" />
        </div>
        <div>${post.userId}</div>
    </div>

    <div class="sl__item__img">
        <img src="${post.postImageUrl}" />
    </div>

    <div class="sl__item__contents">
        <div class="sl__item__contents__icon">
            <button>
                <i class="far fa-heart" id="storyLikeIcon-${post.postingId}" onclick="toggleLike(${post.postingId})"></i>
            </button>
        </div>

        <span class="like"><b id="storyLikeCount-${post.postingId}">${post.likeCount} </b>likes</span>

        <div class="sl__item__contents__content">
            <p>${post.content}</p>
        </div>

        <div id="storyCommentList-${post.postingId}">
            <!-- 주석: 댓글 기능은 아직 구현되지 않았으므로 제거 -->
        </div>

        <div class="sl__item__input">
            <input type="text" placeholder="댓글 달기..." id="storyCommentInput-${post.postingId}" />
            <button type="button" onClick="addComment(${post.postingId})">게시</button>
        </div>

    </div>
</div>`;
	return item;
}

// (2) 스토리 스크롤 페이징하기
$(window).scroll(() => {
	let checkNum = $(window).scrollTop() - ($(document).height() - $(window).height());

	if (checkNum < 1 && checkNum > -1) {
		page++;
		storyLoad();
	}
});

// (3) 좋아요, 안좋아요
// 주석: 좋아요 기능은 아직 구현되지 않았으므로 주석 처리
/*
function toggleLike(postingId) {
    // 구현 필요
}
*/

// (4) 댓글쓰기
// 주석: 댓글 기능은 아직 구현되지 않았으므로 주석 처리
/*
function addComment(postingId) {
    // 구현 필요
}
*/

// (5) 댓글 삭제
// 주석: 댓글 삭제 기능은 아직 구현되지 않았으므로 주석 처리
/*
function deleteComment(commentId) {
    // 구현 필요
}
*/