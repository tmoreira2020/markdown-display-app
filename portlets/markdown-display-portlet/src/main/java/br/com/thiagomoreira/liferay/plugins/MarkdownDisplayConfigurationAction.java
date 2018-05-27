/**
 * Copyright (C) 2015 Thiago Moreira (tmoreira2020@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.thiagomoreira.liferay.plugins;

import java.io.Serializable;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

@Component(
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=br_com_thiagomoreira_liferay_plugins_MarkdownDisplayPortlet"
	},
	service = ConfigurationAction.class)
public class MarkdownDisplayConfigurationAction
		extends
			DefaultConfigurationAction {

	@Override
	protected void postProcess(long companyId, PortletRequest portletRequest,
			PortletPreferences portletPreferences) throws PortalException {

		PortalCache<Serializable, Object> portalCache = SingleVMPoolUtil
				.getPortalCache(MarkdownDisplayPortlet.class.getName());

		portalCache.removeAll();
	}
}
