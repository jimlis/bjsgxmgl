
window.onload = function(){
    relPicker("intsgdw",[{"text":"地勘单位","value":""},{"text":"总包单位","value":""}]);

    dtPicker('#dtmtzrq');

    dtPicker('#dtmwczgrq');

    upLoadImg('#chbtn',{"busType":"bj_xm_aqbg"});
}

function save(){
	bjToast("保存成功");
}
