package server.custom.forum;


import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tools.FileoutputUtil;

public class Forum_Thread {
	
	private int ThreadId;

	private int sectionId;
	private String threadName;
	private int characterId;
	private String characterName;
	private String releaseTime;
        private Long lastReply;
	private int up;
	private int down;
	private static ArrayList<Forum_Thread> allThread = new ArrayList<Forum_Thread>();

	public Forum_Thread() {

	}
	
	public Forum_Thread(int threadId, int sectionId, String threadName,	int characterId, String characterName, String releaseTime, int up, int down,long lastReply) {
		super();
		ThreadId = threadId;
		this.sectionId = sectionId;
		this.threadName = threadName;
		this.characterId = characterId;
		this.characterName = characterName;
		this.releaseTime = releaseTime;
		this.up = up;
		this.down = down;
                this.lastReply=lastReply;
	}


	public int getThreadId() {
		return ThreadId;
	}


	public void setThreadId(int threadId) {
		ThreadId = threadId;
	}


	public int getSectionId() {
		return sectionId;
	}


	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}


	public String getThreadName() {
		return threadName;
	}


	public void setThreadName(String threadName) {
		this.threadName = threadName;
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


	public int getUp() {
		return up;
	}


	public void setUp(int up) {
		this.up = up;
	}


	public int getDown() {
		return down;
	}


	public void setDown(int down) {
		this.down = down;
	}


	public static ArrayList<Forum_Thread> getAllThread() {
		return allThread;
	}

	public static void setAllThread(ArrayList<Forum_Thread> allThread) {
		Forum_Thread.allThread = allThread;
	}

	public static ArrayList<Forum_Thread> getCurrentAllThread(int sid) {
		ArrayList<Forum_Thread> CurrentThread = new ArrayList<Forum_Thread>();
		for (Forum_Thread ft : allThread) {
			if (ft.getSectionId() == sid) {
				CurrentThread.add(ft);
			}
		}
		sortDesc(CurrentThread);
		return CurrentThread;
	}

    public Long getLastReply() {
        return lastReply;
    }

    public void setLastReply(Long lastReply) {
        this.lastReply = lastReply;
    }
	public static void sortDesc(List<Forum_Thread> CurrentThread){
            Collections.sort(CurrentThread, new Comparator<Forum_Thread>(){
                public int compare(Forum_Thread o1, Forum_Thread o2) {
                    long value1=0;
                    long value2=0;
                    try{
                         value1=o1.getLastReply();
                         value2=o2.getLastReply();
                    }catch (Exception e){
                        value1=0;
                        value2=0;
                    }
                    return (int) (o2.getLastReply()-o1.getLastReply());
                }
            });
        }
	public static ArrayList<Forum_Thread> loadAllThread() {
            Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_thread  order by lastreply desc");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				allThread.add(new Forum_Thread(rs.getInt("tid"), rs.getInt("sid"), rs.getString("tname"), rs.getInt("cid"), rs.getString("cname"), rs.getString("time"), rs.getInt("up"), rs.getInt("down"),rs.getLong("lastReply")));
			}

			rs.close();
			ps.close();
			
			Forum_Reply.loadAllReply();
			
			return allThread;
		} catch (SQLException ex) {
			FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
		}

		return null;
	}
	
	public static Forum_Thread getThreadById(int sid, int tid) {
		for (Forum_Thread ft : allThread) {
			if (ft.getThreadId() == tid) {
				return ft;
			}
		}

		return null;
	}
	
	public static Forum_Thread getThreadByName(int sid, String name) {
		ArrayList<Forum_Thread> allThread = getCurrentAllThread(sid);
		for (Forum_Thread ft : allThread) {
			if (ft.getThreadName().equals(name)) {
				return ft;
			}
		}

		return null;
	}
	
	public static Forum_Thread getThreadByNameToSql(int sid, String name) {
            Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_thread WHERE sid = ? AND tname = ? order by lastReply desc");
			ps.setInt(1, sid);
			ps.setString(2, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Forum_Thread(rs.getInt("tid"), rs.getInt("sid"), rs.getString("tname"), rs.getInt("cid"), rs.getString("cname"), rs.getString("time"), rs.getInt("up"), rs.getInt("down"),rs.getLong("lastReply"));
			}
		} catch (SQLException ex) {
			FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
		}

		return null;
	}
	
	public static boolean addThread(int sid, String tname, int cid, String cname) {
            Connection con = DatabaseConnection.getConnection();
		try {

			if (getThreadByName(sid, tname) != null) {
				return false;
			}

			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO forum_thread(sid, tname, cid, cname, lastreply) VALUES (?,?,?,?,?)");

			PreparedStatement ps = con.prepareStatement(query.toString());
			
			ps.setInt(1, sid);
			ps.setString(2, tname);
			ps.setInt(3, cid);
			ps.setString(4, cname);
                        ps.setLong(5, System.currentTimeMillis());
			ps.executeUpdate();
			ps.close();
			
			allThread.add(getThreadByNameToSql(sid, tname));

			return true;
		} catch (SQLException ex) {
			FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
		}
		return false;
	}
	public static int updateThreadReply(int tid) {
            int ret=-1;
            Connection con = DatabaseConnection.getConnection();
		try {
                        Forum_Thread thread=getThreadById(0, tid);
                        long now=System.currentTimeMillis();
			if (thread == null) {
				return -1;
			}

			StringBuilder query = new StringBuilder();
			query.append("UPDATE forum_thread SET lastreply = ? WHERE tid = ?");
			PreparedStatement ps = con.prepareStatement(query.toString());
                        ps.setLong(1, now);
                        ps.setInt(2, tid);
			ret=ps.executeUpdate();
			ps.close();
			if(ret>0){
                            thread.setLastReply(now);
                        }else{
                            System.out.println("更新回复时间失败");
                        }
			return ret;
		} catch (SQLException ex) {
			FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
		}
		return ret;
	}
	public static boolean deleteThread(int sid, int tid, boolean isAll) {
            Connection con = DatabaseConnection.getConnection();
		try {

			boolean isExist = false;
			if (isAll) {
				if (getCurrentAllThread(sid) != null) {
					for (Forum_Thread ft : allThread) {
						if (ft.getSectionId() == sid) {
							Forum_Reply.deleteReply(ft.getThreadId(), 0, true);
						}
					}
					allThread.removeAll(getCurrentAllThread(sid));
					isExist = true;
				}
			} else {
				if (getThreadById(sid, tid) != null) {
					allThread.remove(getThreadById(sid, tid));
					Forum_Reply.deleteReply(tid, 0, true);
					isExist = true;
				}
			}

			if (!isExist) {
				return isExist;
			}

			StringBuilder query = new StringBuilder();
			
			if (isAll) {
				query.append("DELETE FROM forum_thread WHERE sid = ?");
			} else {
				query.append("DELETE FROM forum_thread WHERE sid = ? AND tid = ?");
			}

			PreparedStatement ps = con.prepareStatement(query.toString());

			ps.setInt(1, sid);
			if (!isAll) {
				ps.setInt(2, tid);
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
