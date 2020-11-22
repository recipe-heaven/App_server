package no.twct.recipeheaven.resources.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Represents a storeable image.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "images")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;

	private String name;

	@Column(name = "mime_type")
	private String mimeType;

	private float size;


}
