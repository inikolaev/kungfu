package com.github.inikolaev.kapitest.reporter

import com.github.inikolaev.kapitest.cyan
import com.github.inikolaev.kapitest.green
import com.github.inikolaev.kapitest.matchers.MatchingResult
import com.github.inikolaev.kapitest.red
import com.github.inikolaev.kapitest.yellow

class ConsoleReporter(name: String, matchingResults: List<MatchingResult>): Reporter(name, matchingResults) {
    companion object {
        private const val CHECK = "\u2714"
        private const val CROSS = "\u2718"
    }

    override fun report() {
        val matching = matchingResults.all(MatchingResult::match)

        if (matching) {
            println("${green(CHECK)} ${yellow(name)}")
        } else {
            println("${red(CROSS)} ${yellow(name)}")
        }

        matchingResults.forEach {
            if (it.match) {
                println("\t${green(CHECK)} ${reportMatcher(it)}")
            } else {
                println("\t${red(CROSS)} ${reportMatcher(it)}")
            }
        }

        println()
    }

    private fun reportMatcher(matchingResult: MatchingResult) =
        if (matchingResult.nameSuffix.isNotBlank())
            "${cyan(matchingResult.name)} ${matchingResult.nameSuffix} ${matchingResult.message} ${cyan(matchingResult.value)}"
        else
            "${cyan(matchingResult.name)} ${matchingResult.message} ${cyan(matchingResult.value)}"
}