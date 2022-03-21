package dev.rssreader.checks

import com.android.tools.lint.checks.infrastructure.TestFiles.java
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

@Suppress("UnstableApiUsage")
class NoValidMethodNameDetectorTest {
    @Test
    fun `should detect long method name`() {
        TestLintTask().allowMissingSdk().files(
            java(
                """
                    package test.pkg;
                    public class TestClass1 {
                        
                        void test1() {
                        }
                        
                        void test2test2test2test2test2test2test2() {
                        }
                    }
                    """
            ).indented()
        )
            .issues(NoValidMethodNameDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/TestClass1.java:7: Warning: This method name is too long [SampleId]
                    void test2test2test2test2test2test2test2() {
                    ^
                0 errors, 1 warnings
                """
            )
    }
}
