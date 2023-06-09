package kimjinung.commerce.domain;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
public class Item {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "item_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "uuid")
    private Member seller;
    private String name;
    private Integer price;
    private Integer stockQuantity;

    protected Item() {
    }

    public Item(String name, Integer price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Item(Member seller, String name, Integer price, Integer stockQuantity) {
        this.seller = seller;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void addStock(int stock) {
        this.stockQuantity += stock;
    }

    public void reduceStock(int stock) throws IllegalStateException{
        int restStock = this.stockQuantity - stock;

        if (restStock < 0) {
            throw new IllegalStateException("Limit stock quantity");
        } else {
            this.stockQuantity = restStock;
        }
    }

    public Integer getTotalPriceByCount(int count) {
        return this.price * count;
    }

    public void changeName(String name) {
        this.name = name;
    }
    public void changePrice(int price) {
        this.price = price;
    }

    public void changeStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
