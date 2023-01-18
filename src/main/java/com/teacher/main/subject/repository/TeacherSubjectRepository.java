package com.teacher.main.subject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teacher.main.subject.entity.TeacherSubject;
import com.teacher.main.teacher.interfaces.SubjectInterface;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Integer> {

	@Query(value = "select sd.* from subject_details sd inner join teacher_subject_details tsd "
			+ "on sd.id = tsd.subject_id where tsd.teacher_id = :id", nativeQuery = true)
	List<SubjectInterface> getSubjectsByTeacherId(@Param("id") Integer id);
}
