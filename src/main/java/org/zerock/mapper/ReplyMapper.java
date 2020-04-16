package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {

	@Insert("insert into tbl_reply (bno, reply, replyer) values(#{bno}, #{reply}, #{replyer})")
	public int insert(ReplyVO vo);
	
	@Select("select * from tbl_reply where bno= #{bno} order by rno asc")
	public List<ReplyVO> list(@Param("bno") Integer bno);
	
	@Select("select * from tbl_reply where rno = #{rno}")
	public ReplyVO select(@Param("rno") Integer rno);
	
	@Delete("delete from tbl_reply where rno = #{rno}")
	public int delete(@Param("rno") Integer rno);
	
	@Update("update tbl_reply set reply = #{vo.reply} where rno = #{vo.rno}")
	public int update(@Param("vo") ReplyVO vo);
	
	//트랜잭션 예재
	@Insert("insert into tbl_s1 (col1) values(#{str})")
	public void insert1(@Param("str") String str);
	
	@Insert("insert into tbl_s2 (col2) values(#{str})")	
	public void insert2(@Param("str") String str);
}
