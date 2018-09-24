
var createFragment = function(count) {
			var fragment = document.createDocumentFragment();
			var ul = document.createElement('ul');
			ul.className = 'mui-table-view mui-table-view-chevron mui-grid-view';
			var li;
			for(var i = 0; i < count; i++) {
				li = document.createElement('li');
				li.className = 'mui-table-view-cell mui-media mui-col-xs-4';
				li.innerHTML = `<a>
									<img class="mui-media-object mui-pull-left" data-lazyload="http://www.dcloud.io/hellomui/images/1.jpg">
								</a>`;
				ul.appendChild(li);
			}
			fragment.appendChild(ul);
			return fragment;
		};

var lazyLoad = mui('#list').imageLazyload({
    placeholder: '../images/bj_building.png',
    destroy: false
});

window.onload = function(){
	var list = document.getElementById('list');
	 list.appendChild(createFragment(40));
	 lazyLoad.refresh(true);
}
