/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.service.dto;

import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.entity.Periodicidade;
import br.com.crescer.wallet.entity.Situacao;
import static br.com.crescer.wallet.service.service.ServiceUtils.CALC_SCALE;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_EVEN;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author victor.ribeiro
 */
public class ServicoDTO {    
    
    private long id;
    private BigDecimal custoMensal;
    private BigDecimal porcentagemCustoTotal;
    private String nomeUsuarioResponsavel;
    
    @NotEmpty
    @Length(max = 255)
    private String nome;
    
    @NotEmpty
    @Length(max = 255)
    @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
    private String webSite;
    
    @NotEmpty
    @Length(max = 800)
    private String descricao;
    
    @NotNull
    private Periodicidade periodicidade;
    
    @NotNull
    private Moeda moeda;
  
    @NotNull
    @Range(min = 1)
    private long idUsuarioResponsavel;
    
    @NotNull
    @Range(min = 0)
    private BigDecimal valorTotal;   
    
    private Situacao situacao;

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }   

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getCustoMensal() {
        return custoMensal;
    }

    public void setCustoMensal(BigDecimal custoMensal) {
        this.custoMensal = custoMensal;
    }

    public BigDecimal getPorcentagemCustoTotal() {
        return porcentagemCustoTotal;
    }

    public void setPorcentagemCustoTotal(BigDecimal gastoTotal) {
        BigDecimal porcentCustoTotal = this.custoMensal.multiply(BigDecimal.valueOf(100)).divide(gastoTotal, CALC_SCALE, HALF_EVEN);
        this.porcentagemCustoTotal = porcentCustoTotal;
    }    

    public Periodicidade getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(Periodicidade periodicidade) {
        this.periodicidade = periodicidade;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;
    }

    public long getIdUsuarioResponsavel() {
        return idUsuarioResponsavel;
    }

    public void setIdUsuarioResponsavel(long idUsuarioResponsavel) {
        this.idUsuarioResponsavel = idUsuarioResponsavel;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNomeUsuarioResponsavel() {
        return nomeUsuarioResponsavel;
    }

    public void setNomeUsuarioResponsavel(String nomeUsuarioResponsavel) {
        this.nomeUsuarioResponsavel = nomeUsuarioResponsavel;
    }
}
