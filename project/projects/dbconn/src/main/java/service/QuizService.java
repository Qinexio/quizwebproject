package service;

import org.hibernate.query.Query;

import dao.QuizDAO;
import entity.Detail;
import entity.Quiz;

public class QuizService {
//service classes are used to handle the sessions and transaction behavior
//statics are used to ensure there is a common object used among all service classes, if more (see score)

	private static QuizDAO quizDAO;

	public QuizService() {
		quizDAO = new QuizDAO();

	}

	public Quiz createquiz(Quiz toCreate, Detail toAppend) {
		quizDAO.getSessionUT().openSessionWithTransaction();
		toCreate.setQuizDetail(toAppend);
		toAppend.setDetailQuiz(toCreate);
		quizDAO.createEntity(toCreate);
		quizDAO.getSessionUT().closeSessionWithTransaction();
		return toCreate;
	}

	public Quiz updatequiz(Quiz toUpdate) {
		quizDAO.getSessionUT().openSessionWithTransaction();
		quizDAO.updateEntity(toUpdate);
		quizDAO.getSessionUT().closeSessionWithTransaction();
		return toUpdate;
	}
	
	public void validateQuiz(Quiz toValidate)
	{
		quizDAO.getSessionUT().openSessionWithTransaction();
		toValidate.setValidation(true);
		quizDAO.updateEntity(toValidate);
		quizDAO.getSessionUT().closeSessionWithTransaction();
	}
	
	public void deleteQuiz(int ID)
	{
		quizDAO.getSessionUT().openSessionWithTransaction();
		Query<?> hibernateQuery = quizDAO.getSessionUT().getSession().createQuery("from Quiz as quiz where quiz.id = ?");
		Quiz toDelete = (Quiz) hibernateQuery.setParameter(0, ID);
		quizDAO.deleteEntity(toDelete);
		quizDAO.getSessionUT().closeSessionWithTransaction();
	}
	
	

}
