package SDC.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Min(value = 1, message = "{product.price.min}")
	private int price;
	
	private String imageURL;
	
	@NotEmpty(message = "{product.name.notempty}")
	@Size(min = 3, message = "{product.name.size}")
	private String name;
	
	@Size(min = 3, message = "{product.description.size}")
	private String description;

}
