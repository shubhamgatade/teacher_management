package com.teacher.main.teacher.service;

import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.teacher.main.exception.BadRequestException;
import com.teacher.main.network.entity.Student;
import com.teacher.main.network.entity.Subject;
import com.teacher.main.network.service.NetworkService;
import com.teacher.main.redis.service.RedisService;
import com.teacher.main.student.entity.TeacherStudent;
import com.teacher.main.student.repository.TeacherStudentRepository;
import com.teacher.main.subject.entity.TeacherSubject;
import com.teacher.main.subject.repository.TeacherSubjectRepository;
import com.teacher.main.teacher.dto.TeacherDTO;
import com.teacher.main.teacher.entity.Teacher;
import com.teacher.main.teacher.interfaces.StudentInterface;
import com.teacher.main.teacher.interfaces.SubjectInterface;
import com.teacher.main.teacher.repository.TeacherRepository;
import com.teacher.main.utility.DateTimeHelper;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private NetworkService networkService;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private TeacherSubjectRepository teacherSubjectRepository;

	@Autowired
	private TeacherStudentRepository teacherStudentRepository;

	@Autowired
	private RedisService redisService;

	@Override
	public Teacher create(TeacherDTO teacherDTO) throws BadRequestException {

		if (Objects.nonNull(teacherDTO)) {
			Teacher teacher = convertToEntity(teacherDTO);
			String password = teacher.getPassword();
			teacher.setPassword(Base64.getEncoder().encodeToString(password.getBytes()));
			Teacher newTeacher = teacherRepository.save(teacher);
			redisService.setTeacherByIdInCache(newTeacher.getId(), newTeacher);

			if (!CollectionUtils.isEmpty(teacherDTO.getStudentIds())) {
				teacherDTO.getStudentIds().stream().forEach(s -> {
					TeacherStudent teacherStudent = new TeacherStudent();
					teacherStudent.setTeacherId(newTeacher.getId());
					teacherStudent.setStudentId(s);
					teacherStudentRepository.save(teacherStudent);
				});
			}

			if (!CollectionUtils.isEmpty(teacherDTO.getSubjectIds())) {
				teacherDTO.getSubjectIds().stream().forEach(s -> {
					TeacherSubject teacherSubject = new TeacherSubject();
					teacherSubject.setTeacherId(newTeacher.getId());
					teacherSubject.setSubjectId(s);
					teacherSubjectRepository.save(teacherSubject);
				});
			}
			return teacher;
		}
		throw new BadRequestException("EX105");
	}

	private Teacher convertToEntity(TeacherDTO teacherDTO) {

		if (Objects.nonNull(teacherDTO)) {
			return modelMapper.map(teacherDTO, Teacher.class);
		}
		return null;
	}

	private TeacherDTO convertToDTO(Teacher teacher) {

		if (Objects.nonNull(teacher)) {
			return modelMapper.map(teacher, TeacherDTO.class);
		}
		return null;
	}

	@Override
	public Teacher getById(Integer id) throws BadRequestException {

		if (id != null && id > 0) {
			Teacher teacherByIdFromCache = redisService.getTeacherByIdFromCache(id);
			if (Objects.isNull(teacherByIdFromCache)) {
				Optional<Teacher> teacherDetails = teacherRepository.findById(id);
				if (teacherDetails.isPresent()) {
					Teacher teacher = teacherDetails.get();
					redisService.setTeacherByIdInCache(id, teacher);
					return teacher;
				}
				return teacherByIdFromCache;
			}
		}
		throw new BadRequestException("EX108");
	}

	@Override
	public TeacherDTO getCompleteTeacherDetailsById(Integer id) throws BadRequestException {

		if (id != null && id > 0) {

			Teacher teacher = getById(id);
			TeacherDTO teacherDTO = convertToDTO(teacher);

			List<StudentInterface> studentsByTeacherId = teacherStudentRepository.getStudentsByTeacherId(id);
			if (!CollectionUtils.isEmpty(studentsByTeacherId))
				teacherDTO.setStudents(studentsByTeacherId);

			List<SubjectInterface> subjectsByTeacherId = teacherSubjectRepository.getSubjectsByTeacherId(id);
			if (!CollectionUtils.isEmpty(subjectsByTeacherId))
				teacherDTO.setSubjects(subjectsByTeacherId);

			List<Integer> studentIds = studentsByTeacherId.stream().map(StudentInterface::getId)
					.collect(Collectors.toList());
			teacherDTO.setStudentIds(studentIds);

			List<Integer> subjectIds = subjectsByTeacherId.stream().map(SubjectInterface::getId)
					.collect(Collectors.toList());
			teacherDTO.setSubjectIds(subjectIds);

			return teacherDTO;
		}
		throw new BadRequestException("EX108");
	}

	@Override
	public List<Teacher> getAllTeacherDetails() throws BadRequestException {

		List<Teacher> teacherListFromCache = redisService.getTeacherListFromCache();
		if (CollectionUtils.isEmpty(teacherListFromCache)) {
			List<Teacher> allTeacherDetails = teacherRepository.getAllTeacherDetails();
			if (!CollectionUtils.isEmpty(allTeacherDetails)) {
				redisService.setTeacherListInCache(allTeacherDetails);
				return allTeacherDetails;
			}
		}
		return teacherListFromCache;
	}

	@Override
	public List<Teacher> getAllActiveTeacherDetails() throws BadRequestException {

		List<Teacher> teacherListFromCache = redisService.getTeacherListFromCache();
		if (!CollectionUtils.isEmpty(teacherListFromCache)) {
			List<Teacher> activeTeacherDetails = teacherListFromCache.stream()
					.filter(teacher -> teacher.getStatus() == true).collect(Collectors.toList());
			if (!CollectionUtils.isEmpty(activeTeacherDetails)) {
				return activeTeacherDetails;
			}
		}
		return null;
	}

	@Override
	public String deleteById(Integer id) throws BadRequestException {
		Optional<Teacher> teacherDetails = teacherRepository.findById(id);
		if (teacherDetails.isPresent()) {
			Teacher teacher = teacherDetails.get();
			teacher.setStatus(false);
			teacherRepository.save(teacher);
			redisService.clearTeacherByIdFromCache(id);
			redisService.setTeacherByIdInCache(teacher.getId(), teacher);
			return "Teacher deleted successfully";
		}
		throw new BadRequestException("EX102");
	}

	@Override
	public Teacher updateById(Integer id, Teacher teacher) throws BadRequestException {

		if (id != null && id > 0) {

			Teacher existing = getById(id);

			if (Objects.nonNull(existing)) {
				if (!StringUtils.isEmpty(teacher.getName())) {
					existing.setName(teacher.getName());
				}
				if (teacher.getAge() != null && teacher.getAge() >= 18) {
					existing.setAge(teacher.getAge());
				} else {
					throw new BadRequestException("EX107");
				}
				if (!StringUtils.isEmpty(teacher.getUsername())) {
					existing.setUsername(teacher.getUsername());
				}
				if (!StringUtils.isEmpty(teacher.getPassword())) {
					existing.setPassword(teacher.getPassword());
				}
				existing.setUpdatedOn(DateTimeHelper.getCurrentDateTime());
				teacherRepository.save(existing);
				redisService.clearTeacherByIdFromCache(id);
				redisService.setTeacherByIdInCache(existing.getId(), existing);
				return existing;
			}
		}
		throw new BadRequestException("EX102");
	}

	@Override
	public Subject create(Subject subject) throws Exception {

		if (Objects.nonNull(subject)) {
			subject = networkService.createSubject(subject);
			return subject;
		}
		throw new BadRequestException("EX104");
	}

	@Override
	public Student create(Student student) throws Exception {

		if (Objects.nonNull(student)) {
			student = networkService.createStudent(student);
			return student;
		}
		throw new BadRequestException("EX106");
	}
}
