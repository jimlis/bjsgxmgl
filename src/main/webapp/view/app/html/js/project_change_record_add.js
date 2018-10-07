
window.onload = function(){
    relPicker("chrbgsqlx",[{"text":"顾问变更","value":""},{"text":"工程变更","value":""},{"text":"其他","value":""}],"intbgsqlx");

    relPicker("chrsfqd",[{"text":"是","value":""},{"text":"否","value":""}],"intsfqd");

    relPicker("chrbgsqwj",[{"text":"待审批","value":""},{"text":"总审批A","value":""},{"text":"总审批B","value":""},{"text":"业主","value":""}],"chrbgsqwj");

    upLoadImg('#chbtn',{"busType":"bj_xm_bgsqjl"});
}

function save(){
    bjToast("保存成功");
}
