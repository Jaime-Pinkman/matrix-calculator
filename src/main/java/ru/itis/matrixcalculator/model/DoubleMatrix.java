package ru.itis.matrixcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ejml.simple.SimpleMatrix;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DoubleMatrix {
    private SimpleMatrix a;
    private SimpleMatrix b;
}
