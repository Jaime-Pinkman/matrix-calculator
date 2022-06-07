package ru.itis.matrixcalculator.util;

import lombok.experimental.UtilityClass;
import org.ejml.data.DMatrixRMaj;
import org.ejml.simple.SimpleMatrix;
import ru.itis.matrixcalculator.model.DoubleMatrix;
import ru.itis.matrixcalculator.model.wrapped.DoubleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.WrappedMatrix;

@UtilityClass
public final class RealMatrixConverterUtil {

    public static SimpleMatrix toSimpleMatrix(double[][] matrix){
        return new SimpleMatrix(matrix);
    }

    public static SimpleMatrix toSimpleMatrix(WrappedMatrix matrix){
        return toSimpleMatrix(matrix.getMatrix());
    }

    public static DoubleMatrix toDoubleMatrix(DoubleWrappedMatrix matrix) {
        return new DoubleMatrix(toSimpleMatrix(matrix.getA()), toSimpleMatrix(matrix.getB()));
    }

    public static WrappedMatrix toWrappedMatrix(DMatrixRMaj matrix){
        double[][] target = new double[matrix.getNumCols()][matrix.getNumRows()];
        double[] data = matrix.getData();
        int index = 0;
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target[i].length; j++) {
                double number = data[index++];
                String[] splitter = String.valueOf(number).split("\\.");
                if (splitter.length == 2 && splitter[1].length() >= 16) {
                    target[i][j] = Math.round(number);
                } else {
                    target[i][j] = number;
                }
            }
        }
        return new WrappedMatrix(target);
    }

}
