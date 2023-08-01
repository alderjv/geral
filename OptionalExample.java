
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class R4010NormalizacaoPagamentosService {
	
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
	
}
