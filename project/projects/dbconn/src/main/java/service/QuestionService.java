package service;

import org.hibernate.query.Query;

import dao.QuestionDAO;
import entity.Question;
import entity.Quiz;

public class QuestionService {
	
	private static QuestionDAO questionDAO;

	public QuestionService() {
		questionDAO = new QuestionDAO();
	}

	public Question createQuestion(Question toCreate, Quiz toAppend) {
		questionDAO.getSessionUT().openSessionWithTransaction();
		toCreate.setQuestionQuiz(toAppend);
		toAppend.getQuizQuestions().add(toCreate);
		questionDAO.createEntity(toCreate);
		questionDAO.getSessionUT().closeSessionWithTransaction();
		return toCreate;
	}

	public Question updateQuestion(Question toUpdate) {
		questionDAO.getSessionUT().openSessionWithTransaction();
		questionDAO.updateEntity(toUpdate);
		questionDAO.getSessionUT().closeSessionWithTransaction();
		return toUpdate;
	}
	
	public void deleteQuestion(int ID)
	{
		questionDAO.getSessionUT().openSessionWithTransaction();
		Query<?> hibernateQuery = questionDAO.getSessionUT().getSession().createQuery("from Question as question where question.id = ?");
		Question toDelete = (Question) hibernateQuery.setParameter(0, ID);
		questionDAO.deleteEntity(toDelete);
		questionDAO.getSessionUT().closeSessionWithTransaction();
	}

}
