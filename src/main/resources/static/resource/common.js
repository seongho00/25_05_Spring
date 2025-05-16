
$('select[data-value]').each(function(index, el) {
	const $el = $(el);

	defaultValue = $el.attr('data-value').trim();

	if (defaultValue.length > 0) {
		$el.val(defaultValue);
	}
});

function likeChecked() {

	console.log(articleCantLike)
	$("#test").prop("checked", true);
};


