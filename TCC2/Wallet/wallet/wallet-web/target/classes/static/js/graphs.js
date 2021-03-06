'use strict';

var renderizarGrafico = function($container, listaServicos) {
    var labels = [];
    var data = [];
    var colors = [];


    var randomColorGenerator = function () {
        return '#' + (Math.random().toString(16) + '0000000').slice(2, 8);
    };
    $.each(listaServicos, function (i) {
        colors[i] = randomColorGenerator();
    });

    $.each(listaServicos, function (i, servico) {
        labels[i] = servico.nome;
        data[i] = accounting.toFixed(servico.porcentagemCustoTotal, 2);
    });

    var ctx = $container;
    var grafico = new Chart(ctx, {
        type: 'pie',
        animation: {
            animateRotate: true
        },
        data: {
            labels: labels,
            datasets: [{
                    data: data,
                    backgroundColor: colors
                }]
        },
        options: {
            responsive: true
        }
    });
};

var buscarDadosEChamarGraficos = function($container1, $container2) {
    
    AJAXgetByUrl('/servicos-inflar-grafico').done(function (data) {
        renderizarGrafico($container1, data.servicosDesteMes);
        renderizarGrafico($container2, data.servicosProximoMes);
    });
};
