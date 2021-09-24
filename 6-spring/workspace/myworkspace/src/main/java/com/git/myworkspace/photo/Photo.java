package com.git.myworkspace.photo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

// Spring Data JPA(Java Persistence API, �ڹ� ����ȭ API)
// ����ȭ: �ֹ߼� ������ -> ���ֹ߼� ��ġ
//           �ڹ� ��ü(RAM) -> ���̺� ���ڵ�(���ϳ����� Ư����)

// ORM(Object Relational Mapping)
// : ��ü�� ���̺�� ������ �� ����
//  1. ��ü �������� ������ �� �ְ���(����Ʈ�������)
//  2. Ư�� DB�� ���ӵ��� �ʰ���

// @Entity: ���̺�� Ŭ������ ������
// �⺻����� Photo(pascal-case) -> photo(snake-case)

@Entity
public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	// BLOB: binary large object
	@Column(columnDefinition = "VARCHAR(1000)")
	private String description;
	// BLOB: binary large object
	@Column(columnDefinition = "TEXT")
	private String photoUrl;
	private String fileType;
	private String fileName;
	private long createdTime;
}
