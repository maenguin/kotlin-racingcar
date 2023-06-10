package step2.tokenizer

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

internal class ExpressionValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    internal fun `연산식이 null or 빈값 인 경우 예외 발생`(expression: String?) {
        val sut = ExpressionValidator
        assertThatThrownBy { sut.validate(expression) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "1 * 2 /",
            " * 2",
            " * 3 /"
        ]
    )
    internal fun `연산식 형태가 잘못된 경우 예외 발생`(expression: String) {
        val sut = ExpressionValidator
        assertThatThrownBy { sut.validate(expression) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    internal fun `숫자나 사칙 연산 기호가 아닌 문자가 존재할 경우 예외 발생`() {
        val sut = ExpressionValidator
        assertThatThrownBy { sut.validate("1 @ 2") }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    internal fun `빈칸 허용`() {
        val sut = ExpressionValidator
        assertDoesNotThrow { sut.validate(" 1 + 2-   3 / 4") }
    }

    @Test
    internal fun `숫자 자릿 수는 두 자리 이상 가능`() {
        val sut = ExpressionValidator
        assertDoesNotThrow { sut.validate(" 10000 + 22-   330000 / 4") }
    }
}
