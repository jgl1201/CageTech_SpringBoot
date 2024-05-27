const rutinaDetalles = {};

fetch("../templates/header.html")
    .then(response => response.text())
    .then(html => {
        document.getElementById("header").innerHTML = html;
    })
    .catch(error => console.error(error)) ;

function btnAddRutina() {
    document.getElementById("rutinas-form-nueva-rutina").style.display = "block";
}

function btnNuevaRutinaSubmit() {
    var nombreRutina = document.getElementById("rutinas-form-nueva-rutina-input-nombre").value;

    if (nombreRutina.trim() !== "") {
        var nuevaRutinaItem = document.createElement("li");
        nuevaRutinaItem.textContent = nombreRutina;

        var rutinaId = "rutina-" + nombreRutina.toLowerCase().replace(/\s+/g, '-');
        nuevaRutinaItem.id = rutinaId;

        nuevaRutinaItem.addEventListener("click", function() {
            mostrarDetallesRutina(nombreRutina, rutinaId);
        });

        document.getElementById("rutinas-container-rutinas-lista").appendChild(nuevaRutinaItem);

        rutinaDetalles[nombreRutina] = {
            ejercicios: new Set()
        };

        crearContenedorDetallesRutina(nombreRutina, rutinaId);
    } else {
        alert("Introduce nombre de la rutina");
    }

    document.getElementById("rutinas-form-nueva-rutina").style.display = "none";
}

function btnNuevaRutinaCancel() {
    document.getElementById("rutinas-form-nueva-rutina").style.display = "none";
}

function crearContenedorDetallesRutina(nombreRutina, rutinaId) {
    var detallesRutina = document.createElement("div");
    detallesRutina.id = rutinaId + "-detalle";
    detallesRutina.className = "rutina-detalle-container";
    detallesRutina.style.display = "none";

    var container1 = document.createElement("div");
    container1.className = "rutina-detalle-container-row";

    var pNombreRutina = document.createElement("p");
    pNombreRutina.id = rutinaId + "-detalle-nombre";
    pNombreRutina.className = "rutina-detalle-container-row-nombre";
    pNombreRutina.textContent = nombreRutina;
    container1.appendChild(pNombreRutina);

    var btnClose = document.createElement("button");
    btnClose.type = "button";
    btnClose.title = "Cerrar";
    btnClose.className = "rutina-detalle-container-row-btn";
    btnClose.textContent = "X";
    btnClose.onclick = function() {
        detallesRutina.style.display = "none";
    };
    container1.appendChild(btnClose);

    var imgEliminar = document.createElement("img");
    imgEliminar.src = "../img/Trash.png";
    imgEliminar.alt = "Eliminar rutina";
    imgEliminar.title = "Eliminar";
    imgEliminar.className = "rutina-detalle-container-row-img";
    imgEliminar.onclick = function() {
        deleteRutina(nombreRutina, rutinaId);
    };
    container1.appendChild(imgEliminar);

    detallesRutina.appendChild(container1);

    var container2 = document.createElement("div");
    container2.className = "rutina-detalle-container-row";

    var pAgregarEjercicio = document.createElement("p");
    pAgregarEjercicio.id = rutinaId + "-detalle-btn-agregar-ejercicio";
    pAgregarEjercicio.className = "rutina-detalle-container-row-btn-agregar";
    pAgregarEjercicio.textContent = "+";
    pAgregarEjercicio.onclick = function() {
        openExerciseMenu(nombreRutina);
    };
    container2.appendChild(pAgregarEjercicio);

    detallesRutina.appendChild(container2);

    var mainContent = document.getElementById("main-content");
    mainContent.appendChild(detallesRutina);
}

