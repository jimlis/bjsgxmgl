
window.onload = function(){
	$bj_post_ajax({"url":commonApiPath+"getSysDate","data":{"dfm":"yyyy-MM-dd"},"success":function (result) {
					if(result){
							mui("dtmgxrqLabel").innerText=result;
                            mui("dtmgxrq").value=result;
					}
        }
    });

	debugger
	var dictUrl=dictApiPath+"getDictMapByTypes";
	$bj_post_ajax({"url":dictUrl,"data":{"types":"xclb,xcbm"},"success":function (result) {
debugger
			  if(result){
					var xclb=result["xclb"];
					if(xclb&&xclb.length>0){
						for (i in xclb){
                            xclb[i]["value"]=xclb[i]["value"];
                            xclb[i]["text"]=xclb[i]["name"];
						}
                        relPicker('intxclb',xclb);
					}
					var xcbm=result["xcbm"];
                  if(xcbm&&xcbm.length>0){
                      for (i in xcbm){
                          xcbm[i]["value"]=xcbm[i]["value"];
                          xcbm[i]["text"]=xcbm[i]["name"];
                      }
                      relPicker('intxcbm',xcbm);
                  }
			  }
        }});

	dtPicker('#dtmxcrq');
debugger
	upLoadImg('#chbtn',{"busType":"bj_xm_zfxcyzxys"});
}
function save(){
	console.log("baoc");
}
