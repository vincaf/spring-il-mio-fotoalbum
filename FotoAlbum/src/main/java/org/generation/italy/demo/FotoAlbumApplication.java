package org.generation.italy.demo;

import java.util.ArrayList;
import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FotoAlbumApplication implements CommandLineRunner {

	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	public static void main(String[] args) {
		SpringApplication.run(FotoAlbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category category1 = new Category("animali");
		Category category2 = new Category("divertente");
		Category category3 = new Category("anime e giochi");
		Category category4 = new Category("spettacolo");
		Category category5 = new Category("mondiali");


		List<Category> categoryList1 = new ArrayList<>();
		categoryList1.add(category1);
		categoryList1.add(category2);

		List<Category> categoryList2 = new ArrayList<>();
		categoryList2.add(category3);
		categoryList2.add(category4);
		
		List<Category> categoryList3 = new ArrayList<>();
		categoryList3.add(category5);

		categoryService.save(category1);
		categoryService.save(category2);
		categoryService.save(category3);
		categoryService.save(category4);
		categoryService.save(category5);
		
		Photo photo1 = new Photo("Gatto concentrato", "Il gatto mi guarda intensamente", "https://www.repstatic.it/content/contenthub/img/2022/12/16/214504426-90ada51f-a4da-4e81-9fae-28b03e702fde.jpg", "gatto", true, categoryList1);
		Photo photo2 = new Photo("Final Fantasy 7 - Crisis Core", "È appena uscito il nuovo gioco di FFVII!", "https://image.api.playstation.com/vulcan/ap/rnd/202208/2505/DE9sevLlnfHm7vLrRwDFEZpO.png", "finalfantasy", true, categoryList2);
		Photo photo3 = new Photo("Bleach TYBW", "Questa nuova stagione di Bleach è spettacolare!!!", "https://img1.ak.crunchyroll.com/i/spire4/24fbbab5b6ec5ae872466640757786ef1671274735_main.jpg", "bleach", true, categoryList2);
		Photo photo4 = new Photo("Messi Campione del mondo", "L'Argentina batte la Francia e Messi conquista la coppa", "https://www.tvplay.it/wp-content/uploads/2022/12/Lionel-Messi-20221219-TvPlay.jpg", "messi", false, categoryList3);

		photoService.save(photo1);
		photoService.save(photo2);
		photoService.save(photo3);
		photoService.save(photo4);

		System.err.println(photoService.findAllWithCategory());

	}
}
