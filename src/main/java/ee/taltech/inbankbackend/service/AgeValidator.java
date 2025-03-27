package ee.taltech.inbankbackend.service;

import ee.taltech.inbankbackend.config.DecisionEngineConstants;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

/**
 * A class that provides a method to check if the person meets our age requirements.
 * Is determined based on their ID Code.
 */
@Service
public class AgeValidator {

    /**
     * Checks if the customer is not underage and is younger than the current expected lifetime of each respectable
     * country minus the maximum loan period.
     * @param idCode ID Code of the person who made the request.
     * @return If the age meets the requirements.
     */
    public boolean isCorrectAge(String idCode) {
        int maximumAllowedAgeForLoan = DecisionEngineConstants.MAXIMUM_AGE_FOR_LOAN -
                (DecisionEngineConstants.MAXIMUM_LOAN_PERIOD / DecisionEngineConstants.MONTHS_IN_YEAR);

        LocalDate birthDate = LocalDate.of(
                getBirthYear(idCode),
                Integer.parseInt(idCode.substring(3, 5)),
                Integer.parseInt(idCode.substring(5, 7))
        );

        int customersAge = Period.between(birthDate, LocalDate.now()).getYears();

        return DecisionEngineConstants.MINIMUM_AGE_FOR_LOAN <= customersAge &&
                customersAge <= maximumAllowedAgeForLoan;
    }

    /**
     * Calculates the birth year of a customer based on the ID Code.
     * @param idCode ID Code of the person who made the request.
     * @return The birth year of the person based on his/her idCode.
     */
    public int getBirthYear(String idCode) {
        int century = Integer.parseInt(idCode.substring(0, 1));
        int years = Integer.parseInt(idCode.substring(1, 3));

        if (century == 1 || century == 2) {
            return 1800 + years;
        } else if (century == 3 || century == 4) {
            return 1900 + years;
        } else {
            return 2000 + years;
        }
    }
}
