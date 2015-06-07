package app.web.demo;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.web.AppBaseController;

/**
 * 矩阵变量
 *	matrix variable separated with a ";" (semicolon)
 *  URL中要包含矩阵变量，则URL必须满足URI template的要求；
 */
@Controller
public class HelloWorldController extends AppBaseController {
	
	// GET /cars/benz;color=red,green,blue;year=2012
	@RequestMapping(value="/cars/{brand}")
	public void findCar(@PathVariable String brand,
			@MatrixVariable String color,  @MatrixVariable int year) {
		info(currentMethod());
		logger.info("brand={}, color={}, year={}", brand, color, year);
	}
	
	// GET /cars2/benz;colors=red,green,blue;year=2012
	@RequestMapping(value="/cars2/{brand}")
	public void findCar(@PathVariable String brand,
			@MatrixVariable String[] colors,  @MatrixVariable int year) {
		info(currentMethod());
		logger.info(" colors={}, year={}",  colors, year);
	}
	
	// GET /pets/42
	// GET /pets/42;q=11;r=22
	@RequestMapping(value = "/pets/{petId}", method = RequestMethod.GET)
	public void findPet(@PathVariable String petId, 
			@MatrixVariable(required=false, defaultValue="1") int q) {
		// petId == 42
		// q == 11
		logger.info("petId={}", petId);
		logger.info("q={}", q);
	}
	
	// GET /owners/42;q=11/pets/21;q=22
	@RequestMapping(value = "/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET)
	public void findPet2(@PathVariable String ownerId, @PathVariable String petId,
			@MatrixVariable(value="q", pathVar="ownerId") int q1,
	        @MatrixVariable(value="q", pathVar="petId") int q2) {
		logger.info("owners={}", ownerId);
		logger.info("petId={}", petId);
		logger.info("q={}", q1);
		logger.info("q={}", q2);
	}
	
	// GET /owners2/42;q=11;r=12/pets/21;q=22;s=23
	@RequestMapping(value = "/owners2/{ownerId}/pets/{petId}", method = RequestMethod.GET)
	public void findPet(
	        @MatrixVariable Map<String, String> matrixVars,
	        @MatrixVariable(pathVar="petId") Map<String, String> petMatrixVars) {

	    // matrixVars: ["q" : [11,22], "r" : 12, "s" : 23]
	    // petMatrixVars: ["q" : 11, "s" : 23]
		
		info(matrixVars);
		info(petMatrixVars);
		
	}
}
