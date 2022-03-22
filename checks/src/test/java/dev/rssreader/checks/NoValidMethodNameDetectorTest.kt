package dev.rssreader.checks

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

@Suppress("UnstableApiUsage")
class NoValidMethodNameDetectorTest {
    @Test
    fun `should detect long method name`() {
        TestLintTask().allowMissingSdk().files(
            kotlin(
                """
                    package test.pkg;

                    class TestClass1 {
                        interface ItemClickListener{ fun onClick() }
                        val onClickTestClassListener = object : ItemClickListener {
                            override fun onClick() {}
                        }

                        fun test1() {
                        }
                        
                        fun test2test2test2test2test2test2test2() {
                        }
                    }
                    """.trimIndent()
            )
        )
            .issues(NoValidMethodNameDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/TestClass1.kt:12: Warning: This method name "test2test2test2test2test2test2test2" is too long [NoValidMethodName]
                    fun test2test2test2test2test2test2test2() {
                    ^
                0 errors, 1 warnings
                """.trimIndent()
            )
    }
}
