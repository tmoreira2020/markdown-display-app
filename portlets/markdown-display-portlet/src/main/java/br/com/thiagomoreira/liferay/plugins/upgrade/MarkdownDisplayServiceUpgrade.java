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
package br.com.thiagomoreira.liferay.plugins.upgrade;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import br.com.thiagomoreira.liferay.plugins.upgrade.v1_0_0.UpgradePortletId;

@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class MarkdownDisplayServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.0", new UpgradePortletId());
	}

}
