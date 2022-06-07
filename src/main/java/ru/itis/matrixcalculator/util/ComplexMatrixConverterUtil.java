package ru.itis.matrixcalculator.util;

import lombok.experimental.UtilityClass;
import org.ejml.data.CMatrixRMaj;
import org.ejml.data.DMatrixRMaj;
import org.ejml.data.MatrixType;
import org.ejml.data.ZMatrixD1;
import org.ejml.data.ZMatrixRMaj;
import org.ejml.simple.SimpleMatrix;
import ru.itis.matrixcalculator.model.DoubleMatrix;
import ru.itis.matrixcalculator.model.wrapped.DoubleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.WrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.complex.ComplexDoubleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.complex.ComplexWrappedMatrix;

@UtilityClass
public final class ComplexMatrixConverterUtil {

    public static SimpleMatrix toSimpleMatrix(double[][] matrix, double[][] imaginary){
        SimpleMatrix simpleMatrix = new SimpleMatrix(matrix.length, matrix[0].length, MatrixType.ZDRM);
        ZMatrixRMaj zMatrixRMaj = simpleMatrix.getZDRM();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                zMatrixRMaj.set(i, j, matrix[i][j], imaginary[i][j]);
            }
        }
        return simpleMatrix;
    }

    public static SimpleMatrix toSimpleMatrix(ComplexWrappedMatrix matrix){
        return toSimpleMatrix(matrix.getMatrix(), matrix.getMatrixImaginary());
    }

    public static DoubleMatrix toDoubleMatrix(ComplexDoubleWrappedMatrix matrix) {
        return new DoubleMatrix(toSimpleMatrix(matrix.getA(), matrix.getAImaginary()),
                toSimpleMatrix(matrix.getB(), matrix.getBImaginary()));
    }

    public static ComplexWrappedMatrix toWrappedMatrix(ZMatrixRMaj matrix){
        double[][] real = new double[matrix.getNumCols()][matrix.getNumRows()];
        double[][] imaginary = new double[matrix.getNumCols()][matrix.getNumRows()];
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = matrix.getReal(i, j);
                imaginary[i][j] = matrix.getImag(i, j);
            }
        }
        return ComplexWrappedMatrix.builder()
                .matrix(real)
                .matrixImaginary(imaginary)
                .build();
    }

}
