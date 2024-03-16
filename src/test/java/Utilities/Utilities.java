package Utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;

public class Utilities {
	public static void createDocxFile(WebDriver driver, String nombreArchivo, String rutaImagen) throws IOException, InvalidFormatException {
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File imageFile = new File(rutaImagen);
		FileUtils.copyFile(screen, imageFile);
		
		File fichero = new File(nombreArchivo); 
		 XWPFDocument docx;
		 if (!fichero.exists()) {
			 docx = new XWPFDocument();
			 
		 } else {
			 FileInputStream ficheroStream = new FileInputStream(fichero);
			 docx = new XWPFDocument(ficheroStream);
			 
		 }
		 XWPFParagraph paragraph = docx.createParagraph();
		 XWPFRun run = paragraph.createRun();
		 
		 run.setText("Evidencia de pruebas");
		 run.setFontSize(13);
		 
		 InputStream pic = new FileInputStream(rutaImagen);
		 run.addPicture(pic, Document.PICTURE_TYPE_PNG, rutaImagen,
		 Units.toEMU(500), Units.toEMU(200));
		 pic.close();

		 FileOutputStream out = new FileOutputStream(nombreArchivo);
		 docx.write(out);
		 out.flush();
		 out.close();
		 docx.close();
 
    }
	public static void takeScreenshot(WebDriver driver, String imgPath) throws IOException, InvalidFormatException {
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File (imgPath));
		
	}
	public static Object[][] readFromExcelFile(String ruta, String nombreHoja) throws Exception {
		FileInputStream file = new FileInputStream(new File(ruta));
		//instancio archivo excel desde el archivo situado en la ruta indicada
		XSSFWorkbook excelFile = new XSSFWorkbook(file);
		//instancio una hoja en base al archivo excel y asignando el nombre de la hoja
		XSSFSheet sheet = excelFile.getSheet(nombreHoja);
		
		//instanciamos una fila
		XSSFRow row;
		
		//tomamos el numero total de filas
		int rows = sheet.getPhysicalNumberOfRows();
		//instanciamos las columnas
		int column = sheet.getRow(0).getPhysicalNumberOfCells();
		
		//instanciamos el objeto bidimensional que nos va a devolver esta funcion
		Object cellValue[][]=new Object[rows][column];
		
		for (int r = 0; r < rows; r++) {
			row = sheet.getRow(r);

			if (row == null){ 
				break; 
			}else{ 
				for (int c = 0; c < column; c++) {
					DataFormatter dataFormatter = new DataFormatter();
					cellValue[r][c] = dataFormatter.formatCellValue(sheet.getRow(r).getCell(c));
					
				} 
				
			}
			
	   }
	   excelFile.close();
	   return cellValue; 
	
    }
	public static void downloadFile(String wget_command) {
        try {
        @SuppressWarnings("deprecation")
			Process exec = Runtime.getRuntime().exec(wget_command);
        	int exitVal = exec.waitFor();
        	System.out.println("Exit value: " + exitVal);
        
        } catch (Exception ex) {
        	System.out.println(ex.toString());
        
        }
	}
	public static void devToolsCreateSession(WebDriver driver ) {
		DevTools devTools = ((HasDevTools) driver).getDevTools();
		devTools.createSession();

	}
	public static void setMobileMetrics(WebDriver driver, Object width, Object height) {
		Map<String, Object> deviceMetrics = new HashMap<>();
		deviceMetrics.put("width", width);
		deviceMetrics.put("height", height);
		deviceMetrics.put("mobile", true);
		deviceMetrics.put("deviceScaleFactor", 50);

		((ChromiumDriver) driver).executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
	}
}
