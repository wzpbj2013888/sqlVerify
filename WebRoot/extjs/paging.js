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
		fields : fields,
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
			url : url,
			//url : 'data.json.txt',
			reader : {
				root : 'allUsers',
				totalProperty : 'totalCount'
			}
		},
		sorters : [ {
			property : 'lastpost',
			direction : 'DESC'
		} ]
	});

	var grid = Ext.create('Ext.grid.Panel', {
		width : width,
		height : height,
		title : title,
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
		columns : columns,
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
