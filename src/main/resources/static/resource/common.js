

$(".likeCheckBox").click(function() {
	if (loginMemberId == "0") {
		alert("로그인 후 이용해 주세요.");
		return;
	}

	let check;

	if ($(this).parent().hasClass("heart-active")) { // 좋아요 체크 X (빈하트)
		check = 0;
	} else { // 좋아요 체크 O (꽉찬하트)
		check = 1;
	}

	console.log(check);
	let data = {
		"articleId": articleId,
		"memberId": loginMemberId,
		"check": check
	};

	fetch("like", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify(data)
	});

});