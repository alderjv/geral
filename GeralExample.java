public class ParcelaService {

   @Transactional(readOnly = true)
    public List<ParcelaDTO> recuperarParcelasAbertasIndimplentes(Integer coContratoAtena, Integer anoContratoAtena, LocalDate ddAtual) {

        List<MovimentoDTO> movimentos = repository.findAllParcelasEmAberto(coContratoAtena, anoContratoAtena, ddAtual);

        return movimentos.stream().map(this::getParcelasDTO).collect(Collectors.toList());
    }

    private ParcelaDTO getParcelasDTO(MovimentoDTO movimentos) {
        return null;
    }

}
