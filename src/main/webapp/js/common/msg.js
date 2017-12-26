utils.msg={
		get:function(key){
			if(null == globalServerMsg || undefined == globalServerMsg){
				return key;
			}else if (undefined == globalServerMsg[key] || "" == globalServerMsg[key]) {
				return key;
			} else {
				return globalServerMsg[key];
			}
		}
}