import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals


abstract class MultipleCasesTest<Input: Any, Output: Any> {

    abstract val testData: List<Pair<Input, Output>>

    abstract fun execute(input: Input): Output

    fun assertAllCases() = assertAll(
        testData.map { (input, expected) ->
            { assertEquals(expected, execute(input)) }
        }
    )
}