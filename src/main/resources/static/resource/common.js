
$('select[data-value]').each(function(index, el) {
	const $el = $(el);

	defaultValue = $el.attr('data-value').trim();

	if (defaultValue.length > 0) {
		$el.val(defaultValue);
	}
});

$(".likeCheckBox").click(function() {
	if (loginMemberId == "0") {
		alert("로그인 후 이용해 주세요.");
		return;
	}

	let check = $(this).val();

	let data = {
		"articleId": articleId,
		"memberId": loginMemberId,
		"check": check
	};

	$.ajax({
		url: 'like',
		method: 'post',
		dataType: 'text',
		data: data,
		success: function() {
			location.replace('detail?id=' + articleId);
		}
	});

});