'use strict';

// MES ATUAL
var $containerMesAtual = $('#container-mes-atual');
var $containerGraficoMesAtual = $('#grafico-mes-atual');
var $gastoTotalMesAtual = $('#preco-total-mes');
var $verMaisServicosMesAtual = $('#btnVerMaisAtual');
var paginaAtualMesAtual = 0;
var listaServicosMesAtual;

// PROXIMO MES
var $containerProximoMes = $('#container-proximo-mes');
var $containerGraficoProximoMes = $('#grafico-proximo-mes');
var $gastoTotalProximoMes = $('#preco-total-proximo-mes');
var $verMaisServicosProximoMes = $('#btnVerMaisProximo');
var paginaAtualProximoMes = 0;
var listaServicosProximoMes;

//GERAL
var $servicoMaisCaro = $('#servico-mais-caro');

var $gastoTotalMesAtual = $('#preco-total-mes');
var $gastoTotalProximoMes = $('#preco-total-proximo-mes');

var paginaAtualEsteMesFiltrado = 0;
var paginaAtualProximoMesFiltrado = 0;
var filtroAtual = null;
var filtroProximoMes = null;


var $btnPesquisarAtual = $('#btnPesquisarAtual');
var $btnPesquisarProximo = $('#btnPesquisarProximo');

var $formAtual = $('#formFiltrarAtual');
var $formAtualSelect = $('#formFiltrarAtual select');
var $formProximo = $('#formFiltrarProximo');
var $formProximoSelect = $('#formFiltrarProximo select');

var selectAtual = $containerMesAtual.find('#select-gerentes');
var selectProximoMes = $containerProximoMes.find('#select-gerentes');

var rederizaListaServicos = function (containerLista, servicos) {
    $.each(servicos, function (i, servico) {
        containerLista.find('#services-container-list').append(
                $('<section>').fadeIn(400).addClass('col-md-6').addClass('single-service-container').addClass('list-group-item')
                .append($('<div>')
                        .append($('<div>').addClass('text-center').attr('style', 'border: 1px solid #e6e6e6; border-radius: 0px;')
                                .append($('<a>').html($('<h5>').addClass('service-name').text(servico.name)).attr('href', '/servico?idServico=' + servico.id))
                                .append($('<h5>').text("(" + servico.responsibleUserName + ")"))
                                .append($('<h5>').addClass('service-value').text(accounting.formatMoney(servico.monthlyExpense, "R$ ", 2, ".", ",")))
                                )
                        .append($('<div>').attr('style', 'margin-bottom: 30px;')
                                .append($('<a>').addClass('btn').addClass('btn-warning').addClass('service-edit-btn')
                                        .append($('<span>').addClass('glyphicon').addClass('glyphicon-pencil').attr('aria-hidden', true))
                                        )
                                .append($('<a>').addClass('btn').addClass('btn-danger').addClass('service-delete-btn')
                                        .append($('<span>').addClass('glyphicon').addClass('glyphicon-trash').attr('aria-hidden', true))
                                        )
                                )
                        )
                );
    });
};

var getDadosDashboard = function () {
    $.ajax({
        url: "/dashboard?page=0",
        type: "GET"
    }).done(function (dados) {
        $servicoMaisCaro.append($('<a>').html($('<h1>').attr('style', 'display: inline; color: #FBAF41; font-weight: bold;').text(dados.moreExpensiveContract.name)).attr('href', '/servico?idServico=' + dados.moreExpensiveContract.id))
                .append($('<h1>').attr('style', 'display: inline; color: #434343; font-weight: bold;').text(" - " + accounting.formatMoney(dados.moreExpensiveContract.monthlyExpense, "R$ ", 2, ".", ",")));
        $gastoTotalMesAtual.text(accounting.formatMoney(dados.thisMonthAmountExpense, "R$ ", 2, ".", ","));
        $gastoTotalProximoMes.text(accounting.formatMoney(dados.nextMonthAmountExpense, "R$ ", 2, ".", ","));
        listaServicosMesAtual = dados.thisMonthContractDTOs;
        rederizaListaServicos($containerMesAtual, listaServicosMesAtual);
        listaServicosProximoMes = dados.nextMonthContractDTOs;
        rederizaListaServicos($containerProximoMes, listaServicosProximoMes);
    });
};

