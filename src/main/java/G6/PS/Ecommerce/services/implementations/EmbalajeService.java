package G6.PS.Ecommerce.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.converters.EmbalajeConverter;
import G6.PS.Ecommerce.entities.Embalaje;
import G6.PS.Ecommerce.models.EmbalajeModel;
import G6.PS.Ecommerce.repositories.IEmbalajeRepository;
import G6.PS.Ecommerce.services.IEmbalajeService;

@Service("embalajeService")
public class EmbalajeService implements IEmbalajeService {
	

	@Autowired
	private IEmbalajeRepository embalajeRepository;
	
	@Autowired
	@Qualifier("embalajeConverter")
	private EmbalajeConverter embalajeConverter;
	
	@Override
	public List<EmbalajeModel> getAll() {
		List<EmbalajeModel> embalajes = new ArrayList<EmbalajeModel>();
		List<Embalaje> emb = embalajeRepository.findAll();
		for (Embalaje e: emb) {
			embalajes.add(embalajeConverter.entityToModel(e));
		}
		return embalajes;
	}

}
