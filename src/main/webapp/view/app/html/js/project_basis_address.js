

window.onload = function(){
	var list = document.getElementById('list');
	 list.appendChild(createFragment(40,"http://www.dcloud.io/hellomui/images/1.jpg"));
	 funLazyLoad('#list').refresh(true);
}
