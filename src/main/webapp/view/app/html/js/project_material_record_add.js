
window.onload = function(){
    relPicker("chrclyblx",[{"text":"土建","value":""},{"text":"机电","value":""},{"text":"装修","value":""},{"text":"园林","value":""},
        {"text":"其他","value":""}],"intclyblx");

    relPicker("chrsgdw",[{"text":"地勘单位","value":""},{"text":"总包单位","value":""}],"intsgdw");

    relPicker("intsfdtp",[{"text":"是","value":""},{"text":"否","value":""}],"chrsfdtp");

    relPicker("intsplczt",[{"text":"带审批","value":""},{"text":"总部审批A","value":""},{"text":"总部审批B","value":""},{"text":"业主","value":""}],"chrsplczt");

    upLoadImg('#chbtn',{"busType":"bj_xm_clybspjl"});
}

function save(){
    bjToast("保存成功");
}
