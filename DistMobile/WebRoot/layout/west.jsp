<%@ page language="java" pageEncoding="UTF-8"%>
<div class="easyui-panel" data-options=" border:false,fit:true">
 
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="组织机构" data-options="iconCls:'icon-save'">
			<ul id="layout_west_tree" class="easyui-tree" data-options="
					url : '${pageContext.request.contextPath}/menuAction!getAllTreeNode.action',
					parentField : 'pid',
					lines : true,
					onLoadSuccess : function(node,data){
					$(this).tree('collapseAll')
					},
					onClick : function(node) {
						if (node.attributes.url) {
							var url = 'index.html';
							addTab({
								title : node.text,
								closable : true,
								href :url
							});
						}
					}
					"></ul>
		</div>
		<!-- var url = node.attributes.url; 'user!list.action?'+node.id;  alert(node.id);-->
	</div>
</div>