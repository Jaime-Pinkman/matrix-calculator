let activeOperatorName = "plus";
let activeType = "real";
let targetMatrixElements = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
let targetMatrixElementsImaginary = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
let secondMatrixElements = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
let secondMatrixElementsImaginary = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
let secondScaleDivideValue = 0;
let request = {};

function setType(type) {
    activeType = type;
    switch (type) {
        case "real": {
            if (request.aImaginary) {
                delete request.aImaginary
                updateMatrix(targetMatrixElements, targetMatrixElementsImaginary, 'target-matrix-table')
                renderMatrix(targetMatrixElements, targetMatrixElementsImaginary, 'target-matrix-body', 'target-matrix-rows', 'target-matrix-cols', 'target-matrix-body', 'target-matrix-table')
            }
            if (request.bImaginary) {
                delete request.bImaginary
                updateMatrix(secondMatrixElements, secondMatrixElementsImaginary, 'second-matrix-table')
                renderMatrix(secondMatrixElements, secondMatrixElementsImaginary,'second-matrix-body', 'second-matrix-rows', 'second-matrix-cols', 'second-matrix-body', 'second-matrix-table')
            }
            if (request.matrixImaginary) {
                delete request.matrixImaginary
                updateMatrix(targetMatrixElements, targetMatrixElementsImaginary, 'target-matrix-table')
                renderMatrix(targetMatrixElements, targetMatrixElementsImaginary, 'target-matrix-body', 'target-matrix-rows', 'target-matrix-cols', 'target-matrix-body', 'target-matrix-table')
            }
            break;
        }
        case "complex": {
            if (request.a) {
                request.aImaginary = targetMatrixElementsImaginary
                updateMatrix(targetMatrixElements, targetMatrixElementsImaginary, 'target-matrix-table')
                renderMatrix(targetMatrixElements, targetMatrixElementsImaginary, 'target-matrix-body', 'target-matrix-rows', 'target-matrix-cols', 'target-matrix-body', 'target-matrix-table')
            }
            if (request.b) {
                request.bImaginary = secondMatrixElementsImaginary
                updateMatrix(secondMatrixElements, secondMatrixElementsImaginary, 'second-matrix-table')
                renderMatrix(secondMatrixElements, secondMatrixElementsImaginary,'second-matrix-body', 'second-matrix-rows', 'second-matrix-cols', 'second-matrix-body', 'second-matrix-table')
            }
            if (request.matrix) {
                request.matrixImaginary = targetMatrixElementsImaginary
                updateMatrix(targetMatrixElements, targetMatrixElementsImaginary, 'target-matrix-table')
                renderMatrix(targetMatrixElements, targetMatrixElementsImaginary, 'target-matrix-body', 'target-matrix-rows', 'target-matrix-cols', 'target-matrix-body', 'target-matrix-table')
            }
        }
    }
}

function setOperator(operatorName) {
    activeOperatorName = operatorName;
    let operatorElement = document.getElementById("operator");
    updateMatrix(secondMatrixElements, secondMatrixElementsImaginary, 'second-matrix-table')
    clearResult()
    switch (operatorName) {
        case "plus": {
            operatorElement.innerText = "+"
            generateSecondMatrixStrapping();
            renderMatrix(secondMatrixElements, secondMatrixElementsImaginary, 'second-matrix-body', 'second-matrix-rows', 'second-matrix-cols', 'second-matrix-body', 'second-matrix-table')
            request = {
                a: targetMatrixElements,
                b: secondMatrixElements
            }
            break;
        }
        case "minus": {
            operatorElement.innerText = "-"
            generateSecondMatrixStrapping();
            renderMatrix(secondMatrixElements, secondMatrixElementsImaginary,  'second-matrix-body', 'second-matrix-rows', 'second-matrix-cols', 'second-matrix-body', 'second-matrix-table')
            request = {
                a: targetMatrixElements,
                b: secondMatrixElements
            }
            break;
        }
        case "mult": {
            operatorElement.innerText = "╳"
            generateSecondMatrixStrapping();
            renderMatrix(secondMatrixElements, secondMatrixElementsImaginary, 'second-matrix-body', 'second-matrix-rows', 'second-matrix-cols', 'second-matrix-body', 'second-matrix-table')
            request = {
                a: targetMatrixElements,
                b: secondMatrixElements
            }
            break;
        }
        case "divide": {
            operatorElement.innerText = "/"
            generateSecondDivideScaleHtml("Divide");
            request = {
                matrix: targetMatrixElements,
                number: secondScaleDivideValue
            }
            break;
        }
        case "scale": {
            operatorElement.innerText = "╳"
            generateSecondDivideScaleHtml("Scale");
            request = {
                matrix: targetMatrixElements,
                number: secondScaleDivideValue
            }
            break;
        }
        case "inverse": {
            operatorElement.innerText = ""
            generateTextHtml("Inverse", 'second')
            request = {
                matrix: targetMatrixElements
            }
            break;
        }
        case "transpose": {
            operatorElement.innerText = ""
            generateTextHtml("Transpose", 'second')
            request = {
                matrix: targetMatrixElements
            }
            break;
        }
        case "determinant": {
            operatorElement.innerText = ""
            generateTextHtml("Determinant", 'second')
            request = {
                matrix: targetMatrixElements
            }
            break;
        }
        case "rank": {
            operatorElement.innerText = ""
            generateTextHtml("Rank", 'second')
            request = {
                matrix: targetMatrixElements
            }
        }
    }
    setType(activeType)
}

