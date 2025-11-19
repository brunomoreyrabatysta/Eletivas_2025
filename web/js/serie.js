async function CarregarListaSerie() {
    request("http://localhost:8080/Eletivas_2025/Serie", "GET").then((data) => {
        console.log(data);
        var lista = data.Dados;
        var tabela = "";
        for (const item of lista) {
            tabela = tabela + "<tr>";
            tabela = tabela + "  <td style='text-align: right;'>" + item.SerieId + "</td>";
            tabela = tabela + "  <td style='text-align: center;'>" + item.Descricao + "</td>";
            tabela = tabela + "  <td style='text-align: center;'>" + item.Situacao + "</td>";
            tabela = tabela + "  <td style='text-align: center;'>" + item.Turno + "</td>";
            tabela = tabela + "  <td style='text-align: center;'><button onclick='ExcluirSerie(" + item.SerieId + ")'>Excluir</button></td>";
            tabela = tabela + "  <td style='text-align: center;'><button onclick='EditarSerie(" + item.SerieId + ")'>Editar</button></td>";
            tabela = tabela + "</tr>";
        }        
        document.getElementById("carregamentoTabela").innerHTML = tabela;
    });
}

async function IncluirSerie() {
    request("http://localhost:8080/Eletivas_2025/Serie", "POST").then((data) => {
        console.log(data);
    });
}

async function AlterarSerie() {
    request("http://localhost:8080/Eletivas_2025/Serie", "PUT").then((data) => {
        console.log(data);
    });
}

async function ExcluirSerie(serieId) {
    request("http://localhost:8080/Eletivas_2025/Serie?serieId="+serieId, "DELETE").then((data) => {
        console.log(data);
    });
}