var getProxPaginaServicosProximoMes = function () {
    var url = filtroProximoMes !== null ?
            '/servicos-proximo-mes?idGerente=' + filtroProximoMes + '&page=' + ++paginaAtualProximoMesFiltrado
            : '/servicos-proximo-mes?page=' + ++paginaAtualProximoMesFiltrado;

    $.ajax({
        type: 'GET',
        url: url
    }).done(function (data) {
        if (typeof data === 'string') {
            location.reload();
        } else {
            if (data.length < 4) {
                $('#btnVerMaisProximo').text("Não existem mais serviços").attr('style', 'margin-left: 31%; margin-right: 40%;');
                $('#btnVerMaisProximo').addClass('disabled');
            }
            rederizaListaServicos($containerProximoMes, data);
        }
    });
};


var getProxPaginaServicosEsteMes = function () {
    var url = filtroAtual !== null ?
            '/servicos-mes-atual?idGerente=' + filtroAtual + '&page=' + ++paginaAtualEsteMesFiltrado
            : '/servicos-mes-atual?page=' + ++paginaAtualEsteMesFiltrado;
    $.ajax({
        type: 'GET',
        url: url
    }).done(function (data) {
        if (typeof data === 'string') {
            location.reload();
        } else {
            if (data.length < 4) {
                $('#btnVerMaisAtual').text('Não existem mais serviços.').attr('style', 'margin-left: 31%; margin-right: 40%;');
                $('#btnVerMaisAtual').addClass('disabled');
            }
            rederizaListaServicos($containerMesAtual, data);
        }
    });
};

$(function () {
    $formAtual.hide();
    $formProximo.hide();

    getDadosDashboard();

    buscarDadosEChamarGraficos($containerGraficoMesAtual, $containerGraficoProximoMes);

    $verMaisServicosMesAtual.click(function () {
        getProxPaginaServicosEsteMes();
    });

    $verMaisServicosProximoMes.click(function () {
        getProxPaginaServicosProximoMes();
    });

    setarOnClickBotaoPesquisar($btnPesquisarAtual, $btnPesquisarProximo);
    buscaGerentes();
});

function setarOnClickBotaoPesquisar($btnPesquisarAtual, $btnPesquisarProximo) {
    $btnPesquisarAtual.click(function () {
        $formAtual.toggle(250, 'linear');
    });
    $btnPesquisarProximo.click(function () {
        $formProximo.toggle(250, 'linear');
    });

}

function buscaGerentes() {
    $.ajax({
        type: 'GET',
        url: '/buscar-gerentes-ativos'
    }).done(function (data) {
        $.each(data, function (i, gerenteDTO) {
            selectAtual.append($('<option>').val(gerenteDTO.id).text(gerenteDTO.nome));
            selectProximoMes.append($('<option>').val(gerenteDTO.id).text(gerenteDTO.nome));
        });
    });
}

$('#select-gerentes').change(function () {

    $formAtual.submit(function (e) {
        var idGerente = selectAtual.val();
        paginaAtualEsteMesFiltrado = -1;
        filtroAtual = idGerente;
        limparContainer($containerMesAtual.find('#services-container-list'));
        $verMaisServicosMesAtual.removeClass('disabled');
        $verMaisServicosMesAtual.text('Ver mais');
        getProxPaginaServicosEsteMes();
        e.preventDefault();
    });
});

$('#select-gerentes').change(function () {
    $formProximo.submit(function (e) {
        var idGerente = selectProximoMes.val();
        paginaAtualProximoMesFiltrado = -1;
        filtroProximoMes = idGerente;
        limparContainer($containerProximoMes.find('#services-container-list'));
        $verMaisServicosProximoMes.removeClass('disabled');
        $verMaisServicosProximoMes.text('Ver mais');
        getProxPaginaServicosProximoMes();
        e.preventDefault();
    });
});

function limparContainer($container) {
    $container.html('');
}

