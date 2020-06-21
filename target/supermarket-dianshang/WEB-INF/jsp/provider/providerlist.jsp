<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面</span>
        </div>
        <div class="search">
        	<form method="get" action="${pageContext.request.contextPath }/sys/provider/provider.do">
				<input name="method" value="query" type="hidden">
				<span>供应商编码：</span>
				<input name="queryProCode" type="text" value="${queryProCode }">
				
				<span>供应商名称：</span>
				<input name="queryProName" type="text" value="${queryProName }">

				<input type="hidden" name="pageIndex" value="1"/>
				<input value="查 询" type="submit" id="searchbutton">
				<a href="${pageContext.request.contextPath }/sys/provider/provideradd.html">添加供应商</a>
			</form>
        </div>
        <!--供应商操作表格-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">供应商编码</th>
                <th width="20%">供应商名称</th>
                <th width="10%">联系人</th>
                <th width="10%">联系电话</th>
                <th width="10%">传真</th>
                <th width="10%">创建时间</th>
                <th width="30%">操作</th>
            </tr>
            <c:forEach var="provider" items="${providerList }" varStatus="status">
				<tr>
					<td>
					<span>${provider.proCode }</span>
					</td>
					<td>
					<span>${provider.proName }</span>
					</td>
					<td>
					<span>${provider.proContact}</span>
					</td>
					<td>
					<span>${provider.proPhone}</span>
					</td>
					<td>
					<span>${provider.proFax}</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${provider.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
<%--					<span><a class="viewProvider" href="javascript:;" proid=${provider.id } proname=${provider.proName }><img src="${pageContext.request.contextPath }/statics/images/read.png" alt="查看" title="查看"/></a></span>--%>

						<span>
							<a class="viewProvider" href="${pageContext.request.contextPath }/sys/provider/providerview.html?pid=${provider.id}"  providerid=${provider.id } providername=${provider.proName}><img src="${pageContext.request.contextPath }/statics/images/read.png" alt="查看" title="查看"/></a>
						</span>

<%--					<span><a class="modifyProvider" href="javascript:;" proid=${provider.id } proname=${provider.proName }><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>--%>

						<span>
							<a class="modifyProvider" href="${pageContext.request.contextPath }/sys/provider/providermodify.html?pid=${provider.id}" providerid=${provider.id } providername=${provider.proName}><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改"/></a>
						</span>

<%--				<span><a class="deleteProvider" href="javascript:;" proid=${provider.id } proname=${provider.proName }><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除"/></a></span>--%>

						<span>
								<a class="deleteProvider" href="${pageContext.request.contextPath }/sys/provider/providerdelete.html?pid=${provider.id}" providerid=${provider.id } providername=${provider.proName}><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除"/></a>
						</span>

					</td>
				</tr>
			</c:forEach>
        </table>

	<%--引入（导入）rollpage.jsp文件--%>
	<input type="hidden" id="totalPageCount" value="${pageSupport.totalPageCount}"/>

	<%--相对路径--%>
	<c:import url="../common/rollpage.jsp">
		<c:param name="totalCount" value="${pageSupport.totalCount}"/>
		<c:param name="currentPageNo" value="${pageSupport.currentPageNo}"/>
		<c:param name="totalPageCount" value="${pageSupport.totalPageCount}"/>
	</c:import>

    </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
   <div class="removerChid">
       <h2>提示</h2>
       <div class="removeMain" >
           <p>你确定要删除该供应商吗？</p>
           <a href="#" id="yes">确定</a>
           <a href="#" id="no">取消</a>
       </div>
   </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/providerlist.js"></script>
