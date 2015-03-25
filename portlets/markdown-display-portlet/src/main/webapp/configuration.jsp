<%--

    Copyright (C) 2015 Thiago Moreira (tmoreira2020@gmail.com)

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
<%@include file="/init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<div class="alert alert-info">
Fill in the url for your Markdown file. For instance <strong>https://raw.githubusercontent.com/tmoreira2020/markdown-display-app/master/README.md</strong>
</div>

<aui:form action="${configurationURL}" method="post" name="fm">

	<aui:input type="hidden" name="cmd" value="update"/>

	<fieldset>
		<c:set var="preferences" value="${portletPreferencesValues['markdownURL']}" />
		<aui:input name="preferences--markdownURL--" type="text" value="${preferences[0]}"/>

		<aui:select name="preferences--timeToLive--">
			<c:set var="preferences" value="${portletPreferencesValues['timeToLive']}" />
			<c:forTokens items="1,5,10,15,30,60" delims="," var="minutes">
				<aui:option value="${minutes}" selected="${minutes eq preferences[0]}">${minutes}</aui:option>
			</c:forTokens>
		</aui:select>

		<aui:field-wrapper label="extensions">
			<c:set var="preferences" value="${portletPreferencesValues['autolinks']}" />
			<aui:input name="preferences--autolinks--" type="checkbox" value="${preferences[0]}" />
			<c:set var="preferences" value="${portletPreferencesValues['fencedBlockCodes']}" />
			<aui:input name="preferences--fencedBlockCodes--" type="checkbox" value="${preferences[0]}" />
			<c:set var="preferences" value="${portletPreferencesValues['tables']}" />
			<aui:input name="preferences--tables--" type="checkbox" value="${preferences[0]}" />
		</aui:field-wrapper>

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</fieldset>
</aui:form>