var obj = getRequest();

$bjAjax({
	url:xmzfxcyzxysApiDetail,
	type:"post",
	data:{
		xmZfxcyzxysId:obj.id
	},
	success:function(data){
		document.getElementById("dtmgxrq").innerText=data.dtmgxrq;
		document.getElementById("dtmxcrq").innerText=data.dtmxcrq;
		document.getElementById("intxcbm").innerText=data.intxcbm;
		document.getElementById("chrxcry").innerText=data.chrxcry;
		document.getElementById("dtmxcrq").innerText=data.dtmxcrq;
		document.getElementById("chrzb").innerText=data.chrzb;
		document.getElementById("chrbgrmc").innerText=data.chrbgrmc;
	}
});

window.onload = function(){
    var list = document.getElementById('img-list');
    list.appendChild(createFragment(10,'../images/ly.png'));
    funLazyLoad('#img-list').refresh(true);

}

function edit(){
	toUrl("project_gov_record_add.html?id="+obj.id);
}


