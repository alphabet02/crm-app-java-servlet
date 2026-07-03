package crm_app12.services;

import java.util.List;

import crm_app12.entity.JobEntity;
import crm_app12.entity.TaskEntity;
import crm_app12repository.JobRepository;

public class JobServices {
	private JobRepository jobRepository = new JobRepository();
	public List<JobEntity> findAll(){
		return jobRepository.findAll();
	}
	public List<TaskEntity> findTaskByJobId(int jobId){
		return jobRepository.findTaskByJobId(jobId);
	}
	
	public int insertJob(JobEntity jobEntity) {
		return jobRepository.insertJob(jobEntity);
	}
}
