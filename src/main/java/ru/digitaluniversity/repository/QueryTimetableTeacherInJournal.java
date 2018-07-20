//package ru.digitaluniversity.repository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//import ru.digitaluniversity.entity.Journal;
//import ru.digitaluniversity.entity.Timetable;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.sql.Time;
//import java.util.List;
//
//@Repository
//public class QueryTimetableTeacherInJournal {
//
//    @Autowired
//    private EntityManager entityManager;
//
//    public List<Journal> searchJournalByTimetableTeacher(Integer id){
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Journal> journalCriteriaQuery = builder.createQuery(Journal.class);
//        Root<Journal> journalRoot = journalCriteriaQuery.from(Journal.class);
//
//
//        CriteriaQuery<Timetable>  timetableCriteriaQuery = builder.createQuery(Timetable.class);
//        Root<Timetable> timetableRoot = timetableCriteriaQuery.from(Timetable.class);
//
//
//        Predicate equal = builder.equals(root.get(Customer_.birthday))
//    }
//
//}
