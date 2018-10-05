

window.onload = function(){
	var list = document.getElementById('list');
	 list.appendChild(createFragment(10,'../images/ly.png'));
	 funLazyLoad('#list').refresh(true);
}
