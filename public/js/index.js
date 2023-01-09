url = "http://localhost:8080/veiculos"

function goToNewVehiclePage() {
    window.location.assign("/cadastrar.html");
}

function goToListVehiclePage() {
    window.location.assign("/listar.html");
}

window.onload = async function () {
    await fetch(`${url}`)
        .then(response => response.json())
        .then(veiculos => {
            const veiculosNaoVendidos = veiculos.filter(veiculo => !veiculo.vendido);
            const veiculosDecadaNoventa = veiculos.filter(veiculo => veiculo.ano >= 1990 && veiculo.ano <= 1999)
            const veiculosDecadaDoisMil = veiculos.filter(veiculo => veiculo.ano >= 2000)

            const dateLastWeek = new Date();
            dateLastWeek.setDate(dateLastWeek.getDate() - 7)

            const veiculosUltimaSemana = veiculos.filter(veiculo => new Date(veiculo.criacao) > dateLastWeek)

            const marcas = ['Volkswagen', 'Chevrolet', 'Fiat', 'Ford', 'Toyota', 'Honda', 'Hyundai', 'Renault',
                'Mitsubishi', 'Nissan'];

            for (let marca of marcas) {
                document.querySelector(`#${marca}`).textContent = veiculos
                    .filter(veiculo => veiculo.marca === marca).length
            }

            document.querySelector('#total-veiculos-nao-vendidos').textContent = veiculosNaoVendidos.length;
            document.querySelector('#decada-90').textContent = veiculosDecadaNoventa.length;
            document.querySelector('#decada-20').textContent = veiculosDecadaDoisMil.length;

            updateTable(veiculosUltimaSemana)
        });


}

function updateTable(carros) {
    const carrosTable = document.querySelector("#index-carros");
    carrosTable.innerHTML = "";
    carros.forEach(carro => {
        const row = document.createElement("tr");
        row.innerHTML = `
      <td class="d-none">${carro.id}</td>
      <td>${carro.veiculo}</td>
      <td>${carro.marca}</td>
      <td>${carro.ano}</td>
      <td>${carro.cor}</td>
      <td>${new Date(carro.criacao).toLocaleDateString()}</td>
   
    `;
        carrosTable.appendChild(row);
    });
}


