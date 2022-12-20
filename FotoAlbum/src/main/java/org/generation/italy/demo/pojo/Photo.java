package org.generation.italy.demo.pojo;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "il titolo deve contenere qualcosa")
	@Column(unique = true)
	private String title;

	@Column
	@Lob
	private String description;

	@Column
	@NotEmpty(message = "l'immagine deve contenere qualcosa")
	@URL(message = "il percorso dell'immagine deve essere valido")
	private String url;

	@Column
	@NotEmpty(message = "il tag deve contenere qualcosa")
	private String tag;

	@Column
	@NotNull(message = "la visibilità deve essere impostata")
	private boolean visible;

	public Photo() { }
	public Photo(String title, String description, String url, String tag, boolean visible) {
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setTag(tag);
		setVisible(visible);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "(" + getId() + ") " + getTitle() + "\n" 
				+ getDescription() + "\n" 
				+ getTag() + " - " + isVisible() + "\n" 
				+ getUrl() + "\n";
	}
}