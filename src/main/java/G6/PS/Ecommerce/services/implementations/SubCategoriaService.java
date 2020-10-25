package G6.PS.Ecommerce.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import G6.PS.Ecommerce.converters.SubCategoriaConverter;
import G6.PS.Ecommerce.entities.SubCategoria;
import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;
import G6.PS.Ecommerce.repositories.ISubCategoriaRepository;
import G6.PS.Ecommerce.services.ICategoriaService;
import G6.PS.Ecommerce.services.IProductoService;
import G6.PS.Ecommerce.services.ISubCategoriaService;

@Service("subCategoriaService")
public class SubCategoriaService implements ISubCategoriaService {

	@Autowired
	@Qualifier("categoriaService")
	private ICategoriaService categoriaService;

	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;

	@Autowired
	private ISubCategoriaRepository subCategoriaRepository;

	@Autowired
	@Qualifier("subCategoriaConverter")
	private SubCategoriaConverter subCategoriaConverter;

	@Override
	public List<SubCategoriaModel> getAll() {
		List<SubCategoriaModel> subCategorias = new ArrayList<SubCategoriaModel>();
		List<SubCategoria> sC = subCategoriaRepository.findAll();
		for (SubCategoria s : sC) {
			subCategorias.add(subCategoriaConverter.entityToModel(s));
		}
		return subCategorias;
	}

	@Override
	public SubCategoriaModel insertOrUpdate(SubCategoriaModel model) {
		SubCategoria s = subCategoriaRepository.save(subCategoriaConverter.modelToEntity(model));
		return subCategoriaConverter.entityToModel(s);
	}

	@Override
	public SubCategoriaModel listarId(int id) {
		return subCategoriaConverter.entityToModel(subCategoriaRepository.findById(id));

	}

	@Override
	public String delete(int id) {
		subCategoriaRepository.deleteById(id);
		return "subCategoria Eliminada";
	}

	@Override
	public List<SubCategoriaModel> getSubcategoriasByCategoria(int idCategoria) {
		List<SubCategoriaModel> subCategorias = new ArrayList<SubCategoriaModel>();
		for (SubCategoriaModel s : this.getAll()) {
			if (s.getCategoria().getId() == idCategoria) {
				subCategorias.add(s);
			}
		}
		return subCategorias;
	}

	@Override
	public void deleteDependencies(int idSubcategoria) {
		SubCategoriaModel sModel = listarId(idSubcategoria);
		List<ProductoModel> productos = productoService.findBySubCategoria(idSubcategoria);
		if (productos.isEmpty()) {
			delete(idSubcategoria);
			int idCategoria = sModel.getCategoria().getId();
			if (getSubcategoriasByCategoria(idCategoria).isEmpty()) {
				categoriaService.delete(idCategoria);
			}
		}

	}

	@Override
	public SubCategoriaModel traerPorNombre(String nombre) {
		SubCategoria sC = subCategoriaRepository.findBynombre(nombre);
		if (sC != null) {
			return subCategoriaConverter.entityToModel(sC);
		} else {
			return null;
		}
	}

}
