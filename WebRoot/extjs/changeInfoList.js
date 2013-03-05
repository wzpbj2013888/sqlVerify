Ext.Loader.setConfig({enabled: true});

Ext.Loader.setPath('Ext.ux', 'ux/');
Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.toolbar.Paging',
    'Ext.ux.PreviewPlugin',
    'Ext.ModelManager',
    'Ext.tip.QuickTipManager'
]);


Ext.onReady(function() {
	Ext.define('changeInfoList', {
		extend : 'Ext.data.Model',
		fields : [ 'id', 'name', 'owner', 'state' ],
		idProperty : 'ID'
	});

    var pluginExpanded = true;
	// create the Data Store
	var store = Ext.create('Ext.data.Store', {
		pageSize : 50,
		model : 'changeInfoList',
		// remoteSort : true,
		proxy : {
			// load using script tags for cross domain, if the data in on the
			// same domain as
			// this page, an HttpProxy would be better
			type : 'ajax',
			//url : 'http://localhost:8080/test/json/showAll.action',
			url : parent.getRootUrl() +'json/showAllChangeInfo.action',
			//url : 'data.json.txt',
			reader : {
				root : 'changeInfo',
				totalProperty : 'totalCount'
			}
		},
		sorters : [ {
			property : 'lastpost',
			direction : 'DESC'
		} ]
	});
	function rendererCompany(value,p,record){
		//console.log(value);
		//return Ext.String.format("<b>v: "+value+", p:  "+p+", r:  "+record+"</b>");
		return Ext.String.format("<b><a alt='click to look up this on baidu' href='http://www.baidu.com/s?wd="+value+"'>"+value+"</a></b>");
	}
	var grid = Ext.create('Ext.grid.Panel', {
		width : 700,
		height : 500,
		title : '变更信息',
		store : store,
		disableSelection : true,
		loadMask : true,
		viewConfig: {
            id: 'gv',
            trackOver: false,
            stripeRows: false,
            plugins: [{
                ptype: 'preview',
                bodyField: 'excerpt',
                expanded: true,
                pluginId: 'preview'
            }]
        },
		// grid columns
		columns : [ {
			text : "变更号",
			dataIndex : 'id'
		}, {
			text : "变更名称",
			dataIndex : 'name'
		}, {
			text : "变更提交人",
			dataIndex : 'owner',
			renderer: rendererCompany
		}, {
			text : "变更状态",
			dataIndex : 'state'
		} ],
		bbar : Ext.create('Ext.PagingToolbar', {
			store : store,
			displayInfo : true,
			displayMsg : 'Displaying topics {0} - {1} of {2}',
			emptyMsg : "No topics to display",
			items : [ '-', {
				text : 'Show Preview',
				pressed : pluginExpanded,
				enableToggle : true,
				toggleHandler : function(btn, pressed) {
					var preview = Ext.getCmp('gv').getPlugin('preview');
					preview.toggleExpanded(pressed);
				}
			} ]
		}),
		renderTo : 'topic-grid'
	});

	// trigger the data store load
	store.loadPage(1);
});
