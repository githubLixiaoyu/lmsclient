utils.select={
		//查询销售区域
		bindSaleRegionSelect:function(options){
			//ctrlName,val,callback
			var data = {};
			var url = server + "/wofcontent/selectSaleRegion.do";
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//绑定common到select控件 ——港口（销售区域与港口级联）
		bindPortSelect:function(options){
			//ctrlName,provinceCd,val
			var data = {};
			var url = server + "/common/selectPort.do";
			data["countryId"] = options.countryId;
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//根据common_type查询division1和common_content
		commonTypeSelect:function(options){
			//ctrlName,val,callback
			var data = {};
			var url = server + "/nscommon/selectByCommonType.do";
			data["commonType"] = options.commonType;
			data["division2"] = options.division2;
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//绑定common到select控件——查询制造场所
		bindMasterSelect:function(options){
			//ctrlName,val,callback
			var data = {};
			var url = server + "/producelocation/selectAllMaster.do";
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//绑定common到select控件——查询全部机种
		bindMachineKindSelect:function(options){
			//ctrlName,val,callback
			var data = {};
			var url = server + "/machinekind/selectAllMachineKind.do";
			data["elEscFlg"] = options.elEscFlg;
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//绑定common到select控件——查询全部大区
		bindRegionalSelect:function(options){
			//ctrlName,val,callback
			var data = {};
			var url = server + "/region/selectByAllRegional.do";
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//绑定common到select控件——查询根据大区code查询分公司
		bindBranchSelect:function(options){
			//ctrlName,val,callback
			var data = {};
			var url = server + "/region/selectByAllBranch.do";
			data["regionalCoed"] = options.regionalCoed;
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//绑定common到select控件——查询根据大区、分公司查询代理店
		bindAgentSelect:function(options){
			//ctrlName,val,callback
			var data = {};
			var url = server + "/customervendor/selectByAllAgent.do";
			data["regionalCoed"] = options.regionalCoed;
			data["attachedSubcompanyId"] = options.attachedSubcompanyId;
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//select控件 --角色
		roleSelectList:function(options){
			//ctrlName,val
			var data = {};
			var url = server + "/role/selectByAll.do";
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//绑定common到select控件
		bindCommonSelect:function(options){
			//ctrlName,val
			var data = {};
			var url = server + "/common/selectCommonById.do";
			data["commonId"] = options.commonId;
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//查询common内容list(暂时没有用上)
		getCommonList:function(commonId,callback){
			var data = {};
			data["commonId"] = commonId;
			var url = server + "/common/selectCommonById.do";
			this.getList(url,data,function(result){
				callback(result);
			});
		},
		//绑定common到select控件——省
		bindProvinceSelect:function(options){
			//ctrlName,val,callback
			var data = {};
			var url = server + "/common/selectProvince.do";
			data["countryType"] = options.countryType;
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//绑定common到select控件 ——市（省市联动）
		bindCitySelect:function(options){
			//ctrlName,provinceCd,val
			var data = {};
			var url = server + "/common/selectCity.do";
			data["provinceCd"] = options.provinceCd;
			data["countryType"] = options.countryType;
			options["url"] = url;
			options["data"] = data;
			this.bindSelect(options);
		},
		//绑定内容到指定的Select控件
		bindSelect : function(options) {
			//ctrlName, url, data,val,callback
			var control = $('#' + options.ctrlName);
			var allowClear = true;
			if(options.allowClear != undefined && !options.allowClear){
				allowClear=options.allowClear;
			}
			// 设置Select2的处理
			 this.getList(options.url,options.data,function(result){
				 control.empty();//清空下拉框
				 control.select2({
						placeholder : " ",
						data:result,
						allowClear : allowClear,
						formatResult : this.format,
						formatSelection : this.format,
						escapeMarkup : function(m) {
							return m;
						}
					});
				 //设置默认值
				 var val = "";
				 if(options.val == undefined || options.val == ""){
					 if(!$.isEmptyObject(result) && options.noblank){
						 val = result[0].id;
						 control.select2("val",val);
					 }
				 } else {
					 val = options.val;
					 control.select2("val",val);
				 }
				 if(utils.tools.isFunction(options.callback)){
					 options.callback(val);
	    		 }
			 });
		},
		// 查询内容
		getList:function(url,data,callback){
		    // 绑定Ajax的内容
		    utils.ajax.post(url, data, function(result){
		    	result = result.data;
		    	callback(result);
		    })
		},
		//格式化列表项
		format:function(state) {
			return state.text;
		}		
};