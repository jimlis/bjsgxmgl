var obj = getRequest();
$bjAjax({
	url:photoApiDetailText,
	type:"post",
	data:{
		xmZpjlId:obj.id
	},
	success:function(data){
		document.getElementById("dtmbgrq").innerText = data.dtmbgrq;
		document.getElementById("intbglb").innerText = data.intbglb;
		document.getElementById("").innerText = data.dtmbgrq;
		document.getElementById("chrbgrmc").innerText = data.chrbgrmc;
	}
});
$bjAjax({
	url:photoApiDetail,
	type:"post",
	data:{
		xmZpjlId:obj.id
	},
	success:function(data){
		
	}
});

window.onload = function(){
	var list = document.getElementById('list');
	 list.appendChild(createFragment(10,'../images/ly.png'));
	 funLazyLoad('#list').refresh(true);
}
