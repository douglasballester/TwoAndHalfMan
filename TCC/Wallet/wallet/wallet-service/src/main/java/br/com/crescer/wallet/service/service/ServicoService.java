package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Moeda;
import static br.com.crescer.wallet.entity.Moeda.BRL;
import br.com.crescer.wallet.entity.Periodicidade;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.entity.Usuario;
import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.dto.GraficoDTO;
import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.repository.ServicoRepository;
import static br.com.crescer.wallet.service.service.ServiceUtils.CALC_SCALE;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_EVEN;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import static br.com.crescer.wallet.service.service.ServiceUtils.PAGE_SIZE;

/**
 *
 * @author victo
 */
@Service
public class ServicoService {
    
    @Autowired
    ServicoRepository repository;
    
    @Autowired
    CotacaoService cotacaoService;
    
    @Autowired
    UsuarioService usuarioService;
    
    public DashboardDTO geraDadosDashboard(Pageable pageable) {
        DashboardDTO dashboard = new DashboardDTO();
        {
            //TODO: EXCEPTION
            List<ServicoDTO> servicosDTOMesAtualPaginados = this.getServicosDTOMesAtualPaginados(pageable);
            dashboard.setServicosMesAtual(servicosDTOMesAtualPaginados);
            dashboard.setServicosProximoMes(this.getServicosDTOProximoMesPaginados(pageable));
            dashboard.setServicoMaisCaroContratado(servicosDTOMesAtualPaginados.isEmpty() ? null : servicosDTOMesAtualPaginados.get(0));
            dashboard.setGastoTotalAtual(this.getGastoTotalAtual());
            dashboard.setGastoTotalProximoMes(this.getGastoTotalProximoMes());
        }
        return dashboard;
    }
    
    public BigDecimal getGastoTotalAtual() {
        List<ServicoDTO> servicosDTOMesAtual = this.getServicosDTOMesAtual(this.servicosMesAtual());
        BigDecimal gastoTotalAtual = this.calculaGastoTotalMes(servicosDTOMesAtual);
        return gastoTotalAtual;
    }
    
    public BigDecimal getGastoTotalProximoMes() {
        List<ServicoDTO> servicosDTOProximoMes = this.getServicosDTOProximoMes(this.servicosProximoMes());
        BigDecimal gastoTotalProximoMes = this.calculaGastoTotalMes(servicosDTOProximoMes);
        return gastoTotalProximoMes;
    }
    
    public List<ServicoDTO> getServicosDTOMesAtualPaginados(Pageable pageable) {
        return this.getServicosDTOMesAtual(this.servicosMesAtualPaginados(pageable));
    }
    
    public List<ServicoDTO> getServicosDTOProximoMesPaginados(Pageable pageable) {
        return this.getServicosDTOProximoMes(this.servicosProximoMesPaginados(pageable));
    }
    
    public List<ServicoDTO> getServicosDTOMesAtualFiltradosPorGerentePaginados(Long idGerente, Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return this.getServicosDTOMesAtual(this.servicosMesAtualPaginadosFiltradosPorGerente(idGerente, pageable));
    }
    
    public List<ServicoDTO> getServicosDTOProximoMesFiltradosPorGerentePaginados(Long idGerente, Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return this.getServicosDTOProximoMes(this.servicosProximoMesPaginadosFiltradosPorGerente(idGerente, pageable));
    }
    
