package racingcar;

import org.junit.jupiter.api.Test;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCarRace;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class RacingCarRaceTest {

    @Test
    void 자동차_이름_입력_시도_횟수_입력에_따른_경주_정보_생성() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni", "jun");
        int attemptCount = 5;

        // when
        RacingCarRace racingCarRace = RacingCarRace.createRacingCarRace(carNames, attemptCount);

        // then
        List<RacingCar> racingCars = racingCarRace.getRacingCars();

        assertThat(racingCars.size()).isEqualTo(3);
        assertThat(racingCarRace.getAttemptCount()).isEqualTo(attemptCount);
    }

    @Test
    void 숫자_0에서_9까지_랜덤으로_하나의_수를_반환() {
        // given
        RacingCar racingCar = RacingCar.of("name");

        // when
        int randomNumber = racingCar.pickRandomNumber();

        // then
        assertThat(randomNumber).isGreaterThanOrEqualTo(0);
        assertThat(randomNumber).isLessThanOrEqualTo(9);
    }

    @Test
    void 숫자_0에서_9까지_숫자중에서_4이상이면_전진() {
        // given
        RacingCar racingCar1 = RacingCar.of("name1");
        RacingCar racingCar2 = RacingCar.of("name2");

        // when
        racingCar1.move(3);
        racingCar2.move(4);

        // then
        assertThat(racingCar1.getWinningPoint()).isEqualTo(0);
        assertThat(racingCar2.getWinningPoint()).isEqualTo(1);
    }

    @Test
    void 경주에서의_최종_우승자를_결정합니다() {
        // given
        List<String> carNames = Arrays.asList("pobi", "woni", "jun");
        int attemptCount = 5;
        RacingCarRace racingCarRace = RacingCarRace.createRacingCarRace(carNames, attemptCount);
        List<RacingCar> racingCars = racingCarRace.getRacingCars();

        // when
        RacingCar racingCar1 = racingCars.get(0);
        RacingCar racingCar2 = racingCars.get(1);

        racingCar1.move(9);
        racingCar2.move(9);

        // then
        List<RacingCar> winners = racingCarRace.findWinners();
        assertThat(winners.get(0)).isSameAs(racingCar1);
        assertThat(winners.get(1)).isSameAs(racingCar2);
    }
}