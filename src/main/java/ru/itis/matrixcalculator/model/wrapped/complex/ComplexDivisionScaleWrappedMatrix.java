package ru.itis.matrixcalculator.model.wrapped.complex;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.matrixcalculator.model.wrapped.DivisionScaleWrappedMatrix;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@ApiModel(description = "Wrapping model for matrix and multiplier/divider")
public class ComplexDivisionScaleWrappedMatrix extends DivisionScaleWrappedMatrix {
    @ApiModelProperty(value = "Matrix model for imaginary part | 2D-array",
                      position = 1)
    @NotNull(message = "Matrix Imaginary cannot be null")
    private double[][] matrixImaginary;
}
