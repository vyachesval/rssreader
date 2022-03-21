package dev.rssreader.checks

import com.android.tools.lint.client.api.IssueRegistry

@Suppress("UnstableApiUsage")
class RssLintRegistry : IssueRegistry() {
    override val issues = listOf(NoValidMethodNameDetector.ISSUE)
}