package server.custom.forum;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tools.FileoutputUtil;

public class Forum_Reply {

	private int replyId;
	private int threadId;
	private int characterId;
	private String characterName;
	private String releaseTime;
	private String news;
	private static ArrayList<Forum_Reply> allReply = new ArrayList<Forum_Reply>();
	
	public Forum_Reply() {

	}

	public Forum_Reply(int replyId, int threadId, int characterId, String characterName, String releaseTime, String news) {
		this.replyId = replyId;
		this.threadId = threadId;
		this.characterId = characterId;
		this.characterName = characterName;
		this.releaseTime = releaseTime;
		this.news = news;
	}
	
	public int getReplyId() {
		return replyId;
	}
	
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	
	public int getThreadId() {
		return threadId;
	}
	
	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}
	
	public int getCharacterId() {
		return characterId;
	}
	
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
	
	public String getCharacterName() {
		return characterName;
	}
	
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	
	public String getReleaseTime() {
		return releaseTime;
	}
	
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	public String getNews() {
		return news;
	}
	
	public void setNews(String news) {
		this.news = news;
	}

	public static ArrayList<Forum_Reply> getAllReply() {
		return allReply;
	}

	public static void setAllReply(ArrayList<Forum_Reply> allReply) {
		Forum_Reply.allReply = allReply;
	}
	
	public static ArrayList<Forum_Reply> getCurrentAllReply(int tid) {
		ArrayList<Forum_Reply> CurrentReply = new ArrayList<Forum_Reply>();
		
		for (Forum_Reply fr : allReply) {
			if (fr.getThreadId() == tid) {
				CurrentReply.add(fr);
			}
		}
		
		return CurrentReply;
	}
	
	public static ArrayList<Forum_Reply> loadAllReply() {
             Connection con = DatabaseConnection.getConnection();
		try  {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_reply");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				allReply.add(new Forum_Reply(rs.getInt("rid"), rs.getInt("tid"), rs.getInt("cid"), rs.getString("cname"), rs.getString("time"), rs.getString("news")));
			}

			rs.close();
			ps.close();
			return allReply;
		} catch (SQLException ex) {
			FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
		}

		return null;
	}
	
	public static boolean addReply(int tid, int cid, String cname, String news) {
            Connection con = DatabaseConnection.getConnection();
		try {

			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO forum_reply(tid, cid, cname, news) VALUES (?,?,?,?)");

			PreparedStatement ps = con.prepareStatement(query.toString());

			ps.setInt(1, tid);
			ps.setInt(2, cid);
			ps.setString(3, cname);
			ps.setString(4, news);

			ps.executeUpdate();
			ps.close();
			
			allReply.add(getReplyByNameToSql(tid, news));
                        Forum_Thread.updateThreadReply(tid);
			return true;
		} catch (SQLException ex) {
			FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
		}
		return false;
	}
	
	public static Forum_Reply getReplyById(int rid) {
		for (Forum_Reply fr : allReply) {
			if (fr.getReplyId() == rid) {
				return fr;
			}
		}

		return null;
	}
	
	public static Forum_Reply getReplyByNameToSql(int tid, String news) {
            Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_reply WHERE tid = ? AND news = ?");
			ps.setInt(1, tid);
			ps.setString(2, news);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Forum_Reply(rs.getInt("rid"), rs.getInt("tid"), rs.getInt("cid"), rs.getString("cname"), rs.getString("time"), rs.getString("news"));
			}
		} catch (SQLException ex) {
			FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
		}

		return null;
	}
	
	public static boolean deleteReply(int tid, int rid, boolean isAll) {
            Connection con = DatabaseConnection.getConnection();
		try {

			boolean isExist = false;
			if (isAll) {
				 if (getCurrentAllReply(tid) != null) {
					allReply.removeAll(getCurrentAllReply(tid));
					isExist = true;
				}
			} else {
				if (getReplyById(rid) != null) {
					allReply.remove(getReplyById(rid));
					isExist = true;
				}
			}

			if (!isExist) {
				return isExist;
			}
			
			StringBuilder query = new StringBuilder();
			
			if (isAll) {
				query.append("DELETE FROM forum_reply WHERE tid = ?");
			} else {
				query.append("DELETE FROM forum_reply WHERE tid = ? AND rid = ?");
			}
			
			PreparedStatement ps = con.prepareStatement(query.toString());

			
			ps.setInt(1, tid);
			
			if (!isAll) {
				ps.setInt(2, rid);
			}

			ps.executeUpdate();
			ps.close();
			return true;
		} catch (SQLException ex) {
			FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
		}

		return false;
	}

}
