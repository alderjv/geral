import java.util.concurrent.CompletableFuture;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AsyncExample {

    @SneakyThrows
    private void executar(List<Origem> origens) {

        List<CompletableFuture<Void>> rotinaFutures = Lists.newArrayList();

        for (Origem origem : origens) {
            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> processamento(loteOrigem));
            rotinaFutures.add(completableFuture);
        }

        CompletableFuture.allOf(rotinaFutures.toArray(new CompletableFuture[origens.size()])).get();

        origens.forEach(repository::save);
    }
	
	private void processaLoteOrigem(LoteOrigem loteOrigem) {
		// Processamento em threads
	}
	
}
