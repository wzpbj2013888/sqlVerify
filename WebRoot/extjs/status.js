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
	Ext.define('User', {
		extend : 'Ext.data.Model',
		fields : ["changeId","changeState","id","statementId","optimizer","sqlStatement"],
		idProperty : 'ID'
	});
	
    var pluginExpanded = true;
	// create the Data Store
	var store = Ext.create('Ext.data.Store', {
		pageSize : 50,
		model : 'User',
		// remoteSort : true,
		proxy : {
			// load using script tags for cross domain, if the data in on the
			// same domain as
			// this page, an HttpProxy would be better
			type : 'ajax',
			//url : 'http://localhost:8080/test/json/showAll.action',
			url : parent.getRootUrl() + 'json/showAllSqlVerifyInfo.action',
			//url : 'data.json.txt',
			reader : {
				root : 'verifyInfo',
				totalProperty : 'totalCount'
			}
		},
		sorters : [ {
			property : 'lastpost',
			direction : 'DESC'
		} ]
	});
	function rendererCompany(value, p, record){
		var data = record.data;
		return Ext.String.format("<b><a href='showExecutionPlanInfo.jsp?stat_id="+value+"&sqlStatement=\""+data.sqlStatement.replace(/'/g,"\"")+"\"&optimizer=\""+data.optimizer+"\"&changeState=\""+data.changeState+"\"'>"+value+"</a></b>");
	}
	var grid = Ext.create('Ext.grid.Panel', {
		width : 700,
		height : 500,
		title : 'Sql��֤״̬��Ϣ',
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
			text : "ID",
			dataIndex : 'id',
			width: 40
		}, {
			text : "�����",
			dataIndex : 'changeId',
			width: 50,
		}, {
			text : "״̬",
			dataIndex : 'changeState'
		}, {
			text : "�Ż���ģʽ",
			dataIndex : 'optimizer'
		}, 
		
		
		
		{
			text : "Sql���Id",
			dataIndex : 'statementId',
			 width: 170,
			renderer: rendererCompany
		}, {
			text : "Sql���",
			dataIndex : 'sqlStatement',
			width: 370,
		}
		
		
		],
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
