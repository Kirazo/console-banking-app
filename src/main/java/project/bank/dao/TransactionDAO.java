package project.bank.dao;

public interface TransactionDAO {
	public void transLog(int id1, int id2, int amount);
	public void viewTransLog();
}
