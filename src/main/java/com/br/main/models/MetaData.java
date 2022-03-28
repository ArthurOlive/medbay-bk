package com.br.main.models;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MetaData implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="meta_data_generator")
	@SequenceGenerator(name="meta_data_generator", sequenceName="meta_data_seq", allocationSize=1)
    private long id;
    private String key;
    private String value;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Profile profile;

    public void setId(long id) {
        this.id = id;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public long getId() {
        return id;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }

}
