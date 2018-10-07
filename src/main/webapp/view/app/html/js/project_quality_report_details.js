
window.onload = function(){
    var list = document.getElementById('img-list');
    list.appendChild(createFragment(10,'../images/ly.png'));
    funLazyLoad('#img-list').refresh(true);

/*    relPicker("chrqxlx",[{"text":"土地","value":""},{"text":"机电","value":""},{"text":"装修","value":""},{"text":"园林","value":""}
        ,{"text":"其他","value":""}],"chrqxlx");

    relPicker("chrsgdw",[{"text":"地勘单位","value":""},{"text":"总包单位","value":""}],"intsgdw");

    dtPicker('#dtmtzrq');

    dtPicker('#dtmwczgrq');*/

    //upLoadImg('#chbtn',{"busType":"bj_xm_zlqxbg"});
}

/*
*编辑
 */
function  openAdd() {
    var address = "project_quality_report_add.html?id=";
    toUrl(address);
}
