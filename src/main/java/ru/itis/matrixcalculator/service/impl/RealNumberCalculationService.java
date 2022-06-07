package ru.itis.matrixcalculator.service.impl;

import org.ejml.dense.row.MatrixFeatures_DDRM;
import org.ejml.simple.SimpleMatrix;
import org.springframework.stereotype.Service;
import ru.itis.matrixcalculator.model.DoubleMatrix;
import ru.itis.matrixcalculator.model.ValueModel;
import ru.itis.matrixcalculator.model.wrapped.DivisionScaleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.DoubleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.WrappedMatrix;
import ru.itis.matrixcalculator.service.CalculationService;
import ru.itis.matrixcalculator.util.RealMatrixConverterUtil;

@Service
public class RealNumberCalculationService implements CalculationService<WrappedMatrix, DoubleWrappedMatrix, DivisionScaleWrappedMatrix> {

    @Override
    public WrappedMatrix plus(DoubleWrappedMatrix input) {
        DoubleMatrix doubleMatrix = RealMatrixConverterUtil.toDoubleMatrix(input);
        SimpleMatrix result = doubleMatrix.getA().plus(doubleMatrix.getB());
        return RealMatrixConverterUtil.toWrappedMatrix(result.getDDRM());
    }

    @Override
    public WrappedMatrix minus(DoubleWrappedMatrix input) {
        DoubleMatrix doubleMatrix = RealMatrixConverterUtil.toDoubleMatrix(input);
        SimpleMatrix result = doubleMatrix.getA().minus(doubleMatrix.getB());
        return RealMatrixConverterUtil.toWrappedMatrix(result.getDDRM());
    }

    @Override
    public WrappedMatrix mult(DoubleWrappedMatrix input) {
        DoubleMatrix doubleMatrix = RealMatrixConverterUtil.toDoubleMatrix(input);
        SimpleMatrix result = doubleMatrix.getA().mult(doubleMatrix.getB());
        return RealMatrixConverterUtil.toWrappedMatrix(result.getDDRM());
    }

    @Override
    public WrappedMatrix divide(DivisionScaleWrappedMatrix input) {
        SimpleMatrix matrix = RealMatrixConverterUtil.toSimpleMatrix(input.getMatrix());
        SimpleMatrix result = matrix.divide(input.getNumber());
        return RealMatrixConverterUtil.toWrappedMatrix(result.getDDRM());
    }

    @Override
    public WrappedMatrix scale(DivisionScaleWrappedMatrix input) {
        SimpleMatrix matrix = RealMatrixConverterUtil.toSimpleMatrix(input.getMatrix());
        SimpleMatrix result = matrix.scale(input.getNumber());
        return RealMatrixConverterUtil.toWrappedMatrix(result.getDDRM());
    }

    @Override
    public WrappedMatrix inverse(WrappedMatrix matrix) {
        if (determinant(matrix).getValue() == 0) {
            throw new IllegalArgumentException("Cannot inverse matrix because determinant equals to 0");
        }
        SimpleMatrix simpleMatrix = RealMatrixConverterUtil.toSimpleMatrix(matrix);
        SimpleMatrix inverted = simpleMatrix.invert();
        return RealMatrixConverterUtil.toWrappedMatrix(inverted.getDDRM());
    }

    @Override
    public WrappedMatrix transpose(WrappedMatrix matrix) {
        SimpleMatrix simpleMatrix = RealMatrixConverterUtil.toSimpleMatrix(matrix);
        SimpleMatrix transposed = simpleMatrix.transpose();
        return RealMatrixConverterUtil.toWrappedMatrix(transposed.getDDRM());
    }

    @Override
    public ValueModel determinant(WrappedMatrix matrix) {
        SimpleMatrix simpleMatrix = RealMatrixConverterUtil.toSimpleMatrix(matrix);
        return new ValueModel(simpleMatrix.determinant());
    }

    @Override
    public ValueModel rank(WrappedMatrix matrix) {
        SimpleMatrix simpleMatrix = RealMatrixConverterUtil.toSimpleMatrix(matrix);
        int rank = MatrixFeatures_DDRM.rank(simpleMatrix.getDDRM());
        return new ValueModel(rank);
    }

}
