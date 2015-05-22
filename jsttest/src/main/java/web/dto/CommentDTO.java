package web.dto;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 
 * @author CR Team
 *
 */
public class CommentDTO  extends SubCommentDTO{

	private String title;
	private List<SubCommentDTO> subCommentList;
	
	public CommentDTO(){
		subCommentList = Lists.newArrayList();
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public List<SubCommentDTO> getSubCommentDTO(){
		return subCommentList;
	}
	
	public void addSubCommentDTO(SubCommentDTO dto){
		this.subCommentList.add(dto);
	}
}
