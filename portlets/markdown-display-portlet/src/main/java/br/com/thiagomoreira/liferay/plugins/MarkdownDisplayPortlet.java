/**
 * Copyright © 2015 Thiago Moreira (tmoreira2020@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.thiagomoreira.liferay.plugins;

import java.io.IOException;
import java.io.Serializable;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.profiles.pegdown.Extensions;
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.options.DataHolder;

@Component(immediate = true, property = {
		"com.liferay.portlet.display-category=category.thiagomoreira",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.configuration-action-class=br.com.thiagomoreira.liferay.plugins.MarkdownDisplayConfigurationAction",
		"javax.portlet.display-name=Markdown Display",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"}, service = Portlet.class)
public class MarkdownDisplayPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		PortletPreferences preferences = request.getPreferences();
		String markdownURL = preferences.getValue("markdownURL", null);
		int timeToLive = 60 * GetterUtil.getInteger(preferences.getValue(
				"timeToLive", "1"));
		boolean autolinks = GetterUtil.getBoolean(preferences.getValue(
				"autolinks", "false"));
		boolean fencedBlockCodes = GetterUtil.getBoolean(preferences.getValue(
				"fencedBlockCodes", "false"));
		boolean tables = GetterUtil.getBoolean(preferences.getValue("tables",
				"false"));

		if (Validator.isNotNull(markdownURL)) {
			PortalCache<Serializable, Object> portalCache = SingleVMPoolUtil
					.getPortalCache(MarkdownDisplayPortlet.class.getName());

			String content = (String) portalCache.get(markdownURL);

			if (content == null) {
				String markdownSource = HttpUtil.URLtoString(markdownURL);

				int options = Extensions.NONE;
				if (autolinks) {
					options = options | Extensions.AUTOLINKS;
				}
				if (fencedBlockCodes) {
					options = options | Extensions.FENCED_CODE_BLOCKS;
				}
				if (tables) {
					options = options | Extensions.TABLES;
				}

				DataHolder dataHolder = PegdownOptionsAdapter
						.flexmarkOptions(options);
				Parser parser = Parser.builder(dataHolder).build();
				HtmlRenderer renderer = HtmlRenderer.builder(dataHolder)
						.build();

				Node document = parser.parse(markdownSource);
				content = renderer.render(document);

				portalCache.put(markdownURL, content, timeToLive);
			}

			request.setAttribute("content", content);
		} else {
			request.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY,
					Boolean.TRUE);
		}

		super.render(request, response);
	}
}
