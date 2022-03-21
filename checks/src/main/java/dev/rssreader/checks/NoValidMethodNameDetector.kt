package dev.rssreader.checks

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.android.tools.lint.detector.api.Detector.UastScanner
import org.jetbrains.uast.*

@Suppress("UnstableApiUsage")
class NoValidMethodNameDetector : Detector(), UastScanner {

    companion object {

        const val MAX_NAME_LENGTH = 30

        @JvmField
        val ISSUE: Issue = Issue.create(
            id = "NoValidMethodName",
            briefDescription = "Too long method name Mentions",
            explanation = "Too long method name Mentions",
            category = Category.CORRECTNESS,
            priority = 1,
            severity = Severity.WARNING,
            implementation = Implementation(
                NoValidMethodNameDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement?>> {
        return listOf(UMethod::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return object : UElementHandler() {

            override fun visitMethod(node: UMethod) {
                if(node.name.length > MAX_NAME_LENGTH) {
                    context.report(
                        ISSUE,
                        node,
                        context.getLocation(node),
                        "This method name is too long",
                        createFix(node.name)
                    )
                }
            }
        }
    }

    private fun createFix(name: String): LintFix {
        return fix().replace().text(name).with(name.take(MAX_NAME_LENGTH)).build()
    }
}
