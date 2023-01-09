url = "http://localhost:8080/veiculos"

window.onload = async function () {

    let urlParams = new URLSearchParams(window.location.search);
    let id = urlParams.get("id");
    await fetch(`${url}/${id}`, {
        method: "GET"
    })
        .then(response => response.json())
        .then(veiculo => {
            document.getElementById("veiculo").value = veiculo.veiculo;
            document.getElementById("marca").value = veiculo.marca;
            document.getElementById("ano").value = veiculo.ano;
            document.getElementById("cor").value = veiculo.cor;
            document.getElementById("descricao").value = veiculo.descricao;
            document.getElementById("vendido").checked = veiculo.vendido;
        });
}

async function submitForm() {
    event.preventDefault()

    let urlParams = new URLSearchParams(window.location.search);
    let id = urlParams.get("id");

    const formData = new FormData(document.querySelector("form"));
    const veiculo = formData.get("veiculo");
    const marca = formData.get("marca");
    const ano = formData.get("ano");
    const cor = formData.get("cor");
    const descricao = formData.get("descricao");
    const vendido = formData.get("vendido") === "true";
    const body = {veiculo, marca, ano, cor, descricao, vendido};

    await fetch(`${url}/${id}`, {
        method: "PUT",
        body: JSON.stringify(body),
        headers: {"Content-Type": "application/json"}

    })
        .then(response => response.json())
        .then(result => {
            window.location.replace("/listar.html");
            console.log(result);
        })
        .catch(error => {
            console.error(error);
        });
}

function backPage() {
    window.location.replace('/listar.html')
}

