package course.rating.entities;

/**
 * 
 * @author TODO...
 *
 */
public class LectureEntity {

	private int id;
	private String name;
	private LectureDescriptionEntity description;
	private LectureStatisticsEntity statistics;
	
	public LectureEntity(){
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public LectureDescriptionEntity getDescription(){
		return this.description;
	}
	
	public void setDescription(LectureDescriptionEntity description){
		this.description = description;
	}
	
	public LectureStatisticsEntity getStatistics(){
		return statistics;
	}
	
	public void setStatistics(LectureStatisticsEntity statistics){
		this.statistics = statistics;
	}
}