function mostrarDetallesRutina(nombreRutina, rutinaId) {
    var detallesRutinaId = rutinaId + "-detalle";
    var detallesRutina = document.getElementById(detallesRutinaId);
    
    // Cerrar todos los detalles abiertos
    document.querySelectorAll('.rutina-detalle-container').forEach(detalle => {
        detalle.style.display = 'none';
    });
    
    if (!rutinaDetalles[nombreRutina]) {
        rutinaDetalles[nombreRutina] = {
            ejercicios: []
        };
    }

    detallesRutina.style.display = "block";

    var detallesContainer = document.getElementById(rutinaId + "-detalle-ejercicios");
    detallesContainer.innerHTML = '';

    rutinaDetalles[nombreRutina].ejercicios.forEach(ejercicio => {
        addExerciseToRutina(ejercicio, nombreRutina, false);
    });
}

function deleteRutina(nombreRutina, rutinaId) {
    var rutinaItem = document.getElementById(rutinaId);
    if (rutinaItem) {
        rutinaItem.remove();
    }

    var detallesRutinaId = rutinaId + "-detalle";
    var detallesRutina = document.getElementById(detallesRutinaId);
    if (detallesRutina) {
        detallesRutina.remove();
    }

    delete rutinaDetalles[nombreRutina];
}

function openExerciseMenu(nombreRutina) {
    const menu = document.getElementById('rutinas-menu-ejercicios');
    menu.style.display = "block";

    // Listener para cerrar el menú al hacer clic fuera de él
    const clickOutsideListener = function(event) {
        if (!menu.contains(event.target) && event.target.className !== 'rutina-detalle-container-row-btn-agregar') {
                menu.style.display = "none";
                document.removeEventListener('click', clickOutsideListener);
        }
    };
    document.addEventListener('click', clickOutsideListener);

    fetch('../ejercicios.json')
    .then(response => response.json())
    .then(data => {
        const exerciseList = document.getElementById('rutinas-menu-ejercicios-lista');
        exerciseList.innerHTML = '';

        data.ejercicios.forEach(ejercicio => {
                const li = document.createElement('li');
                li.textContent = ejercicio.Nombre;
                li.addEventListener('click', () => {
                    addExerciseToRutina(ejercicio, nombreRutina);
                    menu.style.display = "none"; // Cerrar el menú al seleccionar un ejercicio
                });
                exerciseList.appendChild(li);
        });

        // Añadir botón "CANCELAR"
        const cancelButton = document.createElement('button');
        cancelButton.textContent = "CANCELAR";
        cancelButton.onclick = function() {
                menu.style.display = "none";
        };
        exerciseList.appendChild(cancelButton);
    })
    .catch(error => console.error('Error al cargar JSON:', error));
}

