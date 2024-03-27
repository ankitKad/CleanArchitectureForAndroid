package dev.ankit.cleanarchitectureforandroid

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import projectTemplate

class CleanArchitectureTemplateProvider: WizardTemplateProvider() {

    override fun getTemplates(): List<Template> {
        return listOf(projectTemplate)
    }

}