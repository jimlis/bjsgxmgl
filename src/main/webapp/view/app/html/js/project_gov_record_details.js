
window.onload = function(){
	$bj_post_ajax({"url":commonApiPath+"getSysDate","data":{"dfm":"yyyy-MM-dd"},"success":function (result) {
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
        }});

	//dtPicker('#dtmxcrq');

	//upLoadImg('#chbtn',{"busType":"bj_xm_zfxcyzxys"});
}


function save(){
	console.log("baoc");
}
