package Service;

import Entity.Serie;
import Repository.SerieRepository;
import java.util.List;

public class SerieService {
    public boolean Incluir(Serie serie) {
        SerieRepository serieRepository = new SerieRepository();
        return serieRepository.Incluir(serie);
    }
    
    public boolean Excluir(int serieId) {
        SerieRepository serieRepository = new SerieRepository();
        return serieRepository.Excluir(serieId);
    }
    
    public boolean Alterar(Serie serie) {
        SerieRepository serieRepository = new SerieRepository();
        return serieRepository.Alterar(serie);
    }
    
    public List<Serie> Listar() {
        SerieRepository serieRepository = new SerieRepository();
        return serieRepository.Listar();
    }
    
    public Serie ListarId(int serieId) {
        SerieRepository serieRepository = new SerieRepository();
        return serieRepository.ListarId(serieId);
    }
}
