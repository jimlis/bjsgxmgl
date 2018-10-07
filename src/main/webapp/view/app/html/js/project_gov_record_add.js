
window.onload = function(){
    relPicker("chrxclb",[{"text":"定期巡查","value":""},{"text":"非定期巡查","value":""},{"text":"专项验收","value":""},{"text":"竣工验收","value":""}]);

    relPicker("chrxcbm",[{"text":"市规划局","value":""},{"text":"区规划局","value":""},{"text":"质监站巡查","value":""},{"text":"安监站巡查","value":""},
        {"text":"业主方巡查","value":""},{"text":"负责验收部门","value":""}]);
	/*$bj_post_ajax({"url":commonApiPath+"getSysDate","data":{"dfm":"yyyy-MM-dd"},"success":function (result) {
					if(result){
						debugger
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

	dtPicker('#dtmxcrq');



	*/

    upLoadImg('#chbtn',null,{"busType":"bj_xm_zfxcyzxys"});

    dtPicker('#dtmxcrq');
}
function save(){
    bjToast("保存成功");
}
