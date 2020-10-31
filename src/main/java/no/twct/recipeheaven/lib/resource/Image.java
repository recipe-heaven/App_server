package no.twct.recipeheaven.lib.resource;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.lib.store.Item;

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

	@JoinColumn(name = "owner", referencedColumnName = "id", nullable = true)
	@ManyToOne
	private Item owner;

}