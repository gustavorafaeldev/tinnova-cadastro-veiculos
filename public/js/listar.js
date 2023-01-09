url = "http://localhost:8080/veiculos"

window.onload = async function () {
    await fetch("http://localhost:8080/veiculos/", {
        method: "GET"

    })
        .then(response => response.json())
        .then(carros => {
            updateTable(carros);
        })
        .catch(error => {
            console.error(error);
        });
};

async function searchById() {
    event.preventDefault();
    const id = document.querySelector("#id").value;
    if (id === "") {
        await fetch(`${url}`, {
            method: "GET"

        })
            .then(response => response.json())
            .then(carros => {
                updateTable(carros);
            })
            .catch(error => {
                console.error(error);
            });
    } else {
        await fetch(`${url}/${id}`)
            .then(response => response.json())
            .then(carro => {
                updateTable([carro]);
            })
            .catch(error => {
                console.error(error);
            });
    }
}

function editCar(event) {
    let id = event.target.getAttribute("data-id");
    window.location.assign("editar.html?id=" + id);
}

async function deleteCar(event) {

    let id = event.target.getAttribute("data-id");

    await fetch(`${url}/${id}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            window.location.reload()
        } else {
            console.error('Erro ao excluir carro');
        }
    });
}

async function searchByParams() {
    event.preventDefault();

    const marca = document.querySelector('#marca').value;
    const ano = document.querySelector('#ano').value;
    const cor = document.querySelector('#cor').value;

    console.log("parametros " + marca)
    console.log("parametros " + ano)
    console.log("parametros " + cor)

    const queryString = `?marca=${marca}&ano=${ano}&cor=${cor}`;

    console.log("query string" + queryString)

    await fetch(`${url}/findByParams/${queryString}`, {
        method: "GET"
    })
        .then(response => response.json())
        .then(carros => {
            console.log(carros)
            updateTable(carros);
        })
        .catch(error => {
            console.error(error);
        });
}


function updateTable(carros) {
    const carrosTable = document.querySelector("#carros");
    carrosTable.innerHTML = "";
    carros.forEach(carro => {
        const row = document.createElement("tr");
        row.innerHTML = `
      <td class="d-none">${carro.id}</td>
      <td>${carro.veiculo}</td>
      <td>${carro.marca}</td>
      <td>${carro.ano}</td>
      <td>${carro.cor}</td>
      <td>${carro.descricao}</td>
      <td>
        <input type="checkbox" class="vendido-checkbox" data-id="${carro.id}" ${carro.vendido ? "checked" : ""}>
        ${carro.vendido ? "Sim" : "Não"}
      </td>
      
      <td>${new Date(carro.criacao).toLocaleDateString()}</td>
      <td>${carro.atualizacao != null ? new Date().toLocaleDateString() : ""}</td>
      <td>
     
        <button class="btn btn-primary" data-id="${carro.id}" onclick="editCar(event)">Alterar</button>
        <button class="btn btn-danger" data-id="${carro.id}" onclick="deleteCar(event)">Excluir</button>
        
      </td>
    `;

        const vendidoCheckbox = row.querySelector(".vendido-checkbox");
        vendidoCheckbox.addEventListener("change", async event => {
            const id = event.target.dataset.id;
            const vendido = event.target.checked;

            await fetch(`${url}/${id}`, {
                method: "PATCH",
                body: JSON.stringify({vendido}),
                headers: {"Content-Type": "application/json"}
            }).then(response => {
                if (!response.ok) {
                    alert("Ocorreu um erro ao atualizar o veículo");
                } else {
                    window.location.reload()
                }
            });
        });

        carrosTable.appendChild(row);
    });
}













