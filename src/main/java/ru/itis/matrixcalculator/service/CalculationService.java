package ru.itis.matrixcalculator.service;

import ru.itis.matrixcalculator.model.ValueModel;
import ru.itis.matrixcalculator.model.wrapped.DivisionScaleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.DoubleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.WrappedMatrix;

public interface CalculationService<W extends WrappedMatrix, D extends DoubleWrappedMatrix, DS extends DivisionScaleWrappedMatrix> {

    W plus(D matrix);

    W minus(D matrix);

    W mult(D matrix);

    W divide(DS input);

    W scale(DS input);

    W inverse(W matrix);

    W transpose(W matrix);

    ValueModel determinant(W matrix);

    ValueModel rank(W matrix);

}
