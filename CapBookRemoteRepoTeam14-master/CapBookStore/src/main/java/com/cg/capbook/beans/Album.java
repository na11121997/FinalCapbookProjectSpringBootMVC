package com.cg.capbook.beans;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//abcd
public class Album {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int AlbumId;
	//@OneToMany(mappedBy="album")
	//private List<Photo> photos;
	
}
