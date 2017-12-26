/***
Wrapper/Helper Class for datagrid based on jQuery Datatable Plugin
***/
    	var languageData;
var Datatable = function() {

    var tableOptions; // main options
    var dataTable; // datatable object
    var table; // actual table jquery object
    var tableContainer; // actual table container object
    var tableWrapper; // actual table wrapper jquery object
    var tableInitialized = false;
    var ajaxParams = {}; // set filter mode
    var the;

    var countSelectedRecords = function() {
        var selected = $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
//            var text = languageData.metronicGroupActions;
//            var selected = $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
//            if (selected > 0) {
//                $('.table-group-actions > span', tableWrapper).text(text.replace("_TOTAL_", selected));
//            } else {
//                $('.table-group-actions > span', tableWrapper).text("");
//            }
    };

    return {
    	
        //main function to initiate the module
        init: function(options) {
            if (!$().dataTable) {
                return;
            }
            var language = navigator.userLanguage || navigator.language;
        	if (language.substring(0, 2) == "zh") {
        		languageData = { // language settings
    	            // metronic spesific
    	            "metronicGroupActions": "_TOTAL_ records selected:  ",
    	            "metronicAjaxRequestGeneralError": "不能完成请求. 请检查你的网络连接",
    	
    	            // data tables spesific
    	            "lengthMenu": "每页显示  _MENU_ 条记录",
    	            "info": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
    	            "infoEmpty": "没有查到数据",
    	            "emptyTable": "没有数据",
    	            "zeroRecords": "抱歉， 没有找到",
    	            "sInfoFiltered": "",
    	            "paginate": {
    	                "previous": "上一页",
    	                "next": "下一页",
    	                "last": "最后一页",
    	                "first": "第一页",
    	                "page": "Page",
    	                "pageOf": "of"
    	            }
    	        }
//        	} else {
//        		languageData = options.dataTable.language;
        	}


            the = this;

            // default settings
            options = $.extend(true, {
                src: "", // actual table  
                filterApplyAction: "filter",
                filterCancelAction: "filter_cancel",
                loadingMessage: '加载中...',
                dataTable: {
                    //"dom": "<'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'<'table-group-actions pull-right'>>r><'table-scrollable't><'row'<'col-md-8 col-sm-12'pli><'col-md-4 col-sm-12'>>", // datatable layout
                    "pageLength": 10, // default records per page
                    "language": languageData,

                    //"orderCellsTop": true,
                    "ordering":false,
                    "searching":false,
                    "select": true,
                    "lengthChange": false,
                   

                    //"pagingType": "bootstrap_extended", // pagination type(bootstrap, bootstrap_full_number or bootstrap_extended)
                    "autoWidth": false, // disable fixed width and enable fluid table
                    "processing": false, // enable/disable display message box on record load
                    "serverSide": true, // enable/disable server side ajax loading

                    "ajax": { // define ajax settings
                        "url": "", // ajax URL
                        "type": "POST", // request type
                        "timeout": 20000,
                        "data": function(data) { // add request parameters before submit
                            $.each(ajaxParams, function(key, value) {
                                data[key] = value;
                            });
//                            Metronic.blockUI({
//                            	animate: true,
//                                target: tableContainer,
//                                overlayColor: 'none',
//                                cenrerY: true
//                            });
                            utils.tools.loading();
                        },
                        "dataSrc": function(res) { // Manipulate the data returned from the server
                            if ($('.group-checkable', table).size() === 1) {
                                $('.group-checkable', table).attr("checked", false);
                                $.uniform.update($('.group-checkable', table));
                            }

                            if (tableOptions.onSuccess) {
                                tableOptions.onSuccess.call(undefined, res);
                            }

//                            Metronic.unblockUI(tableContainer);
                            utils.tools.unloading();
                            return res.data;
                        },
                        "error": function(res) { // handle general connection errors
                        	
                            if (tableOptions.onError) {
                                tableOptions.onError.call(undefined, res);
                            }

//                            Metronic.alert({
//                                type: 'danger',
//                                icon: 'warning',
//                                message: tableOptions.dataTable.language.metronicAjaxRequestGeneralError,
//                                container: tableWrapper,
//                                place: 'prepend'
//                            });

//                            Metronic.unblockUI(tableContainer);
                            utils.tools.unloading();
                        }
                    },
                    "columnDefs" : [{
        				// The `data` parameter refers to the data for the cell (defined by the
        				// `data` option, which defaults to the column being worked with, in
        				// this case `data: 0`.
        				"render" : function(data, type, row) {
        					return data;
        				},
        				"targets" : [ "_all" ]
        			} ],

                    "drawCallback": function(oSettings) { // run some code on table redraw
                        if (tableInitialized === false) { // check if table has been initialized
                            tableInitialized = true; // set table initialized
                            table.show(); // display table
                        }
                        Metronic.initUniform($('input[type="checkbox"]', table)); // reinitialize uniform checkboxes on each table reload
                        countSelectedRecords(); // reset selected records indicator

                        // callback for ajax data load
                        if (tableOptions.onDataLoad) {
                            tableOptions.onDataLoad.call(undefined, the);
                        }
                    }
                }
            }, options);

            tableOptions = options;

            // create table's jquery object
            table = $(options.src);
            tableContainer = table.parents(".table-container");

            // apply the special class that used to restyle the default datatable
            var tmp = $.fn.dataTableExt.oStdClasses;

//            $.fn.dataTableExt.oStdClasses.sWrapper = $.fn.dataTableExt.oStdClasses.sWrapper + " dataTables_extended_wrapper";
//            $.fn.dataTableExt.oStdClasses.sFilterInput = "form-control input-small input-sm input-inline";
//            $.fn.dataTableExt.oStdClasses.sLengthSelect = "form-control input-xsmall input-sm input-inline";
//
            // initialize a datatable
            dataTable = table.DataTable(options.dataTable);
//
//            // revert back to default
//            $.fn.dataTableExt.oStdClasses.sWrapper = tmp.sWrapper;
//            $.fn.dataTableExt.oStdClasses.sFilterInput = tmp.sFilterInput;
//            $.fn.dataTableExt.oStdClasses.sLengthSelect = tmp.sLengthSelect;

            // get table wrapper
            tableWrapper = table.parents('.dataTables_wrapper');

            // build table group actions panel
            if ($('.table-actions-wrapper', tableContainer).size() === 1) {
                $('.table-group-actions', tableWrapper).html($('.table-actions-wrapper', tableContainer).html()); // place the panel inside the wrapper
                $('.table-actions-wrapper', tableContainer).remove(); // remove the template container
            }
            // handle group checkboxes check/uncheck
            $('.group-checkable', table).change(function() {
                var set = $('tbody > tr > td:nth-child(1) input[type="checkbox"]', table);
                var checked = $(this).is(":checked");
                $(set).each(function() {
                    $(this).attr("checked", checked);
                });
                $.uniform.update(set);
                //countSelectedRecords();
            });

            // handle row's checkbox click
            table.on('change', 'tbody > tr > td:nth-child(1) input[type="checkbox"]', function() {
                countSelectedRecords();
            });

            // handle filter submit button click
            table.on('click', '.filter-submit', function(e) {
                e.preventDefault();
                the.submitFilter();
            });

            // handle filter cancel button click
            table.on('click', '.filter-cancel', function(e) {
                e.preventDefault();
                the.resetFilter();
            });
        },

        submitFilter: function(form) {
            // get all typeable inputs
            $('textarea, select, input:not([type="radio"],[type="checkbox"])', form).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all checkboxes
            $('input[type="checkbox"]:checked', form).each(function() {
                the.addAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all radio buttons
            $('input[type="radio"]:checked', form).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            dataTable.ajax.reload();
        },

        resetFilter: function(form) {
            $('textarea.form-filter, select.form-filter, input.form-filter', form).each(function() {
                $(this).val("");
            });
            $('input.form-filter[type="checkbox"]', form).each(function() {
                $(this).attr("checked", false);
            });
            the.clearAjaxParams();
            dataTable.ajax.reload();
        },

        getSelectedRowsCount: function() {
            return $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).size();
        },

        getSelectedRows: function() {
            //var rows = [];
            $('tbody > tr > td:nth-child(1) input[type="checkbox"]', table).each(function() {
            	$(this).parent().parent().parent().parent().removeClass('checked');
            });
            $('tbody > tr > td:nth-child(1) input[type="checkbox"]:checked', table).each(function() {
            	$(this).parent().parent().parent().parent().addClass('checked');
            });
            var rows = dataTable.rows('.checked').data();
            return rows;
        },

        setAjaxParam: function(name, value) {
            ajaxParams[name] = value;
        },

        addAjaxParam: function(name, value) {
            if (!ajaxParams[name]) {
                ajaxParams[name] = [];
            }

            skip = false;
            for (var i = 0; i < (ajaxParams[name]).length; i++) { // check for duplicates
                if (ajaxParams[name][i] === value) {
                    skip = true;
                }
            }

            if (skip === false) {
                ajaxParams[name].push(value);
            }
        },

        clearAjaxParams: function(name, value) {
            ajaxParams = {};
        },
        
        getAjaxParams: function(name, value) {
            return ajaxParams;
        },
        
        initParam: function(form) {
        	the = this;
            // get all typeable inputs
            $('textarea, select, input:not([type="radio"],[type="checkbox"])', form).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all checkboxes
            $('input[type="checkbox"]:checked', form).each(function() {
                the.addAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all radio buttons
            $('input[type="radio"]:checked', form).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });
        },

        getDataTable: function() {
            return dataTable;
        },

        getTableWrapper: function() {
            return tableWrapper;
        },

        gettableContainer: function() {
            return tableContainer;
        },

        getTable: function() {
            return table;
        },
        isInit:function(){
        	return table == undefined?false:true;
        }
    };

};