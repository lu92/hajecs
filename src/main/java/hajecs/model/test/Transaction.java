package hajecs.model.test;

import javax.persistence.*;

/**
 * Created by lucjan on 11.05.15.
 */
@Entity
public class Transaction
{
    @Id @GeneratedValue
    private long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "derived_id", nullable = true)
    private Transaction derivedFrom;



}
