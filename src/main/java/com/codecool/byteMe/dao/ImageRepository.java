package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {
}
