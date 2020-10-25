package G6.PS.Ecommerce.helpers;

import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;

import G6.PS.Ecommerce.models.ProductoModel;
import G6.PS.Ecommerce.models.SubCategoriaModel;
import G6.PS.Ecommerce.models.AtributosModel;
import G6.PS.Ecommerce.models.CategoriaModel;

import G6.PS.Ecommerce.services.ISubCategoriaService;

public class ExcelHelper {

	@Autowired
	@Qualifier("subCategoriaService")
	private ISubCategoriaService subCategoriaService;

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "DescripcionCorta", "DescripcionLarga", "urlImagen", "sku", "precio", "destacado",
			"visible", "subcategoria", "categoria", "atributo", "valor", "atributo", "valor", "atributo", "valor",
			"atributo", "valor" };
	static String SHEET = "Productos";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<ProductoModel> excelToProductos(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);
			DataFormatter formatter = new DataFormatter();
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<ProductoModel> productos = new ArrayList<ProductoModel>();
			List<AtributosModel> atributos = new ArrayList<AtributosModel>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				ProductoModel producto = new ProductoModel();

				producto.setProdAtributos(atributos);

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					String aux = null;
					AtributosModel atr = new AtributosModel();
					switch (cellIdx) {
					case 0:
						aux = formatter.formatCellValue(currentCell);
						producto.setDescripcionCorta(aux);
						break;

					case 1:
						aux = formatter.formatCellValue(currentCell);
						producto.setDescripcionLarga(aux);
						break;

					case 2:
						aux = formatter.formatCellValue(currentCell);
						producto.setUrlImagen(aux);
						break;
					case 3:
						aux = formatter.formatCellValue(currentCell);
						producto.setSku(aux);
						break;
					case 4:
						aux= formatter.formatCellValue(currentCell);
						producto.setPrecio(Float.valueOf(aux)); 
						break;
					case 5:
						aux= formatter.formatCellValue(currentCell);
						producto.setDestacado(Boolean.getBoolean(aux));
						break;
					case 6:
						aux= formatter.formatCellValue(currentCell);
						producto.setDestacado(Boolean.getBoolean(aux));
						break;
					case 7:
						aux = formatter.formatCellValue(currentCell);
						producto.setSubCategoria(new SubCategoriaModel(aux));
						// subcategoria.setId((int) currentCell.getNumericCellValue()); si la
						// subcategoria no existe deberia generar el id automaticamente
						break;
					case 8:
						aux = formatter.formatCellValue(currentCell);
						producto.getSubCategoria().setCategoria(new CategoriaModel(aux));
						break;
					case 9:
						aux = formatter.formatCellValue(currentCell);
						atr.setAtributo(aux);
						currentCell = cellsInRow.next();
						aux = formatter.formatCellValue(currentCell);
						atr.setAtributo(aux);
					
						break;

					case 10:
						aux = formatter.formatCellValue(currentCell);
						atr.setAtributo(aux);
						currentCell = cellsInRow.next();
						aux = formatter.formatCellValue(currentCell);
						atr.setAtributo(aux);
						break;
					case 11:
						aux = formatter.formatCellValue(currentCell);
						atr.setAtributo(aux);
						currentCell = cellsInRow.next();
						aux = formatter.formatCellValue(currentCell);
						atr.setAtributo(aux);
						cellIdx++;
						break;

					case 12:
						aux = formatter.formatCellValue(currentCell);
						atr.setAtributo(aux);
						currentCell = cellsInRow.next();
						aux = formatter.formatCellValue(currentCell);
						atr.setAtributo(aux);
						cellIdx++;
						break;
					default:
						break;
					}

					cellIdx++;
				}
				System.out.println(producto.toString());
				productos.add(producto);
			}

			workbook.close();

			return productos;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}

}
