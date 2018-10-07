
window.onload = function(){

    relPicker("chrqxlx",[{"text":"土地","value":""},{"text":"机电","value":""},{"text":"装修","value":""},{"text":"园林","value":""}
        ,{"text":"其他","value":""}],"chrqxlx");

    relPicker("chrsgdw",[{"text":"地勘单位","value":""},{"text":"总包单位","value":""}],"intsgdw");

    dtPicker('#dtmtzrq');

    dtPicker('#dtmwczgrq');

    upLoadImg('#chbtn',{"busType":"bj_xm_zlqxbg"});
}

function save(){
	console.log("baoc");
}
