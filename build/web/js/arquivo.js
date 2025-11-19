function Mensagem(texto) {
    alert(texto);
}

async function request(url = "", methd, data) {
    const response = await fetch(url, {
        method: methd,
        headers: {"Content-Type": "application/json", },
        body: JSON.stringify(data)
    });
    return response.json();
}