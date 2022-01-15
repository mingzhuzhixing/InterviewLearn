var nativeJs={};
nativeJs.os={};
nativeJs.os.isIOS = /iOS|iPhone|iPad/i.test(navigator.userAgent);
nativeJs.os.isAndroid = !nativeJs.os.isIOS;

nativeJs.takeNativeAction = function(commandName, parameters){
   console.log("commandName:"+commandName+" parameters:"+ parameters);
   var request={};
   request.name = commandName;
   request.param = parameters;
   if(window.nativeJs.os.isAndroid){
        console.log("isAndroid:"+JSON.stringify(request));
        window.webViewJs.takeNativeAction(JSON.stringify(request))
   } else {
        window.webkit.messageHandlers.webViewJs.postMessage(JSON.stringify(request))
   }
}

window.nativeJs = nativeJs;