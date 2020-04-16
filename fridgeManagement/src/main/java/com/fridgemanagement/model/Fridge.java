package com.fridgemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table("fridge")
public class Fridge {

    @PrimaryKey
    @Column
    int fridgeId;
    @Column("fridgeName")
    String fridgeName;
    @Column("sodasInFridge")
    List<Integer> sodaList;

    /**
     * Emplty Constructor.
     */
    public Fridge() {
    }


    /**
     * Constructor
     *
     * @return
     */
    public Fridge(Integer fridgeId, String fridgeName, List<Integer> sodas) {
        this.fridgeId = fridgeId;
        this.fridgeName = fridgeName;
        this.sodaList = sodas;
    }

    public int getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(int fridgeId) {
        this.fridgeId = fridgeId;
    }

    public String getFridgeName() {
        return fridgeName;
    }

    public void setFridgeName(String fridgeName) {
        this.fridgeName = fridgeName;
    }

    public List<Integer> getSodaList() {
        return sodaList;
    }

    public void setSodaList(List<Integer> sodaList) {
        this.sodaList = sodaList;
    }

}
