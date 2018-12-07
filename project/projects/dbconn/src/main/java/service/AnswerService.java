package service;

import java.util.List;

import dao.AnswerDAO;
import entity.Answer;

//service classes use the same static object
//the idea behind this is that certain hibernate queries do not require transaction
public class AnswerService implements IDaoService<Answer,Long> {

	private static AnswerDAO answerDAO;

	public AnswerService() {
		answerDAO = new AnswerDAO();
	}

	@Override
	public List<Answer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Answer toCreate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Answer toUpdate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long idDelete) {
		// TODO Auto-generated method stub
		
	}
	
}
