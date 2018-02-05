package org.ice1000.julia.lang.module

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * @author zxj5470, ice1000
 * @date 2018/2/1
 */
interface JuliaProjectSettingsService {
	var settings: JuliaSettings
}

val Project.juliaSettings: JuliaProjectSettingsService
	get() = ServiceManager.getService(this, JuliaProjectSettingsService::class.java)

@State(
	name = "JuliaProjectSettings",
	storages = [
		Storage(file = "\$PROJECT_FILE\$"),
		Storage(file = "\$PROJECT_CONFIG_DIR\$/juliaConfig.xml", scheme = StorageScheme.DIRECTORY_BASED)])
class JuliaProjectSettingsServiceImpl :
	JuliaProjectSettingsService, PersistentStateComponent<JuliaSettings> {
	override var settings: JuliaSettings = JuliaSettings()
	override fun getState(): JuliaSettings? = XmlSerializerUtil.createCopy(settings)
	override fun loadState(state: JuliaSettings) {
		XmlSerializerUtil.copyBean(state, settings)
	}
}
