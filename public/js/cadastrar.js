async function submitForm() {
    event.preventDefault()
    const formData = new FormData(document.querySelector("form"));
    const veiculo = formData.get("veiculo");
    const marca = formData.get("marca");
    const ano = formData.get("ano");
    const cor = formData.get("cor");
    const descricao = formData.get("descricao");
    const vendido = formData.get("vendido") === "true";
    const body = {veiculo, marca, ano, cor, descricao, vendido};

    await fetch("http://localhost:8080/veiculos", {
        method: "POST",
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
    window.location.replace('/index.html')
}