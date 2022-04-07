package service.aplication.dto.bolao;

public class BoloesOrdenados implements Comparable<BoloesDTO> {
    @Override
    public int compareTo(BoloesDTO dto) {
        return Integer.compare(dto.getQtdMembros(),dto.getQtdMembros());
    }

}