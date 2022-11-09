package br.com.recipebook.startup

import br.com.recipebook.utilitykotlin.coroutines.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class StartupJobsExecutorImplTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private val testDispatcherProvider = TestDispatcherProvider()

    @Test
    fun invoke_callEveryJobFromList() = runTest {
        val job1 = IncrementOneStartupJob(1)
        val job2 = IncrementOneStartupJob(5)
        val startupJobsExecutorImpl = StartupJobsExecutorImpl(listOf(job1, job2), testDispatcherProvider)

        startupJobsExecutorImpl()

        assertEquals(2, job1.counter)
        assertEquals(6, job2.counter)
    }

    private class IncrementOneStartupJob(var counter: Int) : StartupJob {
        override suspend fun invoke() {
            counter++
        }
    }
}

// Move to a new test module
@ExperimentalCoroutinesApi
class CoroutineTestRule : TestRule {
    override fun apply(
        base: Statement?,
        description: Description?
    ): Statement =
        object : Statement() {
            override fun evaluate() {
                Dispatchers.setMain(UnconfinedTestDispatcher())
                base?.evaluate() // Any test statement
                Dispatchers.resetMain()
            }
        }
}

@ExperimentalCoroutinesApi
class TestDispatcherProvider : DispatcherProvider {
    private val testCoroutineDispatcher = UnconfinedTestDispatcher()
    override fun main() = testCoroutineDispatcher
    override fun default() = testCoroutineDispatcher
    override fun io() = testCoroutineDispatcher
    override fun unconfined() = testCoroutineDispatcher
}
