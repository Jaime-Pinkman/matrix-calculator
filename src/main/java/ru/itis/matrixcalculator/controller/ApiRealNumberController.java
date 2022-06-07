package ru.itis.matrixcalculator.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.matrixcalculator.model.ValueModel;
import ru.itis.matrixcalculator.model.wrapped.DivisionScaleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.DoubleWrappedMatrix;
import ru.itis.matrixcalculator.model.wrapped.WrappedMatrix;
import ru.itis.matrixcalculator.service.impl.RealNumberCalculationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/calculation/real")
@RequiredArgsConstructor
public class ApiRealNumberController {

    private final RealNumberCalculationService mainService;

    @PostMapping("/plus")
    @ApiOperation("Sum of 2 real matrices")
    public WrappedMatrix plus(@Valid @RequestBody DoubleWrappedMatrix body){
        return mainService.plus(body);
    }

    @PostMapping("/minus")
    @ApiOperation("Difference of 2 real matrices")
    public WrappedMatrix minus(@Valid @RequestBody DoubleWrappedMatrix body){
        return mainService.minus(body);
    }

    @PostMapping("/mult")
    @ApiOperation("Multiplicity of 2 real matrices")
    public WrappedMatrix mult(@Valid @RequestBody DoubleWrappedMatrix body){
        return mainService.mult(body);
    }

    @PostMapping("/divide")
    @ApiOperation("Dividence of real matrix to real number")
    public WrappedMatrix divide(@Valid @RequestBody DivisionScaleWrappedMatrix body){
        return mainService.divide(body);
    }

    @PostMapping("/scale")
    @ApiOperation("Multiplicity of real matrix to real number")
    public WrappedMatrix scale(@Valid @RequestBody DivisionScaleWrappedMatrix body){
        return mainService.scale(body);
    }

    @PostMapping("/inverse")
    @ApiOperation("Inversion of real matrix")
    public WrappedMatrix inverse(@Valid @RequestBody WrappedMatrix body){
        return mainService.inverse(body);
    }

    @PostMapping("/transpose")
    @ApiOperation("Transposing of real matrix")
    public WrappedMatrix transpose(@Valid @RequestBody WrappedMatrix body){
        return mainService.transpose(body);
    }

    @PostMapping("/determinant")
    @ApiOperation("Get a determinant of real matrix")
    public ValueModel determinant(@Valid @RequestBody WrappedMatrix body){
        return mainService.determinant(body);
    }

    @PostMapping("/rank")
    @ApiOperation("Get a rank of real matrix")
    public ValueModel rank(@Valid @RequestBody WrappedMatrix body){
        return mainService.rank(body);
    }
}
