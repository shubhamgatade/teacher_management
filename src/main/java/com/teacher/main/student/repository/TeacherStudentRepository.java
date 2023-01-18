package com.teacher.main.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teacher.main.student.entity.TeacherStudent;
import com.teacher.main.teacher.interfaces.StudentInterface;

@Repository
public interface TeacherStudentRepository extends JpaRepository<TeacherStudent, Integer>{

	@Query(value = "select sd.id, sd.name, sd.age, sd.gender, sd.email, sd.attendance, sd.status from teacher_student_details tsd inner join student_details sd "
			+ "on tsd.student_id =sd.id where tsd.teacher_id = :id", nativeQuery = true)
	List<StudentInterface> getStudentsByTeacherId(@Param("id") Integer id);
}
