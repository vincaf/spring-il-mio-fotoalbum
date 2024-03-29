package org.generation.italy.demo.pojo;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
	
	@ManyToMany
	@JsonIgnore
	private List<Category> categories;
	
	@OneToMany(mappedBy = "photo", cascade = CascadeType.REMOVE)
	private List<Comment> comments;

	public Photo() { }
	public Photo(String title, String description, String url, String tag, boolean visible, List<Category> categories) {
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setTag(tag);
		setVisible(visible);
		setCategories(categories);
	}
	public Photo(String title, String description, String url, String tag, boolean visible, Category category) {
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setTag(tag);
		setVisible(visible);
		addCategory(category);
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
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addCategory(Category category) {
		boolean finded = false;
		for (Category c : getCategories())
			if (c.getId() == category.getId())
				finded = true;
		if(!finded)
			getCategories().add(category);
	}
	
	public void removeCategory(Category category) {
		getCategories().remove(category);
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@Override
	public String toString() {
		return "(" + getId() + ") " + getTitle() + "\n" 
				+ getDescription() + "\n" 
				+ getTag() + " - " + isVisible() + "\n" 
				+ getUrl() + "\n \t"
						+ getCategories() + "\n"
						+ getComments();
	}
}