package com.latihan.tugas6.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

//@Data
//public class Karyawan {
//    private long id;
//    private String name;
//    private String jk;
//    private String dob;
//    private String alamat;
//    private String status;
//}


@Data
@Entity
@Table(name = "karyawan")
@Where(clause = "deleted_date is null")
public class Karyawan implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nama", nullable = false, length = 100)
    private String nama;

    @Column(name = "jk", nullable = false, length = 15)
    private String jk;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dob", nullable = false)
    private java.sql.Date dob;

    @Column(name = "alamat", nullable = false, length = 500)
    private String alamat;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "created_date")
    private java.util.Date created_date;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "updated_date")
    private java.util.Date updated_date;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "deleted_date")
    private java.util.Date deleted_date;

}