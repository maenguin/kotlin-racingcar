package step3.domain

import step3.domain.strategy.CarMovingCondition
import step3.domain.strategy.RandomMoveCondition

/**
 * ### 자동차 클래스 입니다.
 *
 * 외부에서 넘겨받은 전진 조건에 따라 전진 여부를 결정합니다.
 */
class Car(
    private val name: String = "car1",
    private val carMovingStrategy: CarMovingCondition = RandomMoveCondition(threshold = 4, boundFrom = 0, boundUntil = 10)
) {

    var position: Int = 0
        private set

    fun move() {
        if (carMovingStrategy.canMove()) {
            position++
        }
    }

    fun getCurrentState(): CarState {
        return CarState(name, position)
    }
}

/**
 * ### 자동차의 현재 상태를 표현하기 위한 클래스 입니다.
 */
data class CarState(val name: String, val position: Int)
