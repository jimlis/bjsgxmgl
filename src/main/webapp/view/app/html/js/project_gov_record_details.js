
window.onload = function(){
    var list = document.getElementById('img-list');
    list.appendChild(createFragment(10,'../images/ly.png'));
    funLazyLoad('#img-list').refresh(true);

	/*$bj_post_ajax({"url":commonApiPath+"getSysDate","data":{"dfm":"yyyy-MM-dd"},"success":function (result) {
					if(result){
							mui("#dtmgxrqLabel")[0].innerText=result;
                            mui("#dtmgxrq")[0].value=result;
					}
        }
    });

	var dictUrl=dictApiPath+"getDictMapByTypes";
	$bj_post_ajax({"url":dictUrl,"data":{"types":"xclb,xcbm"},"success":function (result) {
			  if(result){
					var xclb=result["xclb"];
					if(xclb&&xclb.length>0){
						for (i in xclb){
                            xclb[i]["value"]=xclb[i]["value"];
                            xclb[i]["text"]=xclb[i]["name"];
						}
                        relPicker('chrxclb',xclb,'intxclb');
					}
					var xcbm=result["xcbm"];
                  if(xcbm&&xcbm.length>0){
                      for (i in xcbm){
                          xcbm[i]["value"]=xcbm[i]["value"];
                          xcbm[i]["text"]=xcbm[i]["name"];
                      }
                      relPicker('chrxcbm',xcbm,'intxcbm');
                  }
			  }
        }});*/

	//    upLoadImg('#chbtn',{"busType":"bj_xm_zfxcyzxys"});

	//dtPicker('#dtmxcrq');

   // dtPicker('#dtmxcrq');


}


function save(){
	console.log("baoc");
}

/**
 *编辑
 */
function openAdd(){
    var address = "project_gov_record_add.html?id=";
    toUrl(address);
}

