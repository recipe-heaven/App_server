package no.twct.recipeheaven.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.twct.recipeheaven.resources.control.ImageObjectAdapter;
import no.twct.recipeheaven.resources.entity.Image;
import no.twct.recipeheaven.user.entity.User;

import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

/* Represents a sellable/buyable item in the store */
@Entity
@Table(name = "items")
@NamedQuery(name = Item.FIND_BY_SELLER, query = "SELECT i FROM Item i WHERE i.seller = :seller")
@NamedQuery(name = Item.DELETE_BY_ID, query = "DELETE FROM Item i WHERE i.id = :id AND i.seller = :seller")
@NamedQuery(name = Item.GET_ALL_DESC, query = "SELECT i FROM Item i ORDER BY i.id DESC")
@NamedQuery(name = Item.COUNT_TOTAL_ITEMS, query = "SELECT count(i.id) from Item i")
@Data
@NoArgsConstructor
public class Item implements Serializable {

    public static final String FIND_BY_SELLER = "Item.FindBySeller";
    public static final String DELETE_BY_ID = "Item.DeleteById";
    public static final String GET_ALL_DESC = "Item.GetPaginatedItems";
    public static final String COUNT_TOTAL_ITEMS = "Item.CountTotalItems";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private float price;

    private String name;

    private String description;

    @JsonbTypeAdapter(ImageObjectAdapter.class)
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Image> image;

    private boolean sold;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updated;

    @OneToOne
    @JoinColumn(name = "seller", referencedColumnName = "id", nullable = true)
    private User seller;

    @OneToOne
    @JoinColumn(name = "buyer", referencedColumnName = "id", nullable = true)
    private User buyer;

    public Item(String name, String description, float price, User seller) {
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setSeller(seller);
    }

    @PrePersist
    protected void onCreate() {
        this.created = new Date();
        this.updated = new Date();
    }

}