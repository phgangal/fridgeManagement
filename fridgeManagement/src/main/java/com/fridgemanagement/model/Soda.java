package com.fridgemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table("soda")
public class Soda {
    @PrimaryKey
    Integer sodaId;
    @Column("fridgeId")
    Integer fridgeId;
    @Column("sodaBrandName")
    String sodaBrandName;

    /**
     * Emplty Constructor
     *
     * @return Soda
     */
    public Soda() {
    }


    /**
     * Constructor
     *
     * @return
     */
    public Soda(Integer sodaId, Integer fridgeId, String sodaBrandName) {
        this.sodaId = sodaId;
        this.fridgeId = fridgeId;
        this.sodaBrandName = sodaBrandName;
    }

    public int getSodaId() {
        return sodaId;
    }

    public void setSodaId(int sodaId) {
        this.sodaId = sodaId;
    }

    public int getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(int fridgeId) {
        this.fridgeId = fridgeId;
    }

    public String getSodaBrandName() {
        return sodaBrandName;
    }

    public void setSodaBrandName(String sodaBrandName) {
        this.sodaBrandName = sodaBrandName;
    }

}
