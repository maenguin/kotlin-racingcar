package step3.domain

/**
 * ### 경주 전체 기록을 표현하는 클래스 입니다.
 *
 * 경주 기록은 전진 시도 했던 기록으로 세분화 됩니다.
 */
data class RacingRecord(val attempts: List<RacingAttempt>) {

    val winners by lazy {
        val maxOrder = attempts.maxOf { it.order }
        val lastAttempt = attempts.find { it.order == maxOrder } ?: attempts.last()
        val maxPosition = lastAttempt.carStates.maxOf { it.position }
        lastAttempt.carStates
            .filter { it.position == maxPosition }
            .map { it.name }
    }
}

/**
 * ### 자동차별 전진 시도를 표현하는 클래스입니다.
 *
 * 전진 시도를 했을때의 자동차들의 상태와 시도 순서를 가지고 있습니다.
 */
data class RacingAttempt(val carStates: List<CarState>, val order: Int)
