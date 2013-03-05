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
			fieldLabel : "�û���",
			id : "txtName",
			name : 'user.username',
			allowBlank : false,
			blankText : "�û�������Ϊ��!"
		}, {
			xtype : 'textfield',
			fieldLabel : "����",
			allowBlank : false,
			blankText : "���벻��Ϊ��!",
			name : 'user.password',
			inputType : 'password'
		} ],
		buttons : [ {
			text : "�ύ",
			type : 'submit',
			handler : function() {
				if (form1.getForm().isValid()) {
					Ext.MessageBox.show({
						title : '��ȴ�',
						msg : '���ڼ���',
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
					// �ύ������������
					form1.form.doAction('submit', {
						url : 'Login.action',
						method : 'post',
						success : function(form, action) {
							document.location = "index.jsp";
							Ext.Msg.alert("��¼�ɹ���", action.result.message);
						},
						failure : function(form, action) {
							Ext.Msg.alert("��¼ʧ�ܣ�", action.result.message);
						}
					});
				}
			}
		}, {
			text : "����",
			handler : function() {
				form1.getForm().reset();
			}
		} ]
	});

	var window = new Ext.Window({
		title : "��¼����",
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