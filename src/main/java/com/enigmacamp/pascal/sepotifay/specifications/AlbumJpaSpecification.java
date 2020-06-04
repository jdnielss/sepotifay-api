package com.enigmacamp.pascal.sepotifay.specifications;

import com.enigmacamp.pascal.sepotifay.entities.Album;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class AlbumJpaSpecification {
  public static Specification<Album> findByCriteria(Album searchForm) {
    return new Specification<Album>() {
      @Override
      public Predicate toPredicate(Root<Album> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        final Collection<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(searchForm.getTitle())) {
          final Predicate predicate = criteriaBuilder.like(
              criteriaBuilder.lower(root.get("title")), '%' + searchForm.getTitle().toLowerCase() + '%'
          );

          predicates.add(predicate);
        }

        if (!StringUtils.isEmpty(searchForm.getDescription())) {
          final Predicate predicate = criteriaBuilder.like(
              criteriaBuilder.lower(root.get("description")), '%' + searchForm.getDescription().toLowerCase() + '%'
          );

          predicates.add(predicate);
        }

        if (!StringUtils.isEmpty(searchForm.getReleaseYear())) {
          final Predicate predicate = criteriaBuilder.equal(
              root.get("releaseYear"), searchForm.getReleaseYear()
          );

          predicates.add(predicate);
        }

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
      }
    };
  }
}