function generateMatrix(matrixElements, matrixImaginaryElements, matrix, rows = 0, cols = 0) {
    let html = ""
    for (let row = 0; row < rows; row++) {
        let text = "<tr>"
        for (let col = 0; col < cols; col++) {
            let value = matrixElements[row] && matrixElements[row][col] ? matrixElements[row][col] : 0;
            let input = '<td> <input type="text" value="' + value + '" class="form-control"> ';
            if (activeType === 'complex') {
                input += ' + '
                input += '<input type="text" value="' + value + '" class="form-control">';
                input += 'i'
            }
            text += input + " </td>";
        }
        text += '</tr>';
        html += text;
    }
    matrix.innerHTML = html;
}

function updateMatrix(matrixElements, matrixImaginaryElements, matrixTableElementId) {
    let table = document.getElementById(matrixTableElementId)
    if (table) {
        matrixElements.length = 0
        matrixImaginaryElements.length = 0
        for (let i = 0; i < table.rows.length; i++) {
            let row = table.rows[i]
            matrixElements[i] = []
            matrixImaginaryElements[i] = []
            for (let j = 0; j < row.cells.length; j++) {
                let col = row.cells[j]
                matrixElements[i][j] = col.children[0].value;
                if (activeType === 'complex') {
                    if (col.children[1]) {
                        matrixImaginaryElements[i][j] = col.children[1].value;
                    }
                }
            }
        }
    }
}

function onStartup() {
    renderMatrix(targetMatrixElements, targetMatrixElementsImaginary, 'target-matrix-body', 'target-matrix-rows', 'target-matrix-cols', 'target-matrix-body', 'target-matrix-table')
    generateSecondMatrixStrapping();
    renderMatrix(secondMatrixElements, secondMatrixElementsImaginary,'second-matrix-body', 'second-matrix-rows', 'second-matrix-cols', 'second-matrix-body', 'second-matrix-table')
    request = {
        a: targetMatrixElements,
        b: secondMatrixElements
    }
}

function renderMatrix(matrixElements, matrixImaginaryElements, matrixBodyId, matrixRowsElementId, matrixColsElementId, matrixBodyElementId, matrixTableElementId) {
    const targetMatrix = document.getElementById(matrixBodyId);
    generateMatrix(matrixElements, matrixImaginaryElements, targetMatrix, matrixElements.length, matrixElements[0].length);
    createMatrixListener(matrixElements, matrixImaginaryElements, matrixRowsElementId, matrixColsElementId, matrixBodyElementId, matrixTableElementId);
}

function createMatrixListener(matrixElements, matrixImaginaryElements, matrixRowsElementId, matrixColsElementId, matrixBodyElementId, matrixTableElementId) {
    const matrixRowsElement = document.getElementById(matrixRowsElementId);
    const matrixColsElement = document.getElementById(matrixColsElementId);
    const targetMatrix = document.getElementById(matrixBodyElementId)
    matrixRowsElement.addEventListener('input', (event) => {
        if (event.target.value) {
            updateMatrix(matrixElements, matrixImaginaryElements, matrixTableElementId);
            generateMatrix(matrixElements, matrixImaginaryElements, targetMatrix, event.target.value, matrixColsElement.value)
        }
    });
    matrixColsElement.addEventListener('input', (event) => {
        if (event.target.value) {
            updateMatrix(matrixElements, matrixImaginaryElements, matrixTableElementId);
            generateMatrix(matrixElements, matrixImaginaryElements, targetMatrix, matrixRowsElement.value, event.target.value)
        }
    });
}

