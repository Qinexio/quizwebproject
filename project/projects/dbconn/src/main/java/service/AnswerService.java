package service;

import dao.AnswerDAO;
import entity.Answer;
import entity.Question;

public class AnswerService {

	private static AnswerDAO answerDAO;

	public AnswerService() {
		answerDAO = new AnswerDAO();
	}

	public Answer createAnswer(Answer toCreate, Question toAppend) {
		answerDAO.getSessionUT().openSessionWithTransaction();
		toCreate.setAnswerQuestion(toAppend);
		toAppend.getQuestionAnswers().add(toCreate);
		answerDAO.createEntity(toCreate);
		answerDAO.getSessionUT().closeSessionWithTransaction();
		return toCreate;
	}

	public Answer updateAnswer(Answer toUpdate) {
		answerDAO.getSessionUT().openSessionWithTransaction();
		answerDAO.updateEntity(toUpdate);
		answerDAO.getSessionUT().closeSessionWithTransaction();
		return toUpdate;
	}
	
}
