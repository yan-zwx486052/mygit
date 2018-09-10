<#macro menuinternal items>
  <#list items as item>
	<#if item.page>
	  <#if item.action?has_content>
	  <li><span><a turl="${item.action?substring(1)}">${item.name}</a></span></li>
	  <#else>
	  <li><span><a turl="common/todo.html">${item.name}</a></span></li>
	  </#if>
	<#elseif item.leaf>
	  <li><span>${item.name}</span></li>
	<#else>
	  <li><span>${item.name}</span>
		<ul>
		  <@menuinternal item.child />
		</ul>
	  </li>
	</#if>
  </#list>
</#macro>

<#macro menu code>
  <@menuinternal resource(code, "mp") />
</#macro>

<#macro toolbar code>
  <#assign items = resource(code, "b") />
  <#if items?? && (items?size > 0)>
  toolbar: [<#list items as item>{text:'${item.name}',iconCls:'${item.icon!''}',handler:${item.action!''}}<#if item_has_next>,</#if></#list>]<#else>toolbar2 : null</#if></#macro>
