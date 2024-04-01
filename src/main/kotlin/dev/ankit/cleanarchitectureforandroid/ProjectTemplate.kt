import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.android.tools.idea.wizard.template.impl.defaultPackageNameParameter
import java.io.File

val projectTemplate
    get() = template {
        name = "Empty Activity for Clean Architecture"
        minApi = MIN_API
        description = "Creates an empty activity along with Clean Architecture Setup"
        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.NewProject,
            WizardUiContext.NewProjectExtraDetail
        )
        constraints = listOf(TemplateConstraint.AndroidX, TemplateConstraint.Kotlin)

        lateinit var layoutName: StringParameter
        val activityClass: StringParameter = stringParameter {
            name = "Activity Name"
            constraints = listOf(Constraint.CLASS, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = {
                layoutToActivity(layoutName.value)
            }
            default = "LauncherActivity"
            help = "The name of the activity class to create"
        }
        layoutName = stringParameter {
            name = "Layout Name"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = {
                activityToLayout(activityClass.value)
            }
            default = "activity_launcher"
            help = "The name of the UI layout to create for the activity"
        }

        val shallAddRoom: BooleanParameter = booleanParameter {
            name = "Add Room Dependencies?"
            visible = { true }
            default = false
            help = "If true, this will add secured Room related dependencies in the project"
        }

        val shallAddPinView: BooleanParameter = booleanParameter {
            name = "Add PinView Dependency?"
            visible = { true }
            default = false
            help = "If true, this will add PinView dependency in the project which will help you make OTP capture view"
        }

        val disableScreenshot: BooleanParameter = booleanParameter {
            name = "Disable Screenshot?"
            visible = { true }
            default = false
            help = "If true, this will disable screenshot for Prod environment"
        }

        val appName: StringParameter = stringParameter {
            name = "App name"
            default = "DemoApp"
        }

        val addRootCheck: BooleanParameter = booleanParameter {
            name = "Add Root Check?"
            visible = { true }
            default = false
            help = "If true, this will enable root check inside the app"
        }

        val packageName = defaultPackageNameParameter

        widgets(
            TextFieldWidget(appName),
            CheckBoxWidget(shallAddRoom),
            CheckBoxWidget(shallAddPinView),
            CheckBoxWidget(disableScreenshot),
            CheckBoxWidget(addRootCheck),
            PackageNameWidget(packageName),
        )

        thumb {
            File("empty-activity").resolve("template_empty_activity.png")
        }

        recipe = { data: TemplateData ->
            generateEmptyActivityWithCA(
                data as ModuleTemplateData,
                shallAddRoom.value,
                shallAddPinView.value,
                packageName.value,
                appName.value,
                minApi,
                disableScreenshot.value,
                addRootCheck.value
            )
        }

    }