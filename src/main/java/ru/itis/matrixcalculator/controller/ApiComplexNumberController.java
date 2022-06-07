package ru.itis.matrixcalculator.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.matrixcalculator.model.ValueModel;
import ru.itis.matrixcalculator.model.wrapped.complex.ComplexDivisionScaleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.complex.ComplexDoubleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.complex.ComplexWrappedMatrix;
import ru.itis.matrixcalculator.service.impl.ComplexNumberCalculationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/calculation/complex")
@RequiredArgsConstructor
public class ApiComplexNumberController {

    private final ComplexNumberCalculationService calculationService;

    @PostMapping("/plus")
    @ApiOperation("Sum of 2 complex matrices")
    public ComplexWrappedMatrix plus(@Valid @RequestBody ComplexDoubleWrappedMatrix body){
        return calculationService.plus(body);
    }

    @PostMapping("/minus")
    @ApiOperation("Difference of 2 complex matrices")
    public ComplexWrappedMatrix minus(@Valid @RequestBody ComplexDoubleWrappedMatrix body){
        return calculationService.minus(body);
    }

    @PostMapping("/mult")
    @ApiOperation("Multiplicity of 2 complex matrices")
    public ComplexWrappedMatrix mult(@Valid @RequestBody ComplexDoubleWrappedMatrix body){
        return calculationService.mult(body);
    }

    @PostMapping("/divide")
    @ApiOperation("Dividence of complex matrix to real number")
    public ComplexWrappedMatrix divide(@Valid @RequestBody ComplexDivisionScaleWrappedMatrix body){
        return calculationService.divide(body);
    }

    @PostMapping("/scale")
    @ApiOperation("Multiplicity of complex matrix to real number")
    public ComplexWrappedMatrix scale(@Valid @RequestBody ComplexDivisionScaleWrappedMatrix body){
        return calculationService.scale(body);
    }

    @PostMapping("/inverse")
    @ApiOperation("Inversion of complex matrix")
    public ComplexWrappedMatrix inverse(@Valid @RequestBody ComplexWrappedMatrix body){
        return calculationService.inverse(body);
    }

    @PostMapping("/transpose")
    @ApiOperation("Transposing of complex matrix")
    public ComplexWrappedMatrix transpose(@Valid @RequestBody ComplexWrappedMatrix body){
        return calculationService.transpose(body);
    }

    @PostMapping("/determinant")
    @ApiOperation("Get a determinant of complex matrix")
    public ValueModel determinant(@Valid @RequestBody ComplexWrappedMatrix body){
        return calculationService.determinant(body);
    }

    @PostMapping("/rank")
    @ApiOperation("Get a rank of complex matrix")
    public ValueModel rank(@Valid @RequestBody ComplexWrappedMatrix body){
        return calculationService.rank(body);
    }
}
