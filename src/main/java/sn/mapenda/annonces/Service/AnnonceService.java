package sn.mapenda.annonces.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.fasterxml.jackson.databind.ObjectMapper;

import sn.mapenda.annonces.Entity.Annonce;
import sn.mapenda.annonces.Repository.AnnonceRepository;



@Service
public class AnnonceService {
	@Autowired
	AnnonceRepository annoncerepository;
	
	@Autowired
	private ServletContext context;
	
	public List<Annonce> listerannonce(){
		
		List<Annonce> listannonce= annoncerepository.findAll();
		
		return listannonce;
		
	}
	
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
	
	public Annonce updateannonce(@PathVariable("id") Long idannonce,@RequestBody Annonce annonce) {
		annonce.setIdannonce(idannonce);
		annoncerepository.save(annonce);
		return annonce;
		
	}
	
	public void deleteannonce(@PathVariable("id") Long id) {
		annoncerepository.deleteById(id);
		
	}
	
	public ResponseEntity<Annonce> getAnnonceById(@PathVariable("id") Long id){
		Annonce annonce=annoncerepository.getById(id);
		return new ResponseEntity<>(annonce,HttpStatus.OK);
	}
		
	
	@GetMapping(path="/getImages/{id}")
	public byte[] getImages (@PathVariable ("id") Long id) throws IOException {
		Annonce annonce=annoncerepository.findById(id).get();
		
			return Files.readAllBytes(Paths.get(context.getRealPath("/images/")+annonce.getPhoto()));
		}
		

}
