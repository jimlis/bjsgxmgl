var key="bjsgxmgl_lfj";

function getToken(){
	var tel=getCookie("tel");
	var timestamp=new Date().getTime();
	if(!tel){
		return "";
	}
	var str=tel+"_"+timestamp;
	return encryptByDES(str, key);
}

/*function getTel(){
	return decryptByDES(getToken(), key);
}*/

//DES加密
function encryptByDES(message, key) {
    var keyHex = CryptoJS.enc.Utf8.parse(key);
    var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.toString();
}

//DES解密
function decryptByDES(message, key) {
    var keyHex = CryptoJS.enc.Utf8.parse(key);
    var decrypted = CryptoJS.DES.decrypt(message,keyHex,{
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
      });
    return decrypted.toString(CryptoJS.enc.Utf8);
}

