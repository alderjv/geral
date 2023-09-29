public class ParcelaService {

   @Transactional(readOnly = true)
    public List<ParcelaDTO> recuperarParcelasAbertasIndimplentes(Integer coContratoAtena, Integer anoContratoAtena, LocalDate ddAtual) {

        List<MovimentoDTO> movimentos = repository.findAllParcelasEmAberto(coContratoAtena, anoContratoAtena, ddAtual);

        return movimentos.stream().map(this::getParcelasDTO).collect(Collectors.toList());
    }

    private ParcelaDTO getParcelasDTO(MovimentoDTO movimentos) {
        return null;
    }

       public Boolean existeDebitoParaMatricula(String matricula) {

        List<Contrato> contratos = contratoRepository.findByNmMatriculaUsuarioResponsavel(matricula);

        Preconditions.checkState(!contratos.isEmpty(),
                mensagemComponent.getMessage("contrato.nao.encontrado.para.matricula", matricula));

        return contratos.stream().anyMatch(this::existeDebito);
    }

    private Boolean existeDebito(Contrato contrato) {

        Integer parcelasEmAtraso = repository.findQuantidadeParcelasAtraso(contrato.getCoSeqContrato());

        return parcelasEmAtraso > 0;
    }

}