    public GraficoDTO getDadosGraficoServicos() {
        List<ServicoDTO> servicosDesteMes = this.getServicosDTOMesAtual(this.servicosMesAtual());
        List<ServicoDTO> servicosProximoMes = this.getServicosDTOProximoMes(this.servicosProximoMes());
        {
            BigDecimal gastoTotalMesAtual = this.calculaGastoTotalMes(servicosDesteMes);
            if (gastoTotalMesAtual != null) {
                servicosDesteMes.stream().forEach((servico) -> {
                    servico.setPorcentagemCustoTotal(gastoTotalMesAtual);
                });
            }
            BigDecimal gastoTotalProximoMes = this.calculaGastoTotalMes(servicosProximoMes);
            if (gastoTotalProximoMes != null) {
                servicosProximoMes.stream().forEach((servico) -> {
                    servico.setPorcentagemCustoTotal(gastoTotalProximoMes);
                });
            }
        }
        GraficoDTO graficoDTO = new GraficoDTO();
        {
            graficoDTO.setServicosDesteMes(servicosDesteMes);
            graficoDTO.setServicosProximoMes(servicosProximoMes);
        }
        return graficoDTO;
    }
    
    public ServicoDTO getServicoDTO(Long idServico) {
        final Map<Moeda, BigDecimal> medias = cotacaoService.findLastExchangeRate();
        Servico s = repository.findOne(idServico);
        return this.buildDTO(s, medias);
    }
    
    public Servico salvarServico(ServicoDTO dto) {
        return repository.save(this.buildServico(dto));
    }
    
    public boolean cancelarServico(Long idServico) {
        try {
            Servico s = repository.findOne(idServico);
            s.setDsSituacao(Situacao.CANCELADO);
            repository.save(s);
            return true;
        } catch (Exception e) {
            //TODO: EXCEPTION 
            return false;
        }
    }
    
    public long countServicosByUsuarioId(Long idUsuario) {
        return repository.countByUsuarioIdUsuario_idUsuarioAndDsSituacao(idUsuario, Situacao.ATIVO);
    }
    
    public void cancelarServicosByIdUsuario(Long idUsuario) {
        List<Servico> servicos = repository.findAllByusuarioIdUsuario_idUsuario(idUsuario);
        servicos.stream().forEach((servico) -> {
            servico.setDsSituacao(Situacao.CANCELADO);
        });        
        repository.save(servicos);
    }
    
    private List<Servico> servicosMesAtual() {
        return repository.findByDsSituacaoNot(Situacao.INATIVO);
    }
    
    private List<Servico> servicosProximoMes() {
        return repository.findByDsSituacao(Situacao.ATIVO);
    }
    
    private List<Servico> servicosMesAtualPaginados(Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return repository.findByDsSituacaoNot(Situacao.INATIVO, pageable);
    }
    
    private List<Servico> servicosProximoMesPaginados(Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return repository.findByDsSituacao(Situacao.ATIVO, pageable);
    }
    
    private List<Servico> servicosMesAtualPaginadosFiltradosPorGerente(Long idGerente, Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return repository.findAllByusuarioIdUsuario_idUsuarioAndDsSituacaoNot(idGerente, Situacao.INATIVO, pageable);
    }
    
    private List<Servico> servicosProximoMesPaginadosFiltradosPorGerente(Long idGerente, Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return repository.findAllByusuarioIdUsuario_idUsuarioAndDsSituacao(idGerente, Situacao.ATIVO, pageable);
    }
    
    private BigDecimal calculaGastoTotalMes(List<ServicoDTO> servicosDTO) {
        BigDecimal gastoTotal = BigDecimal.ZERO;
        for (ServicoDTO servico : servicosDTO) {
            gastoTotal = gastoTotal.add(servico.getCustoMensal());
        }
        return gastoTotal.setScale(CALC_SCALE, HALF_EVEN);
    }
    
    private List<ServicoDTO> getServicosDTOMesAtual(List<Servico> servicos) {
        final Map<Moeda, BigDecimal> medias = cotacaoService.findLastExchangeRate();
        List<ServicoDTO> servicosDTO = new ArrayList<>();
        servicos.stream().forEach((servico) -> {
            servicosDTO.add(this.buildDTO(servico, medias));
        });
        return servicosDTO;
    }
    
    private List<ServicoDTO> getServicosDTOProximoMes(List<Servico> servicos) {
        final Map<Moeda, BigDecimal> medias = cotacaoService.findLastAverage();
        List<ServicoDTO> servicosDTO = new ArrayList<>();
        servicos.stream().forEach((servico) -> {
            servicosDTO.add(this.buildDTO(servico, medias));
        });
        return servicosDTO;
    }
    
