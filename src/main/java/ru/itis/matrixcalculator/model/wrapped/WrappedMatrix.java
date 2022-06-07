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
@ApiModel(description = "Wrapping model for single matrix")
public class WrappedMatrix {
    @NotNull(message = "Matrix cannot be null")
    @ApiModelProperty(value = "Matrix model | 2D-array",
                      position = 1)
    private double[][] matrix;
}
