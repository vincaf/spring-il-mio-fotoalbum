package org.generation.italy.demo;

import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FotoAlbumApplication implements CommandLineRunner {

	@Autowired
	private PhotoService photoService;
	
	public static void main(String[] args) {
		SpringApplication.run(FotoAlbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Photo photo1 = new Photo("Gatto concentrato", "Il gatto mi guarda intensamente", "https://www.repstatic.it/content/contenthub/img/2022/12/16/214504426-90ada51f-a4da-4e81-9fae-28b03e702fde.jpg", "gatto", true);
		Photo photo2 = new Photo("Final Fantasy 7 - Crisis Core", "È appena uscito il nuovo gioco di FFVII!", "https://image.api.playstation.com/vulcan/ap/rnd/202208/2505/DE9sevLlnfHm7vLrRwDFEZpO.png", "finalfantasy", true);
		Photo photo3 = new Photo("Bleach TYBW", "Questa nuova stagione di Bleach è spettacolare!!!", "https://img1.ak.crunchyroll.com/i/spire4/24fbbab5b6ec5ae872466640757786ef1671274735_main.jpg", "bleach", true);
		Photo photo4 = new Photo("Messi Campione del mondo", "L'Argentina batte la Francia e Messi conquista la coppa", "https://www.tvplay.it/wp-content/uploads/2022/12/Lionel-Messi-20221219-TvPlay.jpg", "messi", false);

		photoService.save(photo1);
		photoService.save(photo2);
		photoService.save(photo3);
		photoService.save(photo4);

		System.err.println(photoService.findAll());

	}
}