function generateSecondMatrixStrapping() {
    let rows = secondMatrixElements.length;
    let cols = rows ? secondMatrixElements[0].length : 0;
    let second = document.getElementById("second");
    let html = '<div class="row" style="padding-top: 0.5%">' +
        '                <div class="col-5 text-center">' +
        '                    <div class="mb-3">' +
        '                        <label for="second-matrix-cols" class="form-label">Cols</label>' +
        '                        <input type="text" class="form-control" id="second-matrix-cols" value="' + cols + '">' +
        '                    </div>' +
        '                </div>' +
        '                <div class="col-2 text-center justify-content-center align-self-center">' +
        '                    <span>╳</span>' +
        '                </div>' +
        '                <div class="col-5 text-center">' +
        '                    <div class="mb-3">' +
        '                        <label for="target-matrix-rows" class="form-label">Rows</label>' +
        '                        <input type="text" class="form-control" id="second-matrix-rows" value="' + rows + '">' +
        '                    </div>' +
        '                </div>' +
        '            </div>' +
        '            <div class="row" style="padding-top: 0.5%">' +
        '                <table id="second-matrix-table">' +
        '                    <tbody id="second-matrix-body">' +
        '                    </tbody>' +
        '                </table>' +
        '            </div>'
    second.innerHTML = html;
    // second.classList.remove("text-center justify-content-center align-self-center")
}

function generateSecondDivideScaleHtml(operationName) {
    let second = document.getElementById("second");
    let html = '<label for="second-scale-divide" class="form-label">' + operationName + '</label>' +
        '<input type="text" class="form-control" id="second-scale-divide" value="' + secondScaleDivideValue + '">'
    second.innerHTML = html;
    second.classList.add("text-center", "justify-content-center", "align-self-center")
    second.addEventListener('input', (event) => {
        if (event.target.value != null) {
            secondScaleDivideValue = event.target.value;
            if (request.number != null) {
                request.number = secondScaleDivideValue;
            }
        }
    });
}

function generateTextHtml(operationName, elementId) {
    let element = document.getElementById(elementId);
    let html = '<label class="form-label" style="font-size: 2rem">' + operationName + '</label>';
    element.innerHTML = html;
    element.classList.add("text-center", "justify-content-center", "align-self-center")
}

function clearResult() {
    let element = document.getElementById("result");
    let html = '';
    element.innerHTML = html;
}

function generateResult(matrixElements, matrixImaginary) {
    let result = document.getElementById("result");
    let html = '<table style="font-size: 2rem"> <tbody>';
    for (let row = 0; row < matrixElements.length; row++) {
        let text = "<tr>"
        for (let col = 0; col < matrixElements[row].length; col++) {
            let value = matrixElements[row] && matrixElements[row][col] ? matrixElements[row][col] : 0;
            let input = '<td> ' + value;

            if (activeType === 'complex') {
                let valueImaginary = matrixImaginary[row] && matrixImaginary[row][col] ? matrixImaginary[row][col] : 0;
                input += " +" + valueImaginary + "i";
            }

            input += ' </td>';
            text += input;
        }
        text += '</tr>';
        html += text;
    }
    html += '</tbody> </table>'
    result.innerHTML = html;
}

function generateAlert(message) {
    let alert = document.getElementById("alert");
    let html = "";
    if (message) {
         html = '<div class="alert alert-danger" role="alert" style="margin-top: 1%; margin-bottom: 0">' + message + '</div>';
    }
    alert.innerHTML = html;
}

async function calculate() {
    updateMatrix(secondMatrixElements, secondMatrixElementsImaginary, 'second-matrix-table')
    updateMatrix(targetMatrixElements, targetMatrixElementsImaginary, 'target-matrix-table')

    let url = "/api/calculation/" + activeType + "/" + activeOperatorName;

    const json = await fetch(url, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(request)
    })
        .then(response => response.json()) // .text(), etc.
        .catch((e) => { console.log(e) });

    generateAlert(json.message)

    if (json.matrix) {
        generateResult(json.matrix, json.matrixImaginary)
    }

    if (json.value != null){
        generateTextHtml(json.value, 'result')
    }
}