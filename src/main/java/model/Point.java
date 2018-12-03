package model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "res", schema = "public", catalog = "postgres")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "x")
    private int x;

    @Basic
    @Column(name = "y")
    private double y;

    @Basic
    @Column(name = "r")
    private int r;

    @Basic
    @Column(name = "result")
    private boolean res;

    public Point(int r){
        this.r = r;
    }

    public Point(){}
}


