package ru.itis.matrixcalculator.model.wrapped;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode
@ApiModel(description = "Wrapping model for couple of matrix")
public class DoubleWrappedMatrix {
    @ApiModelProperty(value = "Matrix model A | 2D-array",
                      position = 1)
    @NotNull(message = "Matrix A cannot be null")
    private double[][] a;
    @ApiModelProperty(value = "Matrix model B | 2D-array",
                      position = 1)
    @NotNull(message = "Matrix B cannot be null")
    private double[][] b;
}
