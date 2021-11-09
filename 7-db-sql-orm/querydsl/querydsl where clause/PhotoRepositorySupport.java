package com.git.myworkspace.photo;

import static com.git.myworkspace.photo.QPhoto.photo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class PhotoRepositorySupport {

    private JPAQueryFactory query;

    @Autowired
    public PhotoRepositorySupport(EntityManager em){
        this.query = new JPAQueryFactory(em);
    }

    public List<Photo> searchByKeyword(String keyword){
        return query.selectFrom(photo)
                .where(photo.title.containsIgnoreCase(keyword)
                		.or(photo.title.containsIgnoreCase(keyword)))
                .orderBy(photo.id.desc())
                .fetch();
    }
}
