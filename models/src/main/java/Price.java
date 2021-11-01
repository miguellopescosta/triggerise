import javax.persistence.*;

/* WE ARE NOT USING DATABASE AND AS SUCH, THIS IS A MERE EXAMPLE OF A MODE CLASS - EQUIVALENT TO A TABLE IN A SQL DB */
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="item_code", length=20)
    private String code;

    @Column(name="item_name", length=20)
    private String name;

    @Column(name="item_price", length=20)
    private Double price;

}
