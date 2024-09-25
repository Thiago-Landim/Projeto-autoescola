
// Função para aplicar a máscara no CPF
document.getElementById('cpf').addEventListener('input', function(event) {
        let value = event.target.value;

        // Remove caracteres não numéricos
        value = value.replace(/\D/g, '');

        // Adiciona a máscara
        if (value.length <= 3) {
                value = value.replace(/(\d{1,3})/, '$1');
        } else if (value.length <= 6) {
                value = value.replace(/(\d{3})(\d{1,3})/, '$1.$2');
        } else if (value.length <= 9) {
                value = value.replace(/(\d{3})(\d{3})(\d{1,3})/, '$1.$2.$3');
        } else {
                value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{1,2})/, '$1.$2.$3-$4');
        }

        // Atualiza o valor do campo com a máscara aplicada
        event.target.value = value;
});

function trazPeloID(id) {
        if (!id) {
                alert("ID não fornecido.");
                return;
        }

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/sci/" + id, true); // URL com o ID
        xhr.setRequestHeader("Content-Type", "application/json"); // Configuração do tipo de conteúdo

        xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                                alert(xhr.responseText); // Exibe a resposta do servidor
                        } else {
                                alert("Erro ao processar a solicitação: " + xhr.statusText);
                        }
                }
        };

        xhr.send(); // Envia a solicitação para o servidor
}


        function buscarPorCPF() {
        const cpf = document.getElementById('cpf').value;
        if (cpf) {
        fetch(`/sci?cpf=${cpf}`)
        .then(response => response.json())
        .then(data => {
        if (data) {
        document.getElementById('nome').value = data.nome;
        document.getElementById('email').value = data.email;
        document.getElementById('data-certificado').value = data.dataCertificado;
        document.getElementById('certificado-valido').value = data.valido ? 'Sim' : 'Não';
} else {
        alert('Instrutor não encontrado.');
}
})
        .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao buscar instrutor.');
});
} else {
        alert('Digite um CPF válido.');
}
}







