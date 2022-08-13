package sn.mapenda.annonces.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import sn.mapenda.annonces.Entity.Annonce;
import sn.mapenda.annonces.Repository.AnnonceRepository;
import sn.mapenda.annonces.Service.AnnonceService;


@CrossOrigin("*")
@RestController

public class AnnonceController {
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private AnnonceService annonceservice;
	@Autowired
	AnnonceRepository annoncerepository;
	@PostMapping("/saveAnnonce")	
	public Annonce saveAnnonce(@RequestParam("annonce") String annonce, @RequestParam("file") MultipartFile file) 
			throws   IOException{
				Annonce annonce1 = new ObjectMapper().readValue(annonce, Annonce.class);			
				String filename= file.getOriginalFilename();
				String newFileName= FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
			    File servrFile = new File(context.getRealPath("/images/"+File.separator+newFileName));
			    try {
			    	System.out.println("Image");
			    	FileUtils.writeByteArrayToFile(servrFile, file.getBytes());
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			    annonce1.setPhoto(newFileName);
			    
					return  annoncerepository.save(annonce1);
			
		}
	
	@GetMapping("/listeannonces")
	 public List<Annonce> list() {
		 System.out.println("Toutes les annonce...");
	             return annonceservice.listerannonce();
	   }
	
	@GetMapping("/listeannonce/{id}")
	public ResponseEntity<Annonce> getAnnonceById(@PathVariable("id") Long id){
		Annonce annonce=annoncerepository.getById(id);
		return new ResponseEntity<>(annonce,HttpStatus.OK);
	}

}
