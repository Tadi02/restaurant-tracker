package restaurant.domain;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

}
