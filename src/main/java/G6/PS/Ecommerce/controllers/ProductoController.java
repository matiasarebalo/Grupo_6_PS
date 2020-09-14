package G6.PS.Ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import G6.PS.Ecommerce.services.ICategoriaService;
import G6.PS.Ecommerce.services.ISubCategoriaService;

public class ProductoController {
	

//	@Autowired
//	@Qualifier("productoService")
//	private IProductoService productoService;

	@Autowired
	@Qualifier("categoriaService")
	private ICategoriaService categoriaService;

	@Autowired
	@Qualifier("subCategoriaService")
	private ISubCategoriaService subCategoriaoService;


}
