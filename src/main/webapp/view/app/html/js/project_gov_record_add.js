
window.onload = function(){
	 relPicker('showTypePicker',[{
			value: 'ywj',
			text: '董事长 叶文洁'
		}, {
			value: 'aaa',
			text: '总经理 艾AA'
	}]);
	relPicker('showAddressPicker',[{
			value: 'ywj',
			text: '#1栋'
		}, {
			value: 'aaa',
			text: '#2栋'
	}]);
	dtPicker('#showDatePicker');
	upLoadImg('#chbtn','#upbtn');
}
function save(){
	console.log("baoc");
}