function addExerciseToRutina(ejercicio, nombreRutina, addToDetalles = true) {
    const detallesContainer = document.getElementById("rutina-" + nombreRutina.toLowerCase().replace(/\s+/g, '-') + "-detalle");

    // Verificar si el ejercicio ya está presente en los detalles
    if (rutinaDetalles[nombreRutina].ejercicios.has(ejercicio)) {
        return;
    }

    const div = document.createElement("div");
    div.style.display = "flex";
    div.style.flexDirection = "column";
    div.className = "rutina-detalle-container-row";

    const divRow = document.createElement("div");
    divRow.className = "rutina-detalle-container-row-row";

    const p = document.createElement("p");
    p.textContent = ejercicio.Nombre;
    divRow.appendChild(p);

    const button = document.createElement("button");
    button.textContent = "✔";
    button.title = "Registrar";
    button.className = "rutina-detalle-container-row-row-btn-aceptar";
    button.onclick = function() {
        guardarDatosEjercicio(ejercicio, nombreRutina, div);
    };
    divRow.appendChild(button);

    const eliminar = document.createElement("img");
    eliminar.src = "../img/Trash.png";
    eliminar.alt = "Eliminar ejercicio";
    eliminar.title = "Eliminar ejercicio";
    eliminar.className = "rutina-detalle-container-row-row-img";
    eliminar.onclick = function() {
        div.remove();
        rutinaDetalles[nombreRutina].ejercicios.delete(ejercicio);
    };
    divRow.appendChild(eliminar);

    div.appendChild(divRow);

    if (ejercicio.Tipo === "ejercicio") {
        const series = ejercicio.Series;
        for (let i = 0; i < series; i++) {
            const seriesContainer = document.createElement("div");
            seriesContainer.style.display = "flex";
            seriesContainer.style.alignItems = "center";

            const repeticionesInput = createEditableField("Repeticiones", ejercicio.Repeticiones);
            const pesoInput = createEditableField("Peso", ejercicio.Peso);

            // Botón para eliminar la fila
            const deleteButton = document.createElement("button");
            deleteButton.textContent = "X";
            deleteButton.style.marginLeft = "10px";
            deleteButton.addEventListener("click", function() {
                seriesContainer.remove();
                validateExerciseContainer(div);
            });

            seriesContainer.appendChild(repeticionesInput);
            seriesContainer.appendChild(pesoInput);
            seriesContainer.appendChild(deleteButton);

            div.appendChild(seriesContainer);
        }

        // Botón para añadir series
        const addButton = document.createElement("button");
        addButton.textContent = "+";
        addButton.style.marginTop = "10px";

        addButton.addEventListener("click", function() {
            const repeticionesInput = createEditableField("Repeticiones", ejercicio.Repeticiones);
            const pesoInput = createEditableField("Peso", ejercicio.Peso);

            // Botón para eliminar la fila
            const deleteButton = document.createElement("button");
            deleteButton.textContent = "X";
            deleteButton.style.marginLeft = "10px";

            deleteButton.addEventListener("click", function() {
                seriesContainer.remove();
                validateExerciseContainer(div);
            });

            const seriesContainer = document.createElement("div");
            seriesContainer.style.display = "flex";
            seriesContainer.style.alignItems = "center";

            seriesContainer.appendChild(repeticionesInput);
            seriesContainer.appendChild(pesoInput);
            seriesContainer.appendChild(deleteButton);

            div.insertBefore(seriesContainer, addButton);
        });

        div.appendChild(addButton);
    } else if (ejercicio.Tipo === "arte marcial") {
        div.appendChild(createEditableField("Calentamiento", ejercicio.Calentamiento));
        div.appendChild(createEditableField("Técnica", ejercicio.Técnica));
        div.appendChild(createEditableField("Sparring", ejercicio.Sparring));
        div.appendChild(createEditableField("Num_Sparring", ejercicio.Num_Sparring));
    }

    detallesContainer.appendChild(div);

    if (addToDetalles) {
        rutinaDetalles[nombreRutina].ejercicios.add(ejercicio);
    }
}

function createEditableField(label, value) {
    const span = document.createElement("span");
    span.textContent = label + ": ";

    const input = document.createElement("input");
    input.type = "text";
    input.value = value;
    input.style.marginBottom = "5px";
    input.style.width = "100px";

    span.appendChild(input);
    return span;
}

function guardarDatosEjercicio(ejercicio, nombreRutina, exerciseContainer) {
    const seriesContainers = exerciseContainer.querySelectorAll('div[style*="flex"]:not(:first-child)');
    const series = [];

    seriesContainers.forEach(seriesContainer => {
        const repeticionesInput = seriesContainer.querySelector('input[type="text"]:nth-child(2)');
        const pesoInput = seriesContainer.querySelector('input[type="text"]:nth-child(3)');
        const repeticiones = repeticionesInput ? repeticionesInput.value : 0;
        const peso = pesoInput ? pesoInput.value : 0;
        series.push({ repeticiones, peso });
    });

    rutinaDetalles[nombreRutina].ejercicios = {
        ...rutinaDetalles[nombreRutina].ejercicios,
        [ejercicio.Nombre]: series
    };

    console.log("Datos guardados:", rutinaDetalles[nombreRutina].ejercicios);
}

function validateExerciseContainer(exerciseContainer) {
    const seriesContainers = exerciseContainer.querySelectorAll('div[style*="flex"]');
    if (seriesContainers.length === 0) {
        exerciseContainer.remove();
    }
}

function closeExerciseMenu() {
    const menu = document.getElementById("rutinas-menu-ejercicios");
    menu.style.display = "none";
}