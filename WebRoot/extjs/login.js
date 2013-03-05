Ext.onReady(function() {
	//Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = "side";

	var form1 = new Ext.FormPanel({
		labelWidth : 40,
		baseCls : 'x-plain',
		defaults : {
			width : 180
		},
		items : [ {
			xtype : 'textfield',
			fieldLabel : "用户名",
			id : "txtName",
			name : 'user.username',
			allowBlank : false,
			blankText : "用户名不能为空!"
		}, {
			xtype : 'textfield',
			fieldLabel : "密码",
			allowBlank : false,
			blankText : "密码不能为空!",
			name : 'user.password',
			inputType : 'password'
		} ],
		buttons : [ {
			text : "提交",
			type : 'submit',
			handler : function() {
				if (form1.getForm().isValid()) {
					Ext.MessageBox.show({
						title : '请等待',
						msg : '正在加载',
						progressText : '',
						width : 300,
						progress : true,
						closable : 'false',
						animEl : 'loding'
					});
					var f = function(v) {
						return function() {
							var i = v / 11;
							Ext.MessageBox.updateProgress(i, '');
						}
					}
					for ( var i = 1; i < 33; i++) {
						setTimeout(f(i), i * 1500);
					}
					// 提交到服务器操作
					form1.form.doAction('submit', {
						url : 'Login.action',
						method : 'post',
						success : function(form, action) {
							document.location = "index.jsp";
							Ext.Msg.alert("登录成功！", action.result.message);
						},
						failure : function(form, action) {
							Ext.Msg.alert("登录失败！", action.result.message);
						}
					});
				}
			}
		}, {
			text : "重置",
			handler : function() {
				form1.getForm().reset();
			}
		} ]
	});

	var window = new Ext.Window({
		title : "登录窗口",
		layout : 'fit',
		width : 290,
		height : 250,
		plain : true,
		bodyStyle : 'padding:10px',
		maximizable : false,
		closeActon : 'close',
		closable : false,
		collapsible : true,
		buttonAlign : 'center',
		items : form1
	});
	window.show();

});