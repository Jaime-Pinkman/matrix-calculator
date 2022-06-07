package ru.itis.matrixcalculator.service.impl;

import org.ejml.simple.SimpleMatrix;
import org.springframework.stereotype.Service;
import ru.itis.matrixcalculator.model.DoubleMatrix;
import ru.itis.matrixcalculator.model.ValueModel;
import ru.itis.matrixcalculator.model.wrapped.complex.ComplexDivisionScaleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.complex.ComplexDoubleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.complex.ComplexWrappedMatrix;
import ru.itis.matrixcalculator.service.CalculationService;
import ru.itis.matrixcalculator.util.ComplexMatrixConverterUtil;

@Service
public class ComplexNumberCalculationService implements CalculationService<ComplexWrappedMatrix, ComplexDoubleWrappedMatrix, ComplexDivisionScaleWrappedMatrix> {

    @Override
    public ComplexWrappedMatrix plus(ComplexDoubleWrappedMatrix matrix) {
        DoubleMatrix doubleMatrix = ComplexMatrixConverterUtil.toDoubleMatrix(matrix);
        SimpleMatrix result = doubleMatrix.getA().plus(doubleMatrix.getB());
        return ComplexMatrixConverterUtil.toWrappedMatrix(result.getZDRM());
    }

    @Override
    public ComplexWrappedMatrix minus(ComplexDoubleWrappedMatrix matrix) {
        DoubleMatrix doubleMatrix = ComplexMatrixConverterUtil.toDoubleMatrix(matrix);
        SimpleMatrix result = doubleMatrix.getA().minus(doubleMatrix.getB());
        return ComplexMatrixConverterUtil.toWrappedMatrix(result.getZDRM());
    }

    @Override
    public ComplexWrappedMatrix mult(ComplexDoubleWrappedMatrix matrix) {
        DoubleMatrix doubleMatrix = ComplexMatrixConverterUtil.toDoubleMatrix(matrix);
        SimpleMatrix result = doubleMatrix.getA().mult(doubleMatrix.getB());
        return ComplexMatrixConverterUtil.toWrappedMatrix(result.getZDRM());
    }

    @Override
    public ComplexWrappedMatrix divide(ComplexDivisionScaleWrappedMatrix input) {
        SimpleMatrix matrix = ComplexMatrixConverterUtil.toSimpleMatrix(input.getMatrix(), input.getMatrixImaginary());
        SimpleMatrix result = matrix.divide(input.getNumber());
        return ComplexMatrixConverterUtil.toWrappedMatrix(result.getZDRM());
    }

    @Override
    public ComplexWrappedMatrix scale(ComplexDivisionScaleWrappedMatrix input) {
        SimpleMatrix matrix = ComplexMatrixConverterUtil.toSimpleMatrix(input.getMatrix(), input.getMatrixImaginary());
        SimpleMatrix result = matrix.scale(input.getNumber());
        return ComplexMatrixConverterUtil.toWrappedMatrix(result.getZDRM());
    }

    @Override
    public ComplexWrappedMatrix inverse(ComplexWrappedMatrix matrix) {
        SimpleMatrix simpleMatrix = ComplexMatrixConverterUtil.toSimpleMatrix(matrix);
        SimpleMatrix inverted = simpleMatrix.invert();
        return ComplexMatrixConverterUtil.toWrappedMatrix(inverted.getZDRM());
    }

    @Override
    public ComplexWrappedMatrix transpose(ComplexWrappedMatrix matrix) {
        SimpleMatrix simpleMatrix = ComplexMatrixConverterUtil.toSimpleMatrix(matrix);
        SimpleMatrix inverted = simpleMatrix.transpose();
        return ComplexMatrixConverterUtil.toWrappedMatrix(inverted.getZDRM());
    }

    @Override
    public ValueModel determinant(ComplexWrappedMatrix matrix) {
        SimpleMatrix simpleMatrix = ComplexMatrixConverterUtil.toSimpleMatrix(matrix);
        return new ValueModel(simpleMatrix.determinant());
    }

    @Override
    public ValueModel rank(ComplexWrappedMatrix matrix) {
        throw new IllegalArgumentException("Cannot calculate rank of Complex matrix");
    }
}
