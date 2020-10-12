package G6.PS.Ecommerce.services;

import java.util.List;

import G6.PS.Ecommerce.models.ComentarioModel;



public interface IComentarioService {
	
	public List<ComentarioModel> getAll();

	public ComentarioModel insertOrUpdate(ComentarioModel comentario);

	public ComentarioModel listarId(int id);

    public String delete(int id);
    
    public List<ComentarioModel> getByProducto(int id);
}
