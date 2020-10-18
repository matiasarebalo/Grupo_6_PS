package G6.PS.Ecommerce.helpers;

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
import G6.PS.Ecommerce.services.ISubCategoriaService;
import G6.PS.Ecommerce.services.implementations.SubCategoriaService;


public class ExcelHelper {

@Autowired
@Qualifier("subCategoriaService")
private   ISubCategoriaService subCategoriaService;
	
	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "DescripcionCorta", "DescripcionLarga", "Subcategoria", "urlImagen","sku","precio","destacado","visible" };
	  static String SHEET = "Productos";
	  

	  public static boolean hasExcelFormat(MultipartFile file) {

		    if (!TYPE.equals(file.getContentType())) {
		      return false;	
		    }

		    return true;
		  }
	  


		  public static  List<ProductoModel> excelToProductos(InputStream is) {
		    try {
		      Workbook workbook = new XSSFWorkbook(is);

		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();

		      List<ProductoModel> productos = new ArrayList<ProductoModel>();

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

		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();

		          switch (cellIdx) {
		          case 0:
		            producto.setDescripcionCorta(currentCell.getStringCellValue());
		            break;

		          case 1:
		            producto.setDescripcionLarga(currentCell.getStringCellValue());
		            break;

		          case 2:
			            producto.setSubCategoria(new SubCategoriaModel((int) currentCell.getNumericCellValue()));;

		            break;

		          case 3:
			            producto.setUrlImagen(currentCell.getStringCellValue());        	  
		            break;
		          case 4:
		        	  	producto.setSku(currentCell.getStringCellValue());
			            break;
		          case 5:
			            producto.setPrecio((float) currentCell.getNumericCellValue());
			            break;      
		          case 6:
		        	  	producto.setDestacado(currentCell.getBooleanCellValue());
		        	  	break;
		          case 7:
		        	  	producto.setVisible(currentCell.getBooleanCellValue());
		        	  	break;
		          default:
		            break;
		          }

		          cellIdx++;
		        }

		        productos.add(producto);
		      }

		      workbook.close();

		      return productos;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
		  }

}
