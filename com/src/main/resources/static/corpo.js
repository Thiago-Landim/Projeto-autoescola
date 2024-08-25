
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

function trazPeloID() {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/processa", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                                alert(xhr.responseText);
                        } else {
                                alert("Erro ao processar a solicitação.");
                        }
                }
        };

        xhr.send(); // Envia a solicitação para o servidor
}

