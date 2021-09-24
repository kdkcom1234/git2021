package com.git.myworkspace.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// photo ���̺� �����ϴ� ��ü

// PhotoRepository -�� JpaRepository -�� PagingAndSortingRepository -�� CrudRepository
// JpaRepository���� ������ ó���� ���� �⺻���� �޼������ ����Ǿ�����
// JpaRepository<Photo, Long>
// JpaRepository<��ƼƼŸ��, idŸ��>
// ��ƼƼ(SE, �����Ͱ�ü) == ���̺�(DB, �����Ͱ�ü)

// photo ���̺� ������ �� �ִ� �⺻���� �޼������ ����� �� ����

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
