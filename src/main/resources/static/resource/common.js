

$(".likeCheckBox").click(function() {
	if (loginMemberId == "0") {
		alert("로그인 후 이용해 주세요.");
		return;
	}
	let check = 1;

	let data = {
		"articleId": articleId,
		"memberId": loginMemberId,
		"check": check
	};
	console.log(data);

	fetch("like", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify(data)
	});

});