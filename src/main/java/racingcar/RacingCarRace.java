package racingcar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;
import static racingcar.constant.NumberConstant.ZERO_POINT;

public final class RacingCarRace {

    private final List<RacingCar> racingCars;
    private final int attemptCount;

    private RacingCarRace(final List<RacingCar> racingCars, final int attemptCount) {
        this.racingCars = racingCars;
        this.attemptCount = attemptCount;
    }

    public static RacingCarRace createRacingCarRace(final List<String> carNames, final int attemptCount) {
        List<RacingCar> racingCars = new ArrayList<>();
        for (String carName : carNames) {
            RacingCar racingCar = RacingCar.of(carName);
            racingCars.add(racingCar);
        }

        return new RacingCarRace(racingCars, attemptCount);
    }

    public void runRace() {
        for (RacingCar racingCar : racingCars) {
            int randomNumber = racingCar.pickRandomNumber();
            racingCar.move(randomNumber);
        }
    }

    public List<RacingCar> findWinners() {
        int maxPoint = getMaxPoint();

        return racingCars.stream()
                .filter(racingCar -> maxPoint == racingCar.getWinningPoint())
                .collect(toList());
    }

    private int getMaxPoint() {
        int maxPoint = ZERO_POINT;

        for (RacingCar racingCar : racingCars) {
            if (maxPoint < racingCar.getWinningPoint()) {
                maxPoint = racingCar.getWinningPoint();
            }
        }
        return maxPoint;
    }

    // getter
    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public int getAttemptCount() {
        return attemptCount;
    }
}
