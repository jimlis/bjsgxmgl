
window.onload = function(){
    var list = document.getElementById('img-list');
    list.appendChild(createFragment(10,'../images/ly.png'));
    funLazyLoad('#img-list').refresh(true);

    relPicker("intsgdw",[{"text":"地勘单位","value":""},{"text":"总包单位","value":""}]);

    dtPicker('#dtmtzrq');

    dtPicker('#dtmwczgrq');
}

/*
*编辑
 */
function  openAdd() {
    var address = "project_saft_report_add.html?id=";
    toUrl(address);
}
