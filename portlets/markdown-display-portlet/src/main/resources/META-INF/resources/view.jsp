<%--

    Copyright © 2015 Thiago Moreira (tmoreira2020@gmail.com)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test="${not empty(requestScope.content)}">
		${requestScope.content}
	</c:when>
	<c:otherwise>
		<div class="alert alert-info portlet-configuration">
			<a href="${portletDisplay.URLConfiguration}" onClick="${portletDisplay.URLConfigurationJS}">
				<liferay-ui:message key="please-configure-this-portlet-to-make-it-visible-to-all-users" />
			</a>
		</div>
	</c:otherwise>
</c:choose>
