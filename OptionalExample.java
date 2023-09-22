
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OptionalExample {
	
	@Autowired
    DataRepository repository;

    private void salvarRotina(Origem origem, Data data) {

        Optional<Data> optional = repository.findByOrigem(data.getPagamentoOrigem());

        PagamentoData pagamentoData = optional.orElse(new PagamentoData());

        pagamentoData.setPagamentoData(data);
        pagamentoData.setNmCpf(origem.getNmCpf());
        pagamentoData.setVlDeducao(origem.getVlDeducao());

        repository.save(pagamentoData);
    }

public List<ContratoSaldoInadimplente> recuperarSaldoInadimplente(String codigoEntidade, LocalDate dataAtual) {

        Optional<PessoaFisicaMatricula> optional = pessoaFisicaConsultaService.recuperarMatriculaPorEntidade(codigoEntidade.toString());

        PessoaFisicaMatricula pessoaFisicaMatricula = optional.orElseThrow(() ->
                new IllegalArgumentException(mensagemComponent.getMessage("pessoa.fisica.entidade.nao.encontrada", codigoEntidade)));

        List<Contrato> contratos = contratoService.recuperarNuContratosPorMatricula(pessoaFisicaMatricula.getNmMatricula());
        List<ContratoSaldoInadimplente> contratosSaldoInadimplentes = new ArrayList<ContratoSaldoInadimplente>();

        for (Contrato contrato : contratos) {
            repository.findByContratoCoSeqContrato(contrato.getCoSeqContrato())
                    .ifPresent(contratosSaldoInadimplentes::add);
        }

        return contratosSaldoInadimplentes;
    }
	
}