    private ServicoDTO buildDTO(Servico servico, Map<Moeda, BigDecimal> medias) {
        //TODO adicionar exception()nullPointer
        if (servico == null) {
            return null;
        }
        BigDecimal vlrCusto;
        {
            BigDecimal periodicidade = BigDecimal.valueOf(servico.getDsPeriodicidade().getNumeral());
            BigDecimal media = medias.get(servico.getDsSimboloMoeda());
            BigDecimal taxa = medias.get(BRL);
            
            vlrCusto = servico.getVlTotalServico()
                    .divide(periodicidade, CALC_SCALE, HALF_EVEN)
                    .divide(media, CALC_SCALE, HALF_EVEN)
                    .multiply(taxa).setScale(CALC_SCALE, HALF_EVEN);
        }
        ServicoDTO servicoDTO = new ServicoDTO();
        {
            servicoDTO.setId(servico.getIdServico());
            servicoDTO.setNome(servico.getNmServico());
            servicoDTO.setWebSite(servico.getDsWebsite());
            servicoDTO.setDescricao(servico.getDsDescricao());
            servicoDTO.setPeriodicidade(servico.getDsPeriodicidade());
            servicoDTO.setMoeda(servico.getDsSimboloMoeda());
            servicoDTO.setValorTotal(servico.getVlTotalServico());
            servicoDTO.setCustoMensal(vlrCusto);
            servicoDTO.setIdUsuarioResponsavel(servico.getUsuarioIdUsuario().getIdUsuario());
            servicoDTO.setNomeUsuarioResponsavel(servico.getUsuarioIdUsuario().getNmUsuario());
            servicoDTO.setSituacao(servico.getDsSituacao());
        }
        return servicoDTO;
    }
    
    private Servico buildServico(ServicoDTO servicoDTO) {
        Servico servico = new Servico();
        {
            servico.setIdServico(servicoDTO.getId());
            servico.setNmServico(servicoDTO.getNome());
            servico.setDsWebsite(servicoDTO.getWebSite());
            servico.setDsPeriodicidade(servicoDTO.getPeriodicidade());
            servico.setDsDescricao(servicoDTO.getDescricao());
            servico.setDsSimboloMoeda(servicoDTO.getMoeda());
            servico.setVlTotalServico(servicoDTO.getValorTotal());
        }
        {
            Usuario usuario = usuarioService.findById(servicoDTO.getIdUsuarioResponsavel());
            servico.setUsuarioIdUsuario(usuario);
            
            servico.setVlMensalUSD(this.calculaGastoMensalUSD(servicoDTO.getPeriodicidade(), servicoDTO.getValorTotal(), servicoDTO.getMoeda()));
            
            if (servicoDTO.getSituacao() == null) {
                servico.setDsSituacao(Situacao.ATIVO);
            } else {
                servico.setDsSituacao(servicoDTO.getSituacao());
            }
        }
        return servico;
    }
    
    private BigDecimal calculaGastoMensalUSD(Periodicidade periodicidade, BigDecimal valorTotal, Moeda moedaOriginal) {
        BigDecimal mensalUSD = valorTotal
                .divide(BigDecimal.valueOf(periodicidade.getNumeral()), CALC_SCALE, HALF_EVEN)
                .divide(cotacaoService.findLastCurrencyAverage(moedaOriginal), CALC_SCALE, HALF_EVEN);
        return mensalUSD;
    }
    
    @Scheduled(cron = "0 1 0 1 1/1 ?")
    public void atualizaStatusServicosCancelados() {
        List<Servico> servicosCancelados = repository.findByDsSituacao(Situacao.CANCELADO);
        servicosCancelados.stream().forEach((servico) -> {
            servico.setDsSituacao(Situacao.INATIVO);
        });
        repository.save(servicosCancelados);
    }
    
}
