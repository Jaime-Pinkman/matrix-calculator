package ru.itis.matrixcalculator.model.wrapped.complex;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.matrixcalculator.model.wrapped.DoubleWrappedMatrix;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@ApiModel(description = "Wrapping model for couple of complex matrix")
public class ComplexDoubleWrappedMatrix extends DoubleWrappedMatrix {
    @ApiModelProperty(value = "Matrix model for imaginary part A | 2D-array",
                      position = 1)
    @NotNull(message = "Matrix A Imaginary cannot be null")
    @JsonProperty("aImaginary")
    private double[][] aImaginary;
    @ApiModelProperty(value = "Matrix model for imaginary part B | 2D-array",
                      position = 1)
    @NotNull(message = "Matrix B Imaginary cannot be null")
    @JsonProperty("bImaginary")
    private double[][] bImaginary;
}
